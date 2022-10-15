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
public class _366FindLeaveOfBinaryTree {
/*
366. Find Leaves of Binary Tree
Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

 

Example:

Input: [1,2,3,4,5]
  
          1
         / \
        2   3
       / \     
      4   5    

Output: [[4,5,3],[2],[1]]
*/
    /*
     * thinking process: O(n)/O(n)
     * 
     * the problem is to say: given one BT, each time we will remove the leaves,
     * return the leaves as list of list.
     * 
     * we define height as distance to leaves, leaves as 0, null nodes as -1.
     * 
     * use a map to store map<height, List of nodes>, we use post order to visit the tree
     */
    
    public List<List<Integer>> findLeaves_interview(TreeNode root) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int rootHeight = helper(root, map);
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i<=rootHeight; i++) {
            res.add(map.get(i));
        }
        
        return res;
    }
    
    private int helper(TreeNode root, Map<Integer, List<Integer>> map) {
        if (root == null) return -1;
        
        int h = Math.max(helper(root.left, map), helper(root.right, map)) + 1;
        
        map.computeIfAbsent(h, v->new ArrayList<>()).add(root.val);
        return h;
        
    }
    
    //this still use the height concept but we remove map.
    /*
     * the reason is 
     */
    public List<List<Integer>> findLeaves_Height_recursive(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(root, res);
        return res;
    }
    
    //we consider the leaf as level 0, so it would be straightforward
    private int helper(TreeNode node, List<List<Integer>> res){
        //return -1 to make leaf level as 0
        if(null==node)  return -1;
        int level = 1 + Math.max(helper(node.left, res), helper(node.right, res));
        /*
         * here is the key, we use result size 
         */
        if(res.size()<level+1)  res.add(new ArrayList<>());
        //for above example ,when we went to 3, the array already there, so we just 
        //add it to list
        res.get(level).add(node.val);
        //this is optional
        node.left = node.right = null;
        return level;
    }
    
    public List<List<Integer>> findLeaves(TreeNode root) {
        
        List<List<Integer>> leavesList = new ArrayList< List<Integer>>();
        List<Integer> leaves = new ArrayList<Integer>();
        
        while(root != null) {
            if(isLeave(root, leaves)) root = null;
            leavesList.add(leaves);
            leaves = new ArrayList<Integer>();
        }
        return leavesList;
    }
    
    public boolean isLeave(TreeNode node, List<Integer> leaves) {
        
        if (node.left == null && node.right == null) {
            leaves.add(node.val);
            return true;
        }
        
        if (node.left != null) {
             if(isLeave(node.left, leaves))  node.left = null;
        }
        
        if (node.right != null) {
             if(isLeave(node.right, leaves)) node.right = null;
        }
        
        return false;
    }
}