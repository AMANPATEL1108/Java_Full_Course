package daySixtyThree;

import java.util.*;

public class SixtyThree_63 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] nodes = sc.nextLine().split(",");

        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        int[] a = {3, 2, 2, 3};
        int v = 3;
        System.out.println(removeElement(a, v));

        System.out.println(preorderTraversal(root));
    }

    public static TreeNode buildTree(String[] nodes) {
        if (nodes.length == 0 || nodes[0].equals("null")) return null;

        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;

        while (!queue.isEmpty() && i < nodes.length) {
            TreeNode current = queue.poll();

            if (i < nodes.length && !nodes[i].equals("null")) {
                current.left = new TreeNode(Integer.parseInt(nodes[i]));
                queue.add(current.left);
            }
            i++;

            if (i < nodes.length && !nodes[i].equals("null")) {
                current.right = new TreeNode(Integer.parseInt(nodes[i]));
                queue.add(current.right);
            }
            i++;
        }
        return root;
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preorderList = new ArrayList<>();
        preorderTravel(root, preorderList);
        return preorderList;
    }

    private static void preorderTravel(TreeNode root, List<Integer> preorderList) {
        if (root != null) {
            preorderList.add(root.val);
            if (root.left != null)
                preorderTravel(root.left, preorderList);
            if (root.right != null)
                preorderTravel(root.right, preorderList);
        }
    }

    public static int removeElement(int[] nums, int val) {
        int c = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[c] = nums[i];
                c++;
            }
        }
        return c;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
