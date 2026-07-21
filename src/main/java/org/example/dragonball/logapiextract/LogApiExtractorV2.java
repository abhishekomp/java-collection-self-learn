package org.example.dragonball.logapiextract;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * LogApiExtractor class
 *
 * @author : kjss920
 * @since : 2025-10-14, Tuesday
 **/
public class LogApiExtractorV2 {
    public static void main(String[] args) throws Exception {
        String inputFile;
        String outputFile;
        String outputMode;

        if (args.length == 0) {
            inputFile = "http-logs-sample.log";
            outputFile = "http-api-output.log";
            outputMode = "file";
            System.err.println("No input file provided. Using default: " + inputFile);
        } else {
            inputFile = args[0];
            outputFile = (args.length >= 2) ? args[1] : "http-api-output_new_LST.log";
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

        Pattern apiInvoke = Pattern.compile("- >> \\[-\\] (POST|GET|PUT|DELETE|PATCH) ([^ ]+)");
        Pattern reqBody   = Pattern.compile("- >> Body: (.*)");
        Pattern respLine  = Pattern.compile("- << \\[-\\] (POST|GET|PUT|DELETE|PATCH) ([^ ]+) ([0-9]{3})");
        Pattern respBody  = Pattern.compile("- << Body: (.*)");

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
        if (inBlock) {
            printBlock(method, url, requestBody, responseMethod, responseUrl, responseCode, responseBody, out);
        }
        br.close();
        out.close();

        if (!"console".equals(outputMode)) {
            System.err.println("Output written to: " + outputFile);
        }
    }

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
