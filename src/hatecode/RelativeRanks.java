package hatecode;

import java.util.*;
public class RelativeRanks {
/*
506. Relative Ranks
Given scores of N athletes, find their relative ranks and the people with the top three highest scores, who will be awarded medals: "Gold Medal", "Silver Medal" and "Bronze Medal".

Example 1:
Input: [5, 4, 3, 2, 1]
*/
    //this problem is to say we here a list of scores, for each one, we want to give top 3 strings, while others we want to give them each ranks as string, but their position should not change
    public String[] findRelativeRanks(int[] nums) {
        if (nums == null || nums.length < 1) return new String[]{};
        int n = nums.length;
        int[][] p = new int[n][2];
        for(int i = 0; i< n;i++){
            p[i][0] = nums[i];
            p[i][1] = i;
        }
        
        Arrays.sort(p, (a, b)->(b[0] - a[0]));
        String[] res = new String[n];
        
        for(int i = 0; i<n; i++) {
            if (i == 0) res[p[i][1]] = "Gold Medal";
            else if (i == 1) res[p[i][1]] = "Silver Medal";
            else if (i == 2) res[p[i][1]] = "Bronze Medal";
            else res[p[i][1]] = i+1 + "";
        }
        return res;
    }
}