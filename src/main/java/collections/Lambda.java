package main.java.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Lambda {

    public void compareWithAnonymousClass(String[] strings) {

        Comparator<String> comp = new Comparator<String>() {
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        };
        Arrays.sort(strings, comp);
    }

    public void compareWithLamda(String[] strings) {
        Arrays.sort(strings, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
    }
}
