package hatecode._0001_0999;

import java.util.*;
public class _515FindLargestValueInEachTreeRow {
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
    
    //DFS version, it is tricky to use an arraylist to store the 
    //level d max value
        public List<Integer> largestValues_DFS(TreeNode root) {
            List<Integer> res = new ArrayList<Integer>();
            helper(root, res, 0);
            return res;
        }
        private void helper(TreeNode root, List<Integer> res, int d){
            if(root == null){
                return;
            }
           //expand list size
            if(d == res.size()){
                res.add(root.val);
            }
            else{
            //or set value
                res.set(d, Math.max(res.get(d), root.val));
            }
            helper(root.left, res, d+1);
            helper(root.right, res, d+1);
        }
}