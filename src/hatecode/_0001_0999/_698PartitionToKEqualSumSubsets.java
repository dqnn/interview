package hatecode._0001_0999;

import java.util.*;
import java.util.stream.Collectors;

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
    /*
     * thinking process: O(k2^n)/O(n)
     * the problem is to say: given one integer array and number k,
     * partition array to k groups, sum of each group are the same
     * 
     * I believe tine complexity is: O(2^nk). Some says O( k2^n) is not correct. Getting one subset is 2^n doest not mean picking up k subsets is k2^n
Because we are doing recursion, each subset is not independent but always Dependent (drawing a recursion tree). After first subset with 2^n, for each possibility
from 2^n, we do the second subset pick up. So nodes at last layer of recursion tree become 2^n * 2^n. Finally after k subsets, it is 2^n * ...* 2^n which is O(2^(nk))
     */
    public boolean canPartitionKSubsets(int[] A, int k) {
        int sum = Arrays.stream(A).sum();
        
        if (k <= 0 || sum % k != 0) return false;
        int[] visited = new int[A.length];
        return helper(A, visited, 0, k, 0, 0, sum / k);
    }

    public boolean helper(int[] A, int[] visited, int pos, int k, 
            int cur_sum, int cur_num, int target) {
        if (k == 1) return true;
        if (cur_sum == target && cur_num > 0)
            return helper(A, visited, 0, k - 1, 0, 0, target);

        for (int i = pos; i < A.length; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                if (helper(A, visited, i + 1, k, cur_sum + A[i], cur_num++, target))
                    return true;
                visited[i] = 0;
            }
        }
        return false;
    }
    
    /*
     * following is bucket parition and fastest, 
     */
    public boolean canPartitionKSubsets_Bucket(int[] nums, int k) {

        if (k > nums.length) return false;

        // check wheather totalSum can be divided evenly by k.
        int sum = 0;
        for (int v : nums) sum += v;
        if (sum % k != 0) return false;

        // record sum of each bucket
        int[] bucket = new int[k];
        // target should be sum / k
        int target = sum / k;

        // descending sort array
        Integer[] A = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        Arrays.sort(A, (a ,b)->Integer.compare(b, a));

        return backtrack(nums, 0, bucket, target);
    }
 
    private boolean backtrack(int[] nums, int index, int[] bucket, int target) {
        // 结束条件：每个数字都做出了选择
        if (index == nums.length) {
            // 检查所有桶的数字之和是否都是 target
            for (int k = 0; k < bucket.length; k++) {
                if (bucket[k] != target) return false;
            }
            return true;
        }

        // for 选择 in 选择列表 -- 选择哪个桶
        for (int k = 0; k < bucket.length; k++) {
            // 排除不合法， 剪枝：桶装满了
            if (bucket[k] + nums[index] > target) {
                continue;
            }

            // 前序位置：做选择，把nums[index]装入bucket[k]
            bucket[k] += nums[index];


            if (backtrack(nums, index + 1, bucket, target)) {
                return true;
            }

            // 后序位置：撤销选择，把nums[index]撤出bucket[k]
            bucket[k] -= nums[index];

            //剪枝
            if (bucket[k] == 0) {
                return false;
            }
        }

        // nums[index] 装进哪个桶都不行
        return false;
    }
}