package hatecode._0001_0999;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : RussianDollEnvelopes
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 354. Russian Doll Envelopes
 */
public class _354RussianDollEnvelopes {
    /**
You have a number of envelopes with widths and heights given as a pair of integers (w, h). 
One envelope can fit into another if and only if both the width and height of 
one envelope is greater than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside 
other)

Note:
Rotation is not allowed.

Example:

Input: [[5,4],[6,4],[6,7],[2,3]]
Output: 3 
Explanation: The maximum number of envelopes you can Russian doll is 3 
([2,3] => [5,4] => [6,7]).

     What is the maximum number of envelopes can you Russian doll? 
     (put one inside other)

     Example:
     Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of 
     envelopes you can Russian doll
     is 3 ([2,3] => [5,4] => [6,7]).

     排序
     weight ->
     height <-

     [6 4][6 7]

     [2, 3] -> [5, 4] -> [6, 7] -> [6, 4]

     3 4 7 4 -> 3 4 7

     time : O(nlogn)
     space : O(n)

     * @param envelopes
     * @return
     */
/*
Sort the array. Ascend on width and descend on height if width are same.
Find the longest increasing subsequence based on height.
Since the width is increasing, we only need to consider height.
[3, 4] cannot contains [3, 3], so we need to put [3, 4] before [3, 3] when 
sorting otherwise it will be counted as
 an increasing number if the order is [3, 3], [3, 4]
 */
    
 /*
thinking:
we have 2D array which has [width, height] format array, we want to get LIS which means LIS in width and height

edge case:
if we have 1,1,1,1,1,1,1 like this, we will find the j will always j = mid, which means i will never will 
// increase, so LIS length = always. 

so we sort one dimension, and we want to find LIS on another dimension. 

  */
    //O(nlgn)/O(1)
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) return 0;
        // it will form [2,3], [5,4], [6,7], [6,4], [6,2], width do increasing, 
        Arrays.sort(envelopes, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int[] dp = new int[envelopes.length];
        int res = 0;
        //for each pair, we do bianry search in dp to find out LIS for height since width already done
        //note: we will override the value in i, not insert
        for (int[] envelope : envelopes) {
            //note, res is the end
            int i = binarySearch(dp, 0, res, envelope[1]);
            //so i here is the index of next envelope[1] should be inserted, 
            // if next index is smaller than previous one so i should be incremental 
            // like res, 0,1,2,3 so if we have a smaller number in i which i != res
            // which means this envelop cannot to another one
            
            //so the track of i would height which cantain previous one
            dp[i] = envelope[1];
            if (i == res) {
                res++;
            }
        }
        return res;
    }
    // this is binary search templates 3, 
// https://leetcode.com/articles/introduction-to-binary-search/ 
    //we want to find for each element in envelopes as ith the ceiling of envelope[i]
    public int binarySearch(int[] dp, int start, int end, int target) {
        // start + 1 < end vs start < end
        // we can change to while(start < end) with start = mid + 1; 
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (dp[mid] == target) {
                return mid;
            } else if (dp[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        // we have two candidates left, start and end, we detect start means we scan from left to right, get
        //first 
        if (dp[start] >= target) return start;
        return end;
    }
    //so dp[i] = dp[k] + 1, k = 0...i-1
    public int maxEnvelopes_DP(int[][] envelopes) {
        if (   envelopes           == null
            || envelopes.length    == 0
            || envelopes[0]        == null
            || envelopes[0].length == 0){
            return 0;    
        }
        
        Arrays.sort(envelopes, new Comparator<int[]>(){
            @Override
            public int compare(int[] e1, int[] e2){
                return Integer.compare(e1[0], e2[0]);
            }
        });
        
        int   n  = envelopes.length;
        int[] dp = new int[n];
        
        int ret = 0;
        for (int i = 0; i < n; i++){
            dp[i] = 1;
            
            for (int j = 0; j < i; j++){
                if (   envelopes[i][0] > envelopes[j][0]
                    && envelopes[i][1] > envelopes[j][1]){
                    dp[i] = Math.max(dp[i], 1 + dp[j]);    
                }
            }
            
            ret = Math.max(ret, dp[i]);
        }
        return ret;
    }
}
