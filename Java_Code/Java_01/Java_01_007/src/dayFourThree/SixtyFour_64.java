package dayFourThree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SixtyFour_64 {
    public static void main(String[] args) {
        TreeNode t11 = new TreeNode(
                3,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7))
        );
        System.out.println(zigzagLevelOrder(t11));
        TreeNode t21 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        int t22 = 5;
        System.out.println(pathSum(t21, t22));

    }

    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, 0, new ArrayList<>(), res, targetSum);
        return res;
    }

    public static void dfs(TreeNode root, int sum, List<Integer> path, List<List<Integer>> list, int targetSum) {
        if (root == null) return;
        path.add(root.val);
        sum += root.val;
        if (root.left == null && root.right == null) {
            if (sum == targetSum) {
                list.add(new ArrayList<>(path));
            }
        } else {
            dfs(root.left, sum, path, list, targetSum);
            dfs(root.right, sum, path, list, targetSum);
        }
        path.remove(path.size() - 1);
        sum -= root.val;


    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> currLevel = new ArrayList<>();

        if (root == null) return ans;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        boolean leftToRight = true;
        while (!q.isEmpty()) {

            int size = q.size();
            currLevel = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();

                if (leftToRight) {
                    currLevel.add(curr.val);
                } else {
                    currLevel.add(0, curr.val);
                }

                if (curr.left != null) q.add(curr.left);
                if (curr.right != null) q.add(curr.right);
            }

            ans.add(currLevel);
            leftToRight = !leftToRight;
        }
        return ans;
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}