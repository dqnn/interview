package hatecode;

//this is not in LC, but google interview has similiar question, 
//
public class UniquePathsIV {
/* Problem statement:
LC类似题目: LC 62 Unique Paths 和 LC 63 Unique Paths II 
原题描述: https://www.1point3acres.com/bbs/thread-423857-1-1.html 
给定一个矩形的宽和长，求所有可能的路径数量 

Rules：          
1. 从左上角走到右上角   
2. 机器人只能走右上，右和右下
思路: 
按照列dp, dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1] + dp[i + 1][j - 1]， 注意i-1，i+1需要存在   
 */
    public static int uniquePath(int r, int c) {
        if (r <= 0 || c <= 0) return 0;
        int[][] dp = new int[r][c];
        dp[0][0] = 1;
        for(int i =1; i<c;i++) dp[0][i] = dp[0][i-1];
        for(int i =1; i<r;i++) dp[i][0] = 0;
        
        for(int j= 0; j<c;j++) {
            for(int i =0;i<r;i++) {
                if(i > 0 && j > 0) dp[i][j] += dp[i-1][j-1];
                if (j > 0) dp[i][j] += dp[i][j-1] + i >= r-1 ? 0 : dp[i+1][j-1];
            }
        }
        return dp[0][c-1];
    }
    
    public static void main(String[] args) {
        //2，2->1, 3,3->2
        System.out.println(uniquePath(3,3));
     }
    //follow up1: reduce space to O(n)
    public int uniquePaths_Space_On(int r, int c) {
        int[] dp = new int[c];
        int[] tmp = new int[c];
        dp[0] = 1;
        for(int i = 1 ; i  < r ; i++) {
            for(int j = 0 ; j < c ; j++) {
                int val1 = j - 1 >= 0 ? dp[j - 1] : 0;
                int val2 = dp[j];
                int val3 = j + 1 < c ? dp[j + 1] : 0;
                tmp[i] = val1 + val2 + val3;
            }
            System.arraycopy(tmp, 0, dp, 0, tmp.length);
        }
        return dp[0];
    }


    
    //follow up2: 给定矩形里的三个点，判断是否存在遍历这三个点的路径
    
    
    //followup3: 给定矩形里的三个点，找到遍历这三个点的所有路径数量
    
    //followup4: 给定一个下界(x == H)，找到能经过给定下界的所有从左上到右上的路径数量 (x >= H)
    
    //followup5: 起点和终点改成从左上到左下，每一步只能 ↓↘↙，求所有可能的路径数量
    
    //variations:
    /*
Given a N*N matrix with random amount of money in each cell, you start from top-left, 
and can only move from left to right, or top to bottom one step at a time until you hit the bottom 
right cell. Find the path with max amount of money on its way.
Sample data:
start
|
v
 5,   15,20,  ...
10, 15,  5,   ...
30,  5,  5,    ...
… 
                 ^end here.


思路：LC 64 最小路径和，思路差不多，只是求和变成求相加后的最大值: LC 64. Minimum Path Sum -> Maximum

     */
    
    //Follow up 1：要求重建从end 到 start的路径
    //Follow up 2: 现在要求空间复杂度为O（1），dp且重建路径

}
