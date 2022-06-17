package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : BinaryWatch
 * Creator : professorX
 * Date : Nov, 2017
 * Description : 401. Binary Watch
 * A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).

Each LED represents a zero or one, with the least significant bit on the right
For example, the above binary watch reads "3:25".

Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.

Example:

Input: n = 1
Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
Note:
The order of output does not matter.
The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
 */
public class _401BinaryWatch {

    /**
     * num = 5
     * [8, 4, 2, 1] count = 2
     *
     * time : 不知道
     * space : O(n)
     *
     * @param num
     * @return
     */

    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        if (num < 0 || num > 10) {
            return res;
        }
        int[] nums1 = new int[] {
                8, 4, 2, 1 }; // hours max hour is 12 because we cannot show 23
        int[] nums2 = new int[] {
                32, 16, 8, 4, 2, 1 }; // minutes
        for (int i = 0; i <= num; i++) {
            // 这样这两个list 总是和是 num
            List<Integer> list1 = generateDigit(nums1, i);
            List<Integer> list2 = generateDigit(nums2, num - i);
            for (int num1 : list1) {
                if (num1 >= 12) continue;
                for (int num2 : list2) {
                    if (num2 >= 60) continue;
                    res.add(num1 + ":" + (num2 < 10 ? "0" + num2 : num2));
                }
            }
        }
        return res;
    }

    private List<Integer> generateDigit(int[] nums, int count) {
        List<Integer> res = new ArrayList<>();
        helper(res, nums, count, 0, 0);
        return res;
    }

    // this method is mainly used to get list of all elements sum in res
    // res= {1,2,3,4}, count = 1
    // ---> {1, 2, 3, 4};
    // res= {1,2,3,4}, count = 2
    // --->{1+2, 1+3, 1+4, 2+3, 2+4, 3+4}

    // this is a templates for permuation, like {1,2,3,4} how many are there for 2
    // combinations
    // helper(List<> res, int[] nums, count)
    // res is result, num is candiate list, count is how many numbers are needed
    private void helper(List<Integer> res, int[] nums, int count, int start, int sum) {
        if (count == 0) {
            res.add(sum);
            return;
        }
        for (int i = start; i < nums.length; i++) {
            helper(res, nums, count - 1, i + 1, sum + nums[i]);
        }
    }
}
