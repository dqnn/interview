package hatecode;

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
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example:

Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.


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
            // we don't have the i < max here because we assume we can reach the end
            maxNext = Math.max(maxNext, i + nums[i]);
            // from the model in previous, i catches the curMaxArea after i - 0, and curMaxArea should 
            // catch the latest maxNext then.
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
    // the problem assume we can always reaches to the end of the array, so there is no [0,3] situations
    public int jump2(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int level = 0;
        int curMaxArea = 0;
        int maxNext = 0;
        int i = 0;
        // curMaxArea is the pointer  means from position i, how far it could be
        // so the internal for loop calculate from [i, curMaxArea], how far the i can go
        // then curMaxArea was following there. 
        // thinking about the model we have built, it is more about loop in [i, nums[i] + i], these numbers,
        // te max value, max{num[i] + i}
        
        //from the internal loop, i could be curMaxArea + 1, this is just keeping the loop working
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
    public int jump3(int[] A) {
        if (A == null || A.length < 2) return 0;
        int step = 0;
        int l = 0;
        int r = 0;
        // high can only reached to A.length - 2, not last one
        while(r < A.length-1){
            int preLow = l;
            int preHigh = r;
            // we use [preLow, preHigh] to calculate the possible max  by this loop, 
            //which means how far we can go in this range
            for(int t = preLow;t <= preHigh;t++)
                r = Math.max(t+A[t], r);
            // next loop, our band start position increments 1
            l = preHigh+1;
            // get the result,so our step increased 1
            step++;
        }
        return step;
    }
    //O(n)/O(1) 
    //
    public int jump_Best(int[] A) {
        int jumps = 0, curEnd = 0, curFarthest = 0;
        for (int i = 0; i < A.length - 1; i++) {
            curFarthest = Math.max(curFarthest, i + A[i]);
            if (i == curEnd) {
                jumps++;
                curEnd = curFarthest;
            }
        }
        return jumps;
    }
}
