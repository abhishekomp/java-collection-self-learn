// LogApiExtractor.java
package org.example.dragonball.logapiextract;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogApiExtractorV8 {
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
            if (args.length >= 2 && args[1] != null && !args[1].isEmpty()) {
                outputFile = args[1];
            } else {
                outputFile = inputFile.replace(".log", "_api_output_" + timestamp + ".log");
            }
            outputMode = (args.length >= 3) ? args[2].toLowerCase() : "file";
        }

        System.out.println("Processing file: " + new File(inputFile).getAbsolutePath());

        PrintWriter out;
        if ("console".equals(outputMode)) {
            out = new PrintWriter(System.out, true);
        } else {
            out = new PrintWriter(new FileWriter(outputFile));
        }

        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String line;
        String method = "", url = "", requestBody = "";
        String responseMethod = "", responseUrl = "", responseCode = "", responseBody = "";
        String authorizationHeader = "None";
        boolean inBlock = false;

        int reqUrlLine = -1, reqBodyLine = -1, respUrlLine = -1, respBodyLine = -1;
        int authHeaderLine = -1;
        int currentLineNum = 0;

        Pattern apiInvoke = Pattern.compile("- >> \\[-\\] (POST|GET|PUT|DELETE|PATCH) ([^ ]+)");
        Pattern reqHeaders = Pattern.compile("- >> \\[-\\] Headers: \\[(.*)\\]");
        Pattern reqBody   = Pattern.compile("- >> Body: (.*)");
        Pattern respLine  = Pattern.compile("- << \\[-\\] (POST|GET|PUT|DELETE|PATCH) ([^ ]+) ([0-9]{3})");
        Pattern respBody  = Pattern.compile("- << Body: (.*)");
        Pattern authHeader = Pattern.compile("Authorization:\"([^\"]+)\"");

        int apiCallCount = 0;
        Set<String> uniqueMethodEndpoints = new LinkedHashSet<>();

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
                }
                inBlock = true;
                method = mInvoke.group(1);
                url = mInvoke.group(2);
                reqUrlLine = currentLineNum;
                requestBody = "";
                reqBodyLine = -1;
                responseMethod = ""; responseUrl = ""; responseCode = ""; responseBody = "";
                respUrlLine = -1; respBodyLine = -1;
                br.mark(1000);
                String nextLine = br.readLine();
                currentLineNum++;
                authorizationHeader = "None";
                authHeaderLine = -1;
                if (nextLine != null) {
                    Matcher mHeaders = reqHeaders.matcher(nextLine);
                    if (mHeaders.find()) {
                        String headers = mHeaders.group(1);
                        Matcher mAuth = authHeader.matcher(headers);
                        if (mAuth.find()) {
                            authorizationHeader = mAuth.group(1);
                            authHeaderLine = currentLineNum;
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
        }
        if (inBlock) {
            printBlock(method, url, requestBody, responseMethod, responseUrl, responseCode, responseBody,
                    reqUrlLine, reqBodyLine, respUrlLine, respBodyLine,
                    authorizationHeader, authHeaderLine, out);
            apiCallCount++;
            String endpoint = extractEndpoint(url);
            if (!endpoint.isEmpty()) uniqueMethodEndpoints.add(method + " " + endpoint);
        }
        br.close();

        out.println("========================================");
        out.println("Total API calls extracted: " + apiCallCount);
        out.println("Unique endpoints extracted:");
        for (String methodEndpoint : uniqueMethodEndpoints) {
            out.println("  " + methodEndpoint);
        }
        out.close();

        if (!"console".equals(outputMode)) {
            System.err.println("Output written to: " + outputFile);
        }
    }

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
}