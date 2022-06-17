package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LinkedListCycleII
 * Creator : professorX
 * Date : Aug, 2018
 * Description : 142. Linked List Cycle II
 */
public class _142LinkedListCycleII {

    /**
     * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
     *
     * time : O(n)
     * space : O(1)
     *
     * @param head
     * @return
     */

    //thinking process:
    //we still have a fast and slow pointer, if there was a cycle, so they have to meet,  
    //and when they meet, we start another pointer travel from head with same speed as slow pointer
    //when they meet again, the node slow point to is the starting node of a cycle
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                ListNode slow2 = head;
                while (slow != slow2) {
                    slow = slow.next;
                    slow2 = slow2.next;
                }
                return slow;
            }
        }
        return null;
    }
}
