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


##