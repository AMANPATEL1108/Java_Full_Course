package twentySeven;


import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@FunctionalInterface
interface Calculator{
    int operation(int a,int b);
}

class  Printer{
    public void printMessage(String message){
        System.out.println(message);
    }
}

//#Functional Interface
public class TwentySeven_27 {
    public static void main(String[] args) {
        Calculator mul=(a,b)->a*b;
        System.out.println(mul.operation(6,4));

        List<Integer> numbers = Arrays.asList(15, 22, 7, 18, 25, 33, 10);
        Predicate<Integer> lesTen= n->n>10;
         List<Integer> result=numbers.stream().filter(lesTen).collect(Collectors.toList());
        System.out.println(result);
        Printer printer = new Printer();
        Consumer<String> consumer = printer::printMessage;
        consumer.accept("Hello, World!");

        List<String> names = Arrays.asList("John", "Alice", "Bob", "Charlie", "Dave");
        Function<String, String> toUpperCaseFunction = s -> s.toUpperCase();
        Set<String> upname=names.stream().map(toUpperCaseFunction).collect(Collectors.toSet());
        System.out.println(upname);

        List<Integer> numbers2 = Arrays.asList(1, 2, 3, 4, 5);
        BinaryOperator<Integer> binaryOperator=(a,b)->a+b;
        int sum=numbers2.stream().reduce(0,binaryOperator);
        System.out.println(sum);
    }
}
