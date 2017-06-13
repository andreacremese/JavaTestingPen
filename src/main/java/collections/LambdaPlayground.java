package main.java.collections;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public interface LambdaPlayground {


    // anonymous classes (newing up interfaces)
    static void compareWithAnonymousClass(String[] strings) {

        Comparator<String> comp = new Comparator<String>() {
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        };
        Arrays.sort(strings, comp);
    }

    static void compareWithLamda(String[] strings) {
        Arrays.sort(strings, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
//        Arrays.sort(strings, (final String s1, final String s2) -> Integer.compare(s1.length(), s2.length()));
    }


    // wrap with function

    static Integer PersonAgeWrapper(Person p){

        Function<Person, Integer> f = new Function<Person, Integer> () {
            public Integer apply(Person p) {
                return p.GetAge();
            }
        };

        return f.apply(p);
    }

    static Integer PersonAgeWrapperWithLambda(Person p){
        Function<Person, Integer> f = person -> person.GetAge ();
        return f.apply(p);
    }

    static  Integer PersonAgeWrapperWithLambdaMethodReference(Person p){
        Function<Person, Integer> f = Person::GetAge ;
        return f.apply(p);
    }

    // custom comparers

    static void compareByAge(Person[] people) {
        // these are all equivalent!

        Comparator<Person> cmp1c = Comparator.comparing(p -> p.getAge());

        // this is a functional interface in java, so can be newed up (!!!!)
        Comparator<Person> cmp1 = (p1, p2) -> p1.getAge() - p2.getAge();

        Function<Person, Integer> f1 = p -> p.getAge();
        Comparator<Person> cmp1a = Comparator.comparing(f1);

        Comparator<Person> cmp1b = Comparator.comparing(Person::getAge);



        Arrays.sort(people, cmp1c);
    }


    static void compareByFirstName(Person[] people) {
        // these are all equivalent!

        Comparator<Person> cmp1c = Comparator.comparing(p -> p.getFirstName());

        // this is a functional interface in java, so can be newed up (!!!!)
        Comparator<Person> cmp1 = (p1, p2) -> p1.getAge() - p2.getAge();

        Function<Person, Comparable> f1 = p -> p.getFirstName();
        Comparator<Person> cmp1a = Comparator.comparing(f1);

        Comparator<Person> cmp1b = Comparator.comparing(Person::getFirstName);



        Arrays.sort(people, cmp1b);
    }


    static Boolean shorterThan(String s, Integer i) {
        Predicate<String> p = str -> str.length() < i;
        return p.test(s);
    }

//    static Boolean lengthBetweenTwoIntergers(String s, Integer lo, Integer hi) {
////        MyPredicate<String> p1 = new MyPredicate<String>() {
////            public boolean test(String s) {
////                return s.length() > lo;
////            }
////        };
//
//        //MyPredicate<String> p1 = str -> str.length() > lo;
//        MyPredicate<String> p1 = str -> str.length() > lo;
//        MyPredicate<String> p2 = str -> str.length() < hi;
//        MyPredicate<String> p3 = p1.and(p2);
//
//        return p3.test(s);
//    }

    static void addMaps(HashMap<String, List<String>> m1, HashMap<String, List<String>> m2) {

        m2.forEach(
                (k,v) ->
                        m1.merge(k,v,
                                (existinglist, newlist) -> {
                            existinglist.addAll(newlist);
                            return existinglist;
        }));
    }
}
