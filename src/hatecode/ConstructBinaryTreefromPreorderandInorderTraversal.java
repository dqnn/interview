package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ConstructBinaryTreefromPreorderandInorderTraversal
 * Creator : duqiang
 * Date : Nov, 2017
 * Description : 105. Construct Binary Tree from Preorder and Inorder Traversal
 */
public class ConstructBinaryTreefromPreorderandInorderTraversal {
    /**
     *    3
         / \
        9  20
      /  \
     15   7

     inorder : 15 9 7 3 20
     preorder : 3 9 15 7 20

     time : O(n)
     space : O(n)

     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] pre, int[] in) {
        // edge case
        if (pre == null || in == null || pre.length != in.length || in.length < 1) {
            return null;
        }

        TreeNode root = helper(pre, 0, in, 0, in.length - 1);
        return root;
    }

    public TreeNode helper(int[] pre, int pres, int[] in, int ins, int ine) {
        if (pres > pre.length - 1 || ins > ine) {
            return null;
        }

        int index = -1;
        for (int i = ins; i <= ine; i++) {
            if (in[i] == pre[pres]) {
                index = i;
                break;
            }
        }
        if (index == -1) return null;

        TreeNode root = new TreeNode(pre[pres]);
        // pre, start from index 1, end 0 + index
        root.left = helper(pre, pres + 1, in, ins, index - 1);
        root.right = helper(pre, pres + index - ins + 1, in, index + 1, ine);
        return root;
    }

}
