package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SumRoottoLeafNumbers
 * Creator : professorX
 * Date : Sep, 2018
 * Description : 129. Sum Root to Leaf Numbers
 */
public class _129SumRoottoLeafNumbers {
    /**
     * For example,

       1
      / \
     2   3
     The root-to-leaf path 1->2 represents the number 12.
     The root-to-leaf path 1->3 represents the number 13.

     Return the sum = 12 + 13 = 25.

     time : O(n)
     space : O(n)

     * @param root
     * @return
     */
    //so typical dfs and binary tree ops, 
    //sum of tree path top-bottom
    
    // 1. helper return value, best is to use class field, not return 
    //anything. 
    //2 exit conditions
    public int sumNumbers(TreeNode root) {
        return helper(root, 0);
    }

    //we need 
    private int helper(TreeNode root, int cur) {
        //we need this code since if single leaf node with a parent node
        
        if (root == null) return 0;
        cur = cur * 10 + root.val;
        //leaf node, we have to use or it will be doubled
        if (root.left == null && root.right == null) {
           return cur;
        }
        
        return  helper(root.left, cur) + helper(root.right, cur) ;
    }
    
    int res = 0;
    public int sumNumbers3(TreeNode root) {
        helper(root, "");
        return res;
    }
    
    public void helper(TreeNode node, String pre) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            res += Integer.valueOf(pre+node.val);
        }
        helper(node.left, pre+String.valueOf(node.val));
        helper(node.right, pre+String.valueOf(node.val));
    }
}
