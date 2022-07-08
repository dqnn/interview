package hatecode._1000_1999;

import hatecode._0001_0999.TreeNode;
import java.util.*;

public class _1382BalanceABinarySearchTree {
/*
1382. Balance a Binary Search Tree

Given the root of a binary search tree, return a balanced binary search tree with the same node values. If there is more than one answer, return any of them.

A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.
Input: root = [1,null,2,null,3,null,4,null,null]
Output: [2,1,3,null,null,null,4]
Explanation: This is not the only correct answer, [3,1,4,null,2] is also correct.

*/
    
    //thinking process: O(n)/O(h)
    
    /*
     * the problem is to say, given one binary search tree, 
     * it may not be balanced, you need to change so for every 
     * root node, its left and right sub tree has 1 diff
     */
    public TreeNode balanceBST(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        inorderVisit(root, list);
        return balance(list, 0, list.size() - 1);
    }
    
    private void inorderVisit(TreeNode root, List<TreeNode> list) {
        if (root == null) return;
        inorderVisit(root.left, list);
        list.add(root);
        inorderVisit(root.right, list);
    }
    
    private TreeNode balance(List<TreeNode> list, int l, int r) {
        if (l > r) return null;
        int m = l + (r-l)/2;
        TreeNode root = list.get(m);
        root.left = balance(list, l, m -1);
        root.right = balance(list, m + 1, r);
        return root;
    }
}