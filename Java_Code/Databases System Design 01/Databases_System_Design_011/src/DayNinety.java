import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class DayNinety {
    public static void main(String[] args) {
        TreeNode rootNode = new TreeNode(1);
        rootNode.right = new TreeNode(2);
        rootNode.right.left = new TreeNode(3);
        System.out.println(postorderTraversal(rootNode));
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> ans = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) return ans;

        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            ans.addFirst(cur.val);
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
        return ans;
    }
}
