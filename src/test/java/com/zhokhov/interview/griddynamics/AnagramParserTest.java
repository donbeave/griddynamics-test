package com.zhokhov.interview.griddynamics;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author <a href='mailto:alexey@zhokhov.com'>Alexey Zhokhov</a>
 */
public class AnagramParserTest extends TestCase {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private File inputFile1;
    private File inputFile2;
    private File outputFile;

    public AnagramParserTest() {
        super("Test AnagramParser class");
    }

    public static Test suite() {
        return new TestSuite(AnagramParserTest.class);
    }

    public void testWordsSplittedBySemicolon() throws IOException {
        AnagramParser parser = AnagramParser.parseFromFile(inputFile1, App.CHARSET);

        assertFalse(parser.isEmpty());
        assertEquals(3, parser.size());

        parser.saveToFile(outputFile, App.CHARSET);

        List<String> lines = FileUtils.readLines(outputFile, App.CHARSET);

        assertEquals(3, lines.size());
        assertEquals("form from", lines.get(0));
        assertEquals("no on", lines.get(1));
        assertEquals("loop polo pool", lines.get(2));
    }

    public void testWordsSplittedByDifferentDelimiters() throws IOException {
        AnagramParser parser = AnagramParser.parseFromFile(inputFile2, App.CHARSET);

        assertFalse(parser.isEmpty());
        assertEquals(4, parser.size());

        parser.saveToFile(outputFile, App.CHARSET);

        List<String> lines = FileUtils.readLines(outputFile, App.CHARSET);

        assertEquals(4, lines.size());
        assertEquals("get teg", lines.get(0));
        assertEquals("no on", lines.get(1));
        assertEquals("keep peek", lines.get(2));
        assertEquals("man nam", lines.get(3));
    }

    @Override
    protected void setUp() throws Exception {
        folder.create();

        inputFile1 = folder.newFile("input1.txt");
        inputFile2 = folder.newFile("input2.txt");
        outputFile = folder.newFile("output.txt");

        PrintWriter writer = new PrintWriter(inputFile1, App.CHARSET);
        writer.println("from;on;give;pool;loop;polo;form;no;let;");
        writer.close();

        writer = new PrintWriter(inputFile2, App.CHARSET);
        writer.println("come, get, give, on, keep.");
        writer.println("girl, man");
        writer.println("teg, star, peek, no.");
        writer.println("nam.");
        writer.close();
    }

}
