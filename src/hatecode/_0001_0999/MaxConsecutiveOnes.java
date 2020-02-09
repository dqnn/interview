package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MaxConsecutiveOnes
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : 485. Max Consecutive Ones
 */
public class MaxConsecutiveOnes {
    /**
     * Given a binary array, find the maximum number of consecutive 1s in this array.

     Example 1:
     Input: [1,1,0,1,1,1]
     Output: 3
     Explanation: The first two digits or the last three digits are consecutive 1s.
     The maximum number of consecutive 1s is 3.

     time : O(n)
     space : O(1)

     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0;
        int count = 0;
        // just count when it is 1, and replace everytime
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
                // more frequently replace res
                res = Math.max(res, count);
            } else count = 0;
        }
        return res;
    }
    
    public int findMaxConsecutiveOnes2(int[] nums) {
        //edge case
        if (nums == null) {
            return 0;
        }
        
        int len = nums.length - 1;
        int cnt = 0, max = 0;
        for(int i = 0; i <= len; i++) {
            if (nums[i] == 1) {
                cnt++;
            } else {
                if (cnt > 0) {
                    max = Math.max(max, cnt);
                    cnt = 0;
                }
            }
        }
        
        return Math.max(cnt, max);
    }
}
