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
public class _1740FindDistanceinABinaryTree {
/*
1740. Find Distance in a Binary Tree
    Given the root of a binary tree and two integers p and q, return 
    the distance between the nodes of value p and value q in the tree.

The distance between two nodes is the number of edges on the path from 
one to the other.

 

Example 1:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 0
Output: 3
*/
    /*
     * thinking process: O(n)/O(n)
     * 
     * the problem is to look for distance for specific two nodes in the tree
     * 
     * turn the tree to a graph, then convert the problem to a graph problem with BFS
     */
    public int findDistance(TreeNode root, int src, int dst) {
        if (root == null) return -1;
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        buildMap(root, map);
        
        //System.out.println(map);
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.offer(src);
        visited.add(src);
        int step = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            while(size-- > 0) {
                int e = q.poll();
                if (e == dst) return step;
                
                for(int v : map.get(e)) {
                    if (visited.contains(v)) continue;
                    visited.add(v);
                    q.offer(v);
                }
            }
            step++;
        }
        
        return -1;
    }
    
    private void buildMap(TreeNode root, Map<Integer, List<Integer>> map) {
        if (root == null) return;
        if (root.left != null) {
            map.computeIfAbsent(root.val, v->new ArrayList<>()).add(root.left.val);
            map.computeIfAbsent(root.left.val, v->new ArrayList<>()).add(root.val);
        }
        
        if (root.right != null) {
            map.computeIfAbsent(root.val, v->new ArrayList<>()).add(root.right.val);
            map.computeIfAbsent(root.right.val, v->new ArrayList<>()).add(root.val);
        }
        
        buildMap(root.left, map);
        buildMap(root.right, map);
    }
    
    class Solution_DFS {
    
    private int result;

    public int findDistance(TreeNode root, int p, int q) {
        if (p == q) return 0;
        result = -1;
        dfs(root, p, q);
        return result;
    }
    
	/**
		The return value means the distance from root node to EITHER p OR q. If
		neither p nor q are reachable from the root, return -1.
		
		It is either p or q but not both, because if the root node can reach both 
		p and q, it is a common ancestor of p and q and the answer should already 
		be available.
	**/
    private int dfs(TreeNode root, int p, int q) {
        if (root == null) return -1;
        
        int left = dfs(root.left, p, q);
        int right = dfs(root.right, p, q);
        
        if (root.val == p || root.val == q) {
			// root is p or q, but none of p or q is a descendent of root.
			// The distance from root to one of p and q is 0 in this case.
            if (left < 0 && right < 0) {
                return 0;
            }
			
			// root is p or q, and root is also the LCA of p and q.
            result = 1 + (left >= 0 ? left : right);
            return -1;
        }
        
		// root is neither p nor q, but it is the LCA of p and q.
        if (left >= 0 && right >= 0) {
            result = left + right + 2;
            return -1;
        }
        
        if (left >= 0) {
            return left + 1;
        }
        
        if (right >= 0) {
            return right + 1;
        }
        
        return -1;
    }
}
}