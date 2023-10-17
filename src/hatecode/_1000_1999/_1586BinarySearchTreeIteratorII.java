package hatecode._1000_1999;

import java.util.*;

import hatecode._0001_0999.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class _1586BinarySearchTreeIteratorII {
/*

1586. Binary Search Tree Iterator II

Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):

BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor. The pointer should be initialized to a non-existent number smaller than any element in the BST.
boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
int next() Moves the pointer to the right, then returns the number at the pointer.
boolean hasPrev() Returns true if there exists a number in the traversal to the left of the pointer, otherwise returns false.
int prev() Moves the pointer to the left, then returns the number at the pointer.
Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the smallest element in the BST.

You may assume that next() and prev() calls will always be valid. That is, there will be at least a next/previous number in the in-order traversal when next()/prev() is called.

 

Example 1:



Input
["BSTIterator", "next", "next", "prev", "next", "hasNext", "next", "next", "next", "hasNext", "hasPrev", "prev", "prev"]
[[[7, 3, 15, null, null, 9, 20]], [null], [null], [null], [null], [null], [null], [null], [null], [null], [null], [null], [null]]
Output
[null, 3, 7, 3, 7, true, 9, 15, 20, false, true, 15, 9]

Explanation
// The underlined element is where the pointer currently is.
BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]); // state is   [3, 7, 9, 15, 20]
bSTIterator.next(); // state becomes [3, 7, 9, 15, 20], return 3
bSTIterator.next(); // state becomes [3, 7, 9, 15, 20], return 7
bSTIterator.prev(); // state becomes [3, 7, 9, 15, 20], return 3
bSTIterator.next(); // state becomes [3, 7, 9, 15, 20], return 7
bSTIterator.hasNext(); // return true
bSTIterator.next(); // state becomes [3, 7, 9, 15, 20], return 9
bSTIterator.next(); // state becomes [3, 7, 9, 15, 20], return 15
bSTIterator.next(); // state becomes [3, 7, 9, 15, 20], return 20
bSTIterator.hasNext(); // return false
bSTIterator.hasPrev(); // return true
bSTIterator.prev(); // state becomes [3, 7, 9, 15, 20], return 15
bSTIterator.prev(); // state becomes [3, 7, 9, 15, 20], return 9
*/


    /*
     * thinking process: 
     * 
     * the problem is to say: given one BST, you need to implement 
     * 
     * int next() return next element in BST tree value
     * hasNext()  return true if the tree has next value
     * int prev()   return prev value, note [1,3,5,6], if now you at 5, prev should return 3, if you call prev it will return 1, if you can next it will return 3
     * hasPrev()  return whether it has previous value
     * 
     * 
     * we use two stacks and one visited set, 
     * stack next is to store all next elements for next()
     * prev stack is to store all elements for prev()
     * 
     * but we need visited set to store the elements which we already stored all its left branches into next stack, for example 
     * 
     *          7
     *        /   \
     *       3     15
     *            /  \
     *           9   20
     * 
     * say "next", "next", "prev", "next", "next", "next", "next", "prev", "prev"]
     *       3       7       3       7       9       15      20      15      9
     * 
     * 
     * 1st next()
     * prev   next
     *  3      7
     * 
     * 2st next()
     * prev   next
     *  7      9
     *  3      15
     * 
     * prev(), we need visited set here to prevent to add 15 left branch to next stack
     * 
     * prev  next
     * 3      7
     *        9
     *        15
     *
     * another note: when we call prev(), we return the value 2nd top, not the topest one because top one is just the one returned 
     */


    Stack<TreeNode> next = new Stack<>(), prev = new Stack<>();
    Set<TreeNode> visited = new HashSet<>();
    public _1586BinarySearchTreeIteratorII(TreeNode root) {
        pushLeft(root);
    }
    
    public boolean hasNext() {
        return !next.isEmpty();
    }
    
    public int next() {
        if(!hasNext()) return -1;
        
        TreeNode n = next.pop();
        if(!visited.contains(n) && n.right != null) {
            pushLeft(n.right);
        }
        
        visited.add(n);
        prev.push(n);
        return n.val;
    }
    
    public boolean hasPrev() {
        return prev.size() > 1;
    }
    
    public int prev() {
        if(!hasPrev()) return -1;
        TreeNode p = prev.pop();
        next.push(p);
        
        return prev.peek().val;
    }
    
    private void pushLeft(TreeNode root) {
        while(root != null) {
            next.push(root);
            root = root.left;
        }
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * boolean param_1 = obj.hasNext();
 * int param_2 = obj.next();
 * boolean param_3 = obj.hasPrev();
 * int param_4 = obj.prev();
 */