package com.zhokhov.interview.griddynamics;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author <a href='mailto:alexey@zhokhov.com'>Alexey Zhokhov</a>
 */
public class TokenizerTest extends TestCase {

    private Tokenizer tokenizer;

    public TokenizerTest() {
        super("Test Tokenizer class");
    }

    public static Test suite() {
        return new TestSuite(TokenizerTest.class);
    }

    public void testSemicolonDelimiter() {
        tokenizer = new Tokenizer("from;on;give;pool;");

        assertEquals(4, tokenizer.countTokens());
        assertEquals("from", tokenizer.nextToken());
        assertEquals("on", tokenizer.nextToken());
        assertEquals("give", tokenizer.nextToken());
        assertEquals("pool", tokenizer.nextToken());
    }

    public void testDotsDelimiter() {
        tokenizer = new Tokenizer("from, give, pool");

        assertEquals(3, tokenizer.countTokens());
        assertEquals("from", tokenizer.nextToken());
        assertEquals("give", tokenizer.nextToken());
        assertEquals("pool", tokenizer.nextToken());
    }

    public void testComplexDelimiter() {
        tokenizer = new Tokenizer("from, pool.\ncomplete, cruel,test");

        assertEquals(5, tokenizer.countTokens());
        assertEquals("from", tokenizer.nextToken());
        assertEquals("pool", tokenizer.nextToken());
        assertEquals("complete", tokenizer.nextToken());
        assertEquals("cruel", tokenizer.nextToken());
        assertEquals("test", tokenizer.nextToken());
    }

}
