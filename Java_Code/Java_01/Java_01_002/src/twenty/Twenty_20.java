package twenty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Twenty_20 {
    public static void main(String[] args) {
//        String s = "qrsvbspk";
//        System.out.println(lengthOfLongestSubstring(s));
        int[] a = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println(findDisappearedNumbers(a));
    }

    public static List<Integer> findDisappearedNumbers(int[] arr) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            int index = Math.abs(arr[i]) - 1;
            if (arr[index] > 0) {
                arr[index] = -arr[index];
            }
        }
        System.out.println(Arrays.toString(arr));

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                result.add(i + 1);
            }
            System.out.println(result);
        }


        return result;
    }

    public static int[] sorted(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    int t = arr[i];
                    arr[i] = arr[j];
                    arr[j] = t;
                }
            }
        }
        return arr;
    }

    public static int lengthOfLongestSubstring(String s) {
        HashSet<Character> hashSet = new HashSet();
        String str = "";
        String finalstr = "";
        for (int i = 0; i < s.length(); i++) {

            if (s.length() == 1) {
                return s.length();
            }
            for (int j = i; j < s.length(); j++) {
                if (!hashSet.contains(s.charAt(j))) {
                    hashSet.add(s.charAt(j));
                    str += s.charAt(j);
                } else {
                    if (str.length() > finalstr.length()) {
                        finalstr = str;
                        str = "";
                        hashSet.clear();
                    } else {
                        str = "";
                        hashSet.clear();
                    }
                    break;
                }

            }

        }


        return finalstr.length();
    }


}
