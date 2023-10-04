package org.example.puzzle.regex;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegexDemo {

    public boolean runRegex(String regexPattern, String text) {
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }

    public int getMatchCount(String regexPattern, String text) {
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(text);
        int matches = 0;
        while (matcher.find()) {
            matches++;
        }
        return matches;
    }

    public void runRegexDanielRoss(String regexPattern, String text) {
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("Matcher found "+ matcher.group() + " at index " + matcher.start());
        }
    }

    public static void main(String[] args) {
        RegexDemo runner = new RegexDemo();
        //boolean result = runner.runRegex(".{1,10}", "aaabbbccc");
        //boolean result = runner.runRegex(".{1,5}", "aaabbbcccde");
        //int matchCount = runner.getMatchCount(".{5}", "aaa334");
        //boolean matchCount = runner.runRegex(".{5}", "aaa334");
        //System.out.println("matchCount = " + matchCount);
        //runner.runRegexDanielRoss("[mt]op", "The top of the mop is called a handle.");
        //runner.runRegexDanielRoss(".{0,5}", "abcdef");
        runner.runRegexDanielRoss("^.{1,5}$", "abcdef");
        //runner.runRegexDanielRoss(".{1,5}", "abcdef");
    }

//    public static void main(String[] args) {
//        String text = "Java Lessons";
//        String regex = ".";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(text);
//        boolean result = matcher.find();
//        System.out.println(result);
//    }
}
