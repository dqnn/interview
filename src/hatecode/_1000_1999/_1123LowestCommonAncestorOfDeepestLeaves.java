package hatecode._1000_1999;

import hatecode._0001_0999.TreeNode;

public class _1123LowestCommonAncestorOfDeepestLeaves {
/*
1123. Lowest Common Ancestor of Deepest Leaves
same with 865. Smallest Subtree with all the Deepest Nodes
Given a rooted binary tree, return the lowest common ancestor of its 
deepest leaves.

Recall that:

The node of a binary tree is a leaf if and only if it has no children
The depth of the root of the tree is 0, 
and if the depth of a node is d, 
the depth of each of its children is d+1.
The lowest common ancestor of a set S of nodes is the node 
A with the largest depth such that every node in S is in 
the subtree with root A.
 

Example 1:

Input: root = [1,2,3]
Output: [1,2,3]
Explanation: 
The deepest leaves are the nodes with values 2 and 3.
The lowest common ancestor of these leaves is the node with value 1.
The answer returned is a TreeNode object (not an array) with serialization "[1,2,3]".
*/

    //thinking process: O(n)/O(H), worst space complexity: O(n)
    
    //given a tree, find the deepest nodes lowest common ancestor
    //same as 865, they are the same
    
    //compared to 865, we can find that 865 will have two nodes while here
    //we do not, but ask for deepest nodes, so we use recursive to find the 
    //deepest nodes.
    
    //the key is to understand recursive, we can one pass to find the deepest node 
    //with lca. 
    int deepest = 0;
    TreeNode lca;

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if (root == null) return root;
        
        helper(root, 0);
        return lca;
    }

    //need to understand this recursive function deeply
    private int helper(TreeNode node, int depth) {
        //why need to get deepest before exit function?
        /*
         *     0
         *    /  \
         *   1    2
         *         \
         *          3
         * 
         * the result is 3, if we return early, then deepest will be updated. 
         * null will be last level 
         */
        deepest = Math.max(deepest, depth);
        if (node == null) return depth;
        
        int left = helper(node.left, depth + 1);
        int right = helper(node.right, depth + 1);
        if (left == deepest && right == deepest) {
            lca = node;
        }
        //this is the key, the function return for node, the max depth
        return Math.max(left, right);
    }
}