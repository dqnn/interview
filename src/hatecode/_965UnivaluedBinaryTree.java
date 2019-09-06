package hatecode;

public class _965UnivaluedBinaryTree {
/*
965. Univalued Binary Tree
A binary tree is univalued if every node in the tree has the same value.

Return true if and only if the given tree is univalued.
Input: [1,1,1,1,1,null,1]
Output: true
*/
    //thinking process:
    //recursive, pass the value, thinking each node as root
    public boolean isUnivalTree(TreeNode root) {
        return helper(root, root.val);
    }
    
    private boolean helper(TreeNode node, int val) {
        if (node == null) return true;
        if (node.val == val) {
            return helper(node.left, node.val) 
                    && helper(node.right, node.val);
        } else return false;
    }
}