package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duqiang on 25/07/2017.
 */
public class _257BinaryTreePaths {
    /**
     * 257. Binary Tree Paths
     * Given a binary tree, return all root-to-leaf paths.

     For example, given the following binary tree:
          3
         / \
        9  20
      /  \
     15   7
     ["3->9->15", "3->9->7", "3->20]

     case :
           3
          / \
         9  20
       /  \
     15   7
     3->9->15
     3->9->7
     3->20
     ["3->9->15", "3->9->7", "3->20]

     time : O(n);
     space : O(n);
     * @param root
     * @return
     */
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        helper(res, root, "");
        return res;
    }

    // just remember how to PreVisit the tree and know how to know the leaf nodes
    public static void helper(List<String> res, TreeNode root, String path) {
        if (root.left == null && root.right == null) {
            res.add(path + root.val);
        }
        if (root.left != null) {
            helper(res, root.left, path + root.val + "->");
        }
        if (root.right != null) {
            helper(res, root.right, path + root.val + "->");
        }
    }
}
