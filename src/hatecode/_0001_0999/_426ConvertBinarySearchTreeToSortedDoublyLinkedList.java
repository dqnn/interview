package hatecode._0001_0999;

import java.util.*;
public class _426ConvertBinarySearchTreeToSortedDoublyLinkedList {
/*
 * 426. Convert Binary Search Tree to Sorted Doubly Linked List
Convert a BST to a sorted circular doubly-linked list in-place. Think of the left and 
right pointers as synonymous to the previous and next pointers in a doubly-linked list.

Let's take the following BST as an example, it may help you understand the problem better:
We want to transform this BST into a circular doubly linked list. Each node in a doubly linked list has a 
predecessor and successor. For a circular doubly linked list, the predecessor of the first element 
is the last element, and the successor of the last element is the first element.

The figure below shows the circular doubly linked list for the BST above. The "head" symbol 
means the node it points to is the smallest element of the linked list.
Specifically, we want to do the transformation in place. After the transformation, the left pointer of the 
tree node should point to its predecessor, and the right pointer should point to its successor. We should 
return the pointer to the first element of the linked list.

The figure below shows the transformed BST. The solid line indicates the successor relationship, while the 
dashed line means the predecessor relationship.
 */
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
    //interview friendly, 
    //thinking process: the problem is given BST, covert to sorted double linked list
    //BST, sorted, so we want to try inorder visit, 
    //the inorder visit: we find most left leaf which will be head of the double linked list
    // so we use dummy.next = node, node.prev = dummy, and we move  prev  point to this node
    Node prev = null; //this is the key to remember previous node
    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        
        Node dummy = new Node(0, null, null);
        prev = dummy; 
        //inorder visit the tree
        helper(root);
        //here prev point to most right node, so point to first node, head 
        prev.right = dummy.right; //circuit 
        //point to previous
        dummy.right.left = prev;
        //return left
        return dummy.right;
    }
    //we use inOrder visit so the access order is the order of the sorted double link list
    //we did not use return value in recursive functions
    private void helper(Node node) {
        if (node == null) return;
        helper(node.left);
        
        //here come to 1, 
        prev.right = node;
        node.left = prev;
        prev = node;
        
        helper(node.right);
    }
    
    public Node treeToDoublyList_Stack(Node root) {
        if (root == null) {
            return null;
        }

        Node first = null;
        Node last = null;

        Deque<Node> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (first == null) {
                first = root;
            }
            if (last != null) {
                last.right = root;
                root.left = last;
            }
            last = root;
            root = root.right;
        }
        first.left = last;
        last.right = first;
        return first;
    }
}