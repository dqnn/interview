package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MinimumMovestoEqualArrayElements
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : 453. Minimum Moves to Equal Array Elements
 */
public class MinimumMovestoEqualArrayElements {
    /**
Given a non-empty integer array of size n, find the minimum number of moves required to make all array 
elements equal, where a move is incrementing n - 1 elements by 1.

Example:

Input:
[1,2,3]

Output:
3

Explanation:
Only three moves are needed (remember each move increments two elements):

[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]

     Explanation:
     Only three moves are needed (remember each move increments two elements):

     [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
                  [1,2,2]

     len * (min + k) = sum + k * (len - 1) * 1.
     ==> k = sum - min * len;

     time : O(n)
     space : O(1)


     * @param nums
     * @return
     */
/*
 let's define sum as the sum of all the numbers, before any moves; minNum as the min number in the list; 
 n is the length of the list;

After, say m moves, we get all the numbers as x , and we will get the following equation

 sum + m * (n - 1) = x * n
and actually,

  x = minNum + m
This part may be a little confusing, but @shijungg explained very well. 
let me explain a little again. it comes from two observations:

the minum number will always be minum until it reachs the final number, 
because every move, other numbers (besides the max) will be increamented too;
from above, we can get, the minum number will be incremented in every move. 
So, if the final number is x, it would be minNum + moves;
and finally, we will get

  sum - minNum * n = m
This is just a math calculation.
 */
    public int minMoves(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int min = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
        }
        int res = 0;
        for (int num : nums) {
            res += num - min;
        }
        return res;
    }

    public int minMoves2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int min = nums[0];
        long sum = 0;
        for (int num : nums) {
            sum += num;
            min = Math.min(min, num);
        }
        return (int)(sum - min * nums.length);
    }
}
