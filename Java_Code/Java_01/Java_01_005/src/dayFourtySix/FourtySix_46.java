package dayFourtySix;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FourtySix_46 {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println(coinChange(coins, amount));
        char[][] a = {
                {'1', '1', '1', '1', '0'},
                {
                        '1', '1', '0', '1', '0'
                },
                {
                        '1', '1', '0', '0', '0'
                },
                {
                        '0', '0', '0', '0', '0'
                }
        };

        System.out.println(numIslands(a));

    }

    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) dp[i] = amount + 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static int[] reversedSortedArr(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] < a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        return a;
    }

    public static int numIslands(char[][] grid) {
        int n = grid.length, m = grid[0].length, islands = 0;
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    islands++;
                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[]{i, j});
                    grid[i][j] = '0';
                    while (!q.isEmpty()) {
                        int[] p = q.poll();
                        int x = p[0], y = p[1];
                        for (int[] d : dir) {
                            int nx = x + d[0], ny = y + d[1];
                            if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] == '1') {
                                grid[nx][ny] = '0';
                                q.add(new int[]{nx, ny});
                            }
                        }
                    }
                }
            }
        }
        return islands;
    }
}
