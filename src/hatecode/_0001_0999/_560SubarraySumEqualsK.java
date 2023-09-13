package hatecode._0001_0999;

import java.util.HashMap;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SubarraySumEqualsK
 * Creator : professorX
 * Date : Sep, 2018
 * Description : TODO
 */
public class _560SubarraySumEqualsK {
    /**
     * 560. Subarray Sum Equals K
     * Given an array of integers and an integer k, you need to find 
     * the total number of continuous subarrays whose sum equals to k.

     Example 1:
     Input:nums = [1,1,1], k = 2
     Output: 2


     * @param nums
     * @param k
     * @return
     */

// time : O(n) space : O(n);
/*
Sum[i,j] = prefixSum[j] - prefixSum[i-1].

But we want S[i,j] = k

So we are looking for all pairs (i,j) such that, k = prefixSum[j] + prefixSum[i-1]. 
To make it easier to type out. Let pre_j = prefixSum[j]. Let pre_i = prefixSum[i-1]

Then,
k = pre_j - pre_i =>
k - pre_j = - pre_i =>
pre_j - k = pre_i.

But in this problem we don't explicitly store presums in an array. Instead @shawngao uses the 
variable 'sum' to store the presum computed up the current iteration of the algorithm. 
Basically sum represents pre_j in the algorithm.

In other words we can replace pre_j with the variable sum.

sum - k = pre_i. That's why we have sum - k and not k - sum
 */
    public int subarraySum2(int[] A, int k) {
        int res = 0;
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        //this is to avoid whole array sum == k
        //[1,2,3] k = 6, we need to have this array
        /*
         * another example is [1] k = 1
         */
        map.put(0, 1);

        for(int i = 0; i < A.length; i++) {
            sum += A[i];
            res += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
    
}
