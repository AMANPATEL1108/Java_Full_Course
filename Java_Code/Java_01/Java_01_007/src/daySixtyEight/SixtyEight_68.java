package daySixtyEight;

import java.util.HashMap;

public class SixtyEight_68 {
    public static void main(String[] args) {
        int[][] a1 = {{0, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 1, 1, 1}
        };
        System.out.println(countSquares(a1));
        int[] a2 = {3, 4, 2};
        System.out.println(deleteAndEarn(a2));

    }

    public static int countSquares(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int cnt = 0;
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            dp[i][0] = matrix[i][0];
        }
        for (int j = 0; j < m; j++) {
            dp[0][j] = matrix[0][j];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == 1) {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],
                            Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cnt += dp[i][j];
            }
        }

        return cnt;
    }

    public static int deleteAndEarn(int[] nums) {
        var numToCount = new HashMap<Integer, Integer>();
        var min = Integer.MAX_VALUE;
        var max = Integer.MIN_VALUE;
        for (var num : nums) {
            numToCount.compute(num, (k, v) -> v == null ? 1 : ++v);
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        var prevIncEarn = 0;
        var prevExcEarn = 0;
        for (var i = min; i <= max; i++) {
            var incEarn = prevExcEarn + i * numToCount.getOrDefault(i, 0);
            var excEarn = Math.max(prevIncEarn, prevExcEarn);
            prevIncEarn = incEarn;
            prevExcEarn = excEarn;
        }
        return Math.max(prevIncEarn, prevExcEarn);
    }

}
