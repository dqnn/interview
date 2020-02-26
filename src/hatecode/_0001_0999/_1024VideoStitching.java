package hatecode._0001_0999;

import java.util.*;
public class _1024VideoStitching {
/*
1024. Video Stitching
You are given a series of video clips from a sporting event that lasted T seconds.  These video clips can be overlapping with each other and have varied lengths.

Each video clip clips[i] is an interval: it starts at time clips[i][0] and ends at time clips[i][1].  We can cut these clips into segments freely: for example, a clip [0, 7] can be cut into segments [0, 1] + [1, 3] + [3, 7].

Return the minimum number of clips needed so that we can cut the clips into segments that cover the entire sporting event ([0, T]).  If the task is impossible, return -1.

 

Example 1:

Input: clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
Output: 3
*/
    //O(NlogN)/O(1)
    public int videoStitching_Sort(int[][] clips, int T) {
        int res = 0;
        Arrays.sort(clips, (a,b) ->  a[0] - b[0]);
        for (int i = 0, st = 0, end = 0; st < T; st = end, ++res) {
            for (; i < clips.length && clips[i][0] <= st; ++i)
                end = Math.max(end, clips[i][1]);
            if (st == end) return -1;
        }
        return res;
    }

    public int videoStitching(int[][] clips, int T) {
        if(clips == null || clips.length < 1) return 0;
        
        int[] dp = new int[T+1];
        Arrays.fill(dp, T + 1);
        dp[0] = 0;
        for(int i = 0;i<=T; i++) { 
            for(int[] c : clips) {
                if (i >= c[0] && i <= c[1]) {
                    dp[i] = Math.min(dp[i], dp[c[0]] + 1);
                }
            }
            if (dp[i] == T + 1) return -1;
        }
        return dp[T];
    }
}