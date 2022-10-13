package hatecode._0001_0999;
import java.util.*;
public class _698PartitionToKEqualSumSubsets {
/*
698. Partition to K Equal Sum Subsets
Given an array of integers nums and a positive integer k, 
find whether it's possible to divide this array into k non-empty 
subsets whose sums are all equal.

 

Example 1:

Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
*/
    //TODO: understand how we construct to search the answer. can we do a dp like LC 416
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        
        if (k <= 0 || sum % k != 0) return false;
        int[] visited = new int[nums.length];
        return canPartition(nums, visited, 0, k, 0, 0, sum / k);
    }

    public boolean canPartition(int[] nums, int[] visited, int start_index, int k, 
            int cur_sum, int cur_num, int target) {
        if (k == 1) return true;
        if (cur_sum == target && cur_num > 0)
            return canPartition(nums, visited, 0, k - 1, 0, 0, target);

        for (int i = start_index; i < nums.length; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                if (canPartition(nums, visited, i + 1, k, cur_sum + nums[i], cur_num++, target))
                    return true;
                visited[i] = 0;
            }
        }
        return false;
    }
}