package hatecode;

import java.util.Arrays;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ThreeSumClosest
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 16. 3Sum Closest
 */
public class ThreeSumClosest {
    /**
     * For example, given array S = {-1 2 1 -4}, and target = 1.

     The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

     time : O(n^2);
     space : O(1);

     * @param nums
     * @param target
     * @return
     */
    //thnking process:
    //the problem is get the closet sum to the target
    
    //so we need to 
    public int threeSumClosest(int[] nums, int target) {
        int res = nums[0] + nums[1] + nums[nums.length - 1];
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int start = i + 1, end = nums.length - 1;
            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];
                if (sum > target) {
                    end--;
                } else start++;
                if (Math.abs(sum - target) < Math.abs(res - target)) {
                    res = sum;
                }
            }
        }
        return res;
    }
    // my own solution, should be the same
    public int threeSumClosest2(int[] nums, int target) {
        //edge case 
        if (nums == null || nums.length < 3) {
            return -2;
        }
        
        if (nums.length == 3) {
            return nums[0] + nums[1] + nums[2];
        }
        
        Arrays.sort(nums);
        int delta = Integer.MAX_VALUE, result = Integer.MAX_VALUE;
        for(int i=0; i<=nums.length-3; i++) {
            int p =i+1, p2 = nums.length -1;
            while(p<p2){
                int movingSum = nums[p] + nums[p2];
                int newDeltaWithoutAbs = target - nums[p] - nums[p2] - nums[i];
                if(newDeltaWithoutAbs == 0) {
                    return target;
                } else {
                    if (delta > Math.abs(newDeltaWithoutAbs)) {
                        delta = Math.abs(newDeltaWithoutAbs);
                        result = nums[p]  + nums[p2] + nums[i];
                    }
                    if (newDeltaWithoutAbs > 0) {
                        p++;
                    } else {
                        p2--;
                    }
                }
            }
        }
        
        return result;
    }
}
