package hatecode._0001_0999;

/**
 * Created by duqiang on 25/07/2017.
 */
public class _237DeleteNodeInALinkedList {
    /**
     * 237. Delete Node in a Linked List
     * Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.

     Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, the linked list should become 1 -> 2 -> 4 after calling your function.

     time : O(1);
     space : O(1);
     * @param node
     */

    // so we just adjust the node pointers
    public static void deleteNode(ListNode node) {
        if (node == null) return;
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
