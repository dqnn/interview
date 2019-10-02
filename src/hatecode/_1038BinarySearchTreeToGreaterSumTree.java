package hatecode;

import java.util.*;
public class _1038BinarySearchTreeToGreaterSumTree {
    /*
    1038. Binary Search Tree to Greater Sum Tree
    Given the root of a binary search tree with distinct values, modify it so that every node has a new value equal to the sum of the values of the original tree that are greater than or equal to node.val.

As a reminder, a binary search tree is a tree that satisfies these constraints:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees
Input: [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
    */
    //same as 538. Convert BST to Greater Tree
    //thinking process:
    //given a bianry search tree, to construct a new tree, like node.val =6, find a sum value x = sum(nodes which value >=6), the example is 6+7+8 = 21
    
    //this is excellent solution, we use a variable pre to store the right tree results
    //
    int pre = 0;
    public TreeNode bstToGst(TreeNode root) {
        if (root.right != null) bstToGst(root.right);
        pre = root.val = pre + root.val;
        if (root.left != null) bstToGst(root.left);
        return root;
    }
    
    public TreeNode bstToGst_Iterative (TreeNode root) {
        int sum = 0;
        TreeNode cur = root;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.right;
            }
            cur = stack.pop();
            cur.val += sum;
            sum = cur.val;
            cur = cur.left;
        }
        return root;
    }
    
    public TreeNode convertBST_Moris(TreeNode root) {
        TreeNode cur = root;
        int sum = 0;
        while (cur != null) {
            if (cur.right != null) { // traverse right subtree.
                TreeNode leftMost = cur.right;
                while (leftMost.left != null && leftMost.left != cur) { // locate the left-most node of cur's right subtree.
                    leftMost = leftMost.left;
                }
                if (leftMost.left == null) { // never visit the left-most node yet.
                    leftMost.left = cur; // construct a way back to cur.
                    cur = cur.right; // explore right.
                }else { // visited leftMost already, which implies now on way back.
                    leftMost.left = null; // cut off the fabricated link.
                    sum += cur.val; // update sum.
                    cur.val = sum; // update node value.
                    cur = cur.left; // continue on way back.
                }
            }else { // no right child: 1) cur is the right-most of unvisited nodes; 2) must traverse left.
                sum += cur.val; // update sum.
                cur.val = sum; // update node value.
                cur = cur.left; // continue on way back.
            }
        }
        return root;
    }
    
}