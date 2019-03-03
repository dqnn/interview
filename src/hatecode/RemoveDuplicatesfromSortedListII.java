package hatecode;

/**
 * Created by duqiang on 25/07/2017.
 */
public class RemoveDuplicatesfromSortedListII {

    /**
     * 82. Remove Duplicates from Sorted List II (83. Remove Duplicates from 
     * Sorted List: follow up)
     * Given a sorted linked list, delete all nodes that have duplicate 
     * numbers, leaving only distinct numbers from the original list.

     For example,
     Given 0->1->2->3->3->4->4->5, return 1->2->5.
     Given 0->1->1->1->2->3, return 2->3.
     time : O(n);
     space : O(1);
     * @param head
     * @return
     */
    
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode dummy = new ListNode(-1), cur = dummy, prev = dummy;
        dummy.next = head;
        
        // dummy->1->2->3->3->4->4->5, cur points to 1, while prev point dummy, 
        //every time, if there was no duplicate, then cur will one step by step and 
        //prev is 2 steps behind cur, 
        while(cur != null) {
            cur = cur.next;
            //dummy->1->2->3->3->4->4->5, this is to look for start and end sub array which has same 
            //value, the loop is to identify first and last value, first is prev.next
            while(cur != null && cur.val == prev.next.val) {
                cur = cur.next;
            }
            
            //we did not find any duplicate value, then prev moves to next
            if(prev.next.next == cur) prev = prev.next;
            //if we find duplicate ,this is going to remove all of them
            prev.next = cur;
        }
        
        return dummy.next;
    }
    
    //just for reference, no need to understand this version
    public ListNode deleteDuplicates_recursive(ListNode head) {
        if (head == null) return null;
        
        if (head.next != null && head.val == head.next.val) {
            while (head.next != null && head.val == head.next.val) {
                head = head.next;
            }
            return deleteDuplicates(head.next);
        } else {
            head.next = deleteDuplicates(head.next);
        }
        return head;
    }
}
