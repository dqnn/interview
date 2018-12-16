package hatecode;

import java.util.HashMap;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ConstructBinaryTreefromInorderandPostorderTraversal
 * Creator : duqiang
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
public class ConstructBinaryTreefromInorderandPostorderTraversal {
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

    int pInorder;
    int pPostorder;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
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
