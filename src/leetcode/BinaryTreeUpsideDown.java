package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : BinaryTreeUpsideDown
 * Creator : duqiang
 * Date : Aug, 2017
 * Description : TODO
 */
public class BinaryTreeUpsideDown {
    /**
     * 156. Binary Tree Upside Down
     * Given a binary tree where all the right nodes are either leaf nodes with a
     * sibling (a left node that shares the same parent node) or empty,
     * flip it upside down and turn it into a tree where the original right nodes
     * turned into left leaf nodes. Return the new root.

     For example:
     Given a binary tree {1,2,3,4,5},
         1
        / \
       2   3
      / \
     4   5
     return the root of the binary tree [4,5,2,#,#,3,1].
       4
      / \
     5   2
        / \
       3   1

         1         1
        / \       /
       2   3     2 - 3
      / \       /
     4   5     4 - 5

     time : O(n);
     space : O(n);
     * @param root
     * @return
     */
    // this is the operation to the original tree to make it clock wise rotate.
    // the left--> new Root, right--> left node, root--> right node.
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null && root.right == null) {
            return root;
        }
        TreeNode newRoot = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;

        root.left = null;
        root.right = null;
        return newRoot;
    }

    // non-recursive solution, did not try.
    public TreeNode upsideDownBinaryTree2(TreeNode root) {
        TreeNode node = root, parent = null, right = null;
        while (node != null) {
            TreeNode left = node.left;
            node.left = right;
            right = node.right;
            node.right = parent;
            parent = node;
            node = left;
        }
        return parent;
    }

    // the third solutions, not tried
    private TreeNode out = null;

    public TreeNode upsideDownBinaryTree3(TreeNode root) {
        TreeNode dummy = new TreeNode(0);
        dummy.left = new TreeNode(0);
        out = dummy;

        postorder(root);
        return dummy.right;
    }

    private void postorder(TreeNode root) {
        if (root == null)
            return;

        postorder(root.left);
        postorder(root.right);

        if (out.left == null) {
            out.left = root;
            out.left.left = null;
            out.left.right = null;
        } else if (out.right == null) {
            out.right = root;
            out.right.left = null;
            out.right.right = null;
        }

        if (out.left != null && out.right != null)
            out = out.right;
    }

}
