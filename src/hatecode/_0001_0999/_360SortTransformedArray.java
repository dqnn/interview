package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SortTransformedArray
 * Creator : professorX
 * Date : Sep, 2018
 * Description : 360. Sort Transformed Array
 */
public class _360SortTransformedArray {
    /**
     * Given a sorted array of integers nums and integer values a, b and c.
     * Apply a quadratic function of the form f(x) = ax2 + bx + c to each element x in the array.

     The returned array must be in sorted order.

     Expected time complexity: O(n)

     Example:
     nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,

     Result: [3, 9, 15, 33]

     nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5

     Result: [-23, -5, 1, 7]

     time : O(n)
     space : O(n)

     * @param nums
     * @param a
     * @param b
     * @param c
     * @return
     */
    // thinking process:
    // so  nums already sorted, so if a >= 0, the two sides
    //would have bigger value, then we can use two pointers,
    //so we use start and end to travel in nums array, 
    // and we calc the both sides elements, startNum and endNum,
    // so every one time, we just pick the bigger or smaller one 
    // to the result array and we incr start or end, 
    // we use i as internal pointer for result array. 
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        if (nums == null || nums.length < 1) {
            return nums;
        }
        int[] res = new int[nums.length];
        int start = 0;
        int end = nums.length - 1;
        int i = a >= 0 ? nums.length - 1 : 0;
        while (start <= end) {
            int startNum = getNum(nums[start], a, b, c);
            int endNum = getNum(nums[end], a, b, c);
            if (a >= 0) {
                if (startNum >= endNum) {
                    res[i--] = startNum;
                    start++;
                } else {
                    res[i--] = endNum;
                    end--;
                }
            } else {
                if (startNum <= endNum) {
                    res[i++] = startNum;
                    start++;
                } else {
                    res[i--] = endNum;
                    end--;
                }
            }
        }
        return res;
    }

    private int getNum(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }
}
