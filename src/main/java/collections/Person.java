package main.java.collections;

public class Person {

    public String firstName;
    public String lastName;
    public Integer age;


    // getterSetters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }


    public Person(String n, String ln,  Integer a) {
        this.firstName = n;
        this.lastName = ln;
        this.age = a;
    }


    // ctor
    public Integer GetAge() {
        return this.age;
    }

    // overrides


    @Override
    public String toString() {
        return String.format("Person(firstName = %s, lastName = %s, age = %s)", this.getFirstName(), this.getLastName(), this.getAge());
    }
}
