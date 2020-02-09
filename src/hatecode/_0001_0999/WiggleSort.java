package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : WiggleSort
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 280. Wiggle Sort
 */
public class WiggleSort {
    /**
     * Given an unsorted array nums, reorder it in-place such that 
     * nums[0] <= nums[1] >= nums[2] <= nums[3]....

     For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].

     time : O(n)
     space : O(1)


     * @param nums
     */
/*这道题还有一种O(n)的解法，根据题目要求的nums[0] <= nums[1] >= nums[2] <= nums[3]....，
    //我们可以总结出如下规律：

//当i为奇数时，nums[i] < nums[i - 1]
当i为偶数时，nums[i] > nums[i - 1]
we need to exchange 

那么我们只要对每个数字，根据其奇偶性，跟其对应的条件比较，如果不符合就和前面的数交换位置即可，参见代码如下：
*/
    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if ((i % 2 == 1 && nums[i] < nums[i - 1]) 
                    || (i % 2 == 0 && nums[i] > nums[i - 1])) {
                int temp = nums[i - 1];
                nums[i - 1] = nums[i];
                nums[i] = temp;
            }
        }
    }
}
