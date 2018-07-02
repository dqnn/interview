package leetcode;

import java.util.Stack;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : BinarySearchTreeIterator
 * Creator : duqiang
 * Date : Sep, 2017
 * Description : 173. Binary Search Tree Iterator
 * Implement an iterator over a binary search tree (BST). 
 * Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, 
where h is the height of the tree.
 */
public class BinarySearchTreeIterator {
    /**
     * 173. Binary Search Tree Iterator
     *
     * time : O(n)
     * @param root
     */

    private TreeNode cur;
    private Stack<TreeNode> stack;

    public BinarySearchTreeIterator(TreeNode root) {
        cur = root;
        stack = new Stack<>();
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if (!stack.isEmpty() || cur != null) return true;
        return false;
    }

    /** @return the next smallest number */
    // we use Stack to store all left nodes and we use current to store the current
    // node
    //
    public int next() {
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        cur = stack.pop();
        int val = cur.val;
        cur = cur.right; // set to right because
        return val;
    }
}
