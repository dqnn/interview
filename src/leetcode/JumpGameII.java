package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : JumpGameII
 * Creator : duqiang
 * Date : Sep, 2017
 * Description : TODO
 */
public class JumpGameII {
    /**
     * 45. Jump Game II
     * For example:
     Given array A = [2,3,1,1,4]

     The minimum number of jumps to reach the last index is 2. (Jump 1 res from index 0 to 1,
     then 3 ress to the last index.)

     * @param nums
     * @return
     */

    //time : O(n) space : O(1)
    //The maximum index obtainable from indices 0 to i is maintained, denoted as maxNext in  code.
    //curMaxArea maintains the maximum possible index reachable by taking res steps, i.e. 
    //you can go 0 to j where j <= curMaxArea in res steps (for valid positions, the end index is always a valid position)
    //when i reaches curMaxArea, we know we need to take another step, and if we do, we know that we can do res + 1 
    // steps in order to get to indices up to maxNext.
    //Obviously, this is assuming that we can always reach the end of the array, 
    //which is stated in the problem that we can. So therefore curMaxArea and maxNext will always be >= A.size() - 1 
    //in the end, and therefore res will give us our answer.
    public int jump(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int res = 0;
        int curMaxArea = 0;
        int maxNext = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxNext = Math.max(maxNext, i + nums[i]);
            if (i == curMaxArea) {
                res++;
                curMaxArea = maxNext;
            }
        }
        return res;
    }

    /**
     [2,3,1,1,4]

     level = 2
     cur = 2
     max = 4
     i = 1

     * @param nums
     * @return
     */
    // time : O(n)  space : O(1)
    public int jump2(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int level = 0;
        int curMaxArea = 0;
        int maxNext = 0;
        int i = 0;
        while (curMaxArea - i + 1 > 0) {
            level++;
            for (; i <= curMaxArea; i++) {
                maxNext = Math.max(maxNext, nums[i] + i);
                if (maxNext >= nums.length - 1) {
                    return level;
                }
            }
            curMaxArea = maxNext;
        }
        return 0;
    }
}
