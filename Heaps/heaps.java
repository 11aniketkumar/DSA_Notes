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