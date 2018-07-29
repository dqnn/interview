package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : InsertionSortList
 * Creator : duqiang
 * Date : Nov, 2017
 * Description : 147. Insertion Sort List
 */
public class InsertionSortList {
    /**
     * 
     * Algorithm of Insertion Sort:

Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
It repeats until no input elements remain.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4
Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5
     * time : O(n^2)
     * space : O(1)
     *
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head;
        ListNode temp = null, prev = null;
        while (cur != null && cur.next != null) {
            
            if (cur.val <= cur.next.val) {
                //move to next
                cur = cur.next;
            } else {
             // cur point to next also
                // dummy->head->-1->0->3->2-5->7
                //        cur
                // temp--> 3, cur->0
                temp = cur.next;
                // dummy->head->-1->0->2->5->7
                cur.next = temp.next;
                // dummy->head->2->5->7
                // prev
                prev = dummy;
                // if prev next is lower than 3 which means we find the elment which is bigger then temp.
                //finally pre will point to 2
                // pre-->2
                while (prev.next.val <= temp.val) {
                    prev = prev.next;
                }
                // temp is 3, so 3-> 5->7
                temp.next = prev.next;
                // pre is 2, so 2->3->5->7
                prev.next = temp;
            }
        }
        return dummy.next;
    }
    
    
    public ListNode insertionSortList2(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        //record node before insertNode
        ListNode preNode = head;
        //record node need to be inserted
        ListNode insertNode = head.next;
        
        while(insertNode != null){
            //store next insert node
            ListNode nextInsert = insertNode.next;
            //insert before head
            if(insertNode.val <= head.val){
                preNode.next = insertNode.next;
                insertNode.next = head;
                head = insertNode;
            } else if (insertNode.val >= preNode.val){    //insert after tail
                preNode = preNode.next;
            } else {                                      //insert between head and tail
                ListNode compareNode = head;
                //start from the node after head, find insert position
                while(compareNode.next.val < insertNode.val)   {
                    compareNode = compareNode.next;
                }
                //insert
                preNode.next = insertNode.next;
                insertNode.next = compareNode.next;
                compareNode.next = insertNode;
            }
            //get next insert node
            insertNode = nextInsert;
        }
        return head;
    }
}
