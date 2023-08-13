package hatecode._2000_2999;

import java.util.*;

import hatecode._0001_0999.ListNode;

public class _2816DoubleANumberRepresentedAsALinkedList {
/* 
2816. Double a Number Represented as a Linked List

You are given the head of a non-empty linked list representing a non-negative integer without leading zeroes.

Return the head of the linked list after doubling it.

 

Example 1:


Input: head = [1,8,9]
Output: [3,7,8]
*/

   /*
    * the problem is to say: given a linkedlist, each node contains one digit of a number, return a linklist which its original
    value doubled


    Stack solution or we can reverse the linkedlist
    */
    
    public ListNode doubleIt(ListNode head) {
        if (head == null) return head;
        
        head = reverse(head);
        ListNode prev = null;
        int carry = 0;
        ListNode cur = head;
        while(cur != null) {
            int val = cur.val *2 + carry;
            ListNode temp = new ListNode(val%10);
            carry = val/10;
            temp.next = prev;
            prev = temp;
            cur=cur.next;
        }
        
        if (carry != 0) {
            ListNode temp = new ListNode(carry);
            temp.next = prev;
            prev = temp;
        }
        
        return prev;
    }
    
    
    private ListNode reverse(ListNode head) {
        ListNode cur = head;
        
        ListNode prev = null;
        while(cur !=null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur= next;
        }
        
        return prev;
    }
    
    
    public ListNode doubleIt_Stack(ListNode head) {
        if (head == null) return head;
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        while(cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        
        ListNode prev = null;
        int carry = 0;
        
        while(!stack.isEmpty()) {
            ListNode e = stack.pop();
            int val = e.val*2 + carry;
            ListNode temp = new ListNode(val%10);
            carry= val/10;
            temp.next = prev;
            prev = temp;
        }
        
        if (carry != 0) {
            ListNode temp = new ListNode(carry);
            temp.next = prev;
            prev = temp;
        }
        
        return prev;
    }
}