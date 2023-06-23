package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : PartitionList
 * Date : Sep, 2018
 * Description : 86. Partition List
 */
public class _086PartitionList {
    /**
     * Given a linked list and a value x, partition it such that all nodes less 
     * than x come before nodes
     * greater than or equal to x.

     You should preserve the original relative order of the nodes in each of the two partitions.

     For example,
     Given 1->4->3->2->5->2 and x = 3,
     return 1->2->2->4->3->5.


     1->4->3->2->5->2 and x = 3
     smallHead -> 1 -> 2 -> 2 ->
                          small
     bigHead -> 4 -> 3 -> 5 ->
                         big

     time : O(n)
     space : O(n)


     * @param head
     * @param x
     * @return
     */
    // how we solve this:
    // requirement is less than x should be placed before x while greater or equals to x should be placed 
    // after x. 
    // so we creating two list head, smallHead and new head, 
    // then we visit the list from head via while(head != null)
    // since smaller nodes should be placed before x, we use < first and copy the value of each node
    // if bigger or equals, we copy the value and placed after BigHead, 
    // at last, small move to last and we should use small.next = bigger.next so we can 
    //join them together. and return the smallerHead
    //we do not have to create new nodes
    public ListNode partition(ListNode head, int x) {
        ListNode big = new ListNode(-1), small = new ListNode(-1);
        ListNode BigHead = big, smallHead = small;
        
        while(head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                big.next = head;
                big = big.next;
            }
            head = head.next;
        }
        
        big.next = null;
        small.next = BigHead.next;
        
        return smallHead.next;
    }
}
