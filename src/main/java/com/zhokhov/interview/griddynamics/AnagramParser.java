package com.zhokhov.interview.griddynamics;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author <a href='mailto:alexey@zhokhov.com'>Alexey Zhokhov</a>
 */
public class AnagramParser {

    class Item {

        private Integer position;
        private List<String> words = new ArrayList<>();

        public void sortWords() {
            Collections.sort(words, WORDS_COMPARATOR);
        }

    }

    public static AnagramParser parseFromFile(File file, String charset) throws IOException {
        AnagramParser anagramParser = new AnagramParser();

        LineIterator iterator = FileUtils.lineIterator(file, charset);

        // reading file line by line
        do {
            String line = iterator.next();

            Tokenizer stringTokenizer = new Tokenizer(line);

            while (stringTokenizer.hasMoreElements()) {
                String token = stringTokenizer.nextToken();

                anagramParser.addWord(token);
            }

        } while (iterator.hasNext());

        iterator.close();

        return anagramParser;
    }

    private static Comparator<Item> ITEMS_COMPARATOR = new Comparator<Item>() {
        public int compare(Item itemOne, Item itemTwo) {
            return itemOne.position.compareTo(itemTwo.position);
        }
    };

    private static Comparator<String> WORDS_COMPARATOR = new Comparator<String>() {
        public int compare(String wordOne, String wordTwo) {
            return wordOne.compareTo(wordTwo);
        }
    };

    private HashMap<String, Item> occurrences = new HashMap<>();
    private List<String> duplicates = new ArrayList<>();

    private AnagramParser() {
    }

    public boolean isEmpty() {
        return duplicates.isEmpty();
    }

    public int size() {
        return duplicates.size();
    }

    public void saveToFile(File file, String charset) throws IOException {
        PrintWriter writer = new PrintWriter(file, charset);

        for (AnagramParser.Item item : getItems()) {
            // sorting by alphabetical order all words inside this anagram bucket
            item.sortWords();

            writer.println(StringUtils.join(item.words, " "));
        }

        writer.close();
    }

    private void addWord(String word) {
        String key = getHash(word);

        if (occurrences.containsKey(key)) {
            if (!occurrences.get(key).words.contains(word))
                occurrences.get(key).words.add(word);

            if (!duplicates.contains(key))
                duplicates.add(key);
        } else {
            Item item = new Item();
            item.position = occurrences.size();
            item.words.add(word);

            occurrences.put(key, item);
        }
    }

    private List<Item> getItems() {
        List<Item> result = new ArrayList<>();

        for (String key : duplicates) {
            result.add(occurrences.get(key));
        }

        Collections.sort(result, ITEMS_COMPARATOR);

        return result;
    }

    private String getHash(String word) {
        // TODO not sure, is it necessary to covert to lower case or not
        word = word.toLowerCase();

        char[] chars = word.toCharArray();
        Arrays.sort(chars);

        return new String(chars);
    }

}
