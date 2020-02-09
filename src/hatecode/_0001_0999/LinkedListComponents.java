package hatecode._0001_0999;
import java.util.*;
public class LinkedListComponents {
/*
817. Linked List Components
We are given head, the head node of a linked list containing unique integer values.

We are also given the list G, a subset of the values in the linked list.

Return the number of connected components in G, where two values are connected if they appear consecutively in the linked list.

Example 1:

Input: 
head: 0->1->2->3
G = [0, 1, 3]
Output: 2
*/
    //this is tricky solutions,given the linkedlist 0-N-1 A, and G is subset of 0-N-1, 
    //want to know how many connected components in A for subset G
    
    //consective numbers in linedlist are defined as connected, so if there was only 1 number then 
    //we can say one connected components(TRICKY part)
    
    //so we use a set to include all integers in G and then go through the linkedlist, we have several cases:
    //1 current is in set, next is not, +1
    //2 current and next is in set, +1, 
    //3 consective multiple numbers are in set, +1, 
    //so you can see every case we have to +1 so we just need to make sure only current number is in set then
    //we can cover the above 3 cases. 
    public int numComponents2(ListNode head, int[] G) {
        Set<Integer> set = new HashSet<>();
        for(int i : G) set.add(i);
        int res = 0;
        while(head != null) {
            if (set.contains(head.val) && (head.next ==null || !set.contains(head.next.val))) res++;
            head = head.next;
        }
        
        return res;
    }
    //this is interview friendly and normal thinking:
    
    //so we start from head, and first while to make sure we can loop to end,
    //second loop is to help find how many connected components as defined in the problem. 
    public int numComponents(ListNode head, int[] G) {
         Set<Integer> set = new HashSet<>();
        for(int i : G) set.add(i);
        int res = 0;
        
        while(head != null) {
            //copy current head
            ListNode cur = head;
            boolean find = false;
            //find one connected components
            while(cur != null && set.contains(cur.val)) {
                find = true;
                cur = cur.next;
            }
            
            if (find) res ++;
            //head move to cur position
            head = cur == null ? cur : cur.next;
        }
        
        return res;
    }
    
}