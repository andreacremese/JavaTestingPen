package test.java.collections;

import main.java.collections.LambdaPlayground;
import main.java.collections.Person;
import org.junit.Test;

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
}
