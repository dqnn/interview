package hatecode._0001_0999;

import java.util.HashMap;
import java.util.Map;

public class _523ContinuousSubarraySum {
/*
 * 523. Continuous Subarray Sum
Given a list of non-negative numbers and a target integer k, 
write a function to check if the array has a continuous subarray of size 
at least 2 that sums up to the multiple of k, that is, sums up to n*k 
where n is also an integer.

Example 1:
Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
Example 2:
Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
Note:
The length of the array won't exceed 10,000.
You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 */
    /*
    interview friendly: O(n)/O(n)
    //if say about sub array, always trying to think from prefix sum

    the problem is to say: given one integer array, [23, 2, 4, 6, 7],  k=6, return whether it contains one subarray which its sum = n * k, n = 0, 1,2,3
    
     (sum + nk) %k = a%k, let' use one example 
     [23, 2, 4, 6, 7]
     [23, 25,29, 35, 42]
     [5,  1, 5, 5, 0]  ---- index array
     
     so it means that between two 5 there is subarray whose sum = n * 6

      sum %k + A[i] 

      sum[j] - sum[i] = A[j] + A[j-1].... + A[i+1]
                      = A

    */
    //
    //
    public boolean checkSubarraySum(int[] A, int k) {
        if (A == null || A.length < 1) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        //to avoid sum = k * n, then sum = 0, we 
        //like [0,0], k =0, examples, 
        // keyNote
        map.put(0, -1);
        int sum = 0;
        for(int i = 0; i< A.length;i++) {
            sum += A[i];
            // k can be 0, in that case, 0,-1 can help
            if (k!=0) sum %=k;
            if (map.containsKey(sum)){
                //note [i, j] did not include i, it starts from 
                //[i+1, j],so j - (i +1) + 1>=2
                if (i - map.get(sum) > 1) return true;
            
            } 
            
            //keyNote: we need else here, we want to maxmize the length of the array
            if(!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return false;
    }
}