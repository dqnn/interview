package hatecode._1000_1999;

import java.util.*;

import hatecode._0001_0999.ListNode;
public class _1171RemoveZeroSumConsecutiveNodesFromLinkedList {
/*
1171. Remove Zero Sum Consecutive Nodes from Linked List
Given the head of a linked list, we repeatedly delete consecutive sequences of nodes that sum to 0 until there are no such sequences.

After doing so, return the head of the final linked list.  You may return any such answer.

 

(Note that in the examples below, all sequences are serializations of ListNode objects.)

Example 1:

Input: head = [1,2,-3,3,1]
Output: [3,1]
*/
    //thinking process: O(n)/O(1)
    
    //the problem is to say: given one linkedList, remove the sub-linklist nodes
    //which their sum = 0.  like [1,2,-3,3,1]-->1+2-3 = 0, so-->[3,1], we just remove
    //from beginning to end
    
    //so if we think sub array sum = 0, then it is easy to think about prefix Sum,
    //we use a dummy node, just in case the head is included, 
    //the code first while loop is to visit all nodes, 2nd while loop is 
    //to remove the nodes which sum = 0; 
    public ListNode removeZeroSumSublists(ListNode head) {
        if(head == null) return head;
        
        ListNode dummy = new ListNode(0), cur = dummy;
        dummy.next = head;
        int preSum = 0;
        Map<Integer, ListNode> map = new HashMap<>();
        while (cur != null) {
            preSum += cur.val;
            //means a find a sub linked list sum = 0;
            if (map.containsKey(preSum)) {
                cur =  map.get(preSum).next;
                //p is sub linked list sum
                int p = preSum + cur.val;
                // p == sum means we find all nodes
                while (p != preSum) {
                    map.remove(p);
                    cur = cur.next;
                    p += cur.val;
                }
                //this is to mark node prioir first node to last one's next
                map.get(preSum).next = cur.next;
            } else {
                map.put(preSum, cur);
            }
            cur = cur.next;
        }
        return dummy.next;
    }
    
    //this is same solution as above, but this one is more neat and we did not remove 
    //the old nodes pointer to null
    public ListNode removeZeroSumSublists_2PASS(ListNode head) {
        if (head==null) return null;
        int sum = 0;
        ListNode dmy = new ListNode(0);
        dmy.next = head;
        Map<Integer, ListNode> map = new HashMap<>();
        map.put(0, dmy);
        while (head != null) {
            sum += head.val;
            map.put(sum, head);
            head = head.next;
        }
        
        head = dmy;
        sum = 0;
        while (head != null) {
            sum += head.val;
            ListNode dup = map.get(sum);
            if (dup != head) {
                // remove everything from head.next and dup
                head.next = dup.next;
            }
            head = head.next;
        }
        return dmy.next;
    }
}