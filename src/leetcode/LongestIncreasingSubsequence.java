package leetcode;

import java.util.Arrays;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LongestIncreasingSubsequence
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : 300. Longest Increasing Subsequence
 */
public class LongestIncreasingSubsequence {
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
    //res is the variabe to maintain the insert position for each element
    
    //another explanation:
    /*
 I use an array dp[] to store the largest elements in the increasing subsequences and the array's 
 index is the length of the subsequence (since this, we can make sure that the array dp[] is 
     * in-order). 
     * The idea is keep checking if nums[i] is bigger than dp[len] or not: nums[i] > dp[len] means we can 
     * retrieve a longer subsequence by adding the current element nums[i] into the result, so we just increase 
     * the pointer 'len' by one and put the current element into the new index; otherwise we need to do a binary 
     * search to find out the index of the largest element st. dp[index] < nums[i] && dp[index + 1] > nums[i] and 
     * update the dp[index] with value nums[i].

Let's see an example:
intput: 2 4 9 3 7 8

The array 'dp' looks like these in first two iterations:

2 4 0 0 0 (len = 1)

2 4 9 0 0 (len = 2)

len =1  means if max LIS length is 1, 
len =2  means if max LIS length is 2
In the third iteration, we notice that dp[len] ('9' here) > nums[i] ('3' here), 
so we update the dp[index] with value '3', then we got:

2 3 9 0 0 (len = 2) <---- Attention here, this array doesn't mean we can retrieve subsequence [2,3,9], 
it only means the largest element in a length 1 subsequence is '2' ([2]), the largest element in a 
length 2 subsequence is '3' ([2,3]) and the largest element in a length 3 subsequence is '9' ([2,4,9])
 by far.

In next iteration, we update the dp[index] with value '7' again and we got:
2 3 7 0 0 (len = 2)

In the final round, obviously dp[len] (7) < nums[i] (8) so we increase the 'len' by one and put '8' into dp[3]
 then we got:
2 3 7 8 0 (len = 3)

Clearly len + 1 = 4 is our result : )
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        //to store the LIS last elements in array
        int[] tails = new int[nums.length];
        int res = 0;
        for (int num : nums) {
            //we search from i->j, j is last iteration result
            int i = 0, j = res;
            // binary search on tails here can help to find which position for num
            // this templates is while(left < start), 2# template, we have one element left for 
            //post processing
            while (i != j) {
                int mid = i + (j - i) /2;
                if (tails[mid] < num) {
                    i = mid + 1;
                } else {// please note here should be >=
                    j = mid;
                }
            }
            // this is to update or insert a new value in tails, 
            // 2 4 9 3 7 8, when res = 3, num = 3, we finally will updates tail[1] = 4
            // to tail[1] = 3, and for this time, res will keep previous value
            tails[i] = num;
            // so if i == res, which means num is a correct in last longest sequence
            if (i == res) ++res;
        }
        return res;
    }
    
    // this is interview friendly for O(n^2)
    public int lengthOfLIS2(int[] n) {
        if (n == null || n.length < 1) {
            return 0;
        }
        int len = n.length;

        // dp[i] means if nums[i] as the last elements, the longest increasing sequence length
        int[] dp = new int[len];
        // at least 1 number for LIS
        Arrays.fill(dp, 1);

        // set max = 1, edge case like [2, 2] it will not go through the two loops,
        //also for [0] cases, we also need max to be 1 or else we have add if (len == 1) return 1;
        int max = 1;
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < i; j++) {
                if (n[j] < n[i]) {
                    // means nums[i] could be nums[j] next element, 
                    //that's why we need compare to n[j]
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    max = Math.max(dp[i], max);
                }
            }
        }
        return max;
    }
}
