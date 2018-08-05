package leetcode;

import java.util.Random;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LinkedListRandomNode
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : TODO
 */
public class LinkedListRandomNode {
    /**
     * 382. Linked List Random Node
     * Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.

Follow up:
What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?

Example:

// Init a singly linked list [1,2,3].
ListNode head = new ListNode(1);
head.next = new ListNode(2);
head.next.next = new ListNode(3);
Solution solution = new Solution(head);

// getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
solution.getRandom();
     *
     * time : O(n);
     * @param head
     */
/*
 * Basic idea:
Choose 1, 2, 3, ..., k first and put them into the reservoir.
For k+1, pick it with a probability of k/(k+1), and randomly replace a number in the reservoir.
For k+i, pick it with a probability of k/(k+i), and randomly replace a number in the reservoir.
Repeat until k+i reaches n


Proof:
For k+i, the probability that it is selected and will replace a number in the reservoir is k/(k+i)
For a number in the reservoir before (let's say X), the probability that it keeps staying in the reservoir is
P(X was in the reservoir last time) × P(X is not replaced by k+i)
= P(X was in the reservoir last time) × (1 - P(k+i is selected and replaces X))
= k/(k+i-1) × （1 - k/(k+i) × 1/k）
= k/(k+i)
When k+i reaches n, the probability of each number staying in the reservoir is k/n


Example
Choose 3 numbers from [111, 222, 333, 444]. Make sure each number is selected with a probability of 3/4
First, choose [111, 222, 333] as the initial reservior
Then choose 444 with a probability of 3/4
For 111, it stays with a probability of
P(444 is not selected) + P(444 is selected but it replaces 222 or 333)
= 1/4 + 3/4*2/3
= 3/4
The same case with 222 and 333
Now all the numbers have the probability of 3/4 to be picked
This Problem
This problem is the sp case where k=1

Reservoir Sampling: 
http://blog.jobbole.com/42550/
 */
    private ListNode head;
    private Random rmd;

    public LinkedListRandomNode(ListNode head) {
        this.head = head;
        rmd = new Random();
    }

    /** Returns a random node's value. */
    public int getRandom() {
        ListNode temp = head;
        int res = temp.val;
        int i = 1;
        // suppose this is stream data, actually we know it is not, but if it streaming data, 
        // ++i is the it will return number between 0 and i,  so we can stop anywhere
        while (temp.next != null) {
            temp = temp.next;
            if (rmd.nextInt(++i) == 0) {
                res = temp.val;
            }
        }
        return res;
    }
}
