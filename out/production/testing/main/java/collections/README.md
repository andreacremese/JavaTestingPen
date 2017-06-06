# notes
From the pluralsight course From Collections to Streams in Java 8 Using Lambda Expressions


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

```java
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
Meaning, this interface can be newed up and all the following code does exactly the same

```
    public Integer PersonAgeWrapper(Person p){

        Function<Person, Integer> f = new Function<Person, Integer> () {
            public Integer apply(Person p) {
                return p.GetAge();
            }
        };

        return f.apply(p);
    }

    public Integer PersonAgeWrapperWithLambda(Person p){
        Function<Person, Integer> f = person -> person.GetAge ();
        return f.apply(p);
    }

    public Integer PersonAgeWrapperWithLambdaMethodReference(Person p){
        Function<Person, Integer> f = Person::GetAge ;
        return f.apply(p);
    }

```

The same can be done in, for example, with the BinaryOperator, as the lambda overrides the .apply method.


# some of the Java 8 specific stuff we have seen:


* lambda expressions
* Static method on interfaces they belong only to the interface class. This seems to be basically a work around on the fact that static classes do not exist in Java, so interfaces step in to do the trick. `Interface.someStaticMethod()`
* Default methods
* Method reference `Person :: getName` (this is a shorthand expression for a lambda expression that ecexutes just one method
 it is the same as `p -> p.getName()`. Obviously, it works only on single line methods)
