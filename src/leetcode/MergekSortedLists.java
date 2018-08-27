package leetcode;

import java.util.PriorityQueue;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MergekSortedLists
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : 23. Merge k Sorted Lists
 */
public class MergekSortedLists {
    /**
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
     *
     time : O(nlogk) where k is the number of linked lists
     space : O(n)


     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return sort(lists, 0, lists.length - 1);
    }

    public ListNode sort(ListNode[] lists, int lo, int hi) {
        if (lo >= hi) return lists[lo];
        int mid = (hi - lo) / 2 + lo;
        ListNode l1 = sort(lists, lo, mid);
        ListNode l2 = sort(lists, mid + 1, hi);
        return merge(l1, l2);
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        }
        l2.next = merge(l1, l2.next);
        return l2;
    }


    // use prioirtyqueue as sort
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        for (ListNode list : lists) {
            if (list != null) {
                queue.add(list);
            }
        }
        while (!queue.isEmpty()) {
            cur.next = queue.poll();
            cur = cur.next;
            if (cur.next != null) {
                queue.add(cur.next);
            }
        }
        return dummy.next;
    }
    
    // self solution, 
    //Time O(Nk), N is final elements number in final list, k is linked list number
    public ListNode mergeKLists3(ListNode[] lists) {
        if (lists == null || lists.length < 1) {
            return null;
        }
        int len = lists.length;
        ListNode dummy = new ListNode(0), cur = dummy;
        ListNode[] end = new ListNode[len];
        //we store a list of pointers which point current smallest val in the list
        for(int i = 0; i < len; i++) {
            end[i] = lists[i];
        }
        int minIdx = 0;
        boolean existed = true;
        while (existed) {
            existed = false;
            // the loop is to find the smallest in K lists
            for(int i = 0; i < end.length; i++) {
               if (end[i] != null) {
                   existed = true;
               }
               if (end[i] == null) {
                   continue;
               }
               
               if (end[minIdx] == null || end[minIdx].val >= end[i].val) {
                   minIdx = i;
               }
            }
            // insert to the end of the list
            if (end[minIdx] != null) {
                cur.next = end[minIdx];
                cur = cur.next;
                end[minIdx]= end[minIdx].next;
            }
        }
        cur.next = null;
        return dummy.next;
        
    }
}
