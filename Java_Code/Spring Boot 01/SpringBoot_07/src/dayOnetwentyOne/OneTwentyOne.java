package dayOnetwentyOne;

import java.util.LinkedList;
import java.util.Queue;

public class OneTwentyOne {
    public static void main(String[] args) {
        int[] a = {-10, -3, 0, 5, 9};
        TreeNode root = sortedArrayToBST(a);
        printInOrder(root);
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;

        TreeNode root = new TreeNode(0);

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<int[]> rangeQueue = new LinkedList<>();

        nodeQueue.add(root);
        rangeQueue.add(new int[]{0, nums.length - 1});

        while (!nodeQueue.isEmpty()) {
            TreeNode current = nodeQueue.poll();
            int[] range = rangeQueue.poll();

            int left = range[0];
            int right = range[1];
            int mid = (left + right) / 2;

            current.val = nums[mid];

            if (left <= mid - 1) {
                current.left = new TreeNode(0);
                nodeQueue.add(current.left);
                rangeQueue.add(new int[]{left, mid - 1});
            }

            if (mid + 1 <= right) {
                current.right = new TreeNode(0);
                nodeQueue.add(current.right);
                rangeQueue.add(new int[]{mid + 1, right});
            }
        }

        return root;
    }

    private static void printInOrder(TreeNode root) {
        if (root == null) return;
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
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