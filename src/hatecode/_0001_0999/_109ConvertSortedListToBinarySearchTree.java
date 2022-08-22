package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ConvertSortedListtoBinarySearchTree
 * Creator : professorX
 * Date : Oct, 2017
 * Description : 109. Convert Sorted List to Binary 
 * Search Tree
 * Given a singly linked list where elements are sorted 
 * in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined 
as a binary tree in which the depth of the two subtrees of 
every node never differ by more than 1.

Example:

Given the sorted linked list: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which 
represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5
 */
public class _109ConvertSortedListToBinarySearchTree {

    /**

     time : O(n);
     space : O(n);

     * @param head
     * @return
     */

    //thinking process: O()/O()
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        return toBST(head, null);
    }

    public TreeNode toBST(ListNode head, ListNode tail) {
        if (head == tail) return null;
        ListNode slow = head;
        ListNode fast = head;
        // this is one way to find the middle node since fast = 2 * slow speed
        // this is not high accuracy way to find the middle way but it should be like
        //R s + (e - s) / 2, the same
        // fast != null maybe len % 2 =0, so fast and fast.next will be getting null
        // depends the length of the list, if it is odd, then fast will be null or
        // fast.next will be tail first.
        while (fast != tail && fast.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = toBST(head, slow);
        // why we always specify tail is the last elements instead of fast?
        // fast here may not be correct since it may be tail--> fast
        root.right = toBST(slow.next, tail);
        return root;
    }
}
