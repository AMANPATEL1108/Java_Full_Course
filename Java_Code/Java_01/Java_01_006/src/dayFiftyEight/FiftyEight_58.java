package dayFiftyEight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FiftyEight_58 {
    public static void main(String[] args) {
        String s="abpcplea";
        List<String> a=Arrays.asList("ale","apple","monkey","plea");
        String s1="abccba";

        System.out.println(findLongestWord(s,a));
        System.out.println(breakPalindrome(s1));
    }

    public static String breakPalindrome(String palindrome) {
        int n = palindrome.length();

        if (n == 1) {
            return "";
        }

        StringBuilder sb = new StringBuilder(palindrome);
        for (int i = 0; i < n / 2; i++) {
            if (sb.charAt(i) != 'a') {
                sb.deleteCharAt(i);
                sb.insert(i, "a");
                return sb.toString();
            }
        }

        sb.deleteCharAt(n - 1);
        sb.insert(n - 1, "b");
        return sb.toString();


    }

    public static String findLongestWord(String s, List<String> dictionary) {
        String str = "";
        int temp = 99999999, index = 0;
        for (int i = 0; i < dictionary.size(); i++) {
            String word = dictionary.get(i);
            List<String> list = new ArrayList<>(Arrays.asList(s.split("")));
            List<String> list1 = new ArrayList(Arrays.asList(word.split("")));
            index = 0;
            for (int j = 0; j < list.size() && index < list1.size(); j++) {
                if ((list.get(j)).equals(list1.get(index)))
                    index++;
            }

            if (index == list1.size()) {
                if (word.length() > str.length() ||
                        (word.length() == str.length() && word.compareTo(str) < 0)) {
                    str = word;
                }
            }

        }
        return str;

    }
}
