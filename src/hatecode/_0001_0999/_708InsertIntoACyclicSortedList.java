package hatecode._0001_0999;
public class _708InsertIntoACyclicSortedList {
/*
 * 708. Insert into a Cyclic Sorted List
Given a node from a cyclic linked list which is sorted in ascending order, 
write a function to insert a value into the list such that it remains a cyclic 
sorted list. The given node can be a reference to any single node in the list, 
and may not be necessarily the smallest value in the cyclic list.

If there are multiple suitable places for insertion, you may choose any place 
to insert the new value. After the insertion, the cyclic list should remain sorted.

If the list is empty (i.e., given node is null), you should create a new single 
cyclic list and return the reference to that single node. Otherwise, 
you should return the original given node.


 */
    
    class Node {
        public int  val;
        public Node next;
        public Node() {
        }
        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }
    //interview friendly
    //thinking process: the problem is to say given a circle single list as a head
    // and a value, the list is ascend, but head may not point to smallest value
    
    //it is like you may start any element in a sorted array so you need to decode which phrase where you
    //are
    //to insert this value into the list and return head
    //so we have 3 cases:
    //a point to middle point, next is still bigger, then i need to find a place is in middle until 
    // last one
    //b: point to last one, next is min one
    //c: the list is flat
    public Node insert(Node start, int x) {
        // if start is null, create a node pointing to itself and return
        if (start == null) {
            Node node = new Node(x, null);
            node.next = node;
            return node;
        }
        // is start is NOT null, try to insert it into correct position
        Node cur = start;
        while (true) {
            // case 1A: has a tipping point, still climbing
            //example: [1,3,4] x =2
            if (cur.val < cur.next.val) { 
                if (cur.val <= x && x <= cur.next.val) { // x in between cur and next
                    insertAfter(cur, x);
                    break;
                }
            // case 1B: cur is the max node, the value is smallest or biggest
            //example [1,3,4] x= 0 or 5
            } else if (cur.val > cur.next.val) { 
                if (cur.val <= x || x <= cur.next.val) { // cur is the tipping point, x is max or min val
                    insertAfter(cur, x);
                    break;
                }
            // case 2: NO tipping point, all flat. we already visit one round
            //example: [2,2,5] x=0 
            } else {
                if (cur.next == start) {  // insert x before we traverse all nodes back to start
                    insertAfter(cur, x);
                    break;
                }
            }
            // None of the above three cases met, go to next node
            cur = cur.next;
        }
        return start;
    }
    
    // insert value x after Node cur
    private void insertAfter(Node cur, int x) {
        cur.next = new Node(x, cur.next);
    }
  
    
    public Node insert1(Node start, int x) {
        // if start is null, create a node pointing to itself and return
        if (start == null) {
            Node node = new Node(x, null);
            node.next = node;
            return node;
        }
        // if start is not null, try to insert it into correct position
        // 1st pass to find max node
        Node cur = start;
        while (cur.val <= cur.next.val && cur.next != start) 
            cur = cur.next;
        // 2nd pass to insert the node in to correct position
        Node max = cur;
        Node dummy = new Node(0, max.next); // use a dummy head to make insertion process simpler
        max.next = null; // break the cycle
        cur = dummy;
        while (cur.next != null && cur.next.val < x) {
            cur = cur.next;
        }
        cur.next = new Node(x, cur.next); // insert
        Node newMax = max.next == null ? max : max.next; // reconnect to cycle
        newMax.next = dummy.next;
        return start;
    }
}