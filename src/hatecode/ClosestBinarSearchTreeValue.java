package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ClosestBinarSearchTreeValue
 * Creator : duqiang
 * Date : Oct, 2017
 * Description : 270. Closest Binary Search Tree Value
 * Given a non-empty binary search tree and a target value, 
 * find the value in the BST that is closest to the target.

Note:

Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.

 */
public class ClosestBinarSearchTreeValue {

    // time : O(n) space : O(1)
    public int closestValue(TreeNode root, double target) {
        int res = root.val;
        while (root != null) {
            if (Math.abs(target - root.val) < Math.abs(target - res)) {
                res = root.val;
            }
            // so we need to find nearest node to target
            root = root.val > target ? root.left : root.right;
        }
        return res;
    }


    // time : O(n) space : O(n)
    public int closestValue2(TreeNode root, double target) {
        return helper(root, target, root.val);
    }

    public int helper(TreeNode root, double target, int val) {
            if (root == null) return val;
            if (Math.abs(root.val - target) < Math.abs(val - target)) {
                val = root.val;
            }
        // BST tree so right will be bigger than root and left
        // left < root< right
            if (root.val < target) {
                val = helper(root.right, target, val);
            } else if (root.val > target) {
                val = helper(root.left, target, val);
            }
            return val;
    }
}
