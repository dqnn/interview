package hatecode._0001_0999;
public class _707DesignLinkedList {
/*
707. Design Linked List
MyLinkedList linkedList = new MyLinkedList();
linkedList.addAtHead(1);
linkedList.addAtTail(3);
linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
linkedList.get(1);            // returns 2
linkedList.deleteAtIndex(1);  // now the linked list is 1->3
linkedList.get(1);            // returns 3
*/

    /** Initialize your data structure here. */
    
    class Node {
        int val;
        Node next, prev;
        
        public Node(int val) {this.val = val;}
    }
    
    Node head, tail;
    int size;
    public DesignLinkedList() {
        this.head = new Node(0);
        this.tail = new Node(0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index >= size) return -1;
        Node node = head;
        while(index-- >= 0) {
            node = node.next;
        }
        return node.val;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        addAtIndex(0,val);
        
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        addAtIndex(size,val);
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    //key method, needs to remember how to change the pointer
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) return;
        Node cur = head;
        while(--index >= 0) {
            cur = cur.next;
        }
        
        Node newNode = new Node(val);
        
        //change the new node
        newNode.next = cur.next;
        newNode.prev = cur;
        //change the old nodes in double linked list
        cur.next.prev = newNode;
        cur.next = newNode;
          
        size++;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (size == 0 || index < 0 || index >= size) return;
        
        Node cur = head;
        while(index-- >= 0) {
            cur = cur.next;
        }
        
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
        //these two is optional
        cur.prev = null;
        cur.next = null;
       
        size--;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */