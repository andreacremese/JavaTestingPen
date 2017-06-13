package test.java.collections;

import main.java.collections.Child;
import main.java.collections.LambdaPlayground;
import main.java.collections.MyPredicate;
import main.java.collections.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import java.util.HashMap;

import static org.junit.Assert.*;

public class LambdaPlaygroundTest {
    @Test
    public void compareWithAnonymousSorts() {
        // arrange
        String[] strings = new String[3];
        strings[0] = "aaa";
        strings[1] = "a";
        strings[2] = "aa";

        // act
        LambdaPlayground.compareWithAnonymousClass(strings);
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
        LambdaPlayground.compareWithLamda(strings);
        // assert
        assertTrue("out of order", strings[0] == "a");
        assertTrue("out of order", strings[1] == "aa");
        assertTrue("out of order", strings[2] == "aaa");
    }

    @Test
    public void functionWrapperWorksLongForm() {
        // arrange
        Person p = new Person("asd", "a", 5);
        // act
        Integer result = LambdaPlayground.PersonAgeWrapper(p);
        // assert
        assertTrue("ages don't match!!", 5 == result);

    }

    @Test
    public void functionWrapperWorksShortForm() {
        // arrange
        Person p = new Person("asd", "a", 5);
        // act
        Integer result = LambdaPlayground.PersonAgeWrapperWithLambda(p);
        // assert
        assertTrue("ages don't match!!", 5 == result);
    }

    @Test
    public void functionWrapperWorksShortFormWithMethodReference() {
        // arrange
        Person p = new Person("asd", "a", 5);
        // act
        Integer result = LambdaPlayground.PersonAgeWrapperWithLambdaMethodReference(p);
        // assert
        assertTrue("ages don't match!!", 5 == result);
    }

    @Test
    public void canCompareAgesWithDifferentLambdas() {
        // arrange
        Person[] people = new Person[3];
        people[0] = new Person("asd", "a", 7);
        people[1] = new Person("asd", "b", 5);
        people[2] = new Person("asd", "c", 6);
        // act
        LambdaPlayground.compareByAge(people);
        // assert
        assertTrue("sorting is wrong", people[0].getAge() == 5);
        assertTrue("sorting is wrong", people[1].getAge() == 6);
        assertTrue("sorting is wrong", people[2].getAge() == 7);
    }

    @Test
    public void canCompareFirstNamesWithDifferentLambdas() {
        // arrange
        Person[] people = new Person[3];
        people[0] = new Person("ccc", "c", 7);
        people[1] = new Person("aaa", "a", 5);
        people[2] = new Person("bbb", "b", 6);
        // act
        LambdaPlayground.compareByFirstName(people);
        // assert
        assertTrue("sorting is wrong", people[0].getAge() == 5);
        assertTrue("sorting is wrong", people[1].getAge() == 6);
        assertTrue("sorting is wrong", people[2].getAge() == 7);
    }


    //   ~~~~~~~~~~~~~~~~~~~~~~ Predicates ~~~~~~~~~
    @Test
    public void canUsePredicateForShorterThan(){
        assertTrue("string is actually longer", LambdaPlayground.shorterThan("Hello", 20));
    }
//
//    @Test
//    public void canUseAndPredicateForShorterThan(){
//        assertTrue("string is actually outside bounds", LambdaPlayground.lengthBetweenTwoIntergers("Hello", 3,20));
//    }

//    @Test
//    public void canCreateAPredicateForEqualityWithFixesType() {
//        // arrange
//        MyPredicate<String> p1 = MyPredicate.isEqualString("Test");
//        // act
//        Boolean result = p1.test("Test");
//        // assert
//        assertTrue("they are not the same", result);
//    }

    @Test
    public void canCreateAPredicateForEqualityWithGeneric() {
        // arrange
        MyPredicate<Integer> p1 = MyPredicate.isEqual(1);
        // act
        Boolean result = p1.test(1);
        // assert
        assertTrue("they are not the same", result);
    }

    @Test
    public void canAddHashMaps() {
        // arrange
        HashMap<String, List<String>> m1 = new HashMap<String, List<String>>();
        HashMap<String, List<String>> m2 = new HashMap<String, List<String>>();

        m1.computeIfAbsent("one", (k) -> new ArrayList(Arrays.asList("a", "b")) );
        m2.computeIfAbsent("one", (k) -> new ArrayList(Arrays.asList("c", "d")) );

        // act
        LambdaPlayground.addMaps(m1,m2);

        // assert
        assertTrue("elemtns were not added", m1.get("one").size() == 4);

    }

    @Test
    public void ensureParentInherits(){
        // arrange
        Child c = new Child();
        // test
        boolean result4 = c.test("1234");
        boolean result8 = c.test("12345678");
        // expecting parent to win
        assertFalse("interface got implemented instead", result4);
        assertTrue("interface got implemented instead", result8);
    }
}
