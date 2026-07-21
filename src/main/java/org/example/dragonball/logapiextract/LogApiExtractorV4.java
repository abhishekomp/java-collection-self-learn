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
public class LogApiExtractorV4 {
    public static void main(String[] args) throws Exception {
        // Print usage info at the start
        System.out.println("LogApiExtractorV2 - extracts API information from log files.");
        System.out.println("Usage: java LogApiExtractorV2 [inputFile] [outputFile] [outputMode]");
        System.out.println("  inputFile  - log file to process (default: http-logs-sample.log)");
        System.out.println("  outputFile - output filename (default: adds date/time to 'http-api-output.log')");
        System.out.println("  outputMode - 'file' (default) or 'console'");
        System.out.println();

        // Input/output configuration
        String inputFile;
        String outputFile;
        String outputMode;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);

        if (args.length == 0) {
            // No arguments: use default input and output with dynamic file name
            inputFile = "http-logs-sample.log";
            outputFile = "http-api-output_" + timestamp + ".log";
            outputMode = "file";
            // Error/status messages appear on stderr for better visibility
            System.err.println("No input file provided. Using default: " + inputFile);
        } else {
            inputFile = args[0];
            // If outputFile not given or blank, use dynamic name
            if (args.length >= 2 && args[1] != null && !args[1].isEmpty()) {
                outputFile = args[1];
            } else {
                outputFile = "http-api-output_" + timestamp + ".log";
            }
            outputMode = (args.length >= 3) ? args[2].toLowerCase() : "file";
        }

        // Print file being processed (stdout: normal information)
        System.out.println("Processing file: " + new File(inputFile).getAbsolutePath());

        // Set up output writer depending on mode
        PrintWriter out;
        if ("console".equals(outputMode)) {
            out = new PrintWriter(System.out, true);
        } else {
            out = new PrintWriter(new FileWriter(outputFile));
        }

        // Prepare to read lines from input file
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String line;
        // Variables for request/response info
        String method = "", url = "", requestBody = "";
        String responseMethod = "", responseUrl = "", responseCode = "", responseBody = "";
        boolean inBlock = false;

        // Compile patterns for log parsing
        Pattern apiInvoke = Pattern.compile("- >> \\[-\\] (POST|GET|PUT|DELETE|PATCH) ([^ ]+)");
        Pattern reqBody   = Pattern.compile("- >> Body: (.*)");
        Pattern respLine  = Pattern.compile("- << \\[-\\] (POST|GET|PUT|DELETE|PATCH) ([^ ]+) ([0-9]{3})");
        Pattern respBody  = Pattern.compile("- << Body: (.*)");

        // Main log file reading loop
        while ((line = br.readLine()) != null) {
            Matcher mInvoke = apiInvoke.matcher(line);
            Matcher mReq    = reqBody.matcher(line);
            Matcher mResp   = respLine.matcher(line);
            Matcher mRespB  = respBody.matcher(line);

            if (mInvoke.find()) {
                if (inBlock) {
                    printBlock(method, url, requestBody, responseMethod, responseUrl, responseCode, responseBody, out);
                }
                inBlock = true;
                method = mInvoke.group(1);
                url = mInvoke.group(2);
                requestBody = "";
                responseMethod = ""; responseUrl = ""; responseCode = ""; responseBody = "";
                continue;
            }
            if (mReq.find()) {
                if (inBlock) requestBody = mReq.group(1);
            }
            if (mResp.find()) {
                if (inBlock) {
                    responseMethod = mResp.group(1);
                    responseUrl = mResp.group(2);
                    responseCode = mResp.group(3);
                }
            }
            if (mRespB.find()) {
                if (inBlock) responseBody = mRespB.group(1);
            }
        }
        // Print last block if needed
        if (inBlock) {
            printBlock(method, url, requestBody, responseMethod, responseUrl, responseCode, responseBody, out);
        }
        br.close();
        out.close();

        // Inform where output is written (stderr for status; keeps normal/actual output separate)
        if (!"console".equals(outputMode)) {
            System.err.println("Output written to: " + outputFile);
        }
    }

    /**
     * Prints a formatted block of API call info to the provided writer.
     * Comments indicate which lines may be omitted if values are empty.
     */
    static void printBlock(String method, String url, String requestBody,
                           String responseMethod, String responseUrl, String responseCode, String responseBody,
                           PrintWriter out) {
        out.println("METHOD: " + method);
        out.println("REQUEST URL: " + url);
        if (requestBody != null && !requestBody.isEmpty()) out.println("REQUEST BODY: " + requestBody);
        if (responseMethod != null && !responseMethod.isEmpty()) {
            out.println("RESPONSE METHOD: " + responseMethod);
            out.println("RESPONSE URL: " + responseUrl);
            out.println("STATUS CODE: " + responseCode);
        }
        if (responseBody != null && !responseBody.isEmpty()) out.println("RESPONSE BODY: " + responseBody);
        out.println("----------------------------------------");
    }
}