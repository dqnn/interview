package hatecode._0001_0999;

/**
 * Date : Aug, 2018
 * Description : TODO
 */
public class _617MergeTwoBinaryTrees {
    /**
     * 617. Merge Two Binary Trees
     * Given two binary trees and imagine that when you put one of them to cover the other,
     * some nodes of the two trees are overlapped while the others are not.

     You need to merge them into a new binary tree. The merge rule is that if two nodes overlap,
     then sum node values up as the new value of the merged node. Otherwise, the NOT null node
     will be used as the node of new tree.

     Example 1:
     Input:
     Tree 1                     Tree 2
         1                         2
        / \                       / \
       3   2                     1   3
      /                           \   \
     5                             4   7
     Output:
     Merged tree:
         3
        / \
       4   5
      / \   \
     5   4   7

     time : O(n);
     space : O(n);
     * @param t1
     * @param t2
     * @return
     */

    /*
     * thinking process: O(n)/O(h)
     * the problem is to say:  given two binary trees r1 and r2, 
     * we will need to 
     */
    public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        // we are doing this way to return correct node
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        TreeNode newNode = new TreeNode(t1.val + t2.val);
        newNode.left = mergeTrees(t1.left, t2.left);
        newNode.right = mergeTrees(t1.right, t2.right);
        return newNode;
    }
    
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(5);

        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(3);

        root2.left.right = new TreeNode(4);
        root2.right.right = new TreeNode(7);
        printTree(root1);
        System.out.println("");
        printTree(root2);
        System.out.println("");
        TreeNode res = mergeTrees(root1, root2);
        printTree(res);
    }

    private static void printTree(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");

        printTree(root.left);

        printTree(root.right);
    }

}
