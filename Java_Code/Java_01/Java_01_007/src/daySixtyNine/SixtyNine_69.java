package daySixtyNine;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SixtyNine_69 {
    public static void main(String[] args) {
        int[] a1 = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(a1));
        int[][] a2 = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        System.out.println(orangesRotting(a2));
    }

    public static int orangesRotting(int[][] grid) {
        Queue<pair> q = new LinkedList<>();
        int count = 0;
        int Cresult = 0;
        int Time = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2)
                    q.add(new pair(i, j, 0));
                if (grid[i][j] == 1) count++;
            }
        }
        while (!q.isEmpty()) {
            pair p = q.poll();
            int i = p.row;
            int j = p.col;
            int T = p.time;
            if (j > 0 && grid[i][j - 1] == 1) {
                grid[i][j - 1] = 2;
                q.add(new pair(i, j - 1, T + 1));
                Cresult++;
                Time = T + 1;
            }
            if (i > 0 && grid[i - 1][j] == 1) {
                grid[i - 1][j] = 2;
                q.add(new pair(i - 1, j, T + 1));
                Cresult++;
                Time = T + 1;
            }
            if (i < grid.length - 1 && grid[i + 1][j] == 1) {
                grid[i + 1][j] = 2;
                q.add(new pair(i + 1, j, T + 1));
                Cresult++;
                Time = T + 1;

            }
            if (j < grid[0].length - 1 && grid[i][j + 1] == 1) {
                grid[i][j + 1] = 2;
                q.add(new pair(i, j + 1, T + 1));
                Cresult++;
                Time = T + 1;

            }

        }
        return Cresult == count ? Time : -1;
    }

    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int maxLength = Arrays.stream(dp).max().orElse(0);
        return maxLength;
    }
}

class pair {
    int row;
    int col;
    int time;

    pair(int row, int col, int time) {
        this.row = row;
        this.col = col;
        this.time = time;
    }
}