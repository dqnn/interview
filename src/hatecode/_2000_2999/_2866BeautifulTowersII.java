package hatecode._2000_2999;

import java.util.*;

public class _2866BeautifulTowersII {
/*
2865. Beautiful Towers I, same solution 
2866. Beautiful Towers II
You are given a 0-indexed array maxHeights of n integers.

You are tasked with building n towers in the coordinate line. The ith tower is built at coordinate i and has a height of heights[i].

A configuration of towers is beautiful if the following conditions hold:

1 <= heights[i] <= maxHeights[i]
heights is a mountain array.
Array heights is a mountain if there exists an index i such that:

For all 0 < j <= i, heights[j - 1] <= heights[j]
For all i <= k < n - 1, heights[k + 1] <= heights[k]
Return the maximum possible sum of heights of a beautiful configuration of towers.

 

Example 1:

Input: maxHeights = [5,3,4,1,1]
Output: 13

[6,5,3,9,2,7]--> 22 
*/

/*
 * thinking process: O(n)/O(n)
 * 
 * the problem is to say: given one integer array,  for each A[i], you can assume it is largest number, 
 * then all numbers on its left must be min(A[i], A[k]), k =0... i -1, but A[k] should also needs to be smaller than its neighbors 
 * 
 * 
 * for example  [6,5,3,9,2,7] suppose A[1] is the max one, then 
 *              [5,5,3,3,2,2] ->20   here fo A[3] = 9, it cannot use 5 since it should be smaller than 3, so only can be 3 
 * suppose 9 is max
 *              [3,3,3,9,2,2]->22 correct answer
 * return the max sum of the possible array 
 * 
 * 
 * so 
 * 
 */
    public long maximumSumOfHeights(List<Integer> A) {
         if (A == null || A.size() < 1) return 0;
        
        int n = A.size();

        long[] left = new long[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        long res = 0, cur = 0;
        for (int i = 0; i < n; i++) {
            while (stack.size() > 1 && A.get(stack.peek()) > A.get(i)) {
                int j = stack.pop();
                cur -= 1L * (j - stack.peek()) * A.get(j);
            }
            cur += 1L * (i - stack.peek()) * A.get(i);
            stack.push(i);
            left[i] = cur;
        }

        stack.clear();
        stack.push(n);
        cur = 0;
        for (int i = n - 1; i >= 0; i--) {
            while (stack.size() > 1 && A.get(stack.peek()) > A.get(i)) {
                int j = stack.pop();
                cur -= 1L * -(j - stack.peek()) * A.get(j);
            }
            cur += 1L * -(i - stack.peek()) * A.get(i);
            stack.push(i);
            res = Math.max(res, left[i] + cur - A.get(i));
        }

        return res;
    }
    
    
    
}