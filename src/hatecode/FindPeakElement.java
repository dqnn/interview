package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : FindPeakElement
 * Creator : duqiang
 * Date : Sep, 2017
 * Description : TODO
 */
public class FindPeakElement {

    /**
     * 162. Find Peak Element
     * A peak element is an element that is greater than its neighbors.

     Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

     The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

     You may imagine that num[-1] = num[n] = -∞.

     For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.


     1 2 3 1

     1 2 3 2 1

     time : O(logn);
     space : O(1);
     * @param nums
     * @return
     */
    //since it is peak, which means left is increase while right is decrease so we can use BS
    public int findPeakElement(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (nums[mid] > nums[mid + 1]) {
                end = mid;
            } else {
                //from the templates, this should be mid, but we move to mid + 1, because
                //
                start = mid + 1;
            }
        }
        if (nums[start] > nums[end]) return start;
        return end;
    }
    
    //bruth-force way to solve the problem
    public int findPeakElement2(int[] nums) {
        if (nums == null || nums.length < 1) {
            return -1;
        }
        
        if (nums.length == 1) {
            return 0;
        }
        
        if (nums.length == 2) {
            return nums[0] < nums[1] ? 1: 0;
        }
        
        // sovle the left and right issues
        if (nums[0] > nums[1]) {
            return 0;
        }
        
        if (nums[nums.length - 1] > nums[nums.length - 2]) {
            return nums.length - 1;
        }
        

        for(int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return -1;
    }
}
