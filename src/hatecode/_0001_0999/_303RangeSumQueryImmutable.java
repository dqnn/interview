package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : RangeSumQueryImmutable
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 303. Range Sum Query - Immutable
 */
public class _303RangeSumQueryImmutable {
    /**
     * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

     Example:
     Given nums = [-2, 0, 3, -5, 2, -1]

     sumRange(0, 2) -> 1
     sumRange(2, 5) -> -1
     sumRange(0, 5) -> -3
     Note:
     You may assume that the array does not change.
     There are many calls to sumRange function.

     [-2, 0, 3, -5, 2, -1]

     time : O(n)
     space : O(n)



     */

    private int[] sum;

    public _303RangeSumQueryImmutable(int[] nums) {
        sum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }

}
