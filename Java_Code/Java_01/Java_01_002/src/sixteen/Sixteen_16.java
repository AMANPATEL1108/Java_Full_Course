package sixteen;

import java.util.*;

public class Sixteen_16 {
    public static void main(String[] args) {
        List<String> items = new ArrayList<>();
        items.add("Pen");
        items.add("Book");
        items.add("Notebook");

        Iterator<String> iterator = items.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }


        List<Student> students=new ArrayList<>();
        students.add(new Student("ap",12));
        students.add(new Student("ap3",11));
        students.add(new Student("ap2",15));


//        Collections.sort(students);
//        Collections.sort(students, new SortByName());
        students.sort(Comparator.comparing(Student::getMarks).thenComparing(Student::getName));

        System.out.println(students);

    }
}

class Student{
    String name;
    Integer marks;

    public Student(String name, Integer marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return name + ": " + marks;
    }
}

class SortByName implements Comparator<Student>{

    @Override
    public int compare(Student o1, Student o2) {
       int a=o1.name.compareTo(o2.name);
       if (a!=0){
           return a;
       }
        return a;
    }
}

//class Student implements Comparable<Student>{
//    String name;
//    Integer marks;
//
//    public Student(String name, Integer marks) {
//        this.name = name;
//        this.marks = marks;
//    }
//
//    @Override
//    public int compareTo(Student o) {
//        return this.marks.compareTo(o.marks);
//    }
//
//    @Override
//    public String toString() {
//        return name + ": " + marks;
//    }
//}

//Question:
//Answer briefly (1-2 sentences each):
//
//a. What is the main difference between Comparable and Comparator?->Comparable Compare Object and Comparator is Particular Value or Custom We can Do
//b. When would you use an Iterator instead of a for-each loop?-?Foreach not applicable to type 'java.util.Iterator<java.lang.String>'
//c. Can you use both Comparable and Comparator in the same class? If so, how? Yes But Different implementation in that with Differentiate