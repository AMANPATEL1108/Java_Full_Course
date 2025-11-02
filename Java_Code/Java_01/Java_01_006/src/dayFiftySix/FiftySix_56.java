package dayFiftySix;

public class FiftySix_56 {
    public static void main(String[] args) {

        int[] x = {1, 5, 11, 5};
        System.out.println(canPartition(x));
        int[][] a = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(uniquePathsWithObstacles(a));

    }

    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % 2 != 0) return false;
        sum = sum / 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        for (int i : nums) {
            for (int j = sum; j >= i; j--) {
                dp[j] = dp[j] | dp[j - i];
            }
        }
        return dp[sum];
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1) return 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    if (obstacleGrid[i][j] == 1 || (i != 0 && obstacleGrid[i - 1][0] == 0) || (j != 0 && obstacleGrid[i][j - 1] == 0)) {
                        obstacleGrid[i][j] = 0;
                    } else {
                        obstacleGrid[i][j] = 1;
                    }
                } else {
                    if (obstacleGrid[i][j] == 1) {
                        obstacleGrid[i][j] = 0;

                    } else {
                        obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                    }
                }
            }
        }
        return obstacleGrid[m - 1][n - 1];
    }
}
