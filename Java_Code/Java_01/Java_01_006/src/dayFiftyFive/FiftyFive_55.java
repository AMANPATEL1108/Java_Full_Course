package dayFiftyFive;

import java.util.Arrays;

public class FiftyFive_55 {
    public static void main(String[] args) {
        int[] a = {2, 0, 2, 1, 1, 0};
        sortColors(a);
        String s="the sky is blue";
        System.out.println(reverseWords(s));
    }

    public static String reverseWords(String s) {
        String[] result = s.trim().split("\\s+");
        StringBuilder b = new StringBuilder();

        for (int i = result.length - 1; i >= 0; i--) {
            b.append(result[i]);
            if (i != 0) {
                b.append(" ");
            }
        }

        return b.toString();
    }


    public static void sortColors(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (a[i] < a[j]) {
                    int t = a[j];
                    a[j] = a[i];
                    a[i] = t;
                }
            }
        }

        System.out.println(Arrays.toString(a)       );
    }
}
