package hatecode._2000_2999;

import java.util.*;
public class _2801CountSteppingNumbersInRange {
/*
2801. Count Stepping Numbers in Range
Given two positive integers low and high represented as strings, find the count of stepping numbers in the inclusive range [low, high].

A stepping number is an integer such that all of its adjacent digits have an absolute difference of exactly 1.

Return an integer denoting the count of stepping numbers in the inclusive range [low, high].

Since the answer may be very large, return it modulo 109 + 7.

Note: A stepping number should not have a leading zero.


Example 1:

Input: low = "1", high = "11"
Output: 10
Explanation: The stepping numbers in the range [1,11] are 1, 2, 3, 4, 5, 6, 7, 8, 9 and 10. There are a total of 10 stepping numbers in the range. Hence, the output is 10.
Example 2:

Input: low = "90", high = "101"
Output: 2
Explanation: The stepping numbers in the range [90,101] are 98 and 101. There are a total of 2 stepping numbers in the range. Hence, the output is 2. 

233. 数字 1 的个数（题解）
面试题 17.06. 2出现的次数（题解）
600. 不含连续1的非负整数（题解）
902. 最大为 N 的数字组合（数位 DP 通用模板 33:22
1012. 至少有 1 位重复的数字（题解）
1067. 范围内的数字计数
1397. 找到所有好字符串（有难度，需要结合一个经典字符串算法

*/
    
    private char s[];
    // memo means for "998", each digit can have 0-9 choices, 
    // how many results for i, to have step numbers
    private int memo[][];
    int MOD = (int)1e9 + 7;
    public int countSteppingNumbers(String low, String high) {
        return (calc(high) - calc(low) + MOD + (valid(low) ? 1 : 0))  % MOD;
    }

    

    private int calc(String s) {
        this.s = s.toCharArray();
        int m = s.length();
        memo = new int[m][10];
        for (int i = 0; i < m; i++)
            Arrays.fill(memo[i], -1); // -1 表示没有计算过
        return helper(0, 0, true, false);
    }
    /*
    i : means the "101" the string index 
    pre: previous number we chose, for example, if 101, if previous choose 1, then i = 1, we only choose 0, here prev = 1;
    isLimit: if current digit length < 
    isNum: means if prev is filled, then current i can start from 0,  else from 1
    
       “_ _ _” respresents we have 3 position needs to be filled with digit(0-9).contrains with input like "998"
        /
      (0, 0, true, false), means constrains for position 0, and did not fill in number in preivous digit
        /   \
    
      
    
    
    */

    private int helper(int i, int pre, boolean isLimit, boolean isNum) {
        if (i == s.length)
            return isNum ? 1 : 0; // isNum 为 true 表示得到了一个合法数字
        if (!isLimit && isNum && memo[i][pre] != -1)
            return memo[i][pre];
        
        int res = 0;
        /*
         suppose 998, 
         here we can start from 1, so first 2 digits are 0 
         this will help to calculate only when digits are 1,then 2...
         
         
        */
        if (!isNum)  res = helper(i + 1, pre, false, false);
        
        int up = isLimit ? s[i] - '0' : 9; // 如果前面填的数字都和 s 的一样，那么这一位至多填数字 s[i]（否则就超过 s 啦）
        for (int d = isNum ? 0 : 1; d <= up; d++) // 枚举要填入的数字 d
            if (!isNum || Math.abs(d - pre) == 1) // 第一位数字随便填，其余必须相差 1
                res = (res + helper(i + 1, d, isLimit && d == up, true)) % MOD;
        if (!isLimit && isNum) memo[i][pre] = res;
        return res;
    }

    private boolean valid(String s) {
        for (int i = 1; i < s.length(); i++)
            if (Math.abs(s.charAt(i) - s.charAt(i - 1)) != 1)
                return false;
        return true;
    }
}
