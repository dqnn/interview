package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ReverseLinkedListII
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 92. Reverse Linked List II
 */
public class ReverseLinkedListII {
    /**
Reverse a linked list from position m to n. Do it in one-pass.

Note: 1 ≤ m ≤ n ≤ length of list.

Example:

Input: 1->2->3->4->5->NULL, m = 2, n = 4
Output: 1->4->3->2->5->NULL

     1->2->3->4->5
     p  c  t
1. temp point to 3 first,
2. 2-> 4, 
3. 3->2, 
4. 1->3
so it would be 1->3->2->4->5, 
               p  t  c
and then t will change to 4 again, so we continue this process

     time : O(n);
     space : O(1);

     * @param head
     * @param m
     * @param n
     * @return
     */
    //this is easier than reverseNinGroup, that problem needs to have a 
    //subs problem is reverse(ListNode begin, ListNode end)
    
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = dummy.next;
        //cur starts from 1 so we only need to move m - 1 steps
        for (int i = 1; i < m; i++) {
            cur = cur.next;
            pre = pre.next;
        }
        //since cur points to m already, so we walk n-1 - m + 1= n-m steps
        for(int i =m; i< n;i++) {
            ListNode temp = cur.next;
            cur.next = temp.next;
            temp.next = pre.next;
            pre.next = temp;
        }
        return dummy.next;
    }
    //we re-use the reverse(s, e) function which was created in LC 25. Reverse Nodes in k-Group
    //which reverse a group of ListNode, the reverse() function will reverse the nodes between the 
    //two input parameters(exclusive), and return the new head of this group, note: all links already created
    //well
    public ListNode reverseBetween_better(ListNode head, int m, int n) {
        if(head == null || head.next == null) return head;
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode s = dummy, e = dummy;
        m--;
        while ( m-- >0) s = s.next;
        while ( n-- >0) e = e.next;
        reverse(s, e.next);
        return dummy.next;
    }
    
    private ListNode reverse(ListNode s, ListNode e) {
        if(s == e || s.next == e) return s;
        ListNode cur = s.next;
        ListNode res;
        ListNode prev = s;
        res = cur;
        
        while(cur != e) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        
        s.next = prev;
        res.next = cur;
        
        return res;
    }
}
