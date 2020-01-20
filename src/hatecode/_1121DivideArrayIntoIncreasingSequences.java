package hatecode;
public class _1121DivideArrayIntoIncreasingSequences {
/*
1121. Divide Array Into Increasing Sequences
Given a non-decreasing array of positive integers nums and 
an integer K, find out if this array can be divided 
into one or more disjoint increasing subsequences of 
length at least K.

 

Example 1:

Input: nums = [1,2,2,3,3,4,4], K = 3, 
Output: true, [2,2,3,3], [1,4,4]
*/
  
    //thinking process: O(n)/O(1)
    
    //so the problem is to say: given an integer array A, and postive integer K, 
    //we would like to see whether the array can split into 2+ non -decreasing array and each size
    //should >= K,
    
    //groups is the id of the group,the array is already sorted, 
    //so
    public boolean canDivideIntoSubsequences(int[] A, int K) {
        int cur = 1, groups = 1, n = A.length;
        for (int i = 1; i < n; i++) {
            cur = A[i - 1] < A[i] ?  1 : cur + 1;
            groups = Math.max(groups, cur);
        }
        return n >= K * groups;
    }
    
    public boolean canDivideIntoSubsequences_BestPerformance(int[] nums, int K) {
        int n = nums.length;
        int max = 1;
        int count = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1]) {
                count++;
                if (count > max) {
                    max = count;
                    if (max * K > n) {
                        return false;
                    }
                }
            } else {
                count = 1;
            }
        }
        return max * K <= n;
    }
}