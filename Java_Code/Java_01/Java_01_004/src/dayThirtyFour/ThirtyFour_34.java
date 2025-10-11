package dayThirtyFour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Employee {
    String name;
    int salary;

    Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }
}


public class ThirtyFour_34 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> even = numbers.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
        System.out.println(even);
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");
        int totalLength = words.stream().mapToInt(String::length).reduce(0, (sum, length) -> sum + length);
        System.out.println(totalLength);

        List<Employee> employees = Arrays.asList(
                new Employee("John", 50000),
                new Employee("Jane", 75000),
                new Employee("Jack", 60000),
                new Employee("Jill", 45000)
        );

        List<Employee> emp = new ArrayList<>(employees.stream().filter(n -> n.salary > 55000).collect(Collectors.toList()));
        for (Employee e : emp) {
            System.out.println(e.name +" "+e.salary);
        }
    }
}
