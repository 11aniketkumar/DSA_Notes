# [DSA](../README.md) / Heaps

## Java collections

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.add(3);
pq.add(1);
pq.add(2);
pq.add(4);

while(!pq.isEmpty()) {
    System.out.println(pq.peek());
    pq.remove();
}
```

-   To reverse the order use `PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());`

```java
import java.util.PriorityQueue;
import java.util.Comparator;

class heaps {
    static class Student implements Comparable<Student>{
        String name;
        int rank;

        Student(String name, int rank) {
            this.name = name;
            this.rank = rank;
        }

        @Override
        public int compareTo(Student s2) {
            return this.rank - s2.rank;
        }
    }
    public static void main(String[] args) {
        PriorityQueue<Student> pq = new PriorityQueue<>(Comparator.reverseOrder());

        pq.add(new Student("a",5));
        pq.add(new Student("b",2));
        pq.add(new Student("c",1));
        pq.add(new Student("d",4));
        pq.add(new Student("e",3));

        while(!pq.isEmpty()) {
            System.out.println(pq.peek().name + " -> " + pq.peek().rank);
            pq.remove();
        }
    }
}
```
