package hatecode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : InterleavingString
 * Creator : duqiang
 * Date : Nov, 2017
 * Description : 97. Interleaving String
 */
public class InterleavingString {
    /** LCS type
     * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

     For example,
     Given:
     s1 = "aabcc",
     s2 = "dbbca",

     When s3 = "aadbbcbcac", return true.

     When s3 = "aadbbbaccc", return false.

     [true, true, true, false, false, false]
     [false, false, false, false, false, false]
     [false, false, false, false, false, false]
     [false, false, false, false, false, false]
     [false, false, false, false, false, false]
     [false, false, false, false, false, false]

     [true, true, true, false, false, false]
     [false, false, true, true, false, false]
     [false, false, true, true, true, false]
     [false, false, true, false, true, true]
     [false, false, true, true, true, false]
     [false, false, false, false, true, true]

     time : O(m * n)
     space : O(m * n)

     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    
    // we keep 2D dp array to keep track that for:
    //dp[i][j] means string in s1[0, i], s2[0, j] equals or not s3[0, i+j+2]
    //s2 --> x axis, s1--> y axis
    /*
     *       a  a  b  c  c   S2
(dp[0][0])T  T  T  F  F  F
       d  F  
       b  F
       b  F
       c  F
       a  F
       S1
           S3:  aadbbcbcac"
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        if ((s1.length() + s2.length()) != s3.length()) return false;

       // s1 as row, s2 as column, this determine when we use the condition to check how we write the forumla
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;
        // initialize first column, we compare y axis with s3, compare each character is same as s3
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = dp[i - 1][0] && (s1.charAt(i - 1) == s3.charAt(i - 1));
        }
        for (int j = 1; j < dp[0].length; j++) {
            dp[0][j] = dp[0][j - 1] && (s2.charAt(j - 1) == s3.charAt(j - 1));
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                // (i,j) above and left value, 
                // if we detect i -1 means above, so we have to check S2 axis value is the same as in S3, the path is like 
                //  ---
                //    |
                //    |
                // the as me as       
                // and another way is |
                //                    |
                //                    _____
                dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                        || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }

        return dp[s1.length()][s2.length()];
    }
    
    // here the loop merge first two n visit, it is the same with less code
    public boolean isInterleave2(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        boolean dp[][] = new boolean[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
    
    /*
     * If we expand the two strings s1 and s2 into a chess board, then this problem can be transformed into a path 
     * seeking problem from the top-left corner to the bottom-right corner. 
     * The key is, each cell (y, x) in the board corresponds to an interval between 
     * y-th character in s1 and x-th character in s2. And adjacent cells are connected with like a grid. 
     * A BFS can then be efficiently performed to find the path.

Better to illustrate with an example here:

Say s1 = "aab" and s2 = "abc". s3 = "aaabcb". Then the board looks like

o--a--o--b--o--c--o
|     |     |     |
a     a     a     a
|     |     |     |
o--a--o--b--o--c--o
|     |     |     |
a     a     a     a
|     |     |     |
o--a--o--b--o--c--o
|     |     |     |
b     b     b     b
|     |     |     |
o--a--o--b--o--c--o
Each "o" is a cell in the board. We start from the top-left corner, and try to move right or down. 
If the next char in s3 matches the edge connecting the next cell, 
then we're able to move. When we hit the bottom-right corner, 
this means s3 can be represented by interleaving s1 and s2. 
One possible path for this example is indicated with "x"es below:

x--a--x--b--o--c--o
|     |     |     |
a     a     a     a
|     |     |     |
o--a--x--b--o--c--o
|     |     |     |
a     a     a     a
|     |     |     |
o--a--x--b--x--c--x
|     |     |     |
b     b     b     b
|     |     |     |
o--a--o--b--o--c--x
Note if we concatenate the chars on the edges we went along, 
it's exactly s3. And we went through all the chars in s1 and s2, in order, exactly once.

Therefore if we view this board as a graph, such path finding problem is trivial with BFS. 
I use an unordered_map to store the visited nodes, which makes the code look a bit complicated. 
But a vector should be enough to do the job.

Although the worse case timeis also O(mn), typically it doesn't 
require us to go through every node to find a path. 
Therefore it's faster than regular DP than average.
*/
    public boolean isInterleave3(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        // the coordinations
        boolean[][] visited = new boolean[s1.length() + 1][s2.length() + 1];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            if (visited[p[0]][p[1]]) continue;
            //reached the right bottom
            if (p[0] == s1.length() && p[1] == s2.length()) return true;
            // we find one path down, so p[0] + 1
            if (p[0] < s1.length() && s1.charAt(p[0]) == s3.charAt(p[0] + p[1])) {
                queue.offer(new int[]{p[0] + 1, p[1]});
            }
            // one path to right
            if (p[1] < s2.length() && s2.charAt(p[1]) == s3.charAt(p[0] + p[1])) {
                queue.offer(new int[]{p[0], p[1] + 1});
            }
            visited[p[0]][p[1]] = true;
        }
        return false;
    }

    /* DFS
     * To solve this problem, let's look at if s1[0 ~ i] s2[0 ~ j] can be interleaved to s3[0 ~ k].

Start from indices0, 0, 0 and compare s1[i] == s3[k] or s2[j] == s3[k]
Return valid only if either i or j match k and the remaining is also valid
Caching is the key to performance. This is very similar to top down dp
Only need to cache invalid[i][j] since most of the case s1[0 ~ i] and s2[0 ~ j] does not form s3[0 ~ k]. 
Also tested caching valid[i][j] the run time is also 1ms
Many guys use substring but it's duplicate code since substring itself is checking char by char. 
We are already doing so
     */
    public boolean isInterleave4(String s1, String s2, String s3) {
        char[] c1 = s1.toCharArray(), c2 = s2.toCharArray(), c3 = s3.toCharArray();
        int m = s1.length(), n = s2.length();
        if(m + n != s3.length()) return false;
        return dfs(c1, c2, c3, 0, 0, 0, new boolean[m + 1][n + 1]);
    }

    public boolean dfs(char[] c1, char[] c2, char[] c3, int i, int j, int k, boolean[][] invalid) {
        if (invalid[i][j])
            return false;
        if (k == c3.length)
            return true;
        boolean valid = i < c1.length && c1[i] == c3[k] && dfs(c1, c2, c3, i + 1, j, k + 1, invalid)
                || j < c2.length && c2[j] == c3[k] && dfs(c1, c2, c3, i, j + 1, k + 1, invalid);
        if (!valid)
            invalid[i][j] = true;
        return valid;
    }
    
}
