package hatecode;
import java.util.*;
/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : EditDistance
 * Creator : duqiang
 * Date : Dec, 2017
 * Description : 72. Edit Distance
 */
public class EditDistance {
    /**
     * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2.
     * (each operation is counted as 1 step.)

     You have the following 3 operations permitted on a word:

     a) Insert a character
     b) Delete a character
     c) Replace a character

     aef -> abcd

     dp[i][j]表示的是，从字符串1的i的位置转换到字符串2的j的位置，所需要的最少步数。

    S[0--i-1],T[0--j-1],
    c =s[i-1], d= t[j-1]
     1,c==d: dp[i][j] = dp[i - 1][j - 1] 就是什么都不做,
     2,c != d
         insert: dp[i][j] = dp[i][j - 1] + 1; 把d加到c后面，必然：比如 fxc -> fad 的编辑距离-->fxcd -> fad 的编辑距离 + 1 
                                              = fxc -> fa 的编辑距离 + 1 就是[i+1][j]的对角线[i][j-1] 
         replace: dp[i][j] = dp[i - 1][j - 1] + 1; 将 T中最后一个char 弄到i的位置上 这样步数等同于斜上角 + 1
         delete: dp[i][j] = dp[i - 1][j] + 1;  这个就是把最后一个不等的去掉就和前面一个样了

           a  b  c  d
        0  1  2  3  4    steps when aef  is empty
     a  1  0  1  2  3
     e  2  1  1  2  3
     f  3  2  2  2  3
 steps when abcd is empty
Replace   Insert
Delete     Here


     time : O(m * n)
     space : O(m * n)

     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String s, String t) {
        //if w1 == null or "", then we need insert w2.length() chars into w1, steps are the w2 length
        //if w2 == null or "", then we need to delete w1.length() char into w1,,steps are the w1 length
        //if both are null, then we nothing just return
        if (s == null && t ==null) return 0;
        
        if (s == null) s = "";
        if (t == null) t = "";
        int r = s.length(), c = t.length();
        
        int[][] dp = new int[r+1][c+1];
        dp[0][0] = 0;
        for(int i = 0; i <r; i++) dp[i+1][0] = i + 1;
        for(int j = 0; j < c; j++) dp[0][j+1] = j + 1;
        
        for(int i = 0; i < r; i++) {
            for( int j = 0; j < c; j++) {
                //same char
                if (s.charAt(i) == t.charAt(j)) dp[i+1][j+1] = dp[i][j];
                //compare to those steps count + 1
                else {
                    dp[i+1][j+1] = Math.min(dp[i][j] + 1, Math.min(dp[i+1][j] + 1, dp[i][j+1] + 1));
                }
            }
        }
        for(int[] p: dp) System.out.println(Arrays.toString(p));
        return dp[r][c];
    }
}
