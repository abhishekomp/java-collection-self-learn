package org.example.puzzle.regex;

import org.example.puzzle.utils.StringUtils;

import java.util.Map;

public class SherlockAndValidString {
    public boolean isStringValidPerSherlock(String input) {
        StringUtils stringUtils = new StringUtils();
        Map<String, Long> stringLongMap = stringUtils.stringToCount(input);
        return false;
    }
}
