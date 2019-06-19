package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ReverseNodesinkGroup
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 25. Reverse Nodes in k-Group
 */
public class ReverseNodesinkGroup {
    /**
Given a linked list, reverse the nodes of a linked list 
k at a time and return its modified list.

k is a positive integer and is less than or equal to the 
length of the linked list. If the number of nodes is not a 
multiple of k then left-out nodes in the end should remain 
as it is.

Example:

Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

Note:

Only constant extra memory is allowed.
You may not alter the values in the list's nodes, only nodes itself may be changed.

     time : O(n)
     space : O(n)

     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        int count = 0;
        ListNode cur = head;
        while (cur != null && count != k) {
            cur = cur.next;
            count++;
        }
        if (count == k) { // if k+1 node is found
            cur = reverseKGroup(cur, k);// reverse list with k+1 node as head
            // head - head-pointer to direct part, 
            // curr - head-pointer to reversed part;
            while (count-- > 0) { // reverse current k-group:
                ListNode temp = head.next; // tmp - next head in direct part
                head.next = cur; // preappending "direct" head to the reversed list 
                cur = head; //move head of reversed part to a new node
                head = temp;// move "direct" head to the next node in direct part
            }
            head = cur;
        }
        return head;
    }
    
    // interview friendly, reverse(s, e), s and e are exclusive
    // 
    public ListNode reverseKGroup_Best(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode s = dummy, e = head;
        int idx = 0;
        while(e != null) {
            idx++;
            //first idx % k is when idx = k, which means e point k-th node, head as 1st
            //so k =2, 0->1->2->3->4, s->0, e->2, we want reverse 1 and 2, but keep 0 there, 
            //so our reverse() function is to reverse the nodes between 0 and 3, 1 and 2 are reversed.
            //return 0->2->1->3->4, return 1
            if (idx % k == 0) {
                s = reverse(s, e.next);
                //we need this because e already point to first node, 
                //so we need s here to point to next   
                e = s.next;
            } else e = e.next;
        }
        return dummy.next;
    }
    /**
     * Reverse a link list between begin and end exclusively return the node which point to end
     * an example:
     * a linked list:
     * cur starts from begin.next
     * 0->1->2->3->4->5->6
     * |     |     |   
     * start cur   end
     * after call begin = reverse(start, 3)
     * 
     * 0->3->2->1->4->5->6
     *          |  |
     *      start end
     * @return the reversed list's 'begin' node, which is the precedence of node end
     */
    public ListNode reverse(ListNode start, ListNode end){
        ListNode cur = start.next;
        ListNode res;
        ListNode prev = start;
        res = cur;
        //cur last point to end
        while (cur != end){
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        //cur point to 4, prev = 3, 
        //but we want 1, so we reset begin, begin is 0, begin point to 3
        start.next = prev;
        //this is point to 4, it is the same like 1 but we need external dummy point to 4
        res.next = cur;
        return res;
    }
}
