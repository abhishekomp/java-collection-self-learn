package org.example.dragonball.logapiextract;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * LogApiExtractor class
 *
 * @author : kjss920
 * @since : 2025-10-14, Tuesday
 **/
public class LogApiExtractorV1 {
    public static void main(String[] args) throws Exception {
        if(args.length < 1) {
            //System.err.println("Usage: java com.az.ip.dmdp.systemtest.cucumber.studypod.model.LogApiExtractor <input.txt>");
            System.err.println("Usage: java org.example.dragonball.logapiextract.LogApiExtractor <input.txt>");
            System.exit(1);
        }

        BufferedReader br = new BufferedReader(new FileReader(args[0]));
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
                    printBlock(method, url, requestBody, responseMethod, responseUrl, responseCode, responseBody);
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
            printBlock(method, url, requestBody, responseMethod, responseUrl, responseCode, responseBody);
        }
        br.close();
    }

    static void printBlock(String method, String url, String requestBody,
                           String responseMethod, String responseUrl, String responseCode, String responseBody) {
        System.out.println("METHOD: " + method);
        System.out.println("REQUEST URL: " + url);
        if (requestBody != null && !requestBody.isEmpty()) System.out.println("REQUEST BODY: " + requestBody);
        if (responseMethod != null && !responseMethod.isEmpty()) {
            System.out.println("RESPONSE METHOD: " + responseMethod);
            System.out.println("RESPONSE URL: " + responseUrl);
            System.out.println("STATUS CODE: " + responseCode);
        }
        if (responseBody != null && !responseBody.isEmpty()) System.out.println("RESPONSE BODY: " + responseBody);
        System.out.println("----------------------------------------");
    }
}
