package hatecode._0001_0999;

import java.util.List;

/**
 * Created by professorX on 25/07/2018.
 */
public class _141LinkedListCycle {

    /**
     * 141. Linked List Cycle
     * Given a linked list, determine if it has a cycle in it.
     * time : O(n);
     * space : O(1);
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }
}
