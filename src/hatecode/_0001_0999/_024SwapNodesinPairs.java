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
    
    // interview friendly: 
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

    /*
     * every step we move 2 steps, 
     * for even numbers of list, 1->2->3->4,  it will always left one node last, fast  
     * for odd number  of list, 1->2->3, fast will always point to last elements 
     * 
     * 
     * for this version, we will always fast +2 steps,  example as below:
     * 
     *  dummy->1 --> 2 -- >3--> 4
     *    s          f     nextStart
     * 
     * then first f.next = slow.next;  dummy->1 <--2
     * 
     * slow.next.next = nextStart;       dummy-->1-->3
     *                                      2--->
     * slow.next = fast;              dummy->2-->1-->3
     *                                 s     f
     * 
     * then f move to 1, f =f.next     dummy->2-->1-->3
     * then s catch up with f,                    f
     *                                            s

     
     */
    public ListNode swapPairs_anotherVersion(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode dummy = new ListNode(-1);
        ListNode slow = dummy, fast = dummy;
        dummy.next = head;
        
        while(fast.next!= null && fast.next.next != null) {
            fast =fast.next.next;
                
            ListNode nextStart =fast.next;
            
            fast.next = slow.next;
            fast.next.next = nextStart;
            slow.next = fast;
        
            //fast here needs to point to 1 
            fast =fast.next;
            slow =fast;
           
        }
        
        
        return dummy.next;
    }
    
    //thinking process: O(n)/O(1)
    /*
     * this could help easily extend for every 3 nodes or 4 nodes
     *  dummy--> 1 --> 2 --> 3 --> 4
     *   |                   |
     *   |                   |
     *   s                   e
     *   
     *   reverse() will reverse above sub partition list.
     *  dummy--> 1 --> 2 --> 3 --> 4
     *   |       |           |
     *   |       |           |
     *   prev   res           cur
     *  dummy--> 2 --> 1 --> 3 --> 4
     *   |       |     |     |
     *   |       |     |     |
     *        s=res    prev  cur
     *  
     */
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
    
    /*
     * this us template code that we will reverse linkedlist
     * 
     */
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
