package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : InorderSuccessorinBST
 * Creator : professorX
 * Date : Aug, 2017
 * Description : TODO
 */
public class _285InorderSuccessorinBST {
    /**
     * 285. Inorder Successor in BST
     * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

     Note: If the given node has no in-order successor in the tree, return null.

     time : O(n);
     space : O(n);
     * @param root
     * @param p
     * @return
     */
    // how we think about this question:
    // we visit the tree from left ->parent-> right this way, so if we visit the BST from root,
    // we have to visit its left branch, so for most use cases we have to go through a number of 
    // nodes which are smaller than P, then we mark the node to its right child we can be more closer 
    // to P, 
    //so finally we will meet one node which is bigger than P, then its successor should be a leaf node,
    //and our loop should be exit
    //edge case: thinking about this way if we visit the tree and put into a list, so we just need to find P and 
    //return its next. so it maybe last one, for this case, we just return null, the previous loop still works
    //remember this solutions
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode res = null;
        // it must be its right branch
        while (root != null) {
            //means they are 
            if (root.val <= p.val) {
                root = root.right;
            } else {
                //current node is bigger than p which means 
                // so it must be its right branch, the successor is its first left at least
                //child of its right branch
                res = root;
                root = root.left;
            }
        }
        return res;
    }

    // this is the same as previous but with recursive
    public TreeNode successor(TreeNode root, TreeNode p) {
        if (root == null) return null;
        if (root.val <= p.val) {
            return successor(root.right, p);
        } else {
            TreeNode temp = successor(root.left, p);
            return (temp != null) ? temp : root;
        }
    }
}
