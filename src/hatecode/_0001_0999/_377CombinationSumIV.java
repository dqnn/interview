package hatecode._0001_0999;

import java.util.HashMap;

/**
 * Project Name : Leetcode Package Name : leetcode File Name : CombinationSumIV
 * Creator : professorX Date : Aug, 2018 Description : 377. Combination Sum IV
 */
public class _377CombinationSumIV {
    /**
     * nums = [1, 2, 3]
     target = 4

     The possible combination ways are:
     (1, 1, 1, 1)
     (1, 1, 2)
     (1, 2, 1)
     (1, 3)
     (2, 1, 1)
     (2, 2)
     (3, 1)

     Note that different sequences are counted as different combinations.

     Therefore the output is 7.

     1, DP : res[i] += res[i - num];
     2, DFS + Memoization : HashMap<Integer, Integer>


     * @param nums
     * @param target
     * @return
     */

    // time : (n * k) space : O(k)
    //this is backpack IV, 重复选择 + 不同排列 + 装满可能性总数
    public int combinationSum4(int[] nums, int target) {
        int[] res = new int[target + 1];
        res[0] = 1;
        //one key here is from backpack question 1 - 6, this one 
        //we have to put target in outer loop, all others are put 
        //target in innner loop, why?
        //because 
        for (int i = 1; i < res.length; i++) {
            for (int num : nums) {
                if (i - num >= 0) {
                    res[i] += res[i - num];
                }
            }
        }
        return res[target];
    }

    //time : < O(2^n) space : O(n)
    public int combinationSum42(int[] nums, int target) {
        if (nums.length == 0) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        return helper(nums, target, map);
    }

    private int helper(int[] nums, int target, HashMap<Integer, Integer> map) {
        if (target == 0) return 1;
        if (target < 0) return 0;
        if (map.containsKey(target)) {
            return map.get(target);
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += helper(nums, target - nums[i], map);
        }
        map.put(target, res);
        return res;
    }
}
