package hatecode._0001_0999;

/**
 * Description : 148. Sort List
 */
public class _148SortList {
    /**
     * Sort a linked list in O(n log n) time using constant space 
     * complexity.
     *
     * time : O(nlogn)
     * space : O(n)
     *
     * @param head
     * @return
     */
    //thinking process:
    //to sort a linkedList, we firstly find its middle node, then 
    //we recursively sort two parts and merge them together, so 
    //we need sort(ListNode head), merge(ListNode, ListNode) and 
    // findMiddle(ListNode)  these are all common ops for LinkedList

    /*
     * [4,2,1,3] 
     * 
     * middle = 2(slow), note fast (fast.next != null && fast.next.next != null), so it only forward once and pause at 2 
     * then we continue the same operation, cut each piece into 2 parts until we only have 1 or 2 node, then merge will start to work
     * 
     * 
     * 4 2 1 3  mid =2, 
     * 
     * then two list, [4,2]   [1, 3]   SortList + getMiddle break into 2 
     *                / \       / \
     *               [4] [2]   [1]  [3]  SortList + getMiddle break into 2 
     *                \   /     \    /
     *                [2, 4]     [1,3]  merge 
     *                      \    /
     *                      [1,2,3,4]   merge
        there are several key aspects
        1. when we get the middle, please note we use fast.next && fast.next.next != null, this means fast will NOT point to null, it will stop 
        last node(odd) or the one before last node 
        2. one edge case, only nodes, 1-->2, then it will break to [1] and [2] , if it is odd, [1,2,3]--> [1,2] and [3] 
     
     */
    
    //remembered all ops
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode middle = getMiddle(head);
        ListNode next = middle.next;
        middle.next = null;
        return merge(sortList(head), sortList(next));
    }

    //if total count is even, then it would return middle - 1, fast will point to 
    // the 2nd node from right
    //if total number is odd, then it would return middle - 1
    
    /*
     * this function return previous real middle 
     * 1->2->3->4, it would return pointer to point 2
     */
    private ListNode getMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        //this is to judge fast reaches the end
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    /*
     * this function merge two nodes according to their value
     * 
     * 
     */
    private ListNode merge(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (a != null && b != null) {
            if (a.val <= b.val) {
                cur.next = a;
                a = a.next;
            } else {
                cur.next = b;
                b = b.next;
            }
            cur = cur.next;
        }
        if (a == null) cur.next = b;
        else cur.next = a;
        return dummy.next;
    }
    
  //merge two sorted list, return result head
    public ListNode merge2(ListNode h1, ListNode h2){
        if(h1 == null){
            return h2;
        }
        if(h2 == null){
            return h1;
        }
        
        if(h1.val < h2.val){
            h1.next = merge2(h1.next, h2);
            return h1;
        }
        else{
            h2.next = merge2(h1, h2.next);
            return h2;
        }
        
    }
    
    public ListNode sortList2(ListNode head) {
        //bottom case
        if(head == null || head.next == null){
            return head;
        }
        
        //p1 move 1 step every time, 
        //p2 move 2 step every time, pre record node before p1
        ListNode p1 = head;
        ListNode p2 = head;
        ListNode pre = head;
        //find the middle node of the chain, fast will be 
        //condition
        while(p2 != null && p2.next != null){
            pre = p1;
            p1 = p1.next;
            p2 = p2.next.next;
        }
        //change pre next to null, 
        //make two sub list(head to pre, p1 to p2)
        pre.next = null;
        
        //handle those two sub list
        ListNode h1 = sortList2(head);
        ListNode h2 = sortList2(p1);
        
        return merge2(h1, h2);
        
    }
}
