package hatecode._0001_0999;

import java.util.*;
/**
 * Created by  on 28/07/2017.
 */
public class _236LowestCommonAncestorofaBinaryTree {
    /**
     * 236. Lowest Common Ancestor of a Binary Tree
     * Given a binary tree, find the lowest common ancestor (LCA) of two given 
     * nodes in the tree.

     According to the definition of LCA on Wikipedia: “The lowest common ancestor
      is defined between two nodes v and w as the lowest node in T that has 
      both v and w as descendants (where we allow a node to be a descendant of 
      itself).”

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
    
    //this needs to be remembered, this method is to return starting from root, whether it 
    //has lowest common ancestor for p and q, if not return null, if yes, return the node
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
       //the return function is key, 
        
        //so  p or q will return because no matter which will meet
        //first, it will be the common ancestor because another will be lower.
        //if it is not there, then it will be in another branch, root or higher node
        //will be ancestor
        // returns real root which is LCA since 
        //it’s doesn’t matter where second (p or q) is – root is LCA anyway.
        //one key here is that p q will be in BT, if not, 
        if (root == null || root == p || root == q) {
            return root;
        }
        
        // we have 3 cases, 
        //first is the p q are in two branches, left and right are all not null
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if (left != null && right != null) {
            return root;
        }
        
        //only in left or right branch
        return left == null ? right : left;
    }
  
    //iterative solutions O(n)/O(n)
    public TreeNode lowestCommonAncestor_Iterative(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        parent.put(root, null);
        stack.push(root);

        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }
        Set<TreeNode> ancestors = new HashSet<>();
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }
        while (!ancestors.contains(q))
            q = parent.get(q);
        return q;
    }
}
