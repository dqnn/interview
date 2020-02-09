package hatecode._0001_0999;

import java.util.HashMap;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SubarraySumEqualsK
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : TODO
 */
public class SubarraySumEqualsK {
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
    // time : O(n^2) space : O(1)
    public int subarraySum(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    res++;
                }
            }
        }
        return res;
    }

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
    public int subarraySum2(int[] nums, int k) {
        int res = 0;
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
    
  //using two pointers
    public int subarraySum_TwoPointers(int[] nums, int k) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        
        int res = 0;
        int leftSum = 0;
        for(int i = 0; i < nums.length; i++) {
            leftSum = nums[i];
            if (leftSum == k) res++;
            for(int j = i + 1; j < nums.length; j++) {
                //leftSum == k should be the case but the problems does not think so
                if (leftSum + nums[j] == k) {
                    res++;
                } 
                leftSum += nums[j];
            }
        }
        return res;
    }
}
