package daySeventyOne;

import java.util.*;

public class SeventyOne_71 {
    public static void main(String[] args) {

        Integer[] arr = {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};

        TreeNode root = buildTree(arr);
        TreeNode target = findNode(root, 5);
        int k = 2;

        SeventyOne_71 obj = new SeventyOne_71();
        System.out.println(obj.distanceK(root, target, k));
        int[][] a2 = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int n2 = 4, k2 = 2;
        System.out.println(networkDelayTime(a2, n2, k2));
    }

    public static int networkDelayTime(int[][] times, int n, int k) {
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(graph[i], Integer.MAX_VALUE);
        for (int[] rows : times) graph[rows[0] - 1][rows[1] - 1] = rows[2];

        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[k - 1] = 0;

        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            int v = minIndex(distance, visited);
            if (v == -1) continue;
            visited[v] = true;
            for (int j = 0; j < n; j++) {
                if (graph[v][j] != Integer.MAX_VALUE) {
                    int newDist = graph[v][j] + distance[v];
                    if (newDist < distance[j]) distance[j] = newDist;
                }
            }
        }
        int result = 0;
        for (int dist : distance) {
            if (dist == Integer.MAX_VALUE) return -1;
            result = Math.max(result, dist);
        }
        return result;
    }

    private static int minIndex(int[] distance, boolean[] visited) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int i = 0; i < distance.length; i++) {
            if (!visited[i] && distance[i] < min) {
                min = distance[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    List<Integer> result = new ArrayList<>();
    Map<TreeNode, TreeNode> parentmap = new HashMap<>();
    Set<TreeNode> visited = new HashSet<>();

    public static TreeNode buildTree(Integer[] arr) {
        if (arr.length == 0 || arr[0] == null) return null;

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int i = 1;
        while (!q.isEmpty() && i < arr.length) {
            TreeNode node = q.poll();

            if (arr[i] != null) {
                node.left = new TreeNode(arr[i]);
                q.offer(node.left);
            }
            i++;

            if (i < arr.length && arr[i] != null) {
                node.right = new TreeNode(arr[i]);
                q.offer(node.right);
            }
            i++;
        }
        return root;
    }

    public static TreeNode findNode(TreeNode root, int value) {
        if (root == null) return null;
        if (root.val == value) return root;

        TreeNode left = findNode(root.left, value);
        if (left != null) return left;

        return findNode(root.right, value);
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        buildmap(root);
        FindTarget(target, 0, k);
        return result;
    }

    public void buildmap(TreeNode node) {
        if (node == null) return;

        if (node.left != null) {
            parentmap.put(node.left, node);
            buildmap(node.left);
        }
        if (node.right != null) {
            parentmap.put(node.right, node);
            buildmap(node.right);
        }
    }

    public void FindTarget(TreeNode node, int distance, int k) {
        if (node == null || visited.contains(node)) {
            return;
        }
        visited.add(node);

        if (distance == k) {
            result.add(node.val);
            return;
        }
        FindTarget(node.left, distance + 1, k);
        FindTarget(node.right, distance + 1, k);
        FindTarget(parentmap.get(node), distance + 1, k);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}