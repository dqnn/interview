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
}
