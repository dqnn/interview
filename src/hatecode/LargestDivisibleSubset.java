package hatecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
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
    
    
    public List<Integer> largestDivisibleSubset2(int[] nums) {
        int n = nums.length, maxIdx = 0;
        List<Integer> ans = new LinkedList<>();
        if (n == 0) return ans;
        Arrays.sort(nums);
        int[] lens = new int[n], prevs = new int[n];
        Arrays.fill(prevs, -1);
        for (int i = 0; nums[i] <= nums[n-1]/2; ++i) {
            for (int j = i + 1, f = 2; nums[i] <= nums[n-1]/f; f = (nums[j] + nums[i] - 1)/nums[i]) {
                int idx = Arrays.binarySearch(nums, j, n, f*nums[i]);
                if (idx > 0 && lens[idx] <= lens[i]) {
                    prevs[idx] = i;
                    lens[idx] = lens[i] + 1;
                    if (lens[idx] > lens[maxIdx]) maxIdx = idx;
                }
                j = idx >= 0 ? idx + 1 : -(idx + 1);
                if (j >= n) break;
            }
        }
        for (int i = maxIdx; i >= 0; i = prevs[i]) ans.add(0, nums[i]);
        return ans;
    }
    /*
     * 1. Sort
2. Find the length of longest subset
3. Record the largest element of it.
4. Do a loop from the largest element to nums[0], add every element belongs to the longest subset.
The old version cant pass the test case [1,2,4,8,9,72] and [4,8,10,240], thanks for that @Yanning and @svc
Here comes the revised version:
     */
    public static List<Integer> largestDivisibleSubset3(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        dp[0] = 1;

        //for each element in nums, find the length of largest subset it has.
        for (int i = 1; i < nums.length; i++){
            for (int j = i-1; j >= 0; j--){
                if (nums[i] % nums[j] == 0){
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                }
            }
        }

        //pick the index of the largest element in dp.
        int maxIndex = 0;
        for (int i = 1; i < nums.length; i++){
            maxIndex = dp[i] >= dp[maxIndex] ?  i :  maxIndex;
        }

        //from nums[maxIndex] to 0, add every element belongs to the largest subset.
        int temp = nums[maxIndex];
        int curDp = dp[maxIndex];
        for (int i = maxIndex; i >= 0; i--){
            if (temp % nums[i] == 0 && dp[i] == curDp){
                res.add(nums[i]);
                temp = nums[i];
                curDp--;
            }
        }
        return res;
    }
    /*
     * My solution uses DFS on the array after it is sorted in ascending order. Why sort in ascending order?
Let's pretend our input had the following values {1,6,2,3,4,8,24,9,48} in this order . 
Suppose we are looking at the pair 6 and 3. We know that 6%3=0 but that doesn't mean every multiple of 3 
in 3s longest subset is also a multiple of 6. For example, 9 is a multiple of 3 but not 6. 
However, we do know that every multiple of 6 is also a multiple of 3, hence by ordering the array such 
that 6 occurs after 3 we can recursively get the largest subsets of multiples of 6 (that will 
be comprised of values >=6) BEFORE forming longest subset of multiples of 3. . 
Repeat the same process for 9 (another multiple of 3) and so on and keep track of the largest 
subset that we can add 3 to. Cache the results so as to avoid repeated computation. For example, 
the subset {24,48} contains multiples of 1,2,3,4 and 6.By caching we avoid recomputing this subset 
for each of the aforementioned values.
     */
    public List<Integer> largestDivisibleSubset4(int[] nums) {
        if (nums == null || nums.length < 1) {
            return Collections.<Integer>emptyList();
        }
        if (nums.length == 1) {
            List<Integer> ls = new ArrayList<Integer>(1);
            ls.add(nums[0]);
            return ls;
        }
        Arrays.sort(nums);

        HashMap<Integer, List<Integer>> mp = new HashMap<Integer, List<Integer>>();
        List<Integer> maxSubset = null;
        for (int i = 0; i < nums.length; i++) {
            List<Integer> ls = null;
            if (!mp.containsKey(i)) {
                ls = dfs(i, nums, mp);

            } else {
                ls = mp.get(i);
            }

            if (maxSubset == null || ls.size() > maxSubset.size()) {
                maxSubset = ls;
            }
        }
        return maxSubset;
    }

    private List<Integer> dfs(int idx, int[] arr, HashMap<Integer, List<Integer>> mp) {
        if (mp.containsKey(idx)) {
            return mp.get(idx);
        }
        List<Integer> ls = new ArrayList<Integer>();

        for (int i = idx + 1; i < arr.length; i++) {
            if ((arr[i] % arr[idx]) == 0) {
                List<Integer> r = dfs(i, arr, mp);
                if (r.size() > ls.size()) {
                    ls = r;
                }

            }
        }

        ls = new ArrayList<Integer>(ls);
        ls.add(0, arr[idx]);
        mp.put(idx, ls);
        return ls;

    }
    
    public static void main(String[] args) {
        int[] input = new int[] {1,2,3,4,5,6,8};
        System.out.println("input = " + Arrays.toString(input));
        List<Integer> res = LargestDivisibleSubset.largestDivisibleSubset(input);
        System.out.println(res);
    }
}
