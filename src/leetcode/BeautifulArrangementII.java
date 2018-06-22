package leetcode;

import java.util.ArrayList;

/*
 * 667
 * Given two integers n and k, you need to construct a list which contains n different positive integers ranging from 1 to n and obeys the following requirement: 
Suppose this list is [a1, a2, a3, ... , an], then the list [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] has exactly k distinct integers.

If there are multiple answers, print any of them.

Example 1:
Input: n = 3, k = 1
Output: [1, 2, 3]
Explanation: The [1, 2, 3] has three different positive integers ranging from 1 to 3, and the [1, 1] has exactly 1 distinct integer: 1.
Example 2:
Input: n = 3, k = 2
Output: [1, 3, 2]
Explanation: The [1, 3, 2] has three different positive integers ranging from 1 to 3, and the [2, 1] has exactly 2 distinct integers: 1 and 2.
Note:
The n and k are in the range 1 <= k < n <= 104.
 */

class BeautifulArrangementII {
    
    
    // TL;DR, Time Exceed
    private ArrayList<ArrayList<Integer>> permutations(int[] nums) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
        permute(ans, nums, 0);
        return ans;
    }

    private void permute(ArrayList<ArrayList<Integer>> ans, int[] nums, int start) {
        if (start >= nums.length) {
            ArrayList<Integer> cur = new ArrayList<Integer>();
            for (int x : nums) cur.add(x);
            ans.add(cur);
        } else {
            for (int i = start; i < nums.length; i++) {
                swap(nums, start, i);
                permute(ans, nums, start+1);
                swap(nums, start, i);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int numUniqueDiffs(ArrayList<Integer> arr) {
        boolean[] seen = new boolean[arr.size()];
        int ans = 0;

        for (int i = 0; i < arr.size() - 1; i++) {
            int delta = Math.abs(arr.get(i) - arr.get(i+1));
            if (!seen[delta]) {
                ans++;
                seen[delta] = true;
            }
        }
        return ans;
    }

    public int[] constructArray(int n, int k) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i+1;
        }
        for (ArrayList<Integer> cand : permutations(nums)) {
            if (numUniqueDiffs(cand) == k) {
                int[] ans = new int[n];
                int i = 0;
                for (int x : cand) ans[i++] = x;
                return ans;
            }
        }
        return null;
    }
    
    
    // n Integers only have limited array placements 
    // n =5 k =4 only 1:
    // [1, 5, 2, 4, 3]--> {4, 3, 2, 1}
    //[1... n- k -1] and [n-k, n], use 1 head + 2 tail 
    //[1], [2,3,4,5]
    public int[] constructArray2(int n, int k) {
        //edge case
        if (n < 2 || k < 1) {
            return null;
        }
        int idx = 0;
        int[] res = new int[n];
        for (int i = 1; i <= n - k - 1; i++) {
            res[idx++] = i;
        }
        
        for (int i = 0; i <= k; i++) {
            res[idx++] = (i%2 == 0) ? (n-k + i/2) : (n - i/2);
        }
        
        return res;
    }
}