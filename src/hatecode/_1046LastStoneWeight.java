package hatecode;

import java.util.*;
public class _1046LastStoneWeight {
/*
1046. Last Stone Weight
We have a collection of rocks, each rock has a positive integer weight.

Each turn, we choose the two heaviest rocks and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:

If x == y, both stones are totally destroyed;
If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
At the end, there is at most 1 stone left.  Return the weight of this stone (or 0 if there are no stones left.)

 

Example 1:

Input: [2,7,4,1,8,1]
Output: 1
Explanation: 
We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of last stone.
*/
    //O(n)
    //thinking process:
    //given an array  A, every time we choose two biggest number, 
    //if they are the same, the two disappear, if not bigger-smaller
    //return last or 0 if there is none
    
    //so
    public int lastStoneWeight_Best(int[] A) {
        int[] buckets = new int[1001];
        for (int i = 0; i < A.length; i++) {
            buckets[A[i]]++;
        }

        int slow = buckets.length - 1;   //start from the big to small
        while (slow > 0) {
		// If the number of stones with the same size is even or zero, 
		// these stones can be totally destroyed pair by pair or there is no such size stone existing, 
		// we can just ignore this situation.
		
        // When the number of stones with the same size is odd, 
		// there should leave one stone which is to smash with the smaller size one.
            if (buckets[slow]%2 != 0) {
                int fast = slow - 1;
                while (fast > 0 && buckets[fast] == 0) {
                    fast--;
                }
                if (fast == 0) break;
                buckets[fast]--;
                buckets[slow - fast]++;
            }
            slow--;
        }
        return slow;
    }
    
    
    public int lastStoneWeight(int[] A) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b)-> b - a);
        Arrays.stream(A).forEach(e->pq.offer(e));
        for (int i = 0; i < A.length - 1; ++i)
            pq.offer(pq.poll() - pq.poll());
        return pq.poll();
    }
    
    
    
}