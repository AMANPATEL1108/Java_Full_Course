package twelve;

import java.util.*;

public class Twelve_12 {
    public static void main(String[] args) {
        countFreq();
        checkDuplicacte();
        multipleArray();
        firstNonRepeating();
        pair();
    }

    public static void countFreq() {
        HashMap<Character, Integer> hashMap = new HashMap();
        String s = "aabbbcddd";
        for (int i = 0; i < s.length(); i++) {
            if (hashMap.containsKey(s.charAt(i))) {
                int d = hashMap.get(s.charAt(i));
                d = d + 1;
                hashMap.put(s.charAt(i), d);
            } else {
                hashMap.put(s.charAt(i), 1);

            }
        }
        System.out.println(hashMap);
    }

    public static void checkDuplicacte() {
        int[] a = {1, 2, 3, 4, 5};
        int k = 0;
        HashSet hashSet = new HashSet();
        for (int i = 0; i < a.length; i++) {
            hashSet.add(a[i]);
            k++;
        }
        if (hashSet.size() == k) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }

    public static void multipleArray() {
        HashMap<Integer, String> hashMap1 = new HashMap<>();
        hashMap1.put(1, "a");
        hashMap1.put(2, "b");
        hashMap1.put(3, "c");

        HashMap<Integer, String> hashMap2 = new HashMap<>();
        hashMap2.put(2, "x");
        hashMap2.put(3, "y");
        hashMap2.put(4, "z");


        for (Map.Entry<Integer, String> entry : hashMap2.entrySet()) {
            hashMap1.merge(entry.getKey(), entry.getValue(), String::concat);
        }
        System.out.println(hashMap1);

    }

    public static void firstNonRepeating() {
        HashMap<Character, Integer> freqMap = new HashMap<>();
        String input = "aabcc";

        for (char ch : input.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        char a = 0;
        for (char ch : input.toCharArray()) {
            if (freqMap.get(ch) == 1) {
                a=ch;
                break;
            }
        }

        if (a!=0){
            System.out.println("Repeated is "+a);
        }else{
            System.out.println("Non Repeated");
        }


    }

    public static void pair(){
        int[] nums={1, 2, 3, 4, 3, 5};
        int target=6;
        Set<Integer> seen = new HashSet<>();
        Set<List<Integer>> result = new HashSet<>();

        for (int num : nums) {
            int complement = target - num;

            if (seen.contains(complement)) {
                List<Integer> pair = Arrays.asList(Math.min(num, complement), Math.max(num, complement));
                result.add(pair);
            }

            seen.add(num);
        }

        System.out.println(result);
    }

}
