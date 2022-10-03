package hatecode._0001_0999;

import java.util.*;
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class _993CousinsInBinaryTree {
/*
 * 993. Cousins in Binary Tree
In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.

Two nodes of a binary tree are cousins if they have the same depth, but have different parents.

We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.

Return true if and only if the nodes corresponding to the values x and y are cousins.
Input: root = [1,2,3,4], x = 4, y = 3
Output: false
*/
    //O(n)/O(n)
    //thinking process:
    //given a tree and two integer value, return true if these two integer 
    //in same level but different parent node
    
    //so we do recursive on the tree, if two nodes already find we just return.
    TreeNode px= null, py = null;
    int dx=Integer.MAX_VALUE, dy = Integer.MIN_VALUE;
    public boolean isCousins(TreeNode root, int x, int y) {
        if(root == null || x == y) return true;
        helper(root, x, y, null, 0);
        
        return px != py && dx == dy;
    }
    
    private void helper(TreeNode root, int x, int y, TreeNode p, int depth) {
        //return faster when we two nodes all found
        if(root == null || px != null && py !=null) return;
        
        if(root.val == x) {
            px = p;
            dx = depth;
        }
        if(root.val == y) {
            py = p;
            dy = depth;
        }
        helper(root.left, x, y, root, depth+1);
        helper(root.right, x, y, root, depth+1);
    }
    
    //another solutioin is BFS, and we do bianry search on each level, but we need to know each parent node
    public boolean isCousins_BFS(TreeNode root, int x, int y) {
        Queue<TreeNode> q = new LinkedList<>();
        
        q.offer(root);
        
        while(!q.isEmpty()) {
            boolean isX = false, isY = false;
            int size = q.size();
            while(size-- > 0) {
                TreeNode e = q.poll();
                if (e.left != null && e.right != null) {
                    if (e.left.val == x && e.right.val == y || 
                    e.left.val == y && e.right.val == x) return false;
                }
                
                
                if (e.val == x) isX = true;
                if (e.val == y) isY = true;
                
                if (isX && isY) return true;
                if (e.left != null) q.offer(e.left);
                if (e.right != null) q.offer(e.right);
            }
            isX = false;
            isY = false;
        }
        
        return false;
    }
}