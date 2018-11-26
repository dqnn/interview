package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MaximumWidthOfBinaryTree {
/*
662. Maximum Width of Binary Tree
Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.

The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.

Example 1:
Input: 

           1
         /   \
        3     2
       / \     \  
      5   3     9 

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
Example 2:
Input: 

          1
         /  
        3    
       / \       
      5   3     

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).
Example 3:
Input: 

          1
         / \
        3   2 
       /        
      5      

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).
Example 4:
Input: 

          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 
(6,null,null,null,null,null,null,7).


 */
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int res = Integer.MIN_VALUE;
        while(!q.isEmpty()) {
            int size = q.size();
            res = Math.max(res, size);
            List<TreeNode> list = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node == null) {
                    list.add(null);
                    list.add(null);
                } else {
                    list.add(node.left);
                    list.add(node.right);
                }
                
            }
            int left = 0, right = list.size() - 1;
            while(left < list.size() && list.get(left) == null) left++;
            while(right >=0 && list.get(right) == null) right--;
            
            for(int i = left; i<=right;i++) {
                q.offer(list.get(i));
            }
        }
        return res;
    }
    
    //another solution, O(n) / O(n), the main points is to know the position 
    // node i in next row is 2i-1 and 2i + 1
    class AnnotatedNode {
        TreeNode node;
        int depth, pos;
        AnnotatedNode(TreeNode n, int d, int p) {
            node = n;
            depth = d;
            pos = p;
        }
    }
    public int widthOfBinaryTree2(TreeNode root) {
            Queue<AnnotatedNode> queue = new LinkedList();
            queue.add(new AnnotatedNode(root, 0, 0));
            int curDepth = 0, left = 0, ans = 0;
            while (!queue.isEmpty()) {
                AnnotatedNode a = queue.poll();
                if (a.node != null) {
                    queue.add(new AnnotatedNode(a.node.left, a.depth + 1, a.pos * 2));
                    queue.add(new AnnotatedNode(a.node.right, a.depth + 1, a.pos * 2 + 1));
                    //for each depth, this only run once so we can know most left number
                    if (curDepth != a.depth) {
                        curDepth = a.depth;
                        left = a.pos;
                    }
                    ans = Math.max(ans, a.pos - left + 1);
                }
            }
            return ans;
        }

    
    
}