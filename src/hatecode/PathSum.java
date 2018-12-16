package hatecode;

import java.util.Stack;

/**
 * Created by duqiang on 27/07/2017.
 */
public class PathSum {
    /**
     * 112. Path Sum
     * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding 
     * up all the values along the path equals the given sum.

     For example:
     Given the below binary tree and sum = 22,
           5
          / \
         4   8
        /   / \
       11  13  4
      /  \      \
     7    2      1
     return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

     time : O(n);
     space : O(n);
     * @param root
     * @param sum
     * @return
     */
    // this is one way to solve, but not interview friendly
    public static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    public static boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) return false;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur.left == null && cur.right == null) {
                if (cur.val == sum) {
                    return true;
                }
            }
            if (cur.right != null) {
                stack.push(cur.right);
                cur.right.val += cur.val;
            }
            if (cur.left != null) {
                stack.push(cur.left);
                cur.left.val += cur.val;
            }
        }
        return false;
    }
    
    
    boolean isFound = false;
    int target = 0;
    public boolean hasPathSum3(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        this.target = sum;
        
        helper(root, 0);
        return isFound; 
    }
    
    public void helper(TreeNode node, int sum) {
        if (isFound || node == null) {
            return;
        }
        sum += node.val;
        if (node.left == null && node.right == null) {
            if (sum == this.target) {
                isFound = true;
            }
        }

        if (node.left != null) {
            helper(node.left, sum);
        }
        if (node.right != null) {
            helper(node.right, sum);
        }
    }
}
