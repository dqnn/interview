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
     
    //thinking process: O(N * min(L, H))/O(H), N = tree size, L = list length, H = tree height
    //
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
    
    
    //KMP algorithms O(N + L)/O(L + H)
    public boolean isSubPath(ListNode head, TreeNode root) {
        List<Integer> A = new ArrayList<>(), dp = new ArrayList<>();
        A.add(head.val);
        dp.add(0);
        int index = 0;
        
        //code templates for calculateNext function
        for(head = head.next; head!=null;) {
            if(head.val == A.get(index)) {
                dp.add(index+1);
                index++;
                A.add(head.val);
                head = head.next;
            } else {
                if(index!=0) index = dp.get(index-1);
                else {
                    dp.add(0);
                    A.add(head.val);
                    head=head.next;
                }
            }
        }
        return helper(root, 0, A, dp);
    }

    //the way how we do dfs is different compared to string as s and p,
    //we have to rewind back to when it is different
    private boolean helper(TreeNode root, int i, List<Integer> pattern, List<Integer> next) {
        if (root == null) return false;
        while (i > 0 && root.val != pattern.get(i))
            i = next.get(i - 1);
        if (root.val == pattern.get(i)) ++i;
        return i == next.size() || helper(root.left, i, pattern, next) || helper(root.right, i, pattern, next);
    }
}