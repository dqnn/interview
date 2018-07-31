package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : JumpGame
 * Creator : duqiang
 * Date : Sep, 2017
 * Description : TODO
 */
public class JumpGame {
    /**
     * 55. Jump Game
    Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:

Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.

     time : O(n)
     space : O(1)
     * @param nums
     * @return
     */
    
    public boolean canJump(int[] A) {
        if (A == null || A.length <= 1) {
            return true;
        }
        // [0 , 3 , 1 , 1 , 4 ] exclude this use case
        if (A[0] == 0) {
            return false;
        }
        int max = 0;
        for (int i = 0; i <= max && i < A.length; i++) {
            max = Math.max(A[i] + i, max);
        }
        return max >= A.length - 1;
    }
    // we from last to begining
    public boolean canJump2(int[] A) {
        int last = A.length - 1, i;
        for (i = A.length - 2; i >= 0; i--) {
            if (i + A[i] >= last) {
                last = i;
            }
        }
        return last <= 0;
    }
    // this is incorrect answer, not finished yet
    public boolean canJump3(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return true;
        }
        
        int len = nums.length;
        for(int i = 0; i < len;) {
            if (i >= len -1) return true;
            if (nums[i] == 0) {
                return false;
            }
            
            if (i + nums[i] >= len - 1) {
                return true;
            }  else {
                i = i+nums[i];
            }
        }
        return false;
    }
}
