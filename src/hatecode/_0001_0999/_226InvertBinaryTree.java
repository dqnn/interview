package hatecode._0001_0999;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Description : TODO
 */
public class _226InvertBinaryTree {
    /**
     * 226. Invert Binary Tree
     * Invert a binary tree.

          4
        /   \
       2     7
      / \   / \
     1   3 6   9

     to
         4
        /   \
       7     2
      / \   / \
     9   6 3   1

     time : O(n)
     space : O(n);
     * @param root
     * @return
     */

    public TreeNode invertTree(TreeNode root) {
       if (root == null) return root;
       TreeNode left = invertTree(root.left);
       TreeNode right = invertTree(root.right);
       root.left = right;
       root.right = left;
       return root;
    }

    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            TreeNode temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;
            if (cur.left != null) queue.offer(cur.left);
            if (cur.right != null) queue.offer(cur.right);
        }
        return root;
    }
    
    //using recursive, post-order
    public TreeNode invertTree3(TreeNode root) {
        return helper(root);
    }
    
    public TreeNode helper(TreeNode node) {
        if (node == null) return null;
        
        helper(node.left);
        helper(node.right);
        
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        
        return node;
    }
    
    public TreeNode invertTree_PreOrder(TreeNode root) {
        helper_PreOrder(root);
        return root;
    }
    
    private void helper_PreOrder(TreeNode root) {
        if (root == null) return;
        
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        
        helper_PreOrder(root.left);
        helper_PreOrder(root.right);
    }
}
