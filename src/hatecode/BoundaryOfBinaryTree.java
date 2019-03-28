package hatecode;
import java.util.*;
public class BoundaryOfBinaryTree {
/*
545. Boundary of Binary Tree
Input:
  1
   \
    2
   / \
  3   4

Ouput:
[1, 3, 4, 2]

Explanation:
The root doesn't have left subtree, so the root itself is left boundary.
The leaves are node 3 and 4.
The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
So order them in anti-clockwise without duplicates and we have [1,3,4,2].
*/
/*
4 cases: 
1 node.left is left bound if node is left bound;
node.right could also be left bound if node is left bound && node has no right child;
2. Same applys for right bound;
3 if node is left bound, add it before 2 child - pre order;
  if node is right bound, add it after 2 child - post order;
4. A leaf node that is neither left or right bound belongs to the bottom line;
 */
  //interview friendly, 
    //so anti-clock to record  all nodes visited. 
    // we can see left branch + leaves + reverse of right branches
    
    //
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root != null) {
            res.add(root.val);
            getBounds(root.left, res, true, false);
            getBounds(root.right, res, false, true);
        }
        return res;
    }
    //pretty trick and elegant code implementations
    //
    private void getBounds(TreeNode node, List<Integer> res, boolean lb, boolean rb) {
        if (node == null) return;
        if (lb) res.add(node.val);
        if (!lb && !rb && node.left == null && node.right == null) res.add(node.val);
        getBounds(node.left, res, lb, rb && node.right == null);
        getBounds(node.right, res, lb && node.left == null, rb);
        if (rb) res.add(node.val);
    }
    
    
    //just for reference
    List<Integer> res = new ArrayList<>();
    public List<Integer> boundaryOfBinaryTree_TopVoted(TreeNode root) {
        if (root == null) return res;
        
        res.add(root.val);
        
        left(root.left);
        leaves(root.left);
        
       
        leaves(root.right);
        right(root.right);
        
        return res;
    }
    
    private void left(TreeNode node) {
        if (node == null || node.left == null && node.right == null) return;
        res.add(node.val);
        if (node.left == null) left(node.right);
        else left(node.left);
    }
    
    private void right(TreeNode node) {
        if (node == null || node.left == null && node.right == null) return;
        if (node.right == null) right(node.left);
        else right(node.right);
        
        res.add(node.val);
    }
    
    private void leaves(TreeNode node) {
        if (node == null) return;
        if (node.left ==null &&node.right == null)  {
            res.add(node.val);
            return;
        }
        leaves(node.left);
        leaves(node.right);
    }
}