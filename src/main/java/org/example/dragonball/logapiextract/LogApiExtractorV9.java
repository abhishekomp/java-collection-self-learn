// src/main/java/org/example/dragonball/logapiextract/LogApiExtractor.java
package org.example.dragonball.logapiextract;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extracts API call details from log files and summarizes unique endpoints and Bearer tokens.
 */
public class LogApiExtractorV9 {

    public static void main(String[] args) throws Exception {
        System.out.println("LogApiExtractorV2 - extracts API information from log files.");
        System.out.println("Usage: java LogApiExtractorV2 [inputFile] [outputFile] [outputMode]");
        System.out.println("  inputFile  - log file to process (default: http-logs-sample.log)");
        System.out.println("  outputFile - output filename (default: adds date/time to 'http-api-output.log')");
        System.out.println("  outputMode - 'file' (default) or 'console'");
        System.out.println();

        String inputFile;
        String outputFile;
        String outputMode;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);

        if (args.length == 0) {
            inputFile = "http-logs-sample.log";
            outputFile = inputFile.replace(".log", "_api_output_" + timestamp + ".log");
            outputMode = "file";
            System.err.println("No input file provided. Using default: " + inputFile);
        } else {
            inputFile = args[0];
            outputFile = (args.length >= 2 && args[1] != null && !args[1].isEmpty())
                    ? args[1]
                    : inputFile.replace(".log", "_api_output_" + timestamp + ".log");
            outputMode = (args.length >= 3) ? args[2].toLowerCase() : "file";
        }

        System.out.println("Processing file: " + new File(inputFile).getAbsolutePath());

        PrintWriter out;
        if ("console".equals(outputMode)) {
            out = new PrintWriter(System.out, true);
        } else {
            out = new PrintWriter(new FileWriter(outputFile));
        }

        ExtractionResult result = extractApiCalls(inputFile, out);

        printSummary(result, out);

        out.close();

        if (!"console".equals(outputMode)) {
            System.err.println("Output written to: " + outputFile);
        }
    }

    /**
     * Holds the extraction results for summary printing.
     */
    static class ExtractionResult {
        int apiCallCount;
        Set<String> uniqueMethodEndpoints;
        Set<String> uniqueBearerTokens;

        ExtractionResult(int apiCallCount, Set<String> uniqueMethodEndpoints, Set<String> uniqueBearerTokens) {
            this.apiCallCount = apiCallCount;
            this.uniqueMethodEndpoints = uniqueMethodEndpoints;
            this.uniqueBearerTokens = uniqueBearerTokens;
        }
    }

    /**
     * Extracts API call blocks from the log file, tracks unique endpoints and Bearer tokens.
     * Fixes line number tracking for each extracted field.
     */
    static ExtractionResult extractApiCalls(String inputFile, PrintWriter out) throws IOException {
        int apiCallCount = 0;
        Set<String> uniqueMethodEndpoints = new LinkedHashSet<>();
        Set<String> uniqueBearerTokens = new LinkedHashSet<>();

        Pattern apiInvoke = Pattern.compile("- >> \\[-\\] (POST|GET|PUT|DELETE|PATCH) ([^ ]+)");
        Pattern reqHeaders = Pattern.compile("- >> \\[-\\] Headers: \\[(.*)\\]");
        Pattern reqBody   = Pattern.compile("- >> Body: (.*)");
        Pattern respLine  = Pattern.compile("- << \\[-\\] (POST|GET|PUT|DELETE|PATCH) ([^ ]+) ([0-9]{3})");
        Pattern respBody  = Pattern.compile("- << Body: (.*)");
        Pattern authHeader = Pattern.compile("Authorization:\"([^\"]+)\"");

        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String line;
        String method = "", url = "", requestBody = "";
        String responseMethod = "", responseUrl = "", responseCode = "", responseBody = "";
        String authorizationHeader = "None";
        boolean inBlock = false;

        int reqUrlLine = -1, reqBodyLine = -1, respUrlLine = -1, respBodyLine = -1;
        int authHeaderLine = -1;
        int currentLineNum = 0;

        while ((line = br.readLine()) != null) {
            currentLineNum++;
            Matcher mInvoke = apiInvoke.matcher(line);
            Matcher mReqBody = reqBody.matcher(line);
            Matcher mResp = respLine.matcher(line);
            Matcher mRespB = respBody.matcher(line);

            if (mInvoke.find()) {
                if (inBlock) {
                    printBlock(method, url, requestBody, responseMethod, responseUrl, responseCode, responseBody,
                            reqUrlLine, reqBodyLine, respUrlLine, respBodyLine,
                            authorizationHeader, authHeaderLine, out);
                    apiCallCount++;
                    String endpoint = extractEndpoint(url);
                    if (!endpoint.isEmpty()) uniqueMethodEndpoints.add(method + " " + endpoint);
                    if (authorizationHeader.startsWith("Bearer ")) uniqueBearerTokens.add(authorizationHeader);
                }
                inBlock = true;
                method = mInvoke.group(1);
                url = mInvoke.group(2);
                reqUrlLine = currentLineNum;
                requestBody = "";
                reqBodyLine = -1;
                responseMethod = ""; responseUrl = ""; responseCode = ""; responseBody = "";
                respUrlLine = -1; respBodyLine = -1;
                // Peek ahead for headers, but do NOT increment currentLineNum
                br.mark(1000);
                String nextLine = br.readLine();
                int peekLineNum = currentLineNum + 1;
                authorizationHeader = "None";
                authHeaderLine = -1;
                if (nextLine != null) {
                    Matcher mHeaders = reqHeaders.matcher(nextLine);
                    if (mHeaders.find()) {
                        String headers = mHeaders.group(1);
                        Matcher mAuth = authHeader.matcher(headers);
                        if (mAuth.find()) {
                            authorizationHeader = mAuth.group(1);
                            authHeaderLine = peekLineNum;
                        }
                    }
                }
                if (nextLine != null) br.reset();
                continue;
            }
            if (mReqBody.find()) {
                if (inBlock) {
                    requestBody = mReqBody.group(1);
                    reqBodyLine = currentLineNum;
                }
            }
            if (mResp.find()) {
                if (inBlock) {
                    responseMethod = mResp.group(1);
                    responseUrl = mResp.group(2);
                    responseCode = mResp.group(3);
                    respUrlLine = currentLineNum;
                }
            }
            if (mRespB.find()) {
                if (inBlock) {
                    responseBody = mRespB.group(1);
                    respBodyLine = currentLineNum;
                }
            }
            // Also check for Authorization Header lines outside headers block
            if (line.startsWith("Authorization Header")) {
                int idx = line.indexOf(":");
                if (idx >= 0) {
                    String value = line.substring(idx + 1).trim();
                    if (value.startsWith("Bearer ")) uniqueBearerTokens.add(value);
                }
            }
        }
        if (inBlock) {
            printBlock(method, url, requestBody, responseMethod, responseUrl, responseCode, responseBody,
                    reqUrlLine, reqBodyLine, respUrlLine, respBodyLine,
                    authorizationHeader, authHeaderLine, out);
            apiCallCount++;
            String endpoint = extractEndpoint(url);
            if (!endpoint.isEmpty()) uniqueMethodEndpoints.add(method + " " + endpoint);
            if (authorizationHeader.startsWith("Bearer ")) uniqueBearerTokens.add(authorizationHeader);
        }
        br.close();

        return new ExtractionResult(apiCallCount, uniqueMethodEndpoints, uniqueBearerTokens);
    }

    /**
     * Prints a formatted block of extracted API information.
     */
    static void printBlock(String method, String url, String requestBody,
                           String responseMethod, String responseUrl, String responseCode, String responseBody,
                           int reqUrlLine, int reqBodyLine, int respUrlLine, int respBodyLine,
                           String authorizationHeader, int authHeaderLine,
                           PrintWriter out) {
        out.println("METHOD: " + method);
        out.println("REQUEST URL (line: " + reqUrlLine + "): " + url);
        out.println("Authorization Header" + (authHeaderLine > 0 ? " (line: " + authHeaderLine + ")" : "") + ": " + authorizationHeader);
        if (requestBody != null && !requestBody.isEmpty())
            out.println("REQUEST BODY (line: " + reqBodyLine + "): " + requestBody);
        if (responseMethod != null && !responseMethod.isEmpty()) {
            out.println("RESPONSE METHOD: " + responseMethod);
            out.println("RESPONSE URL (line: " + respUrlLine + "): " + responseUrl);
            out.println("STATUS CODE: " + responseCode);
        }
        if (responseBody != null && !responseBody.isEmpty())
            out.println("RESPONSE BODY (line: " + respBodyLine + "): " + responseBody);
        out.println("----------------------------------------");
    }

    /**
     * Extracts the endpoint from a full URL (everything after the hostname).
     */
    static String extractEndpoint(String url) {
        if (url == null || url.isEmpty()) return "";
        int idx = url.indexOf("://");
        if (idx >= 0) url = url.substring(idx + 3);
        int slashIdx = url.indexOf("/");
        if (slashIdx >= 0) {
            return url.substring(slashIdx);
        }
        return "";
    }

    /**
     * Prints the summary of API calls, unique endpoints, and unique Bearer tokens.
     */
    static void printSummary(ExtractionResult result, PrintWriter out) {
        out.println("========================================");
        out.println("Total API calls extracted: " + result.apiCallCount);
        out.println("Unique endpoints extracted:");
        for (String methodEndpoint : result.uniqueMethodEndpoints) {
            out.println("  " + methodEndpoint);
        }
        out.println();
        out.println("Unique Authorization Bearer Tokens:");
        for (String bearer : result.uniqueBearerTokens) {
            out.println("Authorization Header: " + bearer);
            out.println("****************************************************************");
        }
    }
}