package thirteen;

import java.util.LinkedHashMap;

//1. What is the main difference between TreeMap and LinkedHashMap?
//Discuss their differences in terms of key ordering, internal structure, and performance.
//->that both is extend a Map collcextiona nd  in this Tree map provide sorted data and  LinkedHashMap is lru order showing data
//2. How does a TreeMap maintain the order of its keys?
//Explain the internal structure used by TreeMap and how it ensures the keys are sorted.
//-> LRU Order that
//3. Predict the output of the following code snippet:
//TreeMap<Integer, String> map = new TreeMap<>();
//map.put(10, "Apple");
//map.put(5, "Banana");
//map.put(8, "Cherry");
//System.out.println(map);
//->{5,"Banana"},{8,"cherry},{10,"Apple"}
//. If you construct a LinkedHashMap with accessOrder = true, how will the order of keys change after multiple access operations?
//Write an example demonstrating how the order of keys in LinkedHashMap changes based on access.
//->after this is true:accessOrder  that is insertion order maintain
//Explain how TreeMap handles duplicate keys.
//What happens when you insert a new key-value pair with an existing key into a TreeMap?
//->replace a value of that
//What is the time complexity of put(), get(), and remove() operations in a LinkedHashMap and TreeMap?
//Compare the performance of put(), get(), and remove() in both maps and explain the reasons behind it
//->O(1).
//How would you use TreeMap to find all entries whose keys are between two specific values (e.g., between 10 and 20)?
//findallKey method threw
//Implement a simple LRU (Least Recently Used) Cache using LinkedHashMap with access order enabled (accessOrder = true).
//
public class Thirteen_13 {
    public static void main(String[] args) {
        LinkedHashMap<Integer, String> map = new LinkedHashMap<>(16, 0.75f, true);

        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");

        map.get(1);

        System.out.println("LinkedHashMap after access: " + map);
    }
}
