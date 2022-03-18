package hatecode._1000_1999;

import hatecode._0001_0999.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class _1290ConvertBinaryNumberInALinkedListToInteger {
/*
1290. Convert Binary Number in a Linked List to Integer
Input: head = [1,0,1]
Output: 5


Given head which is a reference node to a singly-linked list. The value of each node in the linked list is either 0 or 1. The linked list holds the binary representation of a number.

Return the decimal value of the number in the linked list.
*/
    
    //simpe logic
    public int getDecimalValue(ListNode head) {
        if(head == null) return 0;
        
        int res = 0;
        while(head != null) {
            res = res * 2 + head.val;
            head = head.next;
        }
        
        return res;
    }
}