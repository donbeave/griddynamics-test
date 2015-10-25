package com.zhokhov.interview.griddynamics;

import org.apache.commons.cli.*;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * @author <a href='mailto:alexey@zhokhov.com'>Alexey Zhokhov</a>
 */
public class Cli {

    private File inputFile;
    private File outputFile;
    private boolean interative = false;

    private static final String HELP_OPT = "help";
    private static final String INTERACTIVE_MODE_OPT = "i";

    public Cli(String[] args) {
        Options options = new Options();
        options.addOption(new Option(null, HELP_OPT, false, "print this message"));
        options.addOption(new Option(INTERACTIVE_MODE_OPT, "interactive", false, "interactive mode"));

        parse(options, args);
    }

    public File getInputFile() {
        return inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void printErrorMessage(String msg) {
        if (interative) {
            System.out.println("!!! " + msg + "\n");
        } else {
            System.err.println("ERROR: " + msg);
            System.exit(1);
        }
    }

    private void parse(Options options, String[] args) {
        CommandLineParser parser = new DefaultParser();

        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);

            if (cmd.hasOption(HELP_OPT)) {
                showHelp(options);
            } else {
                if (cmd.hasOption(INTERACTIVE_MODE_OPT))
                    interative = true;

                List<String> cmdArgs = cmd.getArgList();

                if (!cmdArgs.isEmpty()) {
                    inputFile = checkFilePath(cmdArgs.get(0), true);

                    if (cmdArgs.size() > 1)
                        outputFile = checkFilePath(cmdArgs.get(1), false);
                }

                if (interative) {
                    inputFile = inputFile == null ? askFilePath("> Please enter path to SOURCE file:", true) : inputFile;
                    outputFile = outputFile == null ? askFilePath("> Please enter path to DESTINATION file:", false) : outputFile;
                } else {
                    if (inputFile == null || outputFile == null) {
                        System.err.println("No source or destination files specified.\n");

                        showHelp(options);
                    }
                }
            }

        } catch (ParseException e) {
            // oops, something went wrong
            System.err.println("CLI error: " + e.getMessage() + "\n");

            showHelp(options);
        }
    }

    private void showHelp(Options options) {
        HelpFormatter formater = new HelpFormatter();

        String footer =
                "\nSOURCE_FILE\n" +
                        "input file path, which contains words separated by semicolon, comma, dot, space or new line" +
                        "\n\nDESTINATION_FILE\n" +
                        "output file path, which will contain result of anagram parser" +
                        "\n\nboth path can be absolute or relative";

        formater.printHelp("anagram [SOURCE_FILE] [DESTINATION_FILE]", null, options, footer);

        System.exit(0);
    }

    private File askFilePath(String message, boolean input) {
        Scanner scan = new Scanner(System.in);

        File file;

        do {
            System.out.println(message + "\n  [to exit enter q]");

            String path = scan.hasNext() ? scan.next() : "";

            exitIfNecessary(path);
            file = checkFilePath(path, input);
        } while (file == null);

        return file;
    }

    private File checkFilePath(String path, boolean input) {
        File file = new File(path);

        if (input) {
            if (!(file.exists() && file.canRead())) {
                printErrorMessage("File not found or can't read: " + path);
                return null;
            }
        } else {
            if (file.exists()) {
                try {
                    if (StringUtils.equalsIgnoreCase(file.getCanonicalPath(), inputFile.getCanonicalPath())) {
                        printErrorMessage("Destination file can't be the same with source file");
                        return null;
                    }
                } catch (IOException e) {
                    // oops, something went wrong
                    System.err.println("Error when get canonical path. Reason: " + e.getMessage());
                    return null;
                }

                if (interative) {
                    Scanner scan = new Scanner(System.in);
                    System.out.println("File exists (" + path + "). Override it? [y/n]" + "\n  [to exit enter q]");
                    String userChoice = scan.next();

                    exitIfNecessary(path);

                    if (userChoice.toLowerCase().trim().equals("y")) {
                        if (!file.canWrite()) {
                            printErrorMessage("Not enough permissions to write to file: " + path);
                            return null;
                        }
                    } else {
                        return null;
                    }
                } else {
                    printErrorMessage("Destination file is not empty.");
                }
            }
        }

        return file;
    }

    private void exitIfNecessary(String input) {
        input = input.toLowerCase().trim();

        if (input.equals("q") || input.equals("exit")) {
            exitNormally();
        }
    }

    private void exitNormally() {
        System.out.println("Exiting...");
        System.exit(0);
    }

}