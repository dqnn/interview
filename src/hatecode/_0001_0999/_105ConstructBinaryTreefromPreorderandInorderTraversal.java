package hatecode._0001_0999;

import java.util.*;
/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ConstructBinaryTreefromPreorderandInorderTraversal
 * Creator : duqiang
 * Date : Nov, 2017
 * Description : 105. Construct Binary Tree from Preorder and Inorder Traversal
 */
public class _105ConstructBinaryTreefromPreorderandInorderTraversal {
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

    //iterative solutions
    //
    public TreeNode buildTree_Interative(int[] preorder, int[] inorder) {
        // deal with edge case(s)
        if (preorder.length == 0) {
            return null;
        }
        
        // build a map of the indices of the values as they appear in the inorder array
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        
        // initialize the stack of tree nodes
        Stack<TreeNode> stack = new Stack<>();
        int value = preorder[0];
        TreeNode root = new TreeNode(value);
        stack.push(root);
        
        // for all remaining values...
        for (int i = 1; i < preorder.length; i ++) {
            // create a node
            value = preorder[i];
            TreeNode node = new TreeNode(value);
            
            if (map.get(value) < map.get(stack.peek().val)) {
                // the new node is on the left of the last node,
                // so it must be its left child (that's the way preorder works)
                stack.peek().left = node;
            } else {
                // the new node is on the right of the last node,
                // so it must be the right child of either the last node
                // or one of the last node's ancestors.
                // pop the stack until we either run out of ancestors
                // or the node at the top of the stack is to the right of the new node
                TreeNode parent = null;
                while(!stack.isEmpty() && map.get(value) > map.get(stack.peek().val)) {
                    parent = stack.pop();
                }
                parent.right = node;
            }
            stack.push(node);
        }
        return root;
    }
}
