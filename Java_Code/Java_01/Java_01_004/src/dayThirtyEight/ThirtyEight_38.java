package dayThirtyEight;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ThirtyEight_38 {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "cat", "banana", "dog", "elephant");
        int s = words.stream().filter(n -> n.length() >= 4).mapToInt(String::length).sum();
        System.out.println(s);

        List<Integer> numbers = Arrays.asList(2, -3, 4, -5, 6, 7);
        int num = numbers.stream().filter(n -> n > 0).reduce(1, (a, b) -> a * b);
        System.out.println(num);

        List<String> names = Arrays.asList("Alice", "Bob", "Ana", "Charlie", "Adam", "Bob");
        Map<Character, List<String>> groupedNames = names.stream()
                .collect(Collectors.groupingBy(name -> Character.toUpperCase(name.charAt(0)), TreeMap::new, Collectors.toList()));
        System.out.println(groupedNames);
    }
}
