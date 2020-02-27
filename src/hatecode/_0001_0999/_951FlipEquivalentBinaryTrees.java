package hatecode._0001_0999;
import java.util.*;
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class _951FlipEquivalentBinaryTrees {   
    /*
     * 951. Flip Equivalent Binary Trees 
     * For a binary tree T, we can define a flip
     * operation as follows: choose any node, and swap the left and right child
     * subtrees.
     * 
     * A binary tree X is flip equivalent to a binary tree Y if and only if we can
     * make X equal to Y after some number of flip operations.
     * 
     * Write a function that determines whether two binary trees are flip
     * equivalent. The trees are given by root nodes root1 and root2.
     */
      // O(min(N1,N2) ^2)/O(min(H1, H2)) since for every node, we have run 4 times on the recursive functions, and the height is 
    //the how many times,  4^(min(H1, H2)) = 4^(logN)= N^2
    //brute force, thinking process: given two binary trees, to find out if we switch their children, are the the same? 
    
    //so all trees problems can be solved by recursive and stacks, here is the same, so if we compare each node, then it would be 
    //easy, then just try to think about how many different use cases to be the same. 
     public boolean flipEquiv2(TreeNode r1, TreeNode r2) {
         if (r1 == null || r2 == null) return r1 == r2;
         if (r1.val != r2.val) return false;
        //we have 2 use cases here, 
        return (flipEquiv(r1.left, r2.left) && flipEquiv(r1.right, r2.right) ||
                flipEquiv(r1.left, r2.right) && flipEquiv(r1.right, r2.left));
    }
    
    //interview friendly  dfs solution, O(N1 + N2)/O(H1+H2), becasue we dfs on two trees, 
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        List<Integer> vals1 = new ArrayList<>();
        List<Integer> vals2 = new ArrayList<>();
        dfs(root1, vals1);
        dfs(root2, vals2);
        return vals1.equals(vals2);
    }

    public void dfs(TreeNode node, List<Integer> vals) {
        if (node != null) {
            vals.add(node.val);
            int L = node.left != null ? node.left.val : -1;
            int R = node.right != null ? node.right.val : -1;

            if (L < R) {
                dfs(node.left, vals);
                dfs(node.right, vals);
            } else {
                dfs(node.right, vals);
                dfs(node.left, vals);
            }

            vals.add(null);
        }
    }
    
    
}