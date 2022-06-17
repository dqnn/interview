package hatecode._0001_0999;

import java.util.Stack;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : FlattenBinaryTreetoLinkedList
 * Creator : professorX
 * Date : Oct, 2017
 * Description : 114. Flatten Binary Tree to Linked List
 */
public class _114FlattenBinaryTreetoLinkedList {
    /**
     * For example,
     Given

         1
        / \
       2   5
      / \   \
     3   4   6
     The flattened tree should look like:
     1
      \
      2
       \
       3
        \
        4
         \
         5
          \
          6

     time : O(n)
     space : O(n)


     * @param root
     */
    private TreeNode prev = null;
    // reverse of preOrder visit
    public void flatten(TreeNode root) {
        if (root == null) return;
        //first visit right child,because prev will have to save the previos visit, 
        //if we first visit left, then right side will come before left side
        flatten(root.right);
        // left child
        flatten(root.left);
        // vsit root node, so most right here should be null
        root.right = prev;
        root.left = null;
        // here we use this way to store the previous root of right child
        prev = root;
    }
    //this is top down, the tree grow from top down and first process the left child, then left child's child...
    public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        while(!s.isEmpty()) {
            TreeNode node = s.pop();
            // so we push right first into the stack, it will be at bottom of the stack
            // and then we push left, and if cut down its original right connection but right 
            //already in stack, so no worries
            // and then we peek the stack top which is left child into right and when next iteration,
            // we will pick left child and process its child as previous doing.
            if (null != node.right) {
                s.push(node.right);
            }
            if (null != node.left) {
                s.push(node.left);
            }
            // here is to indicate maybe already its leaf node
            if (!s.isEmpty()) {
                node.right = s.peek();
            }
            // cut the left child
            node.left = null;
        }
    }
}
