package hatecode;

import java.util.*;
public class FindLargestValueInEachTreeRow {
/*
515. Find Largest Value in Each Tree Row
You need to find the largest value in each row of a binary tree.

Example:
Input: 

          1
         / \
        3   2
       / \   \  
      5   3   9 

Output: [1, 3, 9]
*/
    //interview friendly, just common operations, no magic stuff
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
            int size = q.size();
            int max = Integer.MIN_VALUE;
            while(size -- > 0) {
                TreeNode node = q.poll();
                max = Math.max(max, node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            res.add(max);
        }
        return res;
    }
}