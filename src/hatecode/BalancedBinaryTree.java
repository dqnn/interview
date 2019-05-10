package hatecode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : BalancedBinaryTree
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : TODO
 */
public class BalancedBinaryTree {

    /**
     * 110. Balanced Binary Tree
     * Given a binary tree, determine if it is height-balanced.

     For this problem, a height-balanced binary tree is defined as a binary tree
     in which the depth of the two subtrees of every node never differ by more than 1.
         1  -- 3
        / \
       2   3  -- 1
      / \
     4   5  -- 1

         1
        / \
       2   3  2 --> 3  3 --> 1
      / \
       * 
     4   5  -- 2
          \
          9  -- 1

     time : O(n);
     space : O(n);
     * @param root
     * @return
     */

    public boolean isBalanced(TreeNode root) {
        //edge case
        if (null == root) return true;
        
        return helper(root) != -1;
    }
    //return faster than previous version, and return node depth for each node
    private int helper(TreeNode node) {
        if (node == null) return 0;
        
        int l = helper(node.left);
        if (l == -1) return -1;
        
        int r = helper(node.right);
        if (r == -1) return -1;
        
        if (Math.abs(l - r) > 1) return -1;
        
        return Math.max(l, r) + 1;
    }
    
    // this is using stack and recursive to compute the results
    public boolean isBalanced2(TreeNode root) {
        //edge case
        if (null == root) {
            return true;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (Math.abs(getH(node.left) - getH(node.right)) > 1) {
                return false;
            }
            if (null != node.left) {
                queue.offer(node.left);
            }
            if (null != node.right) {
                queue.offer(node.right);
            }
        }
        
        return true;
    }
    
    private int getH(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(getH(node.left), getH(node.right)) + 1;
    }
}
