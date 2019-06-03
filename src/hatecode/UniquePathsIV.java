package hatecode;

import java.util.*;

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
        System.out.println(uniquePaths_Space_On(3,3));
     }
    //follow up1: reduce space to O(n)
    public static int uniquePaths_Space_On(int r, int c) {
        int[] dp = new int[c];
        int[] tmp = new int[c];
        dp[0] = 1;
        for(int i = 0 ; i  < r ; i++) {
            for(int j = 0 ; j < c ; j++) {
                int val1 = j - 1 >= 0 ? dp[j - 1] : 0;
                int val2 = dp[j];
                int val3 = j + 1 < c ? dp[j + 1] : 0;
                tmp[i] = val1 + val2 + val3;
            }
            System.arraycopy(tmp, 0, dp, 0, c);
        }
        return dp[c-1];
    }


    
    //follow up2: 给定矩形里的三个点，判断是否存在遍历这三个点的路径
    //from point to point, to see the count > 0 or not
    
    //followup3: 给定矩形里的三个点，找到遍历这三个点的所有路径数量
    //cut the retangel int seveal ones
    
    //followup4: 给定一个下界(x == H)，找到能经过给定下界的所有从左上到右上的路径数量 (x >= H)
    //cut the rectangle into two, first height = r - H
    
    //followup5: 起点和终点改成从左上到左下，每一步只能 ↓↘↙，求所有可能的路径数量
    //same
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
    //Follow up 2: 现在要求空间复杂度为O（1），dp且重建路径， edit origin array
    
    public List<List<Integer>> maxMoney(int[][] moneys) {
        List<List<Integer>> res = new ArrayList<>();
        if (moneys == null || moneys.length < 1 || moneys[0].length < 1) return res;
        // assume: moneys is not null, width and length are equal
        int n = moneys.length;
        // initialize the boundary, we change the cell value , if comes from 
        //top, keep the same, if comes from the left, we change to -sum
        for (int j = 1; j < n; j++) {
            moneys[0][j] = -(Math.abs(moneys[0][j-1]) + moneys[0][j]);
        }
        for (int i = 1 ; i < n ; i++) {
            moneys[i][0] = moneys[i-1][0] + moneys[i][0];
        }
        for(int i = 1; i < n ; i++) {
            for(int j = 1; j < n ; j++) {
                int top = Math.abs(moneys[i-1][j]) + moneys[i][j];
                int left = Math.abs(moneys[i][j-1]) + moneys[i][j];
                if(top >= left) moneys[i][j] = top;
                else moneys[i][j] = -left;
            } 
        }
        System.out.println("Max path sum = " + Math.abs(moneys[n - 1][n - 1]));
        int curri = n-1;
        int currj = n-1;
        while (curri > 0 || currj > 0) {
            res.add(Arrays.asList(curri, currj));
            if(moneys[curri][currj] < 0) {
                currj -= 1;
            } else {
                curri -=1;
            }
        }
        res.add(Arrays.asList(0, 0));
        return res;
    }
}
