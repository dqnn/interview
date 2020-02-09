package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : CountofSmallerNumbersAfterSelf
 * Creator : duqiang
 * Date : Nov, 2017
 * Description : 315. Count of Smaller Numbers After Self
 */
public class CountofSmallerNumbersAfterSelf {
    /**
     * You are given an integer array nums and you have to return a new counts array.
     * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

     Example:

     Given nums = [5, 2, 6, 1]

     To the right of 5 there are 2 smaller elements (2 and 1).
     To the right of 2 there is only 1 smaller element (1).
     To the right of 6 there is 1 smaller element (1).
     To the right of 1 there is 0 smaller element.
     Return the array [2, 1, 1, 0].

     [5, 2, 6, 1]

     int[] Arrays.asList()
     0 1 1 2

     time : O(n^2)
     space : O(n)
     */

    // we maintain array list,
    // and we loop from array end, and added to list
    //
    public List<Integer> countSmaller(int[] nums) {
        Integer[] res = new Integer[nums.length];
        List<Integer> list = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int index = findIndex(list, nums[i]);
            res[i] = index;
            list.add(index, nums[i]);
        }
        return Arrays.asList(res);
    }

    // insert sort and find the correct position, it returns the
    // position that target should be, it is asc
    // and so end is the number how many numbers are there smaller than target
    // binary insert sort
    private int findIndex(List<Integer> list, int target) {
        if (list.size() == 0) return 0;
        int start = 0;
        int end = list.size() - 1;
        if (list.get(end) < target) return end + 1;
        if (list.get(start) >= target) return 0;
        // 
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (list.get(mid) < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (list.get(start) >= target) return start;
        return end;
    }

    // TreeMap time exceed, do not understand
    public List<Integer> countSmaller2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        // edge case
        if (nums == null || nums.length < 1) {
            return res;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int min = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            min = Math.min(min, nums[i]);
            // use treeMap to get subset
            Map<Integer, Integer> tempMap = map.subMap(min, true, nums[i], false);
            int count = 0;
            for (Integer e : tempMap.values()) {
                count += e;
            }
            res.add(0, count);
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        return res;
    }
}
