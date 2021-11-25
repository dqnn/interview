package hatecode._1000_1999;

import java.util.*;
public class _1367LinkedListInBinaryTree {
/*
1367. Linked List in Binary Tree
Given a binary tree root and a linked list with head as the first node. 

Return True if all the elements in the linked list starting from the head correspond to some downward path connected in the binary tree otherwise return False.

In this context downward path means a path that starts at some node and goes downwards.

Input: head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
Output: true
*/
     public class ListNode {
             int val;
             ListNode next;
             ListNode() {}
             ListNode(int val) { this.val = val; }
             ListNode(int val, ListNode next) { this.val = val; this.next = next; }
         }
     public class TreeNode {
              int val;
              TreeNode left;
              TreeNode right;
              TreeNode() {}
              TreeNode(int val) { this.val = val; }
              TreeNode(int val, TreeNode left, TreeNode right) {
                  this.val = val;
                  this.left = left;
                  this.right = right;
              }
          }
    public boolean isSubPath2(ListNode head, TreeNode root) {
        if (head == null) return true;
        if (root == null) return false;
        
        return helper(head, root) || isSubPath2(head, root.left) || isSubPath2(head, root.right);
    }
    
    private boolean helper(ListNode head, TreeNode root) {
        if (head == null) return true;
        if (root == null) return false;
        
        return head.val == root.val && (helper(head.next, root.left) ||helper(head.next, root.right));
    }
    
    
    //KMP algorithms
    public boolean isSubPath(ListNode head, TreeNode root) {
        
        //calc the KMP next function
        List<Integer> A = new ArrayList<>(), next = new ArrayList<>();
        A.add(head.val);
        next.add(0);
        int i = 0;
        head = head.next;
        while (head != null) {
            while (i > 0 && head.val != A.get(i))
                i = next.get(i - 1);
            if (head.val == A.get(i)) ++i;
            A.add(head.val);
            next.add(i);
            head = head.next;
        }
        //use KMP to do the search and also dfs
        return helper(root, 0, A, next);
    }

    private boolean helper(TreeNode root, int i, List<Integer> A, List<Integer> dp) {
        if (root == null) return false;
        while (i > 0 && root.val != A.get(i))
            i = dp.get(i - 1);
        if (root.val == A.get(i)) ++i;
        return i == dp.size() || helper(root.left, i, A, dp) || helper(root.right, i, A, dp);
    }
}