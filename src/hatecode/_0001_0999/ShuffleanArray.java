package hatecode._0001_0999;

import java.util.Random;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ShuffleanArray
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : TODO
 * Tags: Math, Permutations
 */
public class ShuffleanArray {
    /**
     * 384. Shuffle an Array
     * // Init an array with set 1, 2, and 3.
     int[] nums = {1,2,3};
     Solution solution = new Solution(nums);

     // Shuffle the array [1,2,3] and return its result. 
      * Any permutation of [1,2,3] must equally likely to be returned.
     solution.shuffle();

     // Resets the array back to its original configuration [1,2,3].
     solution.reset();

     // Returns the random shuffling of array [1,2,3].
     solution.shuffle();

     time : O(n)
     * @param nums
     */

    private int[] nums;
    private Random rmd;

    // 题中第构造函数时Solution
    public ShuffleanArray(int[] nums) {
        this.nums = nums;
        rmd = new Random();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        if (nums == null) return null;
        int[] clone = nums.clone();
        // so here is the key, how can we have same probability to 
        //generate the permutations
        //sampling algorithms here
        
        //so reservior sampling algorithms, 
        //we want to get k of n, and k all have same probability 
        //for k + i, each elements was to be selected is k/(k+i)
        //suppose there are 3 5 in the array, and when we meet first 5, the probobality is 100% 
        // but that's ok because later we are able to override, next 5, we have 50%, third we have 33%, 
        for (int i = 1; i < clone.length; i++) {
            //random will be [0,1,2...i], so  it would be 1/(i+1), 
            //the array suppose we have n digits, so random one will be 
            //[0,1],[0,1,2],[0,1,2,3]..[0,1,2...n]， so we always choose i to switch with 
            //one index before i(inclusive), 
            //each number should try to switch another number in the loop, but they should
            //have same probability 
            int random = rmd.nextInt(i + 1);
            //so for each number probability, 
            //first is 1/n, 1/(n-1),....1/2 each number in array, that's the same as above one
            swap(clone, i, random);
        }
        return clone;
    }
    private void swap(int[] clone, int i, int j) {
        int temp = clone[i];
        clone[i] = clone[j];
        clone[j] = temp;
    }
}
