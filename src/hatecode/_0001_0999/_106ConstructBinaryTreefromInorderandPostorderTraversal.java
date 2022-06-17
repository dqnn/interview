package hatecode._0001_0999;

import java.util.*;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ConstructBinaryTreefromInorderandPostorderTraversal
 * Creator : professorX
 * Date : Nov, 2017
 * Description : 106. Construct Binary Tree from Inorder and Postorder Traversal
 * Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
 */
public class _106ConstructBinaryTreefromInorderandPostorderTraversal {
    /**
     *      3
         /    \
        9     20
      /  \   /  \
     15   7 1    5

     inorder : 15 9 7 3 1 20 5
     postorder : 15 7 9 1 5 20 3

     time : O(n)
     space : O(n)
     * @param inorder
     * @param postorder
     * @return
     */

    //good solution,compare to pre+in, the whole pattern is the same
    public TreeNode buildTree_Recursive(int[] in, int[] post) {
        if (in == null || in.length == 0 || post == null || post.length == 0) { return null; }
        return helper(post, post.length - 1, in, 0, in.length - 1);
      }
      private TreeNode helper(int[] post, int idx, int[] in, int start, int end) {
        if (start > end || idx < 0) { return null; }
        TreeNode root = new TreeNode(post[idx]);
        int i;
        for (i = start; i <= end; i++) {
          if (in[i] == root.val) {
            break;
          }
        }
        root.right = helper(post, idx - 1, in, i + 1, end);
        root.left = helper(post, idx - (end - i  + 1), in, start, i - 1);
        return root;    
      }
/*
The core idea is: Starting from the last element of the postorder and inorder array, 
we put elements from postorder array to a stack and each one is the right child of the 
last one until an element in postorder array is equal to the element on the inorder array. 
Then, we pop as many as elements we can from the stack and decrease the mark in inorder 
array until the peek() element is not equal to the mark value or the stack is empty. Then, 
the new element that we are gonna scan from postorder array is the left child of the last 
element we have popped out from the stack.
 */
      public TreeNode buildTree_Iterative(int[] inorder, int[] postorder) {
          if (inorder.length == 0 || postorder.length == 0) return null;
          int ip = inorder.length - 1;
          int pp = postorder.length - 1;
          
          Stack<TreeNode> stack = new Stack<TreeNode>();
          TreeNode prev = null;
          TreeNode root = new TreeNode(postorder[pp]);
          stack.push(root);
          pp--;
          
          while (pp >= 0) {
              while (!stack.isEmpty() && stack.peek().val == inorder[ip]) {
                  prev = stack.pop();
                  ip--;
              }
              TreeNode newNode = new TreeNode(postorder[pp]);
              if (prev != null) {
                  prev.left = newNode;
              } else if (!stack.isEmpty()) {
                  TreeNode currTop = stack.peek();
                  currTop.right = newNode;
              }
              stack.push(newNode);
              prev = null;
              pp--;
          }
          
          return root;
      }
    
    
    int pInorder;
    int pPostorder;

    public TreeNode buildTree_Reference(int[] inorder, int[] postorder) {
        // edge case
        if (null == inorder || postorder == null || inorder.length < 1 || postorder.length < 1
                || postorder.length != inorder.length) {
            return null;
        }
        pInorder = inorder.length - 1;
        pPostorder = postorder.length - 1;
        return helper(inorder, postorder, null);
    }

    // last element is the parent node
    public TreeNode helper(int[] inorder, int[] postorder, TreeNode end) {
        if (pPostorder < 0) {
            return null;
        }
        // root node is the last character
        TreeNode root = new TreeNode(postorder[pPostorder--]);
        // which means it has right child because if it does not have r child, inOrder
        // visit last element is the root element
        if (inorder[pInorder] != root.val) {
            root.right = helper(inorder, postorder, root);
        }
        pInorder--; // not last one, so it must be root
        // end equals null which means root
        // still cannot understand
        if ((end == null) || (inorder[pInorder] != end.val)) {
            root.left = helper(inorder, postorder, end);
        }
        return root;
    }

    // The the basic idea is to take the last element in postorder array as the
    // root,
    // find the position of the root in the inorder array; then locate the range for
    // left sub-tree and right sub-tree and do recursion. Use a HashMap to record
    // the index of root in the inorder array.

    public TreeNode buildTreePostIn2(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length)
            return null;
        // store node and position in inOrder visit
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; ++i)
            hm.put(inorder[i], i);
        return buildTreePostIn(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, hm);
    }

    // ps mean postOrder visit start, this is more easiler to understand
    private TreeNode buildTreePostIn(int[] inorder, int is, int ie, int[] postorder, int ps, int pe,
            HashMap<Integer, Integer> hm) {
        if (ps > pe || is > ie)
            return null;
        TreeNode root = new TreeNode(postorder[pe]);
        int ri = hm.get(postorder[pe]);
        TreeNode leftchild = buildTreePostIn(inorder, is, ri - 1, postorder, ps, ps + ri - is - 1, hm);
        TreeNode rightchild = buildTreePostIn(inorder, ri + 1, ie, postorder, ps + ri - is, pe - 1, hm);
        root.left = leftchild;
        root.right = rightchild;
        return root;
    }
}
