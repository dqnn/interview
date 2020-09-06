package hatecode._0001_0999;
import java.util.*;
public class _548SplitArraywithEqualSum {
/*
548. Split Array with Equal Sum
Given an array with n integers, you need to find if there are triplets (i, j, k) which satisfies following conditions:

0 < i, i + 1 < j, j + 1 < k < n - 1
Sum of subarrays (0, i - 1), (i + 1, j - 1), (j + 1, k - 1) and (k + 1, n - 1) should be equal.
where we define that subarray (L, R) represents a slice of the original array starting from the element indexed L to the element indexed R.
Example:
Input: [1,2,1,2,1,2,1]
Output: True
Explanation:
i = 1, j = 3, k = 5. 
sum(0, i - 1) = sum(0, 0) = 1
sum(i + 1, j - 1) = sum(2, 2) = 1
sum(j + 1, k - 1) = sum(4, 4) = 1
sum(k + 1, n - 1) = sum(6, 6) = 1
Here j is used for middle cut, i for left cut and k for right cut.
Iterate middle cuts and then find left cuts which divides the first half into two equal quarters, store that quarter sums in the hashset. Then find right cuts which divides the second half into two equal quarters and check if quarter sum is present in the hashset. If yes return true.
*/
    //thinking process: the problem is to ask to find 3 indexes where it would break 
    //the whole array into 4 parts, sum of each part equals the same, indexes themselves
    //are exclusive
    
    //so this is like 3 Sum, we have a left pointer i ,i must begin with 1, then we have another 
    //2 pointer on i's right, so we can break the whole array into 4 parts, 
    
    //above is brute force, so we can help to improve is to have prefix sum, 
    //then for each i, we will have a set, the set is to memory what's sum we have sum[i-1],
    //when we move middle pointer, j we can get different sum from sum(i+1, j-1), we can add all
    //set, 
    
    //for K, since we must have 1 element, so k must be begin with k +2, then we will do the same
    //thing as j, since we have a set to know whther we have came across the same sum, so this 
    //complexity will only O(n^2)
    public boolean splitArray(int[] nums) {
        //since we have at least 4 subarray and 3 elements
        if (nums.length < 7)
            return false;
        //prefix sum
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        //i is left cut, j is middle, k for right cut
        //max(j) = len -4, so he 3 number on right,
        for (int j = 3; j < nums.length - 3; j++) {
            Set<Integer> set = new HashSet<>();
            //i must begin with 1,so i left could have 1 subarray
            for (int i = 1; i < j - 1; i++) {
                //i-1 left and sum(i+1， j-1) equals, then we add into set
                //set here like we use the map in two sum
                if (sum[i - 1] == sum[j - 1] - sum[i])
                    set.add(sum[i - 1]);
            }
            // k must begin with j + 2 since there must be at least 1 number j+1 for one subarray
            for (int k = j + 2; k < nums.length - 1; k++) {
                if (sum[nums.length - 1] - sum[k] == sum[k - 1] - sum[j] 
                        && set.contains(sum[k - 1] - sum[j]))
                    return true;
            }
        }
        return false;
    }
}