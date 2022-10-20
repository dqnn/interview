package hatecode._0001_0999;

import java.util.Arrays;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LongestIncreasingSubsequence
 * Creator : professorX
 * Date : Aug, 2018
 * Description : 300. Longest Increasing Subsequence
 */
public class _300LongestIncreasingSubsequence {
    /**
     * Given an unsorted array of integers, find the length of longest increasing subsequence.

     For example,
     Given [10, 9, 2, 5, 3, 7, 101, 18],
     The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.
     Note that there may be more than one LIS combination, it is only necessary for you to return the length.

     [10, 9, 2, 5, 3, 7, 101, 18]

     res == size

     10 res = 0 i = 0 j = 0
     9 res = 1 i = 0 j = 0 mid = 0
     2 res = 1 i = 0 j = 0 mid = 0
     5 res = 1 i = 1 j = 1 mid = 0
     3 res = 2
     7 res = 2 i = 2 j = 2 mid = 1
     101 res = 3 i = 2 j = 3 mid = 1
     18 res = 4 i = 3 j = 3 mid = 3


     i, j 相当于tails的起点，终点
     tails : [2,3,7,18]

     time : O(nlogn)
     space : O(n)

     * @param nums
     * @return
     */
    
    //// the idea  is to maintain a list, and for each element in n, we want to find a position in 
    // tail to insert, it is more like insert sort, 
    // the way to identify the element which should be longest increasing sequence is the position for 
    //res is the variable to maintain the insert position for each element

    //another thinking process:
    //we have an array tail which stored the tail[i] means for len = i + 1 sub array, the smallest 
    //element, from 0->i sub array
    
    //so if we meet a bigger value than tail[last], then just append it, if 
    //we meet a value less than tail[i], which means we meet a smaller value in array compared to previous 
    //ones, then we find the correct position and update by binary search, so the key idea here is to keep
    //tracking of the for sub array 0->i, the smallest last element sequence = len +1, the reason why
    //we like smallest because it is greedy to have longest sequence
    
    //
    //LIS length, O(nlgn)/O(n)
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        /** we do not care about what elements are in each subsequence, 
         * we only care about tails of them,
         * because every time we only compare with their tails to decide which subsequence 
         * could we add new item and update the entire structure */
        //[1,3,5,4,7]->[1, 3, 4, 7, 0]
        //len = 1, tails = [1];
        //len = 2, tails = [1, 3]
        //len = 3, tails = [1, 3, 4] 
        //len = 4, tails = [1, 3, 4, 7]
       /**(1) if x is larger than all tails, append it, increase the size by 1
        * (2) if tails[i-1] < x <= tails[i], update tails[i] */
        int[] tails = new int[nums.length];
        int res = 0;
        for (int num : nums) {
            //we search from l->r, r is last iteration result
            int l = 0, r = res;
            // binary search on tails here can help to find which position for num
            // this templates is while(left < start), 2# template, we have one element left for 
            //post processing
            //binary search 2nd templates
            while (l < r) {
                int mid = l + (r - l) /2;
                if (tails[mid] < num) {
                    l = mid + 1;
                } else {// here is  >=
                    r = mid;
                }
            }
            
            // this is to update or insert a new value in tails, 
            // 2 4 9 3 7 8, when res = 3, num = 3, we finally will find and replace tail[1] = 4
            // to tail[1] = 3, and for this time, res will keep previous value
            tails[l] = num;
            // so if i == res, which means num is a correct in last longest sequence
            if (l == res) ++res;
            
            System.out.println(num + "-->" + Arrays.toString(tails));
        }
        //System.out.println(Arrays.toString(tails));
        return res;
    }
    
    // this is interview friendly for O(n^2)， brute force Only
    public int lengthOfLIS2(int[] A) {
        if (A == null || A.length < 1) {
            return 0;
        }
        int len = A.length;

        // dp[i] means if nums[i] as the last elements, the longest increasing sequence length
        int[] dp = new int[len];
        // at least 1 number for LIS
        Arrays.fill(dp, 1);

        // set max = 1, edge case like [2, 2] it will not go through the two loops,
        //also for [0] cases, we also need max to be 1 or else we have add if (len == 1) return 1;
        int max = 1;
        //we compare each nums[j] to nums[i]. if n[j] < n[i] then it should be included. 
        // so we update each dp[i] = max(dp[i],dp[j] + 1)
        //same: if nums[i] as last elment in array, what's the maxium increase array
        //[1,3,5,4,7]->dp[1,2,3,3,4]
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < i; j++) {
                if (A[j] < A[i]) {
                    // means nums[i] could be nums[j] next element, 
                    //that's why we need compare to n[j]
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    max = Math.max(dp[i], max);
                }
            }
        }
        return max;
    }
    public static void main(String[] args) {
        int[] in= {1, 3,5,4,7};
        System.out.println(lengthOfLIS(in));
    }
}
