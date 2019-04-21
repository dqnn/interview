package hatecode;
import java.util.*;

public class MinimumAbsoluteDifferenceInBST {
/*
530. Minimum Absolute Difference in BST
783. Minimum Distance Between BST Nodes
Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.

Example:

Input:

   1
    \
     3
    /
   2

Output:
1
*/
    //so given a binary tree, find min absolute difference between any nodes within this tree
    //since it is BST, the min difference existed in parent-child, so we want to go through left, then right, use a global variable
    
    //O(n)/O(n), 
    int min = Integer.MAX_VALUE;
    Integer pre = null;
    public int getMinimumDifference_DFS(TreeNode root) {
        if (root == null) return 0;
        getMinimumDifference(root.left);
        
        if (pre != null) {
            min = Math.min(root.val - pre, min);
        }
        pre = root.val;
        getMinimumDifference(root.right);
        
        return min;
    }
    //O(n)/O(n),  the way how we visit the tree is classic, first we visit most left node, 
    //then pop its node, and get its parent and right, 
    public int getMinimumDifference(TreeNode node) {
         int diff = Integer.MAX_VALUE;
        if(node == null) return diff;
        
        Stack<TreeNode> stk = new Stack<>();
        TreeNode prev = null;
        while(!stk.isEmpty() || node != null){
            while(node != null){
                stk.push(node);
                node = node.left;
            }
            TreeNode popped = stk.pop();
            if(prev != null){
               diff = Math.min(diff, popped.val-prev.val); 
            }
            prev = popped;
            node = popped.right;
        }
        return diff;
    }
    
    int minDiff = Integer.MAX_VALUE;
    TreeNode prev;
    
    public int getMinimumDifference_InOrder(TreeNode root) {
        inorder(root);
        return minDiff;
    }
    
    public void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        if (prev != null) minDiff = Math.min(minDiff, root.val - prev.val);
        prev = root;
        inorder(root.right);
    }

    
}