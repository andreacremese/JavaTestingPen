package main.java.collections;

// personal implementation of Predicate
@FunctionalInterface
public interface MyPredicate<T> {
    boolean test(T t);

    default boolean test(String t) {
        return t.length() > 4;
    }

    default MyPredicate<T> and(MyPredicate<T> other) {
        return t -> test(t) && other.test(t);
    }

    default MyPredicate<T> or(MyPredicate<T> other) {
        return t -> test(t) || other.test(t);
    }

    // with a single type
    static MyPredicate<String> isEqualString(String string) {
        return s ->  s.equals(s);
    }

    // with generics
    static <U> MyPredicate<U> isEqual(U u) {
        return s ->  s.equals(u);
    }
}

// even though this is an interface, there is the notion of calling basically this.test()
// !!!!!!!!!!!!!!!! this allows to do things like
//    static Boolean lengthBetweenTwoIntergers(String s, Integer lo, Integer hi) {
//        MyPredicate<String> p1 = str -> str.length() > lo;
//        MyPredicate<String> p2 = str -> str.length() < hi;
//        MyPredicate<String> p3 = p1.and(p2);
//
//        return p3.test(s);
//    }
