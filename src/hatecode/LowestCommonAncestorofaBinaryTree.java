package hatecode;

/**
 * Created by duqiang on 28/07/2017.
 */
public class LowestCommonAncestorofaBinaryTree {
    /**
     * 236. Lowest Common Ancestor of a Binary Tree
     * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

     According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v
     and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

     time: O(n);
     space : O(n);

     * @param root
     * @param p
     * @param q
     * @return
     */
    //thinking process: given two nodes and root node, find the lowest common parent node
    
    //so design the recursive func, with the return value, which means we can top down, 
    //the common node only have 3 cases, only in left or right, or both. 
    //so we visit from top by this recursive function, if we find it equals p or q, then we 
    //just return
    
    //this needs to be remembered
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
       // please note here root ==p and root == q
        if (root == null || root == p || root == q) {
            return root;
        }
        
        // recursive to find the elements
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }
}
