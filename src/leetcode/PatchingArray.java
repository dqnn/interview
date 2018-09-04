package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : PatchingArray
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 330. Patching Array
 */
public class PatchingArray {
    /**
     * Given a sorted positive integer array nums and an integer n,
     * add/patch elements to the array such that any number in range [1, n] inclusive can be formed
     * by the sum of some elements in the array. Return the minimum number of patches required.

     Example 1:
     nums = [1, 3], n = 6
     Return 1.

     Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
     Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
     Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
     So we only need 1 patch.

     Example 2:
     nums = [1, 5, 10], n = 20
     Return 2.
     The two patches can be [2, 4].

     Example 3:
     nums = [1, 2, 2], n = 5
     Return 0.

     [1, 2, 5, 13, 24]

     miss: 表示[0,n]之间最小的不能表示的值
     num <= miss  =>  [0, miss+num)

     nums = [1, 2, 5, 13, 24], n = 50

     miss = 1

     1 + 2 + 4 + 5 = 12

     1 : miss = 2
     2 : miss = 4
     5 : miss = 8 res = 1
     5 : miss = 13
     13 : miss = 26
     24 : miss = 50 res = 2

     time : O(n)
     space : O(1)

     * @param nums
     * @param n
     * @return
     */
    // the question is: the array is sorted, we want to  find the minum patche to this array, insert 
    //numbers, so the array could sum to form any num in [1,20], inclusive.  so there is no replace or remove
    //ops
    
    // min ops, so we can try to add number as small from beginning, and as we scan the arrays, we know what kind
    //of value we can generate,
    
    
/*
给定nums = [1, 2, 4, 11, 30], n = 50，我们需要让[0, 50]之间所有的数字都能被nums中的数字之和表示出来。

首先使用1, 2, 4可能表示出0到7之间的所有数，表示范围为[0, 8)，但我们不能表示8，因为下一个数字11太大了，
所以我们要在数组里加上一个8，此时能表示的范围是[0, 16)，那么我们需要插入16吗，答案是不需要，因为我们数组有1和4，
可以组成5，而下一个数字11，加一起能组成16，所以有了数组中的11，我们此时能表示的范围扩大到[0, 27)，但我们没法表示27，
因为30太大了，所以此时我们给数组中加入一个27，那么现在能表示的范围是[0, 54)，已经满足要求了，我们总共添加了两个数8和27，
所以返回2即可。


Let miss be the smallest sum in [0,n] that we might be missing. Meaning we already know we can build all 
sums in [1,miss). Then if we have a number num <= miss in the given array, we can add it to those smaller 
sums to build all sums in [0,miss+num). If we don't, then we must add such a number to the array, 
and it's best to add miss itself, to maximize the reach.
 */
    
    //understand that if we have covered range [1 -> num], then adding num + 1 can extend the range to 
    //[1..2*num + 1].
    // the original array, it must be adding from smallest, so we can extend the sum array to 2* n + 1, 
    // the sum array was added n + 1 more elements
    public int minPatches(int[] nums, int n) {
        int i = 0, res = 0;
        //miss be the smallest sum in [0,n] that we might be missing
        //就是如果按照nums 的剧情发展，得不到的number， miss 按照
        // nums is the same as 1,2,3,4,... 如果[1,2,4,11,30] 那么miss=[1,2,4, 8, 16, 
        long miss = 1; 
        while (miss <= n) {
            // 
            if (i < nums.length && nums[i] <= miss) {
                miss += nums[i++];
            // if we already bigger than nums.length || nums[i] > miss, so under condition <=n, which means
            // we add the number miss into the list
            } else {
                miss += miss;
                res++;
            }
        }
        return res;
    }
    
    // simpler version, we use how many times i moved and how many times we dected miss's difference
    // to get how many patches we need
    public int minPatches2(int[] nums, int n) {
        if (nums == null) {
            return 0;
        }
        int i = 0, count = 0;
        
        for(long miss = 1; miss <= n; count++) {
            miss += i < nums.length && nums[i] <= miss ? nums[i++] : miss;
        }
        
        
        return count - i;
    }
}
