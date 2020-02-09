package hatecode._0001_0999;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : FindBottomLeftTreeValue
 * Creator : duqiang
 * Date : July, 2018
 * Description : TODO
 */
public class FindBottomLeftTreeValue {

    /**
     * 513. Find Bottom Left Tree Value
     *Given a binary tree, find the leftmost value in the last row of the tree.

     Example 1:
     Input:

       2
      / \
     1   3

     Output:
     1
     Example 2:
     Input:

         1
        / \
       2   3
      /   / \
     4   5   6
        /
       7

     Output:
     7

     time : O(n);
     space : O(n);
     * @param root
     * @return
     */

    int res = 0;
    int height = 0;

    public int findBottomLeftValue(TreeNode root) {
        if (root == null) return -1;
        helper(root, 1);
        return res;
    }

    public void helper(TreeNode root, int depth) {
        if (root == null) return;
        if (height < depth) {
            res = root.val;
            height = depth;
        }
        helper(root.left, depth + 1);
        helper(root.right, depth + 1);
    }


    // this is just keeping the last element res, 
    public int findBottomLeftValue2(TreeNode root) {
        if (root == null) return -1;
        int res = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            res = cur.val;
            if (cur.right != null) queue.offer(cur.right);
            if (cur.left != null) queue.offer(cur.left);
        }
        return res;
    }
    
    
    // the same as previous one, but we use post order visit the tree
    int level = 0;
    int value = -1;
    public int findBottomLeftValue3(TreeNode root) {
        //edge case
        if (null == root) {
            return 0;
        }
        
        helper2(root, 0);
        return value;
    }
    
    public void helper2(TreeNode node, int l) {
        if (node == null) {
            return;
        }
        helper(node.left, l + 1);
        helper(node.right, l + 1);
        if (l + 1 > level) {
            value = node.val;
            level = l + 1;
        }
    }

}
