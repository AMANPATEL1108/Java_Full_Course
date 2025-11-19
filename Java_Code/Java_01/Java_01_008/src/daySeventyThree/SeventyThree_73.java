package daySeventyThree;

public class SeventyThree_73 {

    public static void main(String[] args) {
        int[][] a1 = {{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}};
        int[][] a2 = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(isBipartite(a1));
        System.out.println(findCircleNum(a2));
    }

    public static int findCircleNum(int[][] isConnected) {
        if (isConnected.length == 0) return 0;
        int ans = 0;
        int[] arr = new int[isConnected.length];
        for (int i = 0; i < isConnected.length; i++) {
            if (arr[i] == 0) {
                dfs(isConnected, i, arr);
                ans++;
            }
        }
        return ans;
    }

    public static void dfs(int[][] conn, int a, int[] arr) {
        arr[a] = 1;
        for (int i = 0; i < conn.length; i++) {
            if (i == a) continue;
            if (conn[a][i] == 1 && arr[i] == 0) {
                dfs(conn, i, arr);
            }
        }
    }

    public static boolean checkDFS(int start, int[][] edges, int[] color, int markColor) {
        color[start] = markColor;

        for (int edge : edges[start]) {
            if (color[edge] == -1) {
                if (!checkDFS(edge, edges, color, 1 - markColor)) return false;
            } else if (color[edge] == markColor) {
                return false;
            }
        }
        return true;
    }

    public static boolean isBipartite(int[][] edges) {
        int n = edges.length;
        int[] color = new int[n];

        for (int i = 0; i < n; i++) color[i] = -1;
        for (int i = 0; i < n; i++) {
            if (color[i] == -1) {
                if (!checkDFS(i, edges, color, 0)) return false;
            }
        }
        return true;
    }
}
