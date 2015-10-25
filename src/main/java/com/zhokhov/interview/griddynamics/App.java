package com.zhokhov.interview.griddynamics;

import java.io.IOException;

/**
 * @author <a href='mailto:alexey@zhokhov.com'>Alexey Zhokhov</a>
 */
public class App {

    private static final String CHARSET = "UTF-8";

    public static void main(String[] args) throws IOException {
        Cli cli = new Cli(args);

        AnagramParser parser = AnagramParser.parseFromFile(cli.getInputFile(), CHARSET);

        if (parser.isEmpty()) {
            System.out.println("Anagrams not detected.");
            System.exit(0);
        }

        parser.saveToFile(cli.getOutputFile(), CHARSET);

        System.out.println("Total words with anagrams: " + parser.size());
    }

}
