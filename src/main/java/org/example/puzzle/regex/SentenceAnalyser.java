package org.example.puzzle.regex;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class SentenceAnalyser {
    /**
     *
     * @param input: Text input for counting number of words
     * @return: Words in an array
     */
    //https://www.java67.com/2016/09/3-ways-to-count-words-in-java-string.html#:~:text=You%20can%20count%20words%20in,words%20in%20a%20given%20String.
    public String[] getWordAsArray(String input) {
        //Create Empty array
        String[] strings = {};
        if (input == null || input.isEmpty()) { return strings; }
        strings = input.split("\\s+");
        return strings;
        // System.out.println("Count: " + strings.length);
    }

    /**
     * Take a sentence as input and return a list of the words in the
     * sentence, sorted alphabetically in lowercase and without punctuation
     * (,.!?). If there are multiple occurrences of the same word, include it
     * multiple times.
     */
    public List<String> level2WordSorting(String sentence) {
        //Remove punctuation marks from the sentence
        String sentenceWithoutPunctuation = sentence.replaceAll("[^a-zA-Z ]", "");

        //Get an array with list of all words in the sentence
        String[] wordAsArray = getWordAsArray(sentenceWithoutPunctuation);

        //Convert an array to a List (resizable list)
        //https://www.geeksforgeeks.org/conversion-of-array-to-arraylist-in-java/
        List<String> wordList = new ArrayList<>(Arrays.asList(wordAsArray));
        List<String> collect = wordList.stream().map(String::toLowerCase).sorted((Comparator.naturalOrder())).collect(Collectors.toList());
        return collect;
    }

    /**
     * Take a sentence as input and sort the words in it by the number of
     * times they appear in the sentence. In the case of a tie, the words
     * should be sorted alphabetically.
     * V. IMP
     * https://stackoverflow.com/questions/30425836/java-8-stream-map-to-list-of-keys-sorted-by-values
     * https://stackoverflow.com/questions/53512537/java-stream-sort-map-by-key-and-value
     */
    public List<String> level3SortByOccurrences(String sentence) {
        //Remove punctuation marks from the sentence
        String sentenceWithoutPunctuation = sentence.replaceAll("[^a-zA-Z ]", "");

        //Get an array with list of all words in the sentence
        String[] wordAsArray = getWordAsArray(sentenceWithoutPunctuation);

        // Convert String array to List
        List<String> wordList = new ArrayList<>(Arrays.asList(wordAsArray));

        // Create a map from the list with key as the word and value as the count of the word in the list
        final Map<String, Long> countByWord = wordList.stream()
                .map(String::toLowerCase)
                .collect(groupingBy(Function.identity(), counting()));
        System.out.println(countByWord);

        // Sort the entries in the map in reverse order of 'value' and if there is a tie, then the sort the entries based on the key in natural order and finally collect the keys in a list
        List<String> collect = countByWord.entrySet().stream()
                .sorted(Map.Entry.<String,Long>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry::getKey))
                //.sorted(Map.Entry.comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toUnmodifiableList());
        System.out.println(collect);
        return new ArrayList<String>();
    }

    //To do
    public int getCharCount(String text) {
        return -1;
    }

    public static void main(String[] args) {
        String input = "The quick brown fox jumps over the lazy dog and the lazy pig.";
        //String input = "The bear quick bear brown dog jumps over the lazy dog";

        SentenceAnalyser sentenceAnalyser = new SentenceAnalyser();

        //String[] wordAsArray = sentenceAnalyser.getWordAsArray("");
        //String[] wordAsArray = sentenceAnalyser.getWordAsArray(input);
        //System.out.println("wordCount = " + wordAsArray.length);

//        List<String> strings = sentenceAnalyser.level2WordSorting(input);
//        System.out.println(strings);

        sentenceAnalyser.level3SortByOccurrences(input);
    }
}
