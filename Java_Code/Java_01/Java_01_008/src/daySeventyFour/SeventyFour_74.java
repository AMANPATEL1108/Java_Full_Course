package daySeventyFour;

import java.util.*;

public class SeventyFour_74 {
    public static void main(String[] args) {
        String startGene = "AACCGGTT", endGene = "AACCGGTA";
        String[] bank = {"AACCGGTA"};
        System.out.println(minMutation(startGene, endGene, bank));
        int[][] a2 = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        System.out.println(longestIncreasingPath(a2));
    }

    public static int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        for (int d[] : dp) Arrays.fill(d, -1);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == -1) dfs(matrix, dp, m, n, i, j, -1);
            }
        }
        int max = Integer.MIN_VALUE;
        for (int[] d : dp) {
            for (int i : d) max = Math.max(i, max);
        }
        return max;
    }

    public static int dfs(int[][] matrix, int[][] dp, int m, int n, int i, int j, int parent) {
        if (i >= m || j >= n || i < 0 || j < 0 || matrix[i][j] <= parent) return 0;
        parent = matrix[i][j];
        if (dp[i][j] != -1) return dp[i][j];
        int left = dfs(matrix, dp, m, n, i, j - 1, parent);
        int right = dfs(matrix, dp, m, n, i, j + 1, parent);
        int bottom = dfs(matrix, dp, m, n, i + 1, j, parent);
        int top = dfs(matrix, dp, m, n, i - 1, j, parent);
        dp[i][j] = 1 + Math.max(Math.max(left, right), Math.max(top, bottom));
        return dp[i][j];
    }

    public static int minMutation(String startGene, String endGene, String[] bank) {
        Set<String> set = new HashSet<>(Arrays.asList(bank));

        if (!set.contains(endGene)) {
            return -1;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(startGene);

        Set<String> visited = new HashSet<>();
        visited.add(startGene);

        int mutations = 0;
        char[] chars = {'A', 'C', 'G', 'T'};
        int geneLength = startGene.length();

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                String currGene = queue.poll();

                if (currGene.equals(endGene)) {
                    return mutations;
                }

                char[] currChars = currGene.toCharArray();

                for (int j = 0; j < geneLength; j++) {
                    char ogChar = currChars[j];

                    for (char c : chars) {
                        if (c == ogChar) {
                            continue;
                        }

                        currChars[j] = c;

                        String next = new String(currChars);

                        if (set.contains(next) && !visited.contains(next)) {
                            visited.add(next);
                            queue.offer(next);
                        }
                    }
                    currChars[j] = ogChar;
                }
            }
            mutations++;
        }
        return -1;
    }
}
