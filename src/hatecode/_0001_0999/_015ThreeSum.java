package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ThreeSum
 * Creator : professorX
 * Date : Sep, 2018
 * Description : TODO
 */
public class _015ThreeSum {
    /**
     * 15. 3Sum
     * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
     * Find all unique triplets in the array which gives the sum of zero.

     Note: The solution set must not contain duplicate triplets.

     For example, given array S = [-1, 0, 1, 2, -1, -4],
     
    [-4, -1,-1,0,1,2]
     A solution set is:
     [
     [-1, 0, 1],
     [-1, -1, 2]
     ]

     time : O(n^2);
     space : O(n);
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int low = i + 1, high = nums.length - 1, sum = 0 - nums[i];
            while (low < high) {
                if (nums[low] + nums[high] == sum) {
                    res.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    //we move since we found many dup, like  {-1, 0, 1, 1, 2, -1, -4}
                    while (low < high && nums[low] == nums[low + 1]) low++;
                    while (low < high && nums[high] == nums[high - 1]) high--;
                    low++;
                    high--;
                } else if (nums[low] + nums[high] < sum) {
                    low++;
                } else high--;
            }
        }
        return res;
    }
    
    
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(threeSum(new int[] {-1, 0, 1, 1, 2, -1, -4}).toArray()));
    }
}
