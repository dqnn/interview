package hatecode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Project Name : Leetcode Package Name : leetcode File Name :
 * BinaryTreeRightSideView Creator : duqiang Date : July, 2018 Description :
 * TODO
 */
public class _199BinaryTreeRightSideView {

    /**
     * 199. Binary Tree Right Side View
     * given a binary tree, imagine yourself standing on the right side of it,
     * return the values of the nodes you can see ordered from top to bottom.

     For example:
     Given the following binary tree,
        1            <---
      /   \
     2     3         <---
     \     \
     5     4       <---
     You should return [1, 3, 4].

     root 1 res : 0 level : 1

     res : 1, 3, 4

     time : O(n);
     time ; O(n);
     * @param root
     * @return
     */

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        helper(res, root, 0);
        return res;
    }

    private void helper(List<Integer> res, TreeNode root, int level) {
        if (root == null) return;
        if (res.size() == level) {// this is too hard to get this point
            res.add(root.val);
        }
        helper(res, root.right, level + 1);
        helper(res, root.left, level + 1);
    }

    // this should be remembered, because
    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (i == 0) {
                    res.add(cur.val); // because we visit from right first.
                }
                if (cur.right != null) queue.offer(cur.right);
                if (cur.left != null) queue.offer(cur.left);
            }
        }
        return res;
    }

    // here is to get level scanning last elements
    public List<Integer> rightSideView4(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> list = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> tempL = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                tempL.add(node.val);
                if (null != node.left) {
                    queue.offer(node.left);
                }
                if (null != node.right) {
                    queue.offer(node.right);
                }
            }
            list.add(tempL);
        }
        for (List<Integer> tempList : list) {
            res.add(tempList.get(tempList.size() - 1));
        }
        return res;
    }
}
