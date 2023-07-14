package hatecode._0001_0999;

import java.util.*;

public class _876MiddleOfTheLinkedList {
    /*
    876. Middle of the Linked List
    Given the head of a singly linked list, return the middle node of the linked list.
    
    If there are two middle nodes, return the second middle node.
    
     
    
    Example 1:
    
    
    Input: head = [1,2,3,4,5]
    Output: [3,4,5]
    Explanation: The middle node of the list is node 3.
    */

        /*
         * since fast will jump 2 steps while slow just one step, so eventually 
         * if length is even, fast will point to null because there will be n -1 steps, 
         * slow will always point to the correct node 
         * 
         */
        public ListNode middleNode(ListNode head) {
            if (head == null || head.next == null) return head;
            
            ListNode fast = head, slow = head;
            
            while(fast != null && fast.next != null) {
                slow=slow.next;
                fast =fast.next.next;
            }
            
            return slow;
        }
    }