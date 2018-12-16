package hatecode;
/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int _val,Node _prev,Node _next,Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
};
*/
public class FlattenAMultilevelDoublyLinkedList {
/*
430. Flatten a Multilevel Doubly Linked List
You are given a doubly linked list which in addition to the next and previous pointers,
 it could have a child pointer, which may or may not point to a separate doubly linked list. 
 These child lists may have one or more children of their own, and so on, 
 to produce a multilevel data structure, as shown in the example below.

Flatten the list so that all the nodes appear in a single-level, doubly linked list. 
You are given the head of the first level of the list.

 

Example:

Input:
 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL

Output:
1-2-3-7-8-11-12-9-10-4-5-6-NULL
 */
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node() {}

        public Node(int _val,Node _prev,Node _next,Node _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }
    }
    //thinking process: 
/*one hole here is to use recursive, recursive is easy to write but complexity is not good,
here the idea is as the example in problem, we first change to 
 1---2---3--7---8---9---10--4---5---6--NULL
            |   |
               11--12--NULL
*/
    public Node flatten(Node head) {
        if (head == null || head.next == null && head.child == null) {
            return head;
        }
        Node cur = head;
        //for linkedlist, here is the fist templates for the loop
        while (cur != null) {
            if (cur.child == null) {
                cur = cur.next;
                continue;
            }
            //handle one node has child
            //first save its next node
            Node next = cur.next;
            
            //as example, here is to find 10
            Node child = cur.child;
            while(child.next != null) {
                child = child.next;
            }
        
            child.next = next;
            if (next != null) {
                next.prev = child;
            }
            //change next to its child
            cur.next = cur.child;
            //here is tricky that each child node previ not points its parent, 
            // the problem does not state it clearly
            cur.child.prev = cur;
            //break the child pointer
            cur.child = null;
        }
        return head;
    }
}