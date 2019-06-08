package hatecode;

import java.util.HashMap;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : HouseRobberIII
 * Creator : duqiang
 * Date : July, 2018
 * Description : TODO
 */
public class HouseRobberIII {
    /**
     * 
     * The thief has found himself a new place for his thievery again. 
     * There is only one entrance to this area, called the "root." Besides the root, each house 
     * has one and only one parent house. After a tour, the smart thief realized that "all houses in this 
     * place forms a binary tree". It will automatically contact the police if two directly-linked houses 
     * were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.


     * 337. House Robber III
     * Example 1:
       3  red
      / \
     2   3
      \   \
       3   1  red
     Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
     Example 2:
         3
        / \
       4   5  red
      / \   \
     1   3   1
     Maximum amount of money the thief can rob = 4 + 5 = 9.

     time : O(n)
     space : O(n)
     * @param root
     * @return
     */
    //the problem itself is similiar to 968. Binary Tree Cameras
    //
    public int rob(TreeNode root) {
        if (root == null) return 0;
        int res = 0;
        if (root.left != null) {
            res += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            res += rob(root.right.left) + rob(root.right.right);
        }
        return Math.max(res + root.val, rob(root.left) + rob(root.right));
    }
    
    
    // we store the node computation as result in map, this is improvement
    public int rob2(TreeNode root) {
        return helper(root, new HashMap<>());
    }
    
    public int helper(TreeNode node, HashMap<TreeNode, Integer> map) {
        if (node == null) return 0;
        if (map.containsKey(node)) return map.get(node);
        
        int val = node.val;
        if (node.left != null) {
            val += helper(node.left.left, map) + helper(node.left.right, map);
        }
        
        if (node.right != null) {
            val += helper(node.right.left, map) + helper(node.right.right, map);
        }
        // not understand why we also return val
        val = Math.max(val, helper(node.left, map) + helper(node.right, map));
        map.put(node, val);
        return val;
    }
    
    /*why it has overlapped sub problems?
     *  
     * If you trace all the way back to the beginning, you'll find the answer 
     * lies in the way how we have defined rob(root). As I mentioned, for each tree root, 
     * there are two scenarios: it is robbed or is not. rob(root) does not distinguish between 
     * these two cases, so "information is lost as the recursion goes deeper and deeper", which results 
     * in repeated subproblems.
     * 
     * so we use res[2] res[0] not robbed, res[1] robbed
     */
    
    
    // this is the same as first version, 
    public int rob3(TreeNode root) {
        int[] res = robSub(root);
        return Math.max(res[0], res[1]);
    }
    
    public int[] robSub(TreeNode node) {
        if (node == null) {
            return new int[2];
        }
        
        int[] l = robSub(node.left);
        int[] r = robSub(node.right);
        
        int[] res = new int[2];
        // node is not robbed, so child is may be robbed or not.
        res[0] = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        // node is robbed, so left child and right child not be robbed
        res[1] = node.val + l[0] + r[0];
        return res;
    }
    
}
