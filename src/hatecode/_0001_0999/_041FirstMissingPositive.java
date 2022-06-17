package hatecode._0001_0999;

import java.util.HashMap;
import java.util.Map;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : FirstMissingPositive
 * Creator : professorX
 * Date : Nov, 2017
 * Description : 41. First Missing Positive
 */
public class _041FirstMissingPositive {
    /**
     * Given an unsorted integer array, find the first missing positive integer.

     For example,
     Given [1,2,0] return 3,
     and [3,4,-1,1] return 2.

     Your algorithm should run in O(n) time and uses constant space.

     time : O(n)
     space : O(1)
     
     follow up: if output is first two missing numbers, how to handle?

     * @param nums
     * @return
     */
    
    // our purpose is to find the first missing positive integer,
    //we place the integer to be its correct position 5--> nums[4]
    public int firstMissingPositive(int[] nums) {
        // this return value make sense
        if (nums == null || nums.length < 1) return 1;
        // note here two loops, first loop for nums, 
        for (int i = 0; i < nums.length; i++) {
            // this loop is make sure after first switch, we switch position i and nums[i] - 1
            // but if nums[i] - 1 also not on position or we have duplicate number in these two positions
            // nums[nums[i] - 1] != nums[i] this means current index value does not equals to the value which is 
            // nums[i] - 1, which means on position i should be nums[i] - 1
            // nums[nums[i] - 1] is the place that nums[i] should be 
            // so we continue switch elements until we have duplicate elements which means for this position 
            // we have done its work, very similar to insert sort
            // swap sort is like:
            // for(i = 1) {
            //   int j = i;
            //   while(j > 0 && nums[j] < nums[j - 1]) {
            //     swap(j, j - 1);
            //     j --;
            //   }
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                // remember we have replace nums[nums[i] - 1] first
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        
        // we have done the sort so if we find correct value then 
        // if it is less than 0, then it should on the position should be the missing positive.
        // 
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        //if they are all correct on position then we return the len + 1
        return nums.length + 1;
    }
    
    public int firstMissingPositive2(int[] nums) {
        if (nums == null || nums.length ==0) {
                return 1;
        }
        int i = 0, n = nums.length;
        while (i < n) {
            // If the current value is in the range of (0,length) and it's not at its correct position, 
            // swap it to its correct position.
            // Else just continue;
            if (nums[i] >= 0 && nums[i] < n && nums[nums[i]] != nums[i]) {
                // we have to make sure we replace nums[nums[i]] first, because if we do 
                //replace nums[i] = nums[nums[i]], then nums[nums[i]] already point to another figure. 
                int temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
            } else {
                i++;
            }
        }
        int k = 1;
        // Check from k=1 to see whether each index and value can be corresponding.
        while (k < n && nums[k] == k) {
            k++;
        }

        // If it breaks because of empty array or reaching the end. K must be the first missing number.
        if (n == 0 || k < n)
            return k;
        else   // If k is hiding at position 0, K+1 is the number. 
            return nums[0] == k ? k + 1 : k;
    }
    
    // we use a map to store the array, and look for it. 
    // O(n), O(n)
    public int firstMissingPositive4(int[] nums) {
        //edge case
        if (nums == null || nums.length ==0) {
            return 1;
        }
        
        int len = nums.length;
        
        Map<Integer, Integer> map = new HashMap<>();
 
        for(int j = 0; j< len; j++) {
            map.put(nums[j], j);
        }
        
        for(int i =1; i <= len; i++) {
            if(!map.containsKey(i)) {
                return i;
            }
        }
        
        return len + 1;
    }
}
