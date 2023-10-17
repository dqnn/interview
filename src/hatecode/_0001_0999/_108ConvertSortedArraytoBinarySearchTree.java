package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ConvertSortedArraytoBinarySearchTree
 * Creator : professorX
 * Date : Sep, 2017
 * Description : TODO
 */
public class _108ConvertSortedArraytoBinarySearchTree {
    /**
     * 108. Convert Sorted Array to Binary Search Tree
     * Given an array where elements are sorted in ascending order, 
     * convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as 
a binary tree in which the depth of the two subtrees of every node never 
differ by more than 1.

Example:

Given the sorted array: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the 
following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5

     [1,2,3,4,5]

     time : O(n);
     space : O(lgn), the tree height is lgn since it is balanced
     * @param A
     * @return
     */
    public TreeNode sortedArrayToBST(int[] A) {
        if (A == null || A.length == 0) return null;
        return helper(A, 0, A.length - 1);
    }

    // preOrder visit the tree
    public TreeNode helper(int[] A, int l, int r) {  // space : O(logn);
        //here is the tricky part, we return null if left index bigger than right one
        if (l > r) return null;
        int m = (r - l) / 2 + l;
        TreeNode node = new TreeNode(A[m]);
        node.left = helper(A, l, m - 1);
        node.right = helper(A, m + 1, r);
        return node;
    }
}
