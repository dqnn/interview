package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : FirstMissingPositive
 * Creator : duqiang
 * Date : Nov, 2017
 * Description : 41. First Missing Positive
 */
public class FirstMissingPositive {
    /**
     * Given an unsorted integer array, find the first missing positive integer.

     For example,
     Given [1,2,0] return 3,
     and [3,4,-1,1] return 2.

     Your algorithm should run in O(n) time and uses constant space.

     time : O(n)
     space : O(1)

     * @param nums
     * @return
     */
    
    // our purpose is to find the first missing positive integer,
    //we place the integer to be its correct position 5--> nums[4]
    public int firstMissingPositive(int[] nums) {
        // this return value make sense
        if (nums == null || nums.length == 0) return 1;
        // note here two loops, first loop for nums, 
        for (int i = 0; i < nums.length; i++) {
            // this loop is make sure after first switch, we switch position i and nums[i] - 1
            // but if nums[i] - 1 also not on position or we have duplicate number in these two positions
            // nums[nums[i] - 1] != nums[i] this means current index value does not equals to the value which is 
            // nums[i] - 1, which means on position i should be nums[i] - 1
            // nums[nums[i] - 1] is the place that nums[i] should be 
            // so we continue switch elements until we have duplicate elements which means for this position 
            // we have done its work, very similiar to insert sort
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        
        // we have done the sort so if we find correct value then 
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }
}
