package hatecode._0001_0999;

import java.util.Arrays;
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
    
    
    //another recursive solutions, interview friendly 
    public boolean isValidBST2(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null) return true;
        if (root.val >= maxVal || root.val <= minVal) return false;
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }
    
    // this is using stack to in-order visit the tree,
    //interview friendly
    public boolean isValidBST3(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        //this templates is in-order visit the tree,
        //to remember this templates
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
    
    //this implementation is wrong because we only check each sub bianry tree but not whole tree
    public boolean isValidBST_Wrong(TreeNode root) {
        if (root == null) return true;
        return helper(root);
     }
     
     private boolean helper(TreeNode node) {
         if (node == null) return true;
         
         if(node.left!=null && node.left.val >= node.val
           ||node.right!=null && node.right.val <= node.val) return false;
         return helper(node.left) || helper(node.right);
     }
     
     //there was follow up in Google interviewe that if 
     //in BST tree, there was one more error linked parent-child, correct them, and if you have to find it
     //thinking process:
     //1. we can use this problem's thinking, to validate each subtree by min and max,if we find one just return
     //2. if not BST tree, we just try to use Set<TreeNode> to record the nodes we have visited, so when we 
     //pre_order visit the tree, note: each node will only be visited once, so we add the visited nodes into the set
     //but if we find the ones we have visited before, then we find it
     public static int[] findEdge(TreeNode root) {
         if (root == null) return new int[] {};
         return helper_findEdge(null, root, Integer.MIN_VALUE, Integer.MAX_VALUE);
     }
     
     private static int[] helper_findEdge(TreeNode parent, TreeNode cur, int min, int max) {
         if (cur == null) return new int[] {};
         if (cur.val < min || cur.val > max) return new int[] {parent.val, cur.val};
         int[] l = helper_findEdge(cur, cur.left, min, cur.val);
         int[] r = helper_findEdge(cur, cur.right, cur.val, max);
         if (l !=null && l.length > 0) return l;
         else return r;
     }

     
     
/*
     4
    / \
   2   5
  /\  /
 1   3
 we should return [3,5]
 */
     public static void main(String[] args) {
         TreeNode root = new TreeNode(4);
         root.left = new TreeNode(2);
         root.right = new TreeNode(5);
         root.left.left = new TreeNode(1);
         root.left.right = new TreeNode(3);
         
         root.right.left = root.left.right;
         System.out.println(Arrays.toString(findEdge(root)));
     }
     
     //another is to correct the BST tree, we can simply to use helper_findEdge to
     private TreeNode helper_Correct_BST(TreeNode root, int left, int right) {
         if(root == null) return null;
         if(root.val <= left || root.val >= right) return null;
         root.left = helper_Correct_BST(root.left, left, root.val);
         root.right = helper_Correct_BST(root.right, root.val, right);
         return root;
     }

     
}
