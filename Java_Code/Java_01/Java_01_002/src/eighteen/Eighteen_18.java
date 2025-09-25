package eighteen;

import java.util.HashMap;
import java.util.HashSet;

public class Eighteen_18 {
    public static void main(String[] args) {
//        int[] arr = {1,2,3,4,4,5};
//        System.out.println(checkPrimeFrequency(arr));

        String num = "1210";
        System.out.println(digitCount(num));

    }

    public static boolean digitCount(String num) {
            HashMap<Integer, Integer> hashMap = new HashMap<>();

            for (int i = 0; i < num.length(); i++) {
                int el = num.charAt(i) - '0';
                hashMap.put(el, hashMap.getOrDefault(el, 0) + 1);
            }

            for (int i = 0; i < num.length(); i++) {
                int expectedCount = num.charAt(i) - '0';
                int actualCount = hashMap.getOrDefault(i, 0);
                if (expectedCount != actualCount) {
                    return false;
                }
            }

            return true;
    }


    public static boolean checkPrimeFrequency(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            if (hashMap.containsKey(num)) {
                hashMap.put(num, hashMap.get(num) + 1);
            } else {
                hashMap.put(num, 1);
            }

        }

        for (Integer i : hashMap.keySet()) {
            if (hashMap.get(i) > 1) {
                boolean isPrime = checkPrintNumber(hashMap.get(i));
                if (isPrime) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean checkPrintNumber(Integer num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
