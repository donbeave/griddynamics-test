package com.zhokhov.interview.griddynamics;

import java.util.StringTokenizer;

/**
 * @author <a href='mailto:alexey@zhokhov.com'>Alexey Zhokhov</a>
 */
public class Tokenizer extends StringTokenizer {

    public Tokenizer(String string) {
        super(string, ";,.\n ");
    }

}
