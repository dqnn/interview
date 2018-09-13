package leetcode;

/**
 * Created by duqiang on 28/07/2017.
 */
public class ReverseLinkedList {
    /**
     * 206. Reverse Linked List


     time : O(n);
     space : O(1);

     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        return pre;
    }
    //another version but same things
    public ListNode reverseList2(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode first = head.next, sec = head;
        while(first != null) {
            ListNode temp = first.next;
            first.next = sec;
            sec = first;
            first = temp;
        }
        head.next = null;
        return sec;
    }
}
