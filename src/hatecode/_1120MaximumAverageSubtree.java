package hatecode;

import java.util.*;

public class _1120MaximumAverageSubtree {
/*
1120. Maximum Average Subtree
Given the root of a binary tree, find the maximum average value of any subtree of that tree.

(A subtree of a tree is any node of that tree plus all its descendants. The average value of a tree is the sum of its values, divided by the number of nodes.)

Example 1:

Input: [5,6,1]
Output: 6.00000
*/
    //thinking process: O(n)/O(n), actually we do not need to memo the value
    
    //so given a tree, we would like to get max avg value, avg value is for a node, 
    //one node avg value is sum/count of nodes include the root. 
    
    //recursive, but we have to remember how many count of nodes for its right/left
    //or we cannot calculate current node avg
    double res = Double.MIN_VALUE;
    
    public double maximumAverageSubtree(TreeNode root) {
        helper(root, new HashMap<>());
        
        return res == Double.MIN_VALUE ? -1 : res;
    }
    
    //int[], [0] is sum, [1] count of nodes, we do not need map actually since it 
    //does not help not all
    private int[] helper(TreeNode root, Map<TreeNode, int[]> map) {
        if(root == null) return new int[]{0,0};
        if(map.containsKey(root)) return map.get(root);
        
        int[] l = helper(root.left, map),
        r = helper(root.right, map);
        
        int sum = l[0] + r[0] + root.val, count = l[1] + r[1] + 1;
        map.put(root, new int[]{sum, count});
        res = Math.max(res, 1.0 * sum/count);
        return new int[]{sum, count};
    }
}