import java.util.*;

public class _2458HeightOfBinaryTreeAfterSubtreeRemovalQueries {
    
    /*
    2458. Height of Binary Tree After Subtree Removal Queries
You are given the root of a binary tree with n nodes. Each node is assigned a unique value from 1 to n. You are also given an array queries of size m.

You have to perform m independent queries on the tree where in the ith query you do the following:

Remove the subtree rooted at the node with the value queries[i] from the tree. It is guaranteed that queries[i] will not be equal to the value of the root.
Return an array answer of size m where answer[i] is the height of the tree after performing the ith query.

Note:

The queries are independent, so the tree returns to its initial state after each query.
The height of a tree is the number of edges in the longest simple path from the root to some node in the tree.
 

Example 1:


Input: root = [1,3,4,2,null,6,5,null,null,null,null,null,7], queries = [4]
Output: [2]
      
      
    */
   /*
       interview friendly: O(n)/O(h)

       tkinking process: the problem is to say: given one binary tree and one integer array, 
       you will return array which each element means the height of the tree if we remove the element from the query

             1
           /   \
          2     3
         /
        4

     query=[2, 3]
     return [1, 2]

     each query will be independently 

     brute force is to visit tree each time and if we see the element in the tree, then we just try to see the max height of the tree.
     this will be O(mn)

     


   */
    
        int maxH = 0;
        public int[] treeQueries(TreeNode root, int[] queries) {
            int[] preh = new int[100001], posth = new int[100001];
            
            preorder(root, 0, preh);
            maxH = 0;
            postorder(root, 0, posth);
            int[] res = new int[queries.length];
            for (int i = 0; i < res.length; i++) {
                res[i] = Math.max(preh[queries[i]], posth[queries[i]]);
            }
            return res;
        }
        private void preorder(TreeNode root, int h, int[] preh) {
            if (root == null) return;
            preh[root.val] = maxH;
            maxH = Math.max(maxH, h);
            preorder(root.left, h + 1, preh);
            preorder(root.right, h + 1, preh);
        }
        private void postorder(TreeNode root, int h, int[] posth) {
            if (root == null) return;
            posth[root.val] = maxH;
            maxH = Math.max(maxH, h);
            postorder(root.right, h + 1, posth);
            postorder(root.left, h + 1, posth);
        }
    
    
    
    public int[] treeQueries_BF(TreeNode root, int[] A) {
        if (root == null || A == null ||A.length < 1) return new int[]{};
        
        int n = A.length;
        int[] res = new int[n];
        for(int i =0 ; i< n; i++) {
              helper(root, A, res, i, 0);
        }
        
        return res;
    }
    
    private void helper(TreeNode root, int[] A, int[] res, int i, int h) {
        if (root == null) return;
        res[i] = Math.max(res[i], h);
        if (root.left != null && root.left.val != A[i]) {
            helper(root.left, A, res, i, h + 1);
        }
        
        if (root.right != null && root.right.val != A[i]) {
            helper(root.right, A, res, i, h + 1);
        }
    }
}