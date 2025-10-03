package twentysix;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TwentySix_26 {
    public static void main(String[] args) {
        MathOperation addition = (a, b) -> a + b;
        MathOperation substraction = (a, b) -> a - b;
        MathOperation multiplication = (a, b) -> a * b;
        MathOperation division = (a, b) -> a / b;

        System.out.println("Sum: " + addition.operate(10, 5));
        System.out.println("Sub: " + substraction.operate(10, 5));
        System.out.println("Mul: " + multiplication.operate(10, 5));
        System.out.println("Div: " + division.operate(10, 5));

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> num=numbers.stream().filter(f->f%2==0).map(n-> n).collect(Collectors.toList());
        System.out.println(num);

        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");
        List<String> upperWord=words.stream().map(n->n.toUpperCase()).collect(Collectors.toList());
        System.out.println(upperWord);

        List<Integer> numbers2 = Arrays.asList(15, 22, 7, 18, 25, 33, 10);
        Optional<Integer> max=numbers2.stream().max(Integer::compareTo);
        System.out.println(max);

        List<String> names = Arrays.asList("John", "Alice", "Bob", "Charlie", "Dave");

        List<String> asc= names.stream().sorted(String::compareToIgnoreCase).toList();
        System.out.println(asc);

        List<Integer> numbers3 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> mul=numbers3.stream().map(n->n*n).collect(Collectors.toList());
        System.out.println(mul);

    }
}

@FunctionalInterface
interface MathOperation {
    int operate(int a, int b);
}