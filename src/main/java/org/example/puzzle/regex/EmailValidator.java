package org.example.puzzle.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    public boolean hasMaxLength(String text, int allowedLength) {
        StringBuilder regexExpr = new StringBuilder();
        regexExpr.append("^.{1,").append(allowedLength).append("}$");
//        String regexPattern = "^.{1,5}$";
        Pattern pattern = Pattern.compile(regexExpr.toString());
        Matcher matcher = pattern.matcher(text);
        if(matcher.find()) {
            return true;
        }
        return false;
    }

    public boolean applyRegex(String regexExpr, String text) {
        Pattern pattern = Pattern.compile(regexExpr.toString());
        Matcher matcher = pattern.matcher(text);
        if(matcher.find()) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String text = "P.ran";

        EmailValidator emailValidator = new EmailValidator();
        // Max length check for local part
        String maxLengthRegex = "^.{1,5}$";
        boolean maxLengthResult = emailValidator.applyRegex(maxLengthRegex, text);
        System.out.println("maxLengthResult = " + maxLengthResult);

        // Valid char check for local part
        String validCharRegex = "^[a-zA-Z0-9-_.]+$";
        boolean hasValidCharsResult = emailValidator.applyRegex(validCharRegex, text);
        System.out.println("hasValidCharsResult = " + hasValidCharsResult);

        //Local part cannot start or end with a period
        //https://stackoverflow.com/questions/2116328/regexp-matching-string-not-starting-with-my
        //https://stackoverflow.com/questions/899422/regular-expression-for-a-string-that-does-not-start-with-a-sequence
        // To do
        String cannotStartAndEndWithRegex = "^(?!.)w+$";
        boolean cannotStartAndEndWithResult = emailValidator.applyRegex(cannotStartAndEndWithRegex, text);
        System.out.println("cannotStartAndEndWithResult = " + cannotStartAndEndWithResult);
    }
}
