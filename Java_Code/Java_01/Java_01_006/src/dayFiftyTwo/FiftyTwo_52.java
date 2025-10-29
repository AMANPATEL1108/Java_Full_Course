package dayFiftyTwo;

import java.util.concurrent.ConcurrentHashMap;

public class FiftyTwo_52 {
    public static void main(String[] args) {
        // Create a ConcurrentHashMap with default concurrency level
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

        // Inserting elements
        map.put("apple", "fruit");
        map.put("carrot", "vegetable");

        // Concurrently updating the map
        map.putIfAbsent("apple", "tech");  // Will not update since "apple" already exists

        // Getting values
        System.out.println(map.get("apple")); // Output: fruit
        System.out.println(map.get("carrot")); // Output: vegetable

        // Using compute() method for atomic updates
        map.compute("apple", (key, value) -> value.toUpperCase());  // Updates value of "apple"
        System.out.println(map.get("apple")); // Output: FRUIT

        // Using replace() for atomic replacement
        map.replace("carrot", "root vegetable");
        System.out.println(map.get("carrot")); // Output: root vegetable
    }

}
