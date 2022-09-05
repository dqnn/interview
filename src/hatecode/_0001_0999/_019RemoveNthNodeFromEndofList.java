package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : RemoveNthNodeFromEndofList
 * Creator : professorX
 * Date : Sep, 2018
 * Description : 19. Remove Nth Node From End of List
 */
public class _019RemoveNthNodeFromEndofList {
    /**
     *  Given linked list: 1->2->3->4->5, and n = 2.

     After removing the second node from the end, the linked 
     list becomes 1->2->3->5.

     time : O(n)
     space : O(1)

     * @param head
     * @param n
     * @return
     */
    // 1<=n<=list length
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        ListNode slow = dummy;
        ListNode fast = dummy;
        dummy.next = head;
        // the distance between fast and slow should be n+1
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
