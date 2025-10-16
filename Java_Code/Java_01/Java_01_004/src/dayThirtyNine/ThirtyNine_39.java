package dayThirtyNine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThirtyNine_39 {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "bat", "cat", "dog", "elephant");
        List<String> shorterThenFour = strings.stream().filter(n -> n.length() < 4).map(n -> n.toUpperCase()).toList();
        System.out.println(shorterThenFour);

        List<Integer> numbers = Arrays.asList(3, 5, 12, 18, 7, 4);
        int greterThen10Sum = numbers.stream().filter(n -> n < 10).reduce(0, (a, b) -> a + b);
        System.out.println(greterThen10Sum);

        List<Integer> numbers2 = Arrays.asList(2, 4, 5, 7, 8, 10);
        List<Integer> evenNumberUsingReduce = numbers2.stream().reduce(new ArrayList<>(), (acc, num) -> {
            if (num % 2 == 0) {
                acc.add(num);
            }
            return (ArrayList<Integer>) acc;
        }, (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        });

        System.out.println(evenNumberUsingReduce);
    }
}
