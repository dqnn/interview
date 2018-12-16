package hatecode;

/**
 * Created by duqiang on 15/09/2017.
 */
public class SameTree {

    /**
     * 100. Same Tree

     Given two binary trees, write a function to check if they are equal or not.

     Two binary trees are considered equal if they are structurally 
     identical and the nodes have the same value.

     time : O(n);
     space : O(n);
     * @param p
     * @param q
     * @return
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    
    //interview friendly solutions
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null) {
            if (q == null) {
                return true;
            } else return false;
        }
        
        boolean left = false, right = false;
        if (p != null && q != null) {
            if (p.val != q.val) return false;
            right = isSameTree(p.right, q.right);
            left = isSameTree(p.left, q.left);
        }
        return left && right;
    }
}
