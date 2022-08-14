package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LargestBSTSubtree
 * Creator : professorX
 * Date : July, 2018
 * Description : 333. Largest BST Subtree
 */
public class _333LargestBSTSubtree {
    /**
     Note:
A subtree must include all of its descendants.
Here's an example:

    10
    / \
   5  15
  / \   \ 
 1   8   7
The Largest BST Subtree in this case is the highlighted one. 1 5 8
The return value is the subtree's size, which is 3.

Hint:

You can recursively use algorithm similar to 98. Validate Binary Search Tree at each node of the tree, which will result in O(nlogn) time complexity.
Follow up:
Can you figure out ways to solve it with O(n) time complexity?

     null
     The Largest BST Subtree in this case is the highlighted one.
     The return value is the subtree's size, which is 3.

     1, postorder
     2, BST
     3, decide BST

     1 : 1,1,1
     8 : 1,8,8
     5 : 3,1,8
     7 : 1,7,7
     15 : -1,0,0

     time : O(n) because we go through the whole tree
     space : O(n)

     */

    int res = 0;

    // key points:
    //a. post order, mainly help to collect child info then parent info
    //b. BST criteria, how can we use lower and upper to achieve this.
    //each BST tree, all left should be smaller than root, using a recursive solution 
    //here sounds good
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) return 0;
        helper(root);
        return res;
    }

    private SearchNode helper(TreeNode root) {
        if (root == null) {
            // this means the node will not be qualified, this node will be ignored, so 
            //for recursive function that requires returned value, we should 
            // consider return the node which will not be considered
            return new SearchNode(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        SearchNode left = helper(root.left);
        SearchNode right = helper(root.right);
        // means the left child, right child has no more other nodes 
        if (left.size == -1 || right.size == -1 
                // this means root smaller than left child max or right child lower which not satisfy BST
                || root.val <= left.upper || root.val >= right.lower) {
            return new SearchNode(-1, 0, 0);
        }
        // size is the root nodes #
        int size = left.size + right.size + 1;
        // we update the nodes result
        res = Math.max(size, res);
        // return the node stands for the root node. 
        return new SearchNode(size, Math.min(left.lower, root.val), Math.max(right.upper, root.val));
    }

    class SearchNode {
        int size;
        int lower;
        int upper;

        SearchNode(int size, int lower, int upper) {
            // nodes num
            this.size = size;
            // in this tree, the min value of each node's val
            this.lower = lower;
            // the max value in the tree
            this.upper = upper;
        }
    }
}
