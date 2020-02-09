package hatecode._0001_0999;

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
    //reverse the whole function, this function also used in many other places
    public ListNode reverseList_ByReverse_Exclusive_Func(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        reverse(dummy, null);
        return dummy.next;
    }
    //see LC 25. Reverse Nodes in k-Group, it has detailed explanations
    private ListNode reverse(ListNode s, ListNode e) {
        ListNode cur = s.next;
        ListNode res = cur;
        ListNode prev = s;
        
        while(cur != e) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        
        res.next = cur;
        s.next = prev;
        
        return res;
        
    }
}
