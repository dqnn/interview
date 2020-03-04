package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : PalindromeLinkedList
 * Date : Sep, 2018
 * Description : TODO
 */
public class _234PalindromeLinkedList {
    /**
     * 234. Palindrome Linked List
Given a singly linked list, determine if it is a palindrome.

Example 1:

Input: 1->2
Output: false
Example 2:

Input: 1->2->2->1
Output: true
Follow up:
Could you do it in O(n) time and O(1) space?

     * time : O(n)
     * space : O(1)
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        ListNode middle = findMiddle(head);
        middle.next = reverse(middle.next);

        ListNode p = head;
        ListNode q = middle.next;
        while (p != null && q != null) {
            if (p.val != q.val) return false;
            p = p.next;
            q = q.next;
        }
        return true;
    }
    public ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        return prev;
    }
    
    
    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        // please note: we always pass head element to next, leverage recursive to last element
        return find(head, head.next) == null ? false : true;
    }
    
    private ListNode find(ListNode head, ListNode nex) {
         //If only one element
        if (nex == null) {
            return head;
        }
        //If you reach the last element
        if (nex.next == null) {
            return head.val== nex.val ? head.next : null;
        }
        
         //Recursively call till you reach last element.
        //As soon as you reach the last element, Just return the next of the head
        //So that previous calls can use that refernce and compare with the second last and so on
        ListNode checkHead = find(head, nex.next);
        if (checkHead == null) {
            return null;
        }
        
        return checkHead.val == nex.val ? checkHead.next : null;
    }
}
