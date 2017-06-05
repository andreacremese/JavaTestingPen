package test.java.collections;

import main.java.collections.Lambda;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestLamda {
    @Test
    public void compareWithAnonymousSorts() {
        // arrange
        String[] strings = new String[3];
        strings[0] = "aaa";
        strings[1] = "a";
        strings[2] = "aa";

        // act
        new Lambda().compareWithAnonymousClass(strings);
        // assert
        assertTrue("out of order", strings[0] == "a");
        assertTrue("out of order", strings[1] == "aa");
        assertTrue("out of order", strings[2] == "aaa");
    }

    @Test
    public void compareWithLambdaSorts() {
        // arrange
        String[] strings = new String[3];
        strings[0] = "aaa";
        strings[1] = "a";
        strings[2] = "aa";

        // act
        new Lambda().compareWithLamda(strings);
        // assert
        assertTrue("out of order", strings[0] == "a");
        assertTrue("out of order", strings[1] == "aa");
        assertTrue("out of order", strings[2] == "aaa");
    }
}
