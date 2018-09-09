package leetcode;

import java.util.Stack;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : RecoverBinarySearchTree
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 99. Recover Binary Search Tree
 */
public class RecoverBinarySearchTree {
    /**
     * Two elements of a binary search tree (BST) are swapped by mistake.

     Recover the tree without changing its structure.
          6
         / \
        8  1
      /  \
     0   3
        / \
       2  5

     time : O(n)
     space : O(n)


     */
/*
 * 要使用O(1)空间进行遍历，最大的难点在于，遍历到子节点的时候怎样重新返回到父节点（假设节点中没有指向父节点的p指针），
 * 由于不能用栈作为辅助空间。为了解决这个问题，Morris方法用到了线索二叉树（threaded binary tree）的概念。
 * 在Morris方法中不需要为每个节点额外分配指针指向其前驱（predecessor）和后继节点（successor），
 * 只需要利用叶子节点中的左右空指针指向某种顺序遍历下的前驱节点或后继节点就可以了。
 */
    TreeNode first = null;
    TreeNode second = null;
    TreeNode prev = null;

    public void recoverTree(TreeNode root) {
        if (root == null) return;
        helper(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
    public void helper(TreeNode root) {
        if (root == null) return;
        helper(root.left);
        if (prev != null && prev.val >= root.val) {
            if (first == null) first = prev;
            second = root;
        }
        prev = root;
        helper(root.right);
    }

    public void recoverTree2(TreeNode root) {
        if (root == null) return;
        TreeNode first = null;
        TreeNode second = null;
        TreeNode prev = null;

        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (prev != null && cur.val <= prev.val) {
                    if (first == null) first = prev;
                    second = cur;
                }
                prev = cur;
                cur = cur.right;
            }
        }
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}
