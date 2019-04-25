package hatecode;

import java.util.stream.*;
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

public class BinaryTreeCameras {
/*
968. Binary Tree Cameras
Given a binary tree, we install cameras on the nodes of the tree. 

Each camera at a node can monitor its parent, itself, and its immediate children.

Calculate the minimum number of cameras needed to monitor all nodes of the tree.
Input: [0,0,null,0,0]
Output: 1
*/
    
    //so each node has 3 states, 0 not covered, 1 covered, 2 has camerca 
    private int res = 0;
    public int minCameraCover(TreeNode root) {
       if (root == null) return 0;
       
       return (helper(root) == 0 ? 1 : 0) + res;
    }
   
    private int helper(TreeNode node) {
        //means we are on next depth of leaf node
        if (node == null) return 1;
        if (node.left == null && node.right == null) return 0;
        
        int left = helper(node.left), right = helper(node.right);
        if (left == 0 || right == 0) { 
           res++;
           return 2;
       }

       return left == 2 || right == 2 ? 1 : 0;
    }
    
    //O(n)/O(h)
     public int minCameraCover_DP(TreeNode root) {
        int[] ans = solve(root);
        return Math.min(ans[1], ans[2]);
    }

    // 0: Strict Substree; All nodes below this are covered, but not this one
    // 1: Normal Substree; All nodes below and incl this are covered - no camera
    // 2: Placed camera; All nodes below this are covered, plus camera here
    public int[] solve(TreeNode node) {
        if (node == null)
            return new int[]{0, 0, 99999};

        int[] L = solve(node.left);
        int[] R = solve(node.right);
        int mL12 = Math.min(L[1], L[2]);
        int mR12 = Math.min(R[1], R[2]);

        int d0 = L[1] + R[1];
        int d1 = Math.min(L[2] + mR12, R[2] + mL12);
        int d2 = 1 + Math.min(L[0], mL12) + Math.min(R[0], mR12);
        return new int[]{d0, d1, d2};
    }
    
    //Greedy solution, but high quality answer, O(n)/O(h)
    int ans;
    Set<TreeNode> covered;
    public int minCameraCover_Greedy(TreeNode root) {
        ans = 0;
        covered = new HashSet<>();
        covered.add(null);

        dfs(root, null);
        return ans;
    }

    public void dfs(TreeNode node, TreeNode par) {
        if (node != null) {
            dfs(node.left, node);
            dfs(node.right, node);

            if (par == null && !covered.contains(node) ||
                    !covered.contains(node.left) ||
                    !covered.contains(node.right)) {
                ans++;
                covered.add(node);
                covered.add(par);
                covered.add(node.left);
                covered.add(node.right);
            }
        }
    }
    
    
    
    private void helper(Map<TreeNode, Set<TreeNode>> map, TreeNode root) {
        if (root == null || map == null) return;
        if (root.left != null) {
            map.computeIfAbsent(root, v->new HashSet<>()).add(root.left);
            map.computeIfAbsent(root.left, v->new HashSet<>()).add(root);
        }
        if (root.right != null) {
            map.computeIfAbsent(root, v->new HashSet<>()).add(root.right);
            map.computeIfAbsent(root.right, v->new HashSet<>()).add(root);
        }
        helper(map, root.left);
        helper(map, root.right);
    }
    //this is not a workable solution for above  Example 2:
    public int minCameraCover2(TreeNode root) {
        if (root == null) return 0;
        
        Map<TreeNode, Set<TreeNode>> map = new HashMap<>();
        helper(map, root);
        Map<TreeNode, Set<TreeNode>> sorted = map.entrySet().stream().sorted((a, b)->(b.getValue().size() - a.getValue().size())).collect(
                Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2));
        int res = 0;
        while(!sorted.isEmpty()) {
            res++;
            Map.Entry<TreeNode, Set<TreeNode>> entry = sorted.entrySet().stream().findFirst().get();
            for(TreeNode e : entry.getValue()) {
                sorted.remove(e);
            }
            sorted.remove(entry.getKey());
        }
        
        return res;
    }
}