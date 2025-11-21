package daySeventyFive;

import java.util.*;

public class SeventyFive_75 {
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(0,
                new TreeNode(0),
                new TreeNode(0)
        );

        TreeNode t2 = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4),
                        null
                ),
                new TreeNode(3,
                        new TreeNode(2),
                        new TreeNode(4,
                                null,
                                new TreeNode(4)
                        )
                )
        );

        System.out.println(minCameraCover(t1));
        System.out.println(findDuplicateSubtrees(t2).stream().map(n -> String.valueOf(n.val)).toList());

    }

    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, List<TreeNode>> map = new HashMap<>();
        traverse(root, map);
        List<TreeNode> result = new ArrayList<>();
        for (Map.Entry<String, List<TreeNode>> entry : map.entrySet()) {
            List<TreeNode> nodes = entry.getValue();
            if (nodes.size() > 1) {
                result.add(nodes.get(0));
            }
        }
        return result;
    }

    public static String traverse(TreeNode node, Map<String, List<TreeNode>> map) {
        if (node == null) {
            return "#";
        }
        String left = traverse(node.left, map);
        String right = traverse(node.right, map);
        String serialized = node.val + "," + left + "," + right;
        List<TreeNode> nodes = map.getOrDefault(serialized, new ArrayList<>());
        nodes.add(node);
        map.put(serialized, nodes);
        return serialized;

    }

    static int n = 0;

    public static int minCameraCover(TreeNode root) {
        return solve(root) == -1 ? n + 1 : n;
    }

    public static int solve(TreeNode root) {
        if (root == null) return 0;

        int l = solve(root.left);
        int r = solve(root.right);

        if (l == -1 || r == -1) {
            n++;
            return 1;
        }

        if (l == 1 || r == 1) {
            return 0;
        }

        return -1;
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
