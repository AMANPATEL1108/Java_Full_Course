package dayThirty;

import java.util.Arrays;
import java.util.List;

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Utils {
    public static void printUppercase(String str) {
        System.out.println(str.toUpperCase());
    }
}

class Printer {
    public static void printMessage(String message) {
        System.out.println(message);
    }
}

public class Thirty_30 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        names.forEach(System.out::println);

        names.stream().map(Person::new).forEach(p -> System.out.println(p.getName()));

        names.forEach(Utils::printUppercase);

        names.forEach(Printer::printMessage);

        MyInterface myInterface = (message) -> System.out.println(message);
        names.forEach(myInterface::printMessage);
    }
}

@FunctionalInterface
interface MyInterface {
    void printMessage(String message);
}
