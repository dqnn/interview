package hatecode._1000_1999;
public class _1712WaysToSplitArrayIntoThreeSubarrays {
/*
1712. Ways to Split Array Into Three Subarrays
A split of an integer array is good if:

The array is split into three non-empty contiguous subarrays - named left, mid, right respectively from left to right.
The sum of the elements in left is less than or equal to the sum of the elements in mid, and the sum of the elements in mid is less than or equal to the sum of the elements in right.
Given nums, an array of non-negative integers, return the number of good ways to split nums. As the number may be too large, return it modulo 109 + 7.

 

Example 1:

Input: nums = [1,1,1]
Output: 1
*/
    //O(n)/O(n)
    
    //thinking process: the problem is to say: given a positive integer array, 
    //, cut the array to 3 subarrays, then each sum of each subarray satisfy:
    //sum(l) <= sum(m) <= sum(r), 
    
    //we use two pointers to solve this problem
    public int waysToSplit(int[] A) {
        if (A == null || A.length < 1) return 0;
        
        int n = A.length;
        int[] sum = new int[n + 1];
        for(int i = 1; i<=n; i++) {
            sum[i] +=  sum[i-1] + A[i-1];
        }
        
        long res = 0;
        final int MOD = 1000000000 + 7;
        
        // |______|________|_______|________|
        // 1      i        l       r        n
        // i 表示第一刀的位置，枚举第一刀的位置，计算第二刀的可选位置数
        for(int i = 1, l =2,r = 2; i <= n - 1; i++) {
            l = Math.max(l, i+1);
            r = Math.max(r, i+1);
            
            
            while(l <= n-1 && sum[l] - sum[i] < sum[i]) {
                l++;
            }
            
            while(r <= n-1 && sum[n] - sum[r] >= sum[r] - sum[i]) {
                r++;
            }
            
            if (l < r) res += r-l;
            
        }
        
        return (int)(res % MOD);
    }
}