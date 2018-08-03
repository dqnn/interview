package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LargestDivisibleSubset
 * Creator : duqiang
 * Date : Jan, 2018
 * Description : 368. Largest Divisible Subset
 */
public class LargestDivisibleSubset {
    /**
     * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj)
     * of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.

     If there are multiple solutions, return any subset is fine.

     Example 1:

     nums: [1,2,3]

     Result: [1,2] (of course, [1,3] will also be ok)
     Example 2:

     nums: [1,2,4,8]

     Result: [1,2,4,8]

     1 2 4 6 8

            1 2 4 6 8
     pre : -1 0 1 1 2
     count: 1 2 3 3 4

     Arrays.sort()
     count[]
     pre[] : index[]

     int index(4) max(4)

     a % b == 0
     b % c == 0
     a % c == 0

     pre

     time : O(n^2)
     space : O(n)

     * @param nums
     * @return
     */
 // Si % Sj = 0 or Sj % Si = 0. so if si < sj, which means we never 
    // can get 0, so we could only let si > sj, what's why we have to sort first
    // suppose [1,2,3,4,5,6,8]
/*
 * input = [1, 2, 3, 4, 5, 6, 8]
i = 0
[1, 0, 0, 0, 0, 0, 0]
[-1, 0, 0, 0, 0, 0, 0]
i = 1
[1, 2, 0, 0, 0, 0, 0]
[-1, 0, 0, 0, 0, 0, 0]
i = 2
[1, 2, 2, 0, 0, 0, 0]
[-1, 0, 0, 0, 0, 0, 0]
i = 3
[1, 2, 2, 3, 0, 0, 0]
[-1, 0, 0, 1, 0, 0, 0]
i = 4
[1, 2, 2, 3, 2, 0, 0]
[-1, 0, 0, 1, 0, 0, 0]
i = 5
[1, 2, 2, 3, 2, 3, 0]
[-1, 0, 0, 1, 0, 2, 0]
i = 6
[1, 2, 2, 3, 2, 3, 4]
[-1, 0, 0, 1, 0, 2, 3]
[8, 4, 2, 1]
 */
    public static List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return res;
        }

        Arrays.sort(nums);
        //To store count of divisors of all elements
        int[] count = new int[nums.length];
        //To store previous divisor index in result
        int[] pre = new int[nums.length];
        // To store index of largest element in maximum
        // size subset
        int max = 0, index = -1;
        // i from 0 to len - 1
        for (int i = 0; i < nums.length; i++) {
            count[i] = 1;
            // why -1? 
            pre[i] = -1;
            // j is from i to 0 since we sort
            
            for (int j = i - 1; j >= 0; j--) {
                // nums[i] > nums[j]
                if (nums[i] % nums[j] == 0) {
                    //you want the "largest" subset, so the count should be increasing
                    if (1 + count[j] > count[i]) {
                        count[i] = count[j] + 1;
                        pre[i] = j;
                    }
                }
            }
            if (count[i] > max) {
                max = count[i];
                index = i; // point to the value which is max's index
            }
            System.out.println("i = " + i);
            System.out.println(Arrays.toString(count));
            System.out.println(Arrays.toString(pre));
        }
        while (index != -1) {
            res.add(nums[index]);
            index = pre[index];
        }
        return res;
    }
    
    public static void main(String[] args) {
        int[] input = new int[] {1,2,3,4,5,6,8};
        System.out.println("input = " + Arrays.toString(input));
        List<Integer> res = LargestDivisibleSubset.largestDivisibleSubset(input);
        System.out.println(res);
    }
}
