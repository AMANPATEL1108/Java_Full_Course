package twentyeight;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TwentyEight_28 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> even = numbers.stream().filter(n -> n % 2 == 0).toList();
        System.out.println(even);

        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");
        List<Integer> len = words.stream().map(n -> n.length()).toList();
        System.out.println(len);

        List<Integer> numbers2 = Arrays.asList(1, 5, 3, 9, 2);
        Optional<Integer> max = numbers2.stream().max(Integer::compareTo);
        System.out.println(max);

        List<Integer> checkOdd = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> odd=checkOdd.stream().filter(n->n%2!=0).toList();
        System.out.println(odd.size());

        List<String> words2 = Arrays.asList("apple", "banana", "avocado", "cherry", "blueberry", "date");
        Map<Character, List<String>> grouped = words2.stream().collect(Collectors.groupingBy(word -> word.charAt(0)));
        System.out.println(grouped);
    }
    }
