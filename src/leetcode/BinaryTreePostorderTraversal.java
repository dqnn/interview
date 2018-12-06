package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by duqiang on 28/07/2017.
 */
public class BinaryTreePostorderTraversal {
    /**
     * 145. Binary Tree Postorder Traversal
     * Given a binary tree, return the postorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [3,2,1]
Follow up: Recursive solution is trivial, could you do it iteratively?
     *
     * time : O(n);
     * space : O(n);
     * @param root
     * @return
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        helper(res, root);
        return res;
    }
    public static void helper(List<Integer> res, TreeNode root) {
        if (root == null) return;
        helper(res, root.left);
        helper(res, root.right);
        res.add(root.val);
    }

    // this way should be remembered
    public static List<Integer> postorderTraversal2(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.addFirst(cur.val);
            if (cur.left != null) stack.push(cur.left);
            if (cur.right != null) stack.push(cur.right);
        }
        return res;
    }
}
