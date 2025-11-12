package daySixtySix;

public class SixtySix_66 {
    public static void main(String[] args) {
        int[] a1 = {9, 4, 2, 10, 7, 8, 8, 1, 9};
        System.out.println(maxTurbulenceSize(a1));
        int[] a2 = {5, 3, 4,};
        System.out.println(stoneGame(a2));
    }

    public static boolean stoneGame(int[] piles) {
        int i = 0, j = piles.length - 1;
        int alice = 0, bob = 0;
        boolean turn = true;
        while (i <= j) {
            int opt = Math.max(piles[i], piles[j]);
            if (piles[i] >= piles[j]) i++;
            else j--;
            if (turn) alice += opt;
            else bob += opt;
        }
        return alice > bob;
    }

    public static int maxTurbulenceSize(int[] arr) {
        int i = 0;
        int j = 1;
        int res = 1;
        String prev = "";

        while (j < arr.length) {
            if (arr[j - 1] < arr[j] && prev != "<") {
                res = Math.max(res, j - i + 1);
                prev = "<";
                j++;
            } else if (arr[j - 1] > arr[j] && prev != ">") {
                res = Math.max(res, j - i + 1);
                prev = ">";
                j++;
            } else {
                if (arr[j] == arr[j - 1]) {
                    j = j + 1;
                }
                i = j - 1;
                prev = "";
            }

        }
        return res;
    }
}
