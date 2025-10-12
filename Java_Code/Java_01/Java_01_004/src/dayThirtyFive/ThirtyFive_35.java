package dayThirtyFive;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ThirtyFive_35 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> odd=numbers.stream().filter(n->n%2!=0).map(n->n*n).collect(Collectors.toList());
        int totalLength = odd.stream().mapToInt(Integer::intValue).sum();
        System.out.println(totalLength);
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        List<String> uNames=names.stream().map(n->n.toUpperCase()).collect(Collectors.toList());
        System.out.println(uNames);

        List<String> words = Arrays.asList("apple", "banana", "pear", "kiwi", "orange", "apple", "banana");
        List<String> uWords=words.stream().distinct().collect(Collectors.toList());
        System.out.println(uWords);

        List<String> strings = Arrays.asList("Java", "is", "fun", "to", "learn");
        String concateString=strings.stream().reduce("", (accumulator, element) -> accumulator + element);
        System.out.println(concateString);
    }
}
