package hatecode._0001_0999;
import java.util.*;
public class _545BoundaryOfBinaryTree {
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

  //interview friendly, 
    //so anti-clock to record  all nodes visited. 
    // we can see left branch + leaves + reverse of right branches
    
      5
    /    \
   3      7
  / \     /\
 1   2   4  6


 helper(node, res, lb, rb)
 
 lb means whether current node is left  boundary 
 rb means whether current node is right boundary 

so when we pass the flag to next level, 
 for root.left,  it will be (lb, rb&& root.right == null), which means the node is left boundary for its left child, 
 for the root.left would like to right boundary, then from parent, this has to be right boundary and its right it should have no right child because 
 it is root.left

 the same logic applies to root.right


there 4 use cases:
1. it is root
2. it is left boundary, like 3,1 
3. it is right boundary, like 7,6
4. bottom level, like 2,4,6


      5
    /    \
   3      7
  / \     /\
 1   2   4  6

     */
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root != null) {
            //we have to add root here, if not, later we will add node.val twice 
            res.add(root.val);
            helper(root.left, res, true, false);
            helper(root.right, res, false, true);π
        }
        return res;
    }
    //pretty trick and elegant code implementations
    //
    private void helper(TreeNode node, List<Integer> res, boolean lb, boolean rb) {
        if (node == null) return;

         /*
        this also works, but it will pick up leaves first and return 

         * if (node.left == null && node.right == null) {
         *  res.add(node.val);
         *  return;
         * }
         * if (lb) res.add(node.val);
         */

        if (lb) res.add(node.val);
        //leaf node, and not in left boundary and not in right boundary 
        if (!lb && !rb && node.left == null && node.right == null) res.add(node.val);


        // if a left node want to be right boundary, this node right branch must not exist
        helper(node.left, res, lb, rb && node.right == null);
        //if a right node want to be left boundary, its left node must not exist
        helper(node.right, res, lb && node.left == null, rb);
        if (rb) res.add(node.val);
    }
    
    
    /*
     * this one actually is easier to understand use in interview, because it is easy to explain 
     * 
     */
    List<Integer> res = new ArrayList<>();
    public List<Integer> boundaryOfBinaryTree_TopVoted(TreeNode root) {
        if (root == null) return res;
        
        res.add(root.val);
        //only get left side nodes
        left(root.left);
        //only get leaves side nodes
        leaves(root.left);
        
       // right child leaf node
        leaves(root.right);
        //right side node
        right(root.right);
        
        return res;
    }
    
    private void left(TreeNode node) {
        // ignore leaf child 
        if (node == null || node.left == null && node.right == null) return;
        res.add(node.val);
        if (node.left == null) left(node.right);
        else left(node.left);
    }
    
    private void right(TreeNode node) {
        // ignore leaf child 
        if (node == null || node.left == null && node.right == null) return;
        if (node.right == null) right(node.left);
        else right(node.right);
        //collect in the end becaus we want to collect from bottom to top
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