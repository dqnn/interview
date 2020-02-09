package hatecode._1000_1999;

import java.util.*;

import hatecode.TreeNode;
public class _1008ConstructBinarySearchTreeFromPreorderTraversal {
/*
1008. Construct Binary Search Tree from Preorder Traversal
Return the root node of a binary search tree that matches the given preorder traversal.

(Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)

 

Example 1:

Input: [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]
*/

    
    //O(n)/O(n)
  //thinking process: given a array with distinct value--preorder visit, return binary search tree
    
  //so BST only has 1 rule, but this problem seems to have only 1 answer, so every tree problem can be solved 
  //by recursive or iterative, 
    int idx = 0;
    public TreeNode bstFromPreorder(int[] A) {
        return helper(A, Integer.MAX_VALUE);
    }
    
    private TreeNode helper(int[] A, int max) {
        if( idx == A.length || A[idx] > max) return null;
        
        TreeNode root = new TreeNode(A[idx++]);
        root.left = helper(A, root.val);
        root.right = helper(A, max);
        return root;
    }
    
    public TreeNode bstFromPreorder_iterative(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(preorder[0]);
        stack.push(root);
        for (int i = 1; i < preorder.length; i++) {
            TreeNode node = new TreeNode(preorder[i]);
            if (preorder[i] < stack.peek().val) {                
                stack.peek().left = node;                
            } else {
                TreeNode parent = stack.peek();
                while (!stack.isEmpty() && preorder[i] > stack.peek().val) {
                    parent = stack.pop();
                }
                parent.right = node;
            }
            stack.push(node);            
        }
        return root;
    }
}