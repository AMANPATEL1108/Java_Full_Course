package thirty;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class User {
    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {
    }
}

public class Thirty_30 {

    public static void main(String[] args) {
        Optional<User> user = findUserById("123");
        System.out.println(getUserName(user));

        List<String> names = Arrays.asList("Alice", "Bob", null, "Charlie");
        System.out.println(getUpperCaseNames(names));

        Optional<Double> price = Optional.of(100.0);
        getDiscountedPrice(price).ifPresent(p -> System.out.println("Discounted price: " + p));

        printUserInfo(user);
        printUserInfo(Optional.empty());
    }

    public static Optional<User> findUserById(String id) {
        if ("123".equals(id)) {
            return Optional.of(new User("123", "Alice"));
        }
        return Optional.empty();
    }

    public static String getUserName(Optional<User> user) {
        return user.map(User::getName).orElse("Anonymous");
    }

    public static List<String> getUpperCaseNames(List<String> names) {
        return names.stream()
                .map(name -> Optional.ofNullable(name)
                        .map(String::toUpperCase)
                        .orElse("Anonymous"))
                .collect(Collectors.toList());
    }

    public static Optional<Double> getDiscountedPrice(Optional<Double> price) {
        return price.map(p -> p * 0.9);
    }

    public static void printUserInfo(Optional<User> user) {
        user.ifPresentOrElse(
                u -> System.out.println("ID: " + u.getId() + ", Name: " + u.getName()),
                () -> System.out.println("No user found")
        );
    }


}
