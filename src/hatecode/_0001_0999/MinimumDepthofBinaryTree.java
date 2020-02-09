package hatecode._0001_0999;

import java.util.*;

/**
 * Created by duqiang on 28/07/2017.
 */
public class MinimumDepthofBinaryTree {
    /**
     * 111. Minimum Depth of Binary Tree
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its minimum depth = 2.

     time : O(n);
     space : O(n);

     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //only 1 branch which means this node is not leaf node, we need to find another branch
        if (root.left == null || root.right == null) {
            return Math.max(minDepth(root.left), minDepth(root.right)) + 1;
        }
        //leaf node, 
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
    
    // another solution
    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = minDepth2(root.left);
        int right = minDepth2(root.right);
        //leaf node: we get the minimal of left and right tree  + 1
        //if not leaf node, 
        return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
    }
    //here is more easy to understand version
    public int minDepth3(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;

            int left = minDepth(root.left);
            int right = minDepth(root.right);
            
            if(left == 0) {
                return 1 + right;
            } else if(right == 0) {
                return 1 + left;
            } else {
                return 1 + Math.min(left, right);
            }
        }
    
    //bfs version of tree traverse
    public int minDepth_BSF(TreeNode root) {
        if (root == null) return 0;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int min = 0;
        while(!q.isEmpty()) {
            min++;
            int size = q.size();
            while(size-- > 0) {
                TreeNode node = q.poll();
                if (node.left == null && node.right == null) return min;
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
        }
        
        return -1;
    }
}
