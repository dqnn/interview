package leetcode;

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
    
    // interview friendly
    public ListNode reverseKGroup2(ListNode head, int k) {
        ListNode begin;
        if (head==null || head.next ==null || k==1)
            return head;
        ListNode dummyhead = new ListNode(-1);
        dummyhead.next = head;
        begin = dummyhead;
        int i=0;
        while (head != null){
            i++;
            if (i%k == 0){ //i = 0, k, 2k, 3k, etc
                begin = reverse(begin, head.next);
                head = begin.next;
            } else {
                head = head.next;
            }
        }
        return dummyhead.next;
        
    }
    /**
     * Reverse a link list between begin and end exclusively
     * an example:
     * a linked list:
     * cur starts from begin.next
     * 0->1->2->3->4->5->6
     * |        |      |   
     * begin    cur   end
     * after call begin = reverse(begin, end)
     * 
     * 0->3->2->1->4->5->6
     *          |  |
     *      begin end
     * @return the reversed list's 'begin' node, which is the precedence of node end
     */
    public ListNode reverse(ListNode begin, ListNode end){
        ListNode curr = begin.next;
        ListNode next, first;
        ListNode prev = begin;
        first = curr;
        while (curr!=end){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        begin.next = prev;
        first.next = curr;
        return first;
    }
}
