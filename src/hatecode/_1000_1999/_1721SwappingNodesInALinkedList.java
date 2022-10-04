package hatecode._1000_1999;

import java.util.*;

import hatecode._0001_0999.ListNode;

public class _1721SwappingNodesInALinkedList {
/*
1721. Swapping Nodes in a Linked List
You are given the head of a linked list, and an integer k.

Return the head of the linked list after swapping the values of the kth node from the beginning and the kth node from the end (the list is 1-indexed).

 

Example 1:


Input: head = [1,2,3,4,5], k = 2
Output: [1,4,3,2,5]
Example 2:

Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
Output: [7,9,6,6,8,7,3,0,9,5]
*/
    /*
     * thinking process: O(n)/O(1)
     */
    public ListNode swapNodes(ListNode head, int k) {
        if (head.next == null) return head;
        
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prev1 = dummy;
        for (int i = 1; i < k ; i++) {
            prev1 = prev1.next;
        }

        ListNode prev2 = dummy;
        //advanced go to 2nd node next
        ListNode advanced = prev1.next.next;
        while (advanced != null) {
            prev2 = prev2.next;
            advanced = advanced.next;
        }

        ListNode left = prev1.next;
        ListNode right = prev2.next;
        ListNode leftNodeAfter = left.next;
        ListNode rightNodeAfter = right.next;
        
        //if first become 2nd node next
        if (right.next == left) {
            prev2.next = left;
            left.next = right;
            right.next = leftNodeAfter;
        //if they are adjacent nodes, first--second
        } else if (prev2 == left) {
            prev1.next = right;
            right.next = left;
            left.next = rightNodeAfter;
        //normal status, first---some other nodes---second nodes
        } else {
            prev1.next = right;
            right.next = leftNodeAfter;
            prev2.next = left;
            left.next = rightNodeAfter;
        }


        return dummy.next;
        
    }
}