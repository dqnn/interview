package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : AddTwoNumbers
 * Creator : duqiang
 * Date : Sep, 2017
 * Description : 2. Add Two Numbers
 * Similiar question: 2, 43, 445
 */
public class _002AddTwoNumbers {
    /**

    2:
     Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     Output: 7 -> 0 -> 8
     
     445:
     Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
     Output: 7 -> 8 -> 0 -> 7 
     
     higher position is different

     time : O(n)
     space : O(n)

     * @param l1
     * @param l2
     * @return
     */
    
    //Similar problem: multiply-strings, 43, 
    //also 445, the problem different is 
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        int sum = 0;
        ListNode cur = dummy;
        ListNode p1 = l1, p2 = l2;
        //this is how we visit the linked list, if they do not have same length
        //also apply to the case two OR conditions that we have visit both
        while (p1 != null || p2 != null) {
            if (p1 != null) {
                sum += p1.val;
                p1 = p1.next;
            }
            if (p2 != null) {
                sum += p2.val;
                p2 = p2.next;
            }
            cur.next = new ListNode(sum % 10);
            sum /= 10;
            cur = cur.next;
        }
        if (sum == 1) {
            cur.next = new ListNode(1);
        }
        return dummy.next;
    }
}
