package hatecode._1000_1999;

import hatecode._0001_0999.TreeNode;

/*

1973. Count Nodes Equal to Sum of Descendants
 * Given the root of a binary tree, return the number of nodes where the value of the node is equal to the sum of the values of its descendants.

A descendant of a node x is any node that is on the path from node x to some leaf node. The sum is considered to be 0 if the node has no descendants.

 

Example 1:


Input: root = [10,3,4,2,1]
Output: 2
Explanation:
For the node with value 10: The sum of its descendants is 3+4+2+1 = 10.
For the node with value 3: The sum of its descendants is 2+1 = 3.
 */

 /*
  * interview friendly: O(n)/O(n)

  the problem is to say: given one binary tree, return how many nodes which sum of its sub nodes are equals;

     10
    / \
   3   4
  /  \
 2    1
 
 3 = 1 + 2 --> 1
 10 = 6 + 4 --> 2 

 so we return 2

 we use post order to solve the problem
  */
public class _1973CountNodesEqualToSumOfDescendants {
    
    int res = 0;
    public int equalToDescendants(TreeNode root) {
        if(root == null) return 0;
        
        helper(root);
        
        return res;
    }
    
    private int helper(TreeNode root) {
        if(root == null) return 0;
        
        int l = helper(root.left);
        int r = helper(root.right);
        if(l + r == root.val) {
            res++;
        }
        
        return l + r + root.val;
    }
}