package hatecode;

import java.util.*;
public class _971FlipBinaryTreeToMatchPreorderTraversal {
/*
971. Flip Binary Tree To Match Preorder Traversal
Given a binary tree with N nodes, each node has a different value from {1, ..., N}.

A node in this binary tree can be flipped by swapping the left child and the right child of that node.

Consider the sequence of N values reported by a preorder traversal starting from the root.  Call such a sequence of N values the voyage of the tree.

(Recall that a preorder traversal of a node means we report the current node's value, then preorder-traverse the left child, then preorder-traverse the right child.)

Our goal is to flip the least number of nodes in the tree so that the voyage of the tree matches the voyage we are given.

If we can do so, then return a list of the values of all nodes flipped.  You may return the answer in any order.

If we cannot do so, then return the list [-1].

Input: root = [1,2], voyage = [2,1]
Output: [-1]

Input: root = [1,2,3], voyage = [1,3,2]
Output: [1]
*/
    
  //thinking process：
  //the problem is to say: given a binary tree and an array, the array represents
  //the pre-order travels sequence, return an array which each element means if switch
  //its left and right child, will match the sequence in voyage
  
    //so we want to consider each node as a new tree, the root node
    //
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        List<Integer> res = new ArrayList<>();
        //if we cannot find unmatch, just return from deep dfs
        if (!dfs(root, voyage, new int[]{0}, res)) {
            res = new ArrayList<>();
            res.add(-1);
        }
        return res;
    }

    private boolean dfs(TreeNode node, int[] v, int[] i, List<Integer> res) {
        if (node == null) return true;
        if (node.val != v[i[0]++]) return false;
        if (node.left != null && node.left.val != v[i[0]]) {
            res.add(node.val);
            return dfs(node.right, v, i, res) && dfs(node.left, v, i, res);
        }
        return dfs(node.left, v, i, res) && dfs(node.right, v, i, res);
    }
    
    //iterative solution, interview friendly. 
    //since pre-order visit, see comments among the code 
    public List<Integer> flipMatchVoyage_Iterative(TreeNode root, int[] voyage) {
        List<Integer> res = new ArrayList<>();
        if(root == null || voyage == null || root.left == null && root.right == null || voyage.length < 1) return res;
        
        int i = 0;
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        while (s.size() > 0) {
            TreeNode node = s.pop();
            //common that one node is null, like child of a leaf
            if (node == null) continue;
            //impossible to be the same as voyage, i++ can move to next line
            if (node.val != voyage[i++]) return Arrays.asList(-1);
            //if current = its right child value means we need switch, note after i++,
            //here voyage[i] actually means its left
            
            //we also have to keep node.right != null because node.right = null is common
            //like [1,2,3],[1,3,2], when node = 2 or 3
            if (node.right != null && node.right.val == voyage[i]) {
                //if left is null, we do not need to do anything, like 
                //[1, null, 2], [1, 2], the pre-order is the same, but we should do nothing
                if (node.left != null) res.add(node.val);
                //since we switched its right and left here,and we use Stack, so 
                //we push left first
                s.push(node.left);
                s.push(node.right);
            } else {//we do not need switch, just push right first
                s.push(node.right);
                s.push(node.left);
            }
        }
        return res;
        
    }
}