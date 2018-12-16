package hatecode;

import java.util.Stack;

/**
 * Created by duqiang on 24/07/2017.
 */
public class ValidateBinarySearchTree {

    /**
     * 98. Validate Binary Search Tree
     * Given a binary tree, determine if it is a valid binary search tree (BST).

     Assume a BST is defined as follows:

     The left subtree of a node contains only nodes with keys less than the node's key.
     The right subtree of a node contains only nodes with keys greater than the node's key.
     Both the left and right subtrees must also be binary search trees.

     time : O(n)
     space : O(n)
     * @param root
     * @return
     */

    //thinking process: 
    
    // so we want to left and right compare to root node
    // we use min and max object to each unit tree, when we visit the tree, if left node, we need to compare it bigger 
    //than root then return false, and for right, smaller than root, then recursive on these two nodes, 
    // for left, max = root.val, min inherited, for right, min = root.val and max inherited 
    public static boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }

    public static boolean helper(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        if (min != null && root.val <= min) return false;
        if (max != null && root.val >= max) return false;

        return helper(root.left, min, root.val) && helper(root.right, root.val, max);
    }
    
    
    //another recursive solutions
    public boolean isValidBST2(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null) return true;
        if (root.val >= maxVal || root.val <= minVal) return false;
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }
    
    // this is using stack to in-order visit the tree
    public boolean isValidBST3(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        //this templates is pre-visit the tree, so 
        while (root != null || !stack.isEmpty()) {
            //we go to most left
           while (root != null) {
              stack.push(root);
              root = root.left;
           }
           //get most left child
           root = stack.pop();
           // here is to check previous node in stack by pre-visit should be smaller 
           if(pre != null && root.val <= pre.val) return false;
           pre = root;
           //ask root to be right, In order visit the tree, then we can compare in next loop
           root = root.right;
        }
        return true;
     }
}
