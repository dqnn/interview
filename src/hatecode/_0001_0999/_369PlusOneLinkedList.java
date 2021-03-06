package hatecode._0001_0999;

/**
 * File Name : PlusOneLinkedList
 * Date : Sep, 2018
 * Description : TODO
 */
public class _369PlusOneLinkedList {
    /**
     * 369. Plus One Linked List
     * Input:
     1->2->3

     Output:
     1->2->4

     time : O(n)
     space : O(1)
     * @param head
     * @return
     */

    // so the key is to find the element which is not 9, and we just increment it and mark all nodes 
    //after it become 0
    public ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode i = dummy;
        ListNode j = dummy;
        
        //so i is to locate where the range in linkedlist is impacted, 
        //from i to j they are all 9, if not, i would jump to j, and j is last. 
        
        //suppose j after 9 is not 9, i will jump to that node and no impact to previous 9
        while (j.next != null) {
            j = j.next;
            if (j.val != 9) i = j;
        }
        i.val++;
        i = i.next;
        while (i != null) {
            i.val = 0;
            i = i.next;
        }
        // means first digit is less than 9, so we just return next element
        //i first point to dummy, so j will move to head, but for edge case 
        //dummy->9->9->9, dummy will become 1, that's the reason
        if (dummy.val == 0) return dummy.next;
        return dummy;
    }

    /**

     1 -> 0 -> 0 -> 0

     * @param head
     * @return
     */
    public ListNode plusOne2(ListNode head) {
        head = reverse(head);
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        int carry = 1;
        while (carry > 0 || cur.next != null) {
            if (cur.next != null) {
                cur = cur.next;
                carry += cur.val;
                cur.val = carry % 10;
                carry /= 10;
            } else {
                cur.next = new ListNode(carry);
                cur = cur.next;
                carry = 0;
            }
        }
        return reverse(dummy.next);
    }

    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        return prev;
    }

}
