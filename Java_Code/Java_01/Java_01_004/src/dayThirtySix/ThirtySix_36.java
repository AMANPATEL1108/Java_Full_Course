package dayThirtySix;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ThirtySix_36 {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cherry");
        String s=words.stream().map(n->n.toUpperCase()).reduce("", (accumulator, element) -> accumulator +element+",");
        System.out.println(s);

        List<String> names = Arrays.asList("John", "Alice", "Bob", "Christopher", "Emma");
        List<String> morethenfiveLength=names.stream().filter(n->n.length()>=5).sorted().collect(Collectors.toList());
        System.out.println(morethenfiveLength);

        List<Item> items = Arrays.asList(
                new Item("Laptop", 1000),
                new Item("Phone", 500),
                new Item("Tablet", 300)
        );

        int total=items.stream().mapToInt(item -> (int) (item.getPrice() * 0.9)).sum();
        System.out.println(total);

    }
}


class Item {
    String name;
    double price;

    Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }
}
