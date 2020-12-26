package hatecode._1000_1999;

import java.util.*;
public class _1187MakeArrayStrictlyIncreasing {
/*
1187. Make Array Strictly Increasing
Given two integer arrays arr1 and arr2, return the minimum number of operations (possibly zero) needed to make arr1 strictly increasing.

In one operation, you can choose two indices 0 <= i < arr1.length and 0 <= j < arr2.length and do the assignment arr1[i] = arr2[j].

If there is no way to make arr1 strictly increasing, return -1.

 

Example 1:

Input: arr1 = [1,5,3,6,7], arr2 = [1,3,2,4]
Output: 1
*/
    
//thinking process:
/*
 * the problem is to say: given two arrays, a1, a2, you can replace any elements in a1
 * with element in a2,  a1[i] = a2[j], return min operations
 * 
 * TODO: understand the problem
 */

    public static int makeArrayIncreasing_DFS_MEMO(int[] arr1, int[] arr2) {
        int res = dfs(arr1, Arrays.stream(arr2).distinct().sorted().toArray(), 0, Integer.MIN_VALUE, new HashMap<>());
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private static int dfs(int[] arr1, int[] arr2, int i, int prev, Map<String, Integer> memo) {
        String key = i + "," + prev;
        if (memo.containsKey(key)) return memo.get(key);
        
        if (i >= arr1.length) return 0;
        int j = Arrays.binarySearch(arr2, prev);
        j = j < 0 ? -(j + 1) : j + 1;
        int bestWith = Integer.MAX_VALUE, bestWithout = Integer.MAX_VALUE;
        if (j < arr2.length) {
            bestWith = dfs(arr1, arr2, i + 1, arr2[j], memo);
            if (bestWith != Integer.MAX_VALUE)
                ++bestWith;
        }
        if (prev < arr1[i])
            bestWithout = dfs(arr1, arr2, i + 1, arr1[i], memo);
        memo.put(key, Math.min(bestWith, bestWithout));
        return memo.get(key);
    }

    //pretty naive solution and only for understanding the solution
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        int n = arr1.length;

        // sort and generate new arr2, just remove the dup ones
        Arrays.sort(arr2);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr2.length; i++) {
            if (i + 1 < arr2.length && arr2[i] == arr2[i + 1])
                continue;
            list.add(arr2[i]);
        }
        int[] newarr2 = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            newarr2[i] = list.get(i);
        arr2 = newarr2;

        // generate new arr1, add min and max to the array as first and last element
        int[] newarr1 = new int[n + 2];
        for (int i = 0; i < n; i++)
            newarr1[i + 1] = arr1[i];
        newarr1[n + 1] = Integer.MAX_VALUE;
        newarr1[0] = Integer.MIN_VALUE;
        arr1 = newarr1;

        // perform dp based on LIS
        int[] dp = new int[n + 2];
        Arrays.fill(dp, Integer.MAX_VALUE);
        // dp[i] -> answer to change array 0 to i
        dp[0] = 0;
        for (int i = 1; i < n + 2; i++) {
            for (int j = 0; j < i; j++) {
                if (arr1[j] < arr1[i] && dp[j] != Integer.MAX_VALUE) {
                    int change = check(arr1, arr2, j, i);
                    if (change >= 0) {
                        dp[i] = Math.min(dp[i], dp[j] + change);
                    }
                }
            }
        }
        return dp[n + 1] == Integer.MAX_VALUE ? -1 : dp[n + 1];
    }

    // change number from start+1 to end-1
    private int check(int[] arr1, int[] arr2, int start, int end) {
        if (start + 1 == end)
            return 0;
        int min = arr1[start];
        int max = arr1[end];
        int idx = Arrays.binarySearch(arr2, min);
        if (idx < 0)
            idx = -idx - 1;
        else
            idx = idx + 1;

        int maxcount = end - start - 1;
        int endi = idx + maxcount - 1;
        if (endi < arr2.length && arr2[endi] < max)
            return maxcount;
        else
            return -1;
    }
    
    // interview friendly
    public int makeArrayIncreasing_TreeSet(int[] arr1, int[] arr2) {
        if (arr1 == null || arr1.length == 0) return -1;
        if (arr1.length == 1) return 0;
        TreeSet<Integer> ts = new TreeSet<>();
        if (arr2 != null) {
            for (int i = 0; i < arr2.length; i++) ts.add(arr2[i]);
        }
        
        int[][] dp = new int[arr1.length + 1][arr1.length + 1];
        for (int i = 0; i < dp.length; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
        dp[0][0] = Integer.MIN_VALUE;
        
        for (int j = 1; j < dp.length; j++) {
            for (int i = 0; i <= j; i++) {
                if (arr1[j - 1] > dp[i][j - 1]) {
                    dp[i][j] = arr1[j - 1];
                }
                if (i > 0 && ts.higher(dp[i - 1][j - 1]) != null) {
                    dp[i][j] = Math.min(dp[i][j], ts.higher(dp[i - 1][j - 1]));
                }
                if (j == dp.length - 1 && dp[i][j] != Integer.MAX_VALUE) return i; 
            } 
        }
        return -1;
    }
}