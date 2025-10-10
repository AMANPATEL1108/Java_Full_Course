package dayThirtyThree;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ThirtyThree_33 {
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> oddNumbers = list1.stream().filter(n -> n % 2 != 0).collect(Collectors.toList());
        System.out.println(oddNumbers);

        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squareofList = list2.stream().map(n -> n * n).collect(Collectors.toList());
        System.out.println(squareofList);

        List<String> list3 = Arrays.asList("apple", "banana", "cherry");
        int totalLength = list3.stream().mapToInt(String::length).reduce(0, (sum, length) -> sum + length);
        System.out.println(totalLength);

        List<Integer> list4 = Arrays.asList(5, 3, 9, 1, 7);
        int max = list4.stream().max(Integer::compare).orElseThrow(() -> new IllegalArgumentException("List is Empty"));
        System.out.println(max);

        List<String> list5 = Arrays.asList("Hello", " ", "World", "!");
        String fullstr = list5.stream().reduce("", (accumulator, element) -> accumulator + element);
        System.out.println(fullstr);
    }
}
