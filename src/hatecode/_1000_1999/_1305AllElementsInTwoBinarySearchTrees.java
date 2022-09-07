package hatecode._1000_1999;

import java.util.*;

import hatecode._0001_0999.TreeNode;

public class _1305AllElementsInTwoBinarySearchTrees {
/*
1305. All Elements in Two Binary Search Trees
Given two binary search trees root1 and root2, return a list containing all the integers from both trees sorted in ascending order.

 

Example 1:


Input: root1 = [2,1,4], root2 = [1,0,3]
Output: [0,1,1,2,3,4]
*/
    /*
     * thinking process: O(n+m)/O(h)
     * 
     * the problem is to say: given two BST, return sorted values in list.
     * 
     * it is pretty similar to sorted K lists.  but here they stored in trees not in 
     * lists, 
     * 
     * here we used 2 stacks to stored the values from r1 and r2, then we first add
     * all left child nodes to stacks, then we pop from top since they are the smallest
     * 
     */
    public List<Integer> getAllElements(TreeNode r1, TreeNode r2) {
        
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> s1 = new Stack<>(), s2 = new Stack<>(), st;
        
        helper(s1, r1);
        helper(s2, r2);
        
        while(!s1.isEmpty() || !s2.isEmpty()) {
            if (s1.isEmpty()) st = s2;
            else if (s2.isEmpty()) st =s1;
            else st = s1.peek().val < s2.peek().val ? s1 : s2;
            
            TreeNode temp = st.pop();
            res.add(temp.val);
            helper(st,temp.right);
        }
        return res;
        
    }
    

    private void helper(Stack<TreeNode> s, TreeNode root) {
        while(root != null) {
            s.push(root);
            root=root.left;
        }
    }
}