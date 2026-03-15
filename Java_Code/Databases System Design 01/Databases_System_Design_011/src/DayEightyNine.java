public class DayEightyNine {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4),
                        new TreeNode(5)),
                new TreeNode(3,
                        new TreeNode(6),
                        null));
        System.out.println(countNodes(node));
    }

    public static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
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