package dayThirtySeven;

import java.util.Arrays;
import java.util.List;

public class ThirtySeven_37 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(-1, 2, 3, -4, 5, 6);
        int sum = numbers.stream().filter(n->n>0).mapToInt(Integer::intValue).sum();
        System.out.println(sum);

        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");
        int longestLength=words.stream().mapToInt(String::length).max().orElse(0);
        System.out.println(longestLength);

        List<String> words2 = Arrays.asList("apple", "banana", "kiwi", "grape");
        int minLength=words2.stream().mapToInt(String::length).min().orElse(0);
        System.out.println(minLength);
    }
}
