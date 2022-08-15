package hatecode._1000_1999;

import java.util.*;
public class _1944NumberofVisiblePeopleinaQueue {
/*
1944. Number of Visible People in a Queue
There are n people standing in a queue, and they numbered from 0 to n - 1 in left to right order. You are given an array heights of distinct integers where heights[i] represents the height of the ith person.

A person can see another person to their right in the queue if everybody in between is shorter than both of them. More formally, the ith person can see the jth person if i < j and min(heights[i], heights[j]) > max(heights[i+1], heights[i+2], ..., heights[j-1]).

Return an array answer of length n where answer[i] is the number of people the ith person can see to their right in the queue.

 

Example 1:



Input: heights = [10,6,8,5,11,9]
Output: [3,1,2,1,1,0]
Explanation:
Person 0 can see person 1, 2, and 4.
Person 1 can see person 2.
Person 2 can see person 3 and 4.
Person 3 can see person 4.
Person 4 can see person 5.
Person 5 can see no one since nobody is to the right of them.

Similar problems:

84 Largest Rectangle in Histogram
214 Shortest Palindrome
239 Sliding Window Maximum
316 Remove Duplicate Letters
321 Create Maximum Number
402 Remove K Digits
456 132 Pattern
496 Next Greater Element I
503. Next Greater Element II
654 Maximum Binary Tree
739. Daily Temperatures
768 Max Chunks To Make Sorted II
862 Shortest Subarray with Sum at Least K
889 Construct Binary Tree from Preorder and Postorder Traversal
901 Online Stock Span
907 Sum of Subarray Minimums
1019. Next Greater Node In Linked List
1475 Final Prices With a Special Discount in a Shop
*/
    //thinking process: O(n)/O(1)
    
    //the problem is to say: given one integer array A, it presents peoples height
    //when we look from left to right, return one array res, res[i] means how many people
    //he can see from position i to right.
    /* heights = [10,6,8,5,11,9]
     *           [3, 1,2,1,1, 0] 
     * notes: 0 can see 1 2 and 4, he cannot see 3 because 3 is hidden by 2
     * 
     * 
     * we use monotonic stack to record the decreased heights from left to right
     */
    public int[] canSeePersonsCount(int[] A) {
        
        int n = A.length;
        int[] res = new int[n];
        
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i< n; i++) {
            while(!stack.isEmpty() && A[stack.peek()] < A[i]) {
                res[stack.pop()]++;
            }
            
            if (!stack.isEmpty()) {
                res[stack.peek()]++;
            }
            stack.push(i);
        }
        
        return res;
    }
}