package hatecode;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MissingRanges
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : 163. Missing Ranges
 */
public class MissingRanges {
    /**
Given a sorted integer array where the range of elements are [0, 99] inclusive, return its missing ranges.
For example, given [0, 1, 3, 50, 75], return [“2”, “4->49”, “51->74”, “76->99”]

     For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].

     [2147483647] 0 2147483647
     ["0->2147483646"]
     ["0->2147483646","-2147483648->2147483647"]

     time : O(n)
     space : O(1)

     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    // so for the range missing, we use a point point to lower, so 
    // when we scan the array, we wil process
    // if lowe < nums[i], we need to append lower-> nums[i] - 1. here we need to differ 
    // on nums[i] and nums[i] - 1
    
    //interview friendly, find missing ranges given an array and lower, upper bound
    
    //so since nums already sorted, so lets consider what's the relationship between low variable num with num
    //[0, 1, 3, 50, 75],0, 99, two numbers we have 3 cases, 
    //1. equals, then we should continue; 
    //2. low < num, low + 1 == num, then we just 1 number
    //3. low + 1 < num, then we need a range, low ->nums-1
    
    //so we continue this process until over but we need to detect the upper, also two cases same as above. 
    //if low == upper, just 1 number, if not, it will be a range
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        //convert to long because we may need to care about the edge case, like num = Integer.MAX_VALUE, it 
        //will have overflow
        long alower = (long)lower, aupper = (long)upper;
        for (int num : nums) {
            if (num == alower) {
                alower++;
            } else if (alower < num) {
                if (alower + 1 == num) {
                    res.add(String.valueOf(alower));
                } else {
                    res.add(alower + "->" + (num - 1));
                }
                alower = (long)num + 1;
            }
        }
        if (alower == aupper) res.add(String.valueOf(alower));
        else if (alower < aupper) res.add(alower + "->" + aupper);
        return res;
    }
}
