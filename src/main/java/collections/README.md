# notes
From the pluralsight course From Collections to Streams in Java 8 Using Lambda Expressions


## some of the Java 8 specific interface stuff we have seen - maybe for a blog post entry:

* **Lambda expressions** (they do what it says on the tin, they are similar in c#)
* **Static method** on interfaces they belong only to the interface class. This seems to be basically a work around on the fact that static classes do not exist in Java, so interfaces step in to do the trick. `Interface.someStaticMethod()`
* **Default methods** when adding a contract to an interface you can specify a default implementation to it. THis seems **severely** counterintuitive to me, as the point of an interface is NOT to include any implementation, only, well, interface...
I guess they needed it in order to make the old interface to work as functional interfaces?
* **Method reference syntax** `Person :: getName` (this is a shorthand expression for a lambda expression that ecexutes just one method
 it is the same as `p -> p.getName()`. Obviously, it works only on single line methods)
 * **functional interface** this is the parent of a lambda function. A functional interface is an interface with ONLY ONE METHOD. Meaning, I don have to specify which is the method I am implementing, as there is only one! The implementation of `MyPredicate` could be an interesting one.
 
 


## Anonymous class VS inner class
The idea is to have an in line definition for a class, rather than declaring it in another file / namespace.

So you can new up a class and use it straight away. This seems like a pattern that is useful for implementations of 
interfaces (`Runnable`), but for anything larger use a separate class e OR an inner class.

```
    // anonymous class that implements the Runnable
    public void DoSomething(args...) {
        new Thread(new Runnable() {
            public void run() {
                // do some stuff nere
            }
        }).start();    
    }

```

Im the example of a Runnable, the equivalent with an inner class would be:

```$xslt
 public void someMethod()
 {
     new Thread(new MyRunnable()).start();
 }
 
 // Inner class here! //
 
 class MyRunnable implements Runnable
 {
     public void run()
     {
         // do stuff
     }
 }   

```

Note that anonymous classes can `new up` interfaces.

## lambda expressions

The idea is to pass code as a parameter (like taking in a delegate for a method in c#)


* comparable - object with generic that has to provide only one method - compare
* runnable interface - returns void, takes  void, has only one method run. Can be passed to another thread to be executed.


 ```
 Comparator<String> comp = new Comparator<String>() {
    public int compare(String s1, String s2) {
        return Integer.compare(s1.length(), s2.length())
    }
 }  

```

As a lambda, it becomes

```

 Comparator<String> comp = new Comparator<String>() {
    public int compare(String s1, String s2) {
        return Integer.compare(s1.length(), s2.length())
    }
 }
    (String s1, String s2) -> Integer.compare(s1.length(), s2.length()); 

```
Like in c#, if there is more than one line, encompass in {} and put the `return` keyboard. Otherwise, 
a single line will return automatically the single line of the method. 

`final` will still work, `annotation` will work, but no return type. ON the other side, you can remove the 
types of the parameters if needed

```
    (s1, s2) -> Integer.compare(s1.length(), s2.length()); 

```

## functional interface

Note that a functional interface is 

```
A functional interface is an interface that has just one abstract method, and thus
represents a single function contract. This "single" method may take the form
of multiple abstract methods with override-equivalent signatures inherited from
superinterfaces; in this case, the inherited methods logically represent a single
method.
```

And `Evaluation of a lambda expression produces an instance of a functional interface`. Meaning, a lambda can generate only
an instance of an interface with a single method. For that reason there is no need to indicate which method is to be ovewritten,
there is ONLY one method to be overwritten.

From the specs:
```
    Function<T,R>
    Represents a function that accepts one argument and produces a result.

```
So, function is a in interface with only one method (`applly`) hence it is a functionalinterface and, as such, can be implemented with a lambda.
Meaning, this interface can be newed up and all the following code does exactly the same

```
    public Integer PersonAgeWrapper(Person p){
        // anonymous function example
        Function<Person, Integer> f = new Function<Person, Integer> () {
            public Integer apply(Person p) {
                return p.GetAge();
            }
        };

        return f.apply(p);
    }
    
    // lambda example
    public Integer PersonAgeWrapperWithLambda(Person p){
        Function<Person, Integer> f = person -> person.GetAge ();
        return f.apply(p);
    }

    // method reference style
    public Integer PersonAgeWrapperWithLambdaMethodReference(Person p){
        Function<Person, Integer> f = Person::GetAge ;
        return f.apply(p);
    }

```

The same can be done in, for example, with the BinaryOperator, as the lambda overrides the .apply method.

Static and default methods do not count in the constraints to have only one method in the functional interface.


An example of a functional interface


```java
package main.java.collections;

// personal implementation of Predicate API interface
@FunctionalInterface
public interface MyPredicate<T> {
    boolean test(T t);
    
    
    // default methods
    default MyPredicate<T> and(MyPredicate<T> other) {
        return t -> test(t) && other.test(t);
    }

    default MyPredicate<T> or(MyPredicate<T> other) {
        return t -> test(t) || other.test(t);
    }
    
    
    // static methods
    // with a single type
    static MyPredicate<String> isEqualString(String string) {
         return s ->  s.equals(s);
    }
    
    // with generics
    static <U> MyPredicate<U> isEqual(U u) {
        return s ->  s.equals(u);
    }
}


//the consumer would be
// even though this is an interface, there is the notion of calling basically this.test()
// !!!!!!!!!!!!!!!! this allows to do things like
//    static Boolean lengthBetweenTwoIntergers(String s, Integer lo, Integer hi) {
//        MyPredicate<String> p1 = str -> str.length() > lo;
//        MyPredicate<String> p2 = str -> str.length() < hi;
//        MyPredicate<String> p3 = p1.and(p2);
//
//        return p3.test(s);
//    }


        // arrange
//        MyPredicate<Integer> p1 = MyPredicate.isEqual(1);
        // act
  //      Boolean result = p1.test(1);
        // assert
    //    assertTrue("they are not the same", result);

```

##java.util.function 

These are the equivalent of the c# Predicates, Func, .... they all have a different method signature / return type. E.g. the consumer 
does take oneobject and return void, while Functions take one type and return another type. The Predicates take an object and return a boolean for example. 
The full list is at https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html 



## Iterables / collection methods in Java and map inerface methods
The idea is to pass in a lambda to a method. See underscore and, pretty much c#. The only difference is the method reference pattern `Person::getName`

e.g. the foreach take s `biconsumer` for a Map<U,K>, as the consumer will get a reference to both the key and to the value.

there are a few methods like .replace, `.addIfNotPresent` and `.computerIfAbset`, see https://docs.oracle.com/javase/8/docs/api/java/util/Map.html 

computeIfAbsent is interesting, as it basically used for initialization for maps of maps

Map<String, Map<String, Integer>> map = new ...

map.computeIfAbsent("one", key -> new Hashmap<String, INteger>).put("two", someAlreadyInitializedElement)

and so you don't have anymore the null throwing at run time

