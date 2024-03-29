package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * Created by professorX on 25/07/2017.
 */
public class _094BinaryTreeInorderTraversal {
    /**
     * 94. Binary Tree Inorder Traversal
     * Given a binary tree, return the inorder traversal of its nodes' values.
     * Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
Follow up: Recursive solution is trivial, could you do it iteratively?
     * time : O(n)
     * space : O(n)
     * @param root
     * @return
     */

    public static List<Integer> inorderTraversal(TreeNode root) {
       List<Integer> res = new ArrayList<>();
       if (root == null) return res;
       helper(res, root);
       return res;
    }

    public static void helper(List<Integer> res, TreeNode root) {
        if (root == null) return;
        helper(res, root.left);
        res.add(root.val);
        helper(res, root.right);
    }

    // using stack to do inOrder visit, this has to be recited
    public static List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right; // because cur is considered as rootnode, so its right child
            // will be the next smallest one.
        }
        return res;
    }
}
