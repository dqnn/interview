package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SumRoottoLeafNumbers
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 129. Sum Root to Leaf Numbers
 */
public class SumRoottoLeafNumbers {
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
    //so typical dfs and binary tree ops, sum of tree path top-bottom
    
    // 1. helper return value, best is to use class field, not return 
    //anything. 
    //2 exit conditions
    public int sumNumbers(TreeNode root) {
        return helper(root, 0);
    }

    public int helper(TreeNode root, int num) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) {
            return num * 10 + root.val;
        }
        return helper(root.left, num * 10 + root.val) +
                helper(root.right, num * 10 + root.val);
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
