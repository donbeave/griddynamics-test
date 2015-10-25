package com.zhokhov.interview.griddynamics;

import java.io.IOException;

/**
 * @author <a href='mailto:alexey@zhokhov.com'>Alexey Zhokhov</a>
 */
public class App {

    protected static final String CHARSET = "UTF-8";

    public static void main(String[] args) {
        Cli cli = new Cli(args);
        AnagramParser parser = null;

        System.out.println("Source: " + cli.getInputFile().getAbsolutePath());
        System.out.println("Destination: " + cli.getOutputFile().getAbsolutePath());

        System.out.println("\nParsing ...");

        long start_time = System.currentTimeMillis();

        try {
            parser = AnagramParser.parseFromFile(cli.getInputFile(), CHARSET);
        } catch (IOException e) {
            cli.printErrorMessage("Failed read input file: " + e.getMessage());
            System.exit(1);
        }

        if (parser.isEmpty()) {
            System.out.println("Anagrams not detected.");
            System.exit(0);
        }

        try {
            parser.saveToFile(cli.getOutputFile(), CHARSET);
        } catch (IOException e) {
            cli.printErrorMessage("Failed save results to file: " + e.getMessage());
            System.exit(1);
        }

        long end_time = System.currentTimeMillis();

        System.out.println("\nFinished: " + (end_time - start_time) + "ms");
        System.out.println("\nTotal words with anagrams: " + parser.size());
    }


}
