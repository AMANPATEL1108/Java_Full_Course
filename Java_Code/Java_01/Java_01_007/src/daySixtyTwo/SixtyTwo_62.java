package daySixtyTwo;

import java.util.Arrays;

public class SixtyTwo_62 {
    public static void main(String[] args) {
        String a = "abc";
        int a1 = 2;
        System.out.println(minDeletion(a, a1));
        int b = 123;
        System.out.println(reverse(b));
    }

    public static int minDeletion(String s, int k) {
        int[] abc = new int[26];

        for (char ch : s.toCharArray()) {
            abc[ch - 'a']++;
        }
        Arrays.sort(abc);
        int i = 25 - k;
        int cnt = 0;
        while (abc[i] > 0) {
            cnt += abc[i];
            i--;
        }
        return cnt;

    }

    public static int reverse(int x) {
        long reverse = 0;
        int temp;

        while (x != 0) {
            temp = x % 10;
            reverse = reverse * 10 + temp;
            x = x / 10;

            if (reverse > Integer.MAX_VALUE || reverse < Integer.MIN_VALUE) {
                return 0;
            }
        }

        return (int) reverse;
    }

}
