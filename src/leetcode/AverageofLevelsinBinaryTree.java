package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : AverageofLevelsinBinaryTree
 * Creator : duqiang
 * Date : Aug, 2017
 * Description : 637. Average of Levels in Binary Tree
 * Given a non-empty binary tree, return the average value of the nodes on each level 
 * in the form of an array.
 */
public class AverageofLevelsinBinaryTree {

    /**

     Example 1:
     Input:
          3
         / \
        9  20
      /  \
     15   7
     Output: [3, 14.5, 11]
     Explanation:
     The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11.
     Hence return [3, 14.5, 11].

     time : O(n)
     space : O(n)

     * @param root
     * @return
     */
	
	// this is templates for broad visit for tree, using linkedList as travel way
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                sum += cur.val;
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            res.add(sum / size);
        }
        return res;
    }
}
