package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ReorderList
 * Date : Sep, 2018
 * Description : 143. Reorder List
 */
public class _143ReorderList {
    /**
     * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
     reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

     You must do this in-place without altering the nodes' values.

     For example,
     Given {1,2,3,4}, reorder it to {1,4,2,3}.

     time : O(n)
     space : O(1)

     * @param head
     */
    //thinking process:
    //3 steps: 
    //1 find the middle node
    //2 reverse the right side
    //3 merge left and right side one node by one node
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode temp = null;
        ListNode slow = head, fast = head;
        ListNode l1 = head;
        while (fast != null && fast.next != null) {
            temp = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        temp.next = null;
        //reverse the right side
        ListNode l2 = reverse(slow);
        merge(l1, l2);
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        return prev;
    }
    //merge two list, l1 was first, then comes with l2 nodes
    private void merge(ListNode l1, ListNode l2) {
        //while (l2 != null) { also works because l2 is usually longer than l1
        while (l1 != l2) {
            ListNode n1 = l1.next;
            ListNode n2 = l2.next;
            l1.next = l2;

            /*
             * usually if list length is odd, then l1 will have one less element 
             */
            if (n1 == null) break;
            l2.next = n1;
            //pass to next
            l1 = n1;
            l2 = n2;
        }
    }
}
