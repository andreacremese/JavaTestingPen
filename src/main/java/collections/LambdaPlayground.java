package main.java.collections;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;

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
}
