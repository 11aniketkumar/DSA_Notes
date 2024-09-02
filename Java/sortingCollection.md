# Sorting in Java

## Method for Sorting

To sort a collection of items (whether they are data types or user-defined classes), use `Collections.sort(Collection<T>, {optional: Comparator<T>})`.

For `List<T>`, `T` can be a default data type (like `Integer`, `String`) or a user-defined class.

```java
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

// Sorting a list of integers
List<Integer> numbers = new ArrayList<>();
numbers.add(3);
numbers.add(1);
numbers.add(2);

// Sorts in natural order (ascending)
Collections.sort(numbers);
```

## `Comparable` Interface

-   **Purpose:** Defines the natural ordering of objects of a class.
    -   Eg - By default `Integer` class`s natural order is from small to big.
-   **Usage in Sorting:**
    -   When you use `Collections.sort(Collection<T>)` and a `Comparator` object is not provided:-
        -   Java checks the datatype of object, and checks if the datatype implements `Comparable` class.
        -   It uses the `compareTo` method defined in `Comparable` if the type implements it.
    -   The sort method checks if `T` implements `Comparable<T>`, and if it does, it uses the `compareTo` method to determine the order of elements.
-   **Implementation:**
    -   A class implements `Comparable<T>` to specify how instances of that class should be ordered relative to each other.
    -   Implement the `compareTo(T o)` method to define the natural ordering. For example, in `Integer`, this method ensures that numbers are compared numerically.

```java
class Person implements Comparable<Person> {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person other) {
        // Natural ordering by age
        return Integer.compare(this.age, other.age);
    }
}
```

```java
List<Person> people = new ArrayList<>();
people.add(new Person("Alice", 30));
people.add(new Person("Bob", 25));

// Sorts by age using compareTo method
Collections.sort(people);
```

## `Comparator` Interface

-   **Purpose:** Defines a custom ordering for objects that may differ from their natural ordering.
    -   Maybe, you don't want to sort Integers based on small to big, maybe you want to sort based on even or odd, or maybe you want to sort based on their last digits.
-   **Usage in Sorting:**
    -   Pass an instance of `Comparator<T>` to `Collections.sort(Collection<T>, Comparator<T>)`.
    -   If a `Comparator` is provided, `Collections.sort` will use it to determine the order instead of relying on the `compareTo` method.
    -   This allows you to sort objects in various ways, such as in descending order, or based on some other property.
-   **Implementation:**
    -   Implement the `Comparator<T>` interface and override the `compare(T o1, T o2)` method to specify a custom ordering.
    -   Use this when you need a specific ordering that doesn't align with the natural ordering or when you cannot modify the class to implement `Comparable`.

```java
import java.util.Comparator;

class PersonByNameComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        // Custom ordering by name
        return p1.name.compareTo(p2.name);
    }
}

// Or using a lambda expression
Comparator<Person> nameComparator = (p1, p2) -> p1.name.compareTo(p2.name);
```

```java
List<Person> people = new ArrayList<>();
people.add(new Person("Alice", 30));
people.add(new Person("Bob", 25));

// Sorts by name using Comparator
Collections.sort(people, nameComparator);
```

## Summary

-   **Default Sorting (`Comparable`):**

    -   When sorting a collection of `Integer`, `String`, or any class implementing `Comparable`, `Collections.sort` uses the natural order defined by `compareTo`.

-   **Custom Sorting (`Comparator`):**
    -   Use a `Comparator` when you want a different sorting order or when the class does not implement `Comparable`.
