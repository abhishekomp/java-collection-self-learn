package org.example.dragonball.logapiextract;

import java.io.*;
import java.util.regex.*;

/**
 * LogApiExtractor class (prints extracted TenantId and Email)
 *
 * Usage:
 *   java org.example.dragonball.logapiextract.LogApiExtractor <input.txt> [output.txt] [console|file]
 *
 * If output.txt is not provided, writes to "http-api-output.log" in current directory.
 *
 * Extracts:
 *   - HTTP method and URL for each call
 *   - Request/response body, response status for each call
 *   - TenantId (segment after "/tenant/" in request URL) - printed to console
 *   - Email (from contactPoints.email in request JSON)   - printed to console
 *
 * @author : kjss920
 * @since : 2025-10-14, Tuesday
 **/
public class LogApiExtractorV3 {
    public static void main(String[] args) throws Exception {
        String inputFile;
        String outputFile;
        String outputMode;

        // Default files and mode if none are given
        if (args.length == 0) {
            inputFile = "http-logs-sample.log";
            outputFile = "http-api-output.log";
            outputMode = "file";
            System.err.println("No input file provided. Using default: " + inputFile);
        } else {
            inputFile = args[0];
            outputFile = (args.length >= 2) ? args[1] : "http-api-output.log";
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

        // Patterns for parsing
        Pattern apiInvoke = Pattern.compile("- >> \\[-\\] (POST|GET|PUT|DELETE|PATCH) ([^ ]+)");
        Pattern reqBody   = Pattern.compile("- >> Body: (.*)");
        Pattern respLine  = Pattern.compile("- << \\[-\\] (POST|GET|PUT|DELETE|PATCH) ([^ ]+) ([0-9]{3})");
        Pattern respBody  = Pattern.compile("- << Body: (.*)");

        // Pattern to extract TenantId from /tenant/...
        Pattern tenantIdPat = Pattern.compile("/tenant/([^/\\s]+)");
        // Pattern to extract email from contactPoints: [...{"value":"<email>"...}]
        Pattern emailPat = Pattern.compile("\"contactPoints\"\\s*:\\s*\\[.*?\\{[^\\}]*?\"system\"\\s*:\\s*\"EMAIL\"[^\\}]*?\"value\"\\s*:\\s*\"([^\"]+)\"", Pattern.DOTALL);

        while ((line = br.readLine()) != null) {
            Matcher mInvoke = apiInvoke.matcher(line);
            Matcher mReq    = reqBody.matcher(line);
            Matcher mResp   = respLine.matcher(line);
            Matcher mRespB  = respBody.matcher(line);

            // Detect new API call (print previous block if any)
            if (mInvoke.find()) {
                if (inBlock) {
                    printBlock(method, url, requestBody, responseMethod, responseUrl, responseCode, responseBody, out);
                    // After printing block, print TenantId and email if present
                    extractAndPrintTenantIdAndEmail(url, requestBody, tenantIdPat, emailPat);
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
        // Print last API block and extract tenant/email
        if (inBlock) {
            printBlock(method, url, requestBody, responseMethod, responseUrl, responseCode, responseBody, out);
            extractAndPrintTenantIdAndEmail(url, requestBody, tenantIdPat, emailPat);
        }
        br.close();
        out.close();

        if (!"console".equals(outputMode)) {
            System.err.println("Output written to: " + new File(outputFile).getAbsolutePath());
        }
    }

    /**
     * Extracts and prints TenantId from the URL (segment after '/tenant/') and
     * the first EMAIL value from contactPoints in the request body.
     */
    private static void extractAndPrintTenantIdAndEmail(String url, String requestBody, Pattern tenantIdPat, Pattern emailPat) {
        // Extract and print "TenantId"
        Matcher mTenant = tenantIdPat.matcher(url);
        if (mTenant.find()) {
            System.err.println("TenantId: " + mTenant.group(1));
        }

        // Extract and print first "EMAIL" value from contactPoints (in request body, if present)
        if (requestBody != null && !requestBody.isEmpty()) {
            Matcher mEmail = emailPat.matcher(requestBody);
            if (mEmail.find()) {
                System.err.println("Email: " + mEmail.group(1));
            }
        }
    }

    /**
     * Prints all main extracted info about an API call block to output file/console.
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