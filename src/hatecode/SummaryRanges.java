package hatecode;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SummaryRanges
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : TODO
 */
public class SummaryRanges {
    /**
     * 228. Summary Ranges
     * Given a sorted integer array without duplicates, 
     * return the summary of its ranges.
     * Similar Problem: 163. Missing Ranges

     Example 1:
     Input: [0,1,2,4,5,7]
     Output: ["0->2","4->5","7"]
Explanation: 0,1,2 form a continuous range; 
4,5 form a continuous range.
     Example 2:
     Input: [0,2,3,4,6,8,9]
     Output: ["0","2->4","6","8->9"]
Explanation: 2,3,4 form a continuous range; 8,9 form a 
continuous range.

     time : O(n)
     space : O(n)
     * @param nums
     * @return
     */
    //thinking process:
    //problem is output range sum with different format
    
    // so straightforward is to detect continous numbers
    //use two loops then is able to get contious range
    
    //
    public List<String> summaryRanges2(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return res;
        }
        int end = 0, start = 0;
        for(;end < nums.length;) {
            // we use end + 1 so end should stop at nums.length - 2
            while(end < (nums.length - 1) && (nums[end + 1] - nums[end] == 1)) {
                end++;
            }
            // here should pick up last element 
            if (end == start || start == nums.length - 1) {
                res.add(String.valueOf(nums[start]));
            } else {
                res.add(String.valueOf(nums[start])
                     +"->" + String.valueOf(nums[end]));
            }
            //start and end pointer should always start together
            end++;
            start = end;
        }
        return res;
    }
    
    
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        for (int i = 0; i < nums.length; i++) {
            //this is start value
            int num = nums[i];
            //i stop at nums.length - 2 since we use i + 1
            //but i stopped at every old one, but for loop has ++ 
            while ((i < nums.length - 1) 
                    && nums[i] + 1 == nums[i + 1]) {
                i++;
            }
            //start not equals 
            if (num != nums[i]) {
                res.add(num + "->" + nums[i]);
            } else {
                res.add(num + "");
            }
        }
        return res;
    }
}
