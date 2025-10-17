package dayfourty;

import java.util.Arrays;
import java.util.List;

public class Fourty_40 {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("The", "quick", "brown", "fox");
        String s=words.stream().reduce("",(a,b)->a+" "+b);
        System.out.println(s);

        List<String> words2 = Arrays.asList("Apple", "Banana", "Avocado", "Orange", "Apricot", "Grapes");
        List<String> filteerA=words2.stream().filter(f->!f.startsWith("A")).toList();
        System.out.println(filteerA);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> multhree=numbers.stream().map(n->n*3).toList();
        System.out.println(multhree);


    }
}
