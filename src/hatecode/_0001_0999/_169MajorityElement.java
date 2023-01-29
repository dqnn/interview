package hatecode._0001_0999;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MajorityElement
 * Creator : professorX
 * Date : Aug, 2018
 * Description : 169. Majority Element
 */
public class _169MajorityElement {

    /**
     * Given an array of size n, find the majority element. The majority element is the element
     * that appears more than ⌊ n/2 ⌋ times.

     You may assume that the array is non-empty and the majority element always exist in the array
     * @param nums
     * @return
     */

    // time : O(nlogn) space : O(1)
    public int majorityElement1(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    // time : O(n) space : O(n)
    public int majorityElement2(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
            if (map.get(num) > nums.length / 2) {
                res = num;
                break;
            }
        }
        return res;
    }

    //interview friendly
    /*
    Moore voting algorithm
     thinking process: O(n) space : O(1)
     the problem is to say: find the major element in the array which occurrency is more than A.length/2;

     key thoughts:
     we use counter and major, 
     1. when counter = 0, means we have to replace the major, at position i, count for major element decrease to 0, 
     other elements after it may become the new major elements
     2. when count !=0,  we will increase count if major == A[i], if not decrease.

     we do not worry about missing the potential elements in 0-i, because major must be the answer if 
     there is major  from 0-i
     
     [2,2,1,1,1,2,2]

     

    
    */
    public int majorityElement3(int[] nums) {
        //we use counter to record, at position i, what is the count for major element major.
        int count = 0;
        //we use major to record the major element
        int major = 0;
        for (int num : nums) {
            if (count == 0) major = num;
            if (num != major) count--;
            else count++;
        }
        return major;
    }
    
    // using maps to store the num->count
    public int majorityElement4(int[] nums) {
        //edge case
        if (nums == null || nums.length < 1) {
            return -1;
        }
        
        // how to use N/2, how about DP?
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length - 1, maxV = nums[0], cnt = 0;
        for(int i = 0; i <= len; i++) {
            int newCnt = map.getOrDefault(nums[i], 0) + 1;
            if (newCnt > nums.length / 2) {
                return nums[i];
            }
            map.put(nums[i], newCnt);             
        }
        
        return -1;
    }

}
