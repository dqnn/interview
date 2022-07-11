package hatecode._0001_0999;

/**
 */
public class _235LowestCommonAncestorofaBinarySearchTree {
    /**
     * 235. Lowest Common Ancestor of a Binary Search Tree
     * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

     According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as
     the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

     time : O(n);
     space : O(n);
     * @param root
     * @param p
     * @param q
     * @return
     */
    
    //O(lgn)/O(1)
    public TreeNode lowestCommonAncestor_Best(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode cur = root;
        
        while(true) {
            if (p.val < cur.val && q.val < cur.val) {
                cur = cur.left;
            } else if (p.val > cur.val && q.val > cur.val) {
                cur = cur.right;
            } else break;
        }
        
        return cur;
    }
    
    //O(lgn)/O(h)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }
        
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p,q);
        } else if (root.val <p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p,q);
        } else {
            return root;
        }
    }
}
