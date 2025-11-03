package dayFiftySeven;

public class FiftySeven_57 {
    public static void main(String[] args) {
        int[] a = {2, 3, 2};
        System.out.println(rob(a));
        int[][] b = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(minPathSum(b));

    }

    public static int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] cur = new int[m];
        cur[0] = grid[0][0];

        for (int i = 1; i < m; i++)
            cur[i] = cur[i - 1] + grid[i][0];

        for (int j = 1; j < n; j++) {
            cur[0] += grid[0][j];
            for (int i = 1; i < m; i++)
                cur[i] = Math.min(cur[i - 1], cur[i]) + grid[i][j];
        }

        return cur[m - 1];
    }

    public static int rob(int[] nums) {
        if (nums.length == 0)
            return 0;
        else if (nums.length == 1)
            return nums[0];
        else if (nums.length == 2)
            return Math.max(nums[0], nums[1]);
        int[] nums1 = nums.clone();
        int max1 = 0;
        nums1[1] = Math.max(nums1[0], nums1[1]);
        for (int i = 2; i < nums1.length - 1; i++) {
            nums1[i] = Math.max(nums1[i] + nums1[i - 2], nums1[i - 1]);
        }
        max1 = nums1[nums1.length - 2];
        int max2 = 0;
        nums[2] = Math.max(nums[1], nums[2]);
        for (int i = 3; i < nums.length; i++) {
            nums[i] = Math.max(nums[i] + nums[i - 2], nums[i - 1]);
        }
        max2 = nums[nums.length - 1];
        return Math.max(max1, max2);
    }
}
