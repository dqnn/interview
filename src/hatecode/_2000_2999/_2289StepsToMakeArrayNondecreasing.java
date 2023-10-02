package hatecode._2000_2999;

import java.util.*;

public class _2289StepsToMakeArrayNondecreasing {
/*
2289. Steps to Make Array Non-decreasing

You are given a 0-indexed integer array nums. In one step, remove all elements nums[i] where nums[i - 1] > nums[i] for all 0 < i < nums.length.

Return the number of steps performed until nums becomes a non-decreasing array.

 

Example 1:

Input: nums = [5,3,4,4,7,3,6,11,8,5,11]
Output: 3
Explanation: The following are the steps performed:
- Step 1: [5,3,4,4,7,3,6,11,8,5,11] becomes [5,4,4,7,6,11,11]
- Step 2: [5,4,4,7,6,11,11] becomes [5,4,7,11,11]
- Step 3: [5,4,7,11,11] becomes [5,7,11,11]
[5,7,11,11] is a non-decreasing array. Therefore, we return 3.


another good example: [1,5,4,1,2,3] -- > 3, note 5 eat 4 and 4 eat 1 at the same time 

*/

/*
 * thinking process: O(n)/O(n)
 * 
 * the problem is to say given one integer array A, if A[i-1] > A[i], then remove A[i], it happened only once for one round 
 * next round start from left and the same process, return how many rounds needed to make A non-decreasing 
 * 
 * 
 * [a, count], count means for number a, how many numbers are cmpacted by the stack most close biggest left side number
 * [5,3,4,4,7,3,6,11,8,5,11]
 * 
 * [5, 0],[4, 3], 
 * [11,1]
 * 
 * max =3å
 * 
 * so when we meet 7, all previous will be evicted, and we will start another round of it 
 * 
 * if [1,5,4,1,2,3]
 * 
 * 
 */
    public static int totalSteps(int[] A) {
        if(A == null || A.length < 1) return 0;
        
        Stack<int[]> st = new Stack<>();
        int max = Integer.MIN_VALUE;
        for(int a : A) {
            int temp = 0;
            while(!st.isEmpty() && st.peek()[0] <= a) {
                temp = Math.max(temp, st.pop()[1]);
            }
            temp = st.isEmpty() ? 0: temp + 1;
            max = Math.max(max, temp);
            st.push(new int[]{a, temp});
        }
        
        return max;
    }


    public static void main(String[] args) {
        System.out.println(totalSteps(new int[]{5,3,4,4,7,3,6,11,8,5,11}));
    }
}
