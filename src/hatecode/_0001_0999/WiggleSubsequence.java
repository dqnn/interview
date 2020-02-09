package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : WiggleSubsequence
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 376. Wiggle Subsequence
 */
public class WiggleSubsequence {

    /**
     * Examples:
     Input: [1,7,4,9,2,5]
     Output: 6
     The entire sequence is a wiggle sequence.

     Input: [1,17,5,10,13,15,10,5,16,8]
     Output: 7
     There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].

     Input: [1,2,3,4,5,6,7,8,9]
     Output: 2

     dp up[] down[]

     Input: [1,7,4,9,2,5]

     up[1,2,2]
     down[1,1,3]

     time : O(n)
     space : O(n) / O(1)

     * @param nums
     * @return
     */
    /*
For every position in the array, there are only three possible statuses for it.

up position, it means nums[i] > nums[i-1]
down position, it means nums[i] < nums[i-1]
equals to position, nums[i] == nums[i-1]
So we can use two arrays up[] and down[] to record the max wiggle sequence length so far at index i.
If nums[i] > nums[i-1], that means it wiggles up. the element before it must be a down position. so up[i] = down[i-1] + 1; down[i] keeps the same with before.
If nums[i] < nums[i-1], that means it wiggles down. the element before it must be a up position. so down[i] = up[i-1] + 1; up[i] keeps the same with before.
If nums[i] == nums[i-1], that means it will not change anything becasue it didn't wiggle at all. so both down[i] and up[i] keep the same.

In fact, we can reduce the space complexity to O(1), but current way is more easy to understanding.
             3    10      11     10    5    7
up           1     2       2     2     2    4
down         1     1       1     3    3     3   
    */
    //thinking process:
    
    //up = down + 1 becasue up and down here means correct wigger sequence length, so
    //we need to make previous also correct, so up = down + 1 or down = up +1, this can help 
    //to make sure previous are wigger 
    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];

        up[0] = 1;
        down[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = down[i - 1] + 1;
                down[i] = down[i - 1];
            } else if (nums[i] < nums[ i - 1]) {
                down[i] = up[i - 1] + 1;
                up[i] = up[i - 1];
            } else {
                down[i] = down[i - 1];
                up[i] = up[i - 1];
            }
        }
        return Math.max(down[nums.length - 1], up[nums.length - 1]);
    }

    public int wiggleMaxLength2(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int up = 1, down = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            } else if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }
    
    //O(n^2) O(n)
    public int wiggleMaxLength3(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    up[i] = Math.max(up[i],down[j] + 1);
                } else if (nums[i] < nums[j]) {
                    down[i] = Math.max(down[i],up[j] + 1);
                }
            }
        }
        return 1 + Math.max(down[nums.length - 1], up[nums.length - 1]);
    }
    
    //another O(n) solutions
    
    // we use prediff means the previous diff between numbers, if prevdiff > 0 means we are 
    //in increasing sequence, and we want to find decrease one, 
    
    //this is the way we can output the wiggle sequence
    public int wiggleMaxLength4(int[] nums) { 
        if (nums == null || nums.length < 1) {
            return 0;
        }
        if (nums.length < 2) return nums.length;
           
        int prevdiff = nums[1] - nums[0];
        int count = prevdiff != 0 ? 2 : 1;
        List<Integer> res = new ArrayList<>();
        if (count == 1) {
            res.add(nums[1]);
        } else {
            res.add(nums[0]);
            res.add(nums[1]);
        }
        for(int i = 2; i < nums.length; i++) {
            int diff = nums[i] - nums[i-1];
            if (diff > 0 && prevdiff <= 0 || diff < 0 && prevdiff >= 0) {
                count += 1;
                prevdiff = diff;
                res.add(nums[i]);
            }
        }
        System.out.println(res);
        return res.size();
       }

}
