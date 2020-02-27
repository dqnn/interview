package hatecode._0001_0999;

import java.util.HashSet;
import java.util.Set;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : IntersectionofTwoLinkedLists
 * Creator : duqiang
 * Date : Oct, 2017
 * Description : 160. Intersection of Two Linked Lists
 */
public class _160IntersectionofTwoLinkedLists {
    /**
     * For example, the following two linked lists:

     A:          a1 → a2
                         ↘
                         c1 → c2 → c3
                         ↗
     B:     b1 → b2 → b3
     begin to intersect at node c1.

     time : O(n);
     space : O(1);

     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        int lenA = len(headA);
        int lenB = len(headB);
        if (lenA > lenB) {
            while (lenA != lenB) {
                headA = headA.next;
                lenA--;
            }
        } else {
            while (lenA != lenB) {
                headB = headB.next;
                lenB--;
            }
        }
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    public int len(ListNode head) {
        int len = 1;
        while (head != null) {
            head = head.next;
            len++;
        }
        return len;
    }

    /**
     *
     A:          a1 → a2
                         ↘
                         c1 → c2 → c3
                         ↗
     B:     b1 → b2 → b3
     begin to intersect at node c1.

     A : a1 → a2 -> c1 → c2 → c3 -> b1 → b2 → b3 -> c1 → c2 → c3
     B : b1 → b2 → b3 -> c1 → c2 → c3 -> a1 → a2 -> c1 → c2 → c3

     time : O(m + n);
     space : O(1);

     * @param headA
     * @param headB
     * @return
     */

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode a = headA;
        ListNode b = headB;
        /*
         * //
        // If one of them reaches the end earlier then reuse it 
        // by moving it to the beginning of other list.
        // Once both of them go through reassigning, 
        // they will be equidistant from the collision point.
        //
         */
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }
    
    //using set to detect first node
    public ListNode getIntersectionNode3(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return null;
        }
        
        Set<ListNode> set = new HashSet<>();
        while(a != null) {
            set.add(a);
            a = a.next;
        }
        
        while(b != null) {
            if (set.contains(b)) {
                return b;
            }
            b = b.next;
        }
        
        return null;
    }
}
