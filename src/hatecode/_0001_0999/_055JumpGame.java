package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : JumpGame
 * Creator : professorX
 * Date : Sep, 2017
 * Description : TODO
 */
public class _055JumpGame {
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
    // this is the model, the end is the max
    // [2,3,1,1,4]
    //  -----max
    //    -------max
    /*
     * the problem is to say: given one integer array, return each element in the array
     * means how far you can jump from that element. 
     * return true if you can jump to the last element.
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
        //here is the key
        //so we loop all elements and for each elements and store the max always
        // because A[i] is the maxium steps we can walk from that position
        //i <= max means i cannot reach more than max, because 
        // [3,2,1,0,4], wehen i = 4, 4 + 4 =8 > len - 1= 4, but we cannot reach to the end
        // i <= max we can use it
        for (int i = 0; i <= max && i < A.length; i++) {
            max = Math.max(A[i] + i, max);
        }
        return max >= A.length - 1;
    }

    /*
     * dp[i] means the max index you can jump from i-th position 
     * dp[i] = max(dp[i-1], A[i] + i), i <= dp[i-1]
     * because if i out of dp[i-1], then we cannot jump from 0-> i-1 to i.
     */
    public boolean canJump_DP(int[] A) {
        int[] dp = new int[A.length];
        
        dp[0] = A[0];
        for(int i = 1;  i<= dp[i-1] && i< A.length; i++) {
            dp[i] = Math.max(dp[i-1], A[i] + i);
        }
        
        return dp[A.length - 1] >= A.length - 1;
    }
    // we from last to begining
    public boolean canJump2(int[] A) {
        int last = A.length - 1, i;
        //this is likely we detect from each position in array, if we find one position can jump to last 
        //directly, then we move to that position, and go to next
        
        //maybe you will wonder that other position also is able to reach the end, yes, last is to store the 
        //index which is able to reach the end, so if we cannot reach then we ignore the position(i--). 
        //the track of last is the sequence how we get to the end
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
        if (nums[0] == 0) return false;
        
        int len = nums.length, last = 0;
        for(int i = 0; i <= last && i < len;i++) {
            //so always detect the farthest position we can get
            if (i + nums[i] > last) {
                last = i + nums[i];
            }
            System.out.println(String.format("%s-%s", last, i));
        }
        return last >= len - 1;
    }
}
