package hatecode._1000_1999;

import java.util.*;

import hatecode._0001_0999.TreeNode;
public class _1161MaximumLevelSumOfABinaryTree {
/*
1161. Maximum Level Sum of a Binary Tree
Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.

Return the smallest level X such that the sum of all the values of nodes at level X is maximal.
*/
    
    //thinking process: O(n)/O(n)
    //level browse, typical question
    public int maxLevelSum(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int max = root.val;
        int maxLevel = 0, cur = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            int sum = 0;
            cur++;
            while(size -- > 0) {
                TreeNode node = q.poll();
                sum += node.val;
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            if(sum > max) {
                maxLevel = cur;
                max = sum;
            }
        }
        return maxLevel;
    }
    
    public int maxLevelSum_DFS(TreeNode root) {
        int[] map = new int[(int)Math.pow(10, 4)+1];
        recur(root, map, 1);
        int maxLevel = 1;
        for(int i = 0; i < map.length; i++) if(map[i] > map[maxLevel]) maxLevel = i;
        return maxLevel;
    }
    
    public void recur(TreeNode root, int[] map, int level) {
        if(root == null) return;
        map[level] += root.val;
        recur(root.right, map, level + 1);
        recur(root.left, map, level + 1);
    }
}