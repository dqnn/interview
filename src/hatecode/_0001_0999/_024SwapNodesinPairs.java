package hatecode._0001_0999;

/**
 * Description : TODO
 */
public class _024SwapNodesinPairs {
    /**
     * 24. Swap Nodes in Pairs
     * Given a linked list, swap every two adjacent nodes and return its head.

     For example,
     Given 1->2->3->4, you should return the list as 2->1->4->3.


     time : O(n);
     space : O(1);
     * @param head
     * @return
     */
    
    // thinking process；
    // so use paper we can easily draw the pointers change, but this problem
    //1 dummy node is always useful. 
    // 2 while condition is always use fast != null and fast.next != null, 
    // and in the loop, we should use fast.next as many as possible and not 
    //use distance more than fast.next.next, because fast.next.next maybe null
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode l1 = dummy;
        ListNode l2 = head;
        //dummy->1->2->3->4
        // head points to 1
        while (l2 != null && l2.next != null) {
            //move to next start, start point to 3
            ListNode nextStart = l2.next.next;
            // dummy point to 2
            l1.next = l2.next;
            // l2.next.next point to 3,so 2 here will point to 1
            l2.next.next = l2;
            // head move to point to 3
            l2.next = nextStart;
            
            l1 = l2;
            l2 = l2.next;
        }
        return dummy.next;
    }
    
    //reverse templates
    public ListNode swapPairs_Templates(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode s = dummy, e = head;
        int idx = 0;
        while(e != null) {
            idx++;
            if (idx % 2 == 0) {
                s = reverse(s, e.next);
                e= s.next;
            } else e = e.next;
        }
        return dummy.next;
    }
    
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
