package hatecode._0001_0999;

/**
 * Description : 61. Rotate List
 */
public class _061RotateList {
    /**
Given a linked list, rotate the list to the right by k places, 
where k is non-negative.

Example 1:

Input: 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL
Explanation:
rotate 1 steps to the right: 5->1->2->3->4->NULL
rotate 2 steps to the right: 4->5->1->2->3->NULL
Example 2:

Input: 0->1->2->NULL, k = 4
Output: 2->0->1->NULL
Explanation:
rotate 1 steps to the right: 2->0->1->NULL
rotate 2 steps to the right: 1->2->0->NULL
rotate 3 steps to the right: 0->1->2->NULL
rotate 4 steps to the right: 2->0->1->NULL


     time : O(n);
     space : O(1);

     * @param head
     * @param k
     * @return
     */
    // the whole logic is to rotate last k % len elements into front of the 
    // linkedlist so we have to get the length of the list and k = k % len;
    //also link last element to head
    // then next is another pointer to len - k % len - 1 elements from last,it point 
    // previous element which will become the head of the new linkedlist
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        ListNode index = head;
        int len = 1;
        //got to know the length of the list
        while (index.next != null) {
            index = index.next;
            len++;
        }
        //to a circle
        index.next = head;
        
        //find the ne head and break the pointer
        for (int i = 1; i < len - k % len; i++) {
            head = head.next;
        }
        ListNode res = head.next;
        head.next = null;
        return res;
    }
}
