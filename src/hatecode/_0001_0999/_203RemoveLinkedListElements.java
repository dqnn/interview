package hatecode._0001_0999;

/**
 * Created by professorX on 25/07/2017.
 */
public class _203RemoveLinkedListElements {
    /**
     * 203. Remove Linked List Elements
     * Remove all elements from a linked list of integers that have value val.

     Example
     Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
     Return: 1 --> 2 --> 3 --> 4 --> 5

     Credits:
     Special thanks to @mithmatt for adding this problem and creating all test cases.

     time : O(n);
     space : O(1);
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p = dummy;
        while (p.next != null) {
            if (p.next.val == val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return dummy.next;
    }



    /*
     * easier one to solve the problem 
     */
    public ListNode removeElements_easierUnderstand(ListNode head, int val) {
        if(head == null) return head;
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy, fast = head;
        
        while(fast != null) {
            while(fast != null && fast.val != val) {
                fast = fast.next;
                slow = slow.next;
            }

            if(fast == null) return dummy.next;
            
            slow.next = slow.next.next;
            fast.next = null;
            fast = slow.next;
        }
        
        return dummy.next;
    }

    public static ListNode removeElements2(ListNode head, int val) {
        if (head == null) return head;
        ListNode p = head;
        while (p.next != null) {
            if (p.next.val == val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return head.val == val ? head.next : head;
    }

    public static ListNode removeElements3(ListNode head, int val) {
        if (head == null) return head;
        head.next = removeElements3(head.next, val);
        return head.val == val ? head.next : head;
    }
}

