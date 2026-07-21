package org.example.dragonball.logapiextract;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * LogApiExtractor - Extracts API request & response info from logs.
 *
 * Usage:
 *   java LogApiExtractor [inputFile] [outputFile] [outputMode]
 *
 *   - inputFile:  Path to the log file to process (default: http-logs-sample.log)
 *   - outputFile: Name of output file (default: http-api-output_<date>_<time>.log)
 *   - outputMode: "file" (default) to write to output file,
 *                 "console" to write to console.
 *
 * Example:
 *   java LogApiExtractor mylog.log results.log file
 *   java LogApiExtractor mylog.log "" console
 *
 * Notes:
 *   - outputFile is ignored if outputMode is "console"
 *   - System.err.println is used for error/status; System.out.println for ordinary output
 *
 * @author : kjss920
 * @since : 2025-10-23, Thursday
 **/
public class LogApiExtractorV5 {
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
            outputFile = "http-api-output_" + timestamp + ".log";
            outputMode = "file";
            System.err.println("No input file provided. Using default: " + inputFile);
        } else {
            inputFile = args[0];
            if (args.length >= 2 && args[1] != null && !args[1].isEmpty()) {
                outputFile = args[1];
            } else {
                outputFile = "http-api-output_" + timestamp + ".log";
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
        boolean inBlock = false;

        // Line number tracking
        int reqUrlLine = -1, reqBodyLine = -1, respUrlLine = -1, respBodyLine = -1;
        int currentLineNum = 0;

        Pattern apiInvoke = Pattern.compile("- >> \\[-\\] (POST|GET|PUT|DELETE|PATCH) ([^ ]+)");
        Pattern reqBody   = Pattern.compile("- >> Body: (.*)");
        Pattern respLine  = Pattern.compile("- << \\[-\\] (POST|GET|PUT|DELETE|PATCH) ([^ ]+) ([0-9]{3})");
        Pattern respBody  = Pattern.compile("- << Body: (.*)");

        while ((line = br.readLine()) != null) {
            currentLineNum++;
            Matcher mInvoke = apiInvoke.matcher(line);
            Matcher mReq    = reqBody.matcher(line);
            Matcher mResp   = respLine.matcher(line);
            Matcher mRespB  = respBody.matcher(line);

            if (mInvoke.find()) {
                if (inBlock) {
                    printBlock(method, url, requestBody, responseMethod, responseUrl, responseCode, responseBody,
                            reqUrlLine, reqBodyLine, respUrlLine, respBodyLine, out);
                }
                inBlock = true;
                method = mInvoke.group(1);
                url = mInvoke.group(2);
                reqUrlLine = currentLineNum;
                requestBody = "";
                reqBodyLine = -1;
                responseMethod = ""; responseUrl = ""; responseCode = ""; responseBody = "";
                respUrlLine = -1; respBodyLine = -1;
                continue;
            }
            if (mReq.find()) {
                if (inBlock) {
                    requestBody = mReq.group(1);
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
                    reqUrlLine, reqBodyLine, respUrlLine, respBodyLine, out);
        }
        br.close();
        out.close();

        if (!"console".equals(outputMode)) {
            System.err.println("Output written to: " + outputFile);
        }
    }

    // Helper method to print a block of API info. It prints line numbers for URLs and bodies in the format REQUEST URL (line: 6): <url>
    static void printBlock(String method, String url, String requestBody,
                           String responseMethod, String responseUrl, String responseCode, String responseBody,
                           int reqUrlLine, int reqBodyLine, int respUrlLine, int respBodyLine,
                           PrintWriter out) {
        out.println("METHOD: " + method);
        out.println("REQUEST URL (line: " + reqUrlLine + "): " + url);
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

    // Helper method to print a block of API info. It prints line numbers for URLs and bodies.
    /*static void printBlock(String method, String url, String requestBody,
                           String responseMethod, String responseUrl, String responseCode, String responseBody,
                           int reqUrlLine, int reqBodyLine, int respUrlLine, int respBodyLine,
                           PrintWriter out) {
        out.println("METHOD: " + method);
        out.println("REQUEST URL: " + url + " (line: " + reqUrlLine + ")");
        if (requestBody != null && !requestBody.isEmpty())
            out.println("REQUEST BODY: " + requestBody + " (line: " + reqBodyLine + ")");
        if (responseMethod != null && !responseMethod.isEmpty()) {
            out.println("RESPONSE METHOD: " + responseMethod);
            out.println("RESPONSE URL: " + responseUrl + " (line: " + respUrlLine + ")");
            out.println("STATUS CODE: " + responseCode);
        }
        if (responseBody != null && !responseBody.isEmpty())
            out.println("RESPONSE BODY: " + responseBody + " (line: " + respBodyLine + ")");
        out.println("----------------------------------------");
    }*/
}
