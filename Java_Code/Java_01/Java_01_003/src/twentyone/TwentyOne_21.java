package twentyone;

import java.util.Stack;

public class TwentyOne_21 {
    public static void main(String[] args) {
//        int[] a = {8,-1,6,-7,-4,5,-4,7,-6};
//        System.out.println(maximumSum(a));
        String s = "aba";
        System.out.println(validPalindrome(s));
    }

    public static boolean validPalindrome(String s) {
        return checkPalindrome(s, 0, s.length() - 1, false);
    }

    public static boolean checkPalindrome(String s, int left, int right, boolean skipped) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                if (skipped) return false;

                return checkPalindrome(s, left + 1, right, true) ||
                        checkPalindrome(s, left, right - 1, true);
            }
            left++;
            right--;
        }
        return true;
    }

    public static int maximumSum(int[] arr) {
        int n = arr.length;
        int maxSum = arr[0];
        int noDelete = arr[0];
        int oneDelete = 0;

        for (int i = 1; i < n; i++) {
            oneDelete = Math.max(noDelete, oneDelete + arr[i]);
            noDelete = Math.max(arr[i], noDelete + arr[i]);
            maxSum = Math.max(maxSum, Math.max(noDelete, oneDelete));
        }

        return maxSum;
    }
}
