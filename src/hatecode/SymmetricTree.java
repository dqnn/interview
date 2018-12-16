package hatecode;

/**
 * Created by duqiang on 24/07/2017.
 */
public class SymmetricTree {
    /**
     * 101. Symmetric Tree
     *Given a binary tree, check whether it is a mirror 
     *of itself (ie, symmetric around its center).

     For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
         1
        / \
       2   2
      / \ / \
     3  4 4  3
     time : O(n)
     space : O(n)
     * @param root
     * @return
     */
    // thinking process:
    // just know how to recursive visit tree and BSF visit the tree
    
    //we compare each node in two branches, so two use cases here one is 
    // one branch does not have the node, another is data value is not the same
    
    //so we use DFS to visit the tree
    public static boolean isSymmetric(TreeNode root) {

        if (root == null) return true;
        return helper(root.left, root.right);
    }

    public static boolean helper(TreeNode p, TreeNode q) {

        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;

        return helper(p.left, q.right) && helper(p.right, q.left);
    }
}
