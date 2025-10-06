package twentynine;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TwentyNine_29 {
    public static void main(String[] args) {
        //Question - 1 -> Output of Below code ->[20, 25, 30]
        List<Integer> numbers = Arrays.asList(5, 10, 15, 20, 25, 30);
        List<Integer> filtered = numbers.stream()
                .filter(n -> n > 15)
                .collect(Collectors.toList());
        System.out.println(filtered);
        List<String> words = Arrays.asList("java", "stream", "filter", "map");

        List<Integer> lengths = words.stream()
                .map(String::length)
                .collect(Collectors.toList());

        System.out.println(lengths);

//        [apple, banana, pear, orange]
        List<String> fruits = Arrays.asList("apple", "banana", "pear", "apple", "orange");
        Set<String> uniqueFruits = fruits.stream()
                .collect(Collectors.toSet());
        System.out.println(uniqueFruits);

        List<Integer> numbers2 = Arrays.asList(1, 2, 3, 4, 5);

        int product = numbers2.stream()
                .reduce(1, (a, b) -> a * b);

        System.out.println(product);


    }
}
