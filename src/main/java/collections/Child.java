package main.java.collections;

public class Child extends Parent implements MyPredicate<String> {
    public boolean test(String s) {
        return super.test(s);
    }
}
