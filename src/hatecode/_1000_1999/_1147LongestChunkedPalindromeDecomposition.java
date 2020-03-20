package hatecode._1000_1999;

import java.util.Arrays;

public class _1147LongestChunkedPalindromeDecomposition {
/*
1147. Longest Chunked Palindrome Decomposition
Return the largest possible k such that there exists a_1, a_2, ..., a_k such that:

Each a_i is a non-empty string;
Their concatenation a_1 + a_2 + ... + a_k is equal to text;
For all 1 <= i <= k,  a_i = a_{k+1 - i}.
 

Example 1:

Input: text = "ghiabcdefhelloadamhelloabcdefghi"
Output: 7
Explanation: We can split the string on "(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)".
*/
    //thinking process: O(n)/O(n)
    
    //the problem is to say, given one string s, we need to figure out the max k, 
    //the s will be split k strings, assume they are a1..ak, 
    //a1 = ak, a2=ak-1, which means they are palindrom by small piece of strings
    
    //so this problem can be solved by greedy and dp, typically DP. 
    //why we can use Greedy, because the max k only exists when we split each character
    //int short string, we compare each character both end, the edge case is 
    //whole string match, like merchant, k = 1. so we can see this is recursive problem, 
    
    //so how to get max k, we need to compare one char by one char. 
    //l is substring from left, r is substring but read from left to right, 
    //every time we found matched, then res++, and reset l and r. so we can get the min length of 
    //string length so k is max
    public int longestDecomposition_Best(String s) {
        int res = 0, n = s.length();
        String l = "", r = "";
        for (int i = 0; i < n; ++i) {
            l = l + s.charAt(i);
            r = s.charAt(n - i - 1) + r;
            if (l.equals(r)) {
                ++res;
                l = "";
                r = "";
            }
        }
        return res;
    }
    
    //
    public int longestDecomposition(String s) {
        if (null == s) return -1;
        
        int n = s.length();
        // 将字符串分成 [0,i) ~ [n-i, n)
        // 如果上面相等 表示有2份回文字段
        // 并将剩余字段投入下一轮迭代 [i, n-i)
        // i 其实表示的是比较字段的长度
        for (int i = 1; i <= n/2; i++) {
            if (s.substring(0, i).equals(s.substring(n-i, n))) {
                return 2 + longestDecomposition(s.substring(i, n-i));
            }
        }
        
        // 当最终剩余字段大于零 则其单独成一段 +1
        return n == 0 ? 0 : 1;
    }
    //here is the DP solution, 
    //dp[i][j] means in substring s[i,j] the max k. 
    //so
    class DPWith2D {
        int[][] dp;
        String text;
        
        public int longestDecomposition(String text) {
            int n = text.length();
            dp = new int[n][n];
            
            // 初始化为-1 用以表示该位置是否被处理
            for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
            this.text = text;
            
            // 求整个字符串的回文字段k
            return helper(0, n-1);
        }

        /**
         * 求回文字段数 text的子串
         * @param s 起始位置
         * @param e 终止位置
         * @return 该字段的回文字段数
         **/
        int helper(int s, int e) {
            // 非法字段 起始位置大于终止位置
            if (s > e) return 0;
            // 特殊情况 刚好只剩下一个字符
            if (s == e) return dp[s][e] = 1;
            // 不为-1 则表示以及处理过 直接拿来用就可以
            if (dp[s][e] != -1) return dp[s][e];
            
            // 字符串本身算一回文字段
            int res = 1;
            // 枚举长度 1 ~ (e-s+1)/2 从s开始的子串
            for (int l = 1; l <= (e-s+1)/2; l++) {
                String st = text.substring(s, s+l);
                String ed = text.substring(e-l+1, e+1);
                // 如果两子串相等 表示找到回文字段 +2
                if (st.equals(ed)) {
                    int tmp = helper(s+l, e-l);
                    // 更新结果值
                    res = tmp+2;
                }
            }
            
            return dp[s][e] = res;
        }
    }

    class DPWith1D {
        // 存储表示0~i 出现的回文字段
        int dp[] = new int[1050];
        
        public int longestDecomposition(String text) {
            int n = text.length(), ans = 1;
            char[] chs = text.toCharArray();
            
            // dp数组初始化
            Arrays.fill(dp, -1);
            // 肯定是有从0开始的回文字段
            dp[0] = 0;
            
            // 表示最新的子串开始位置
            int left = 0;
            // i 表示终止位置 (j, i)
            for (int i = 1; i <= n/2; i++) {
                for (int j = left; j < i; j++) {
                    // 可不可以作为起始点
                    if (dp[j] == -1) continue;
                    // [j, i-1]是否可以作为回文字段
                    if (!check(chs, j, i, n)) continue;
                    // 如果可以作为回文字段
                    // [0,i) 有多少回文字段
                    dp[i] = dp[j]+1;
                    // 更新最新子串开始位置
                    left = i;
                }
            }

            // 如果最终处理字段的位置 并没有遍历完字符串(n/2) 表示还有剩余1字段可以作为回文字段
            return Math.max(dp[left]*2 + (left*2<n?1:0), ans);
        }
        
        /**
         * 检查[j,i-1]段前后是否相等
         * 暴力遍历
         **/
        boolean check(char[] chs, int j, int i, int n) {
            // j ~ i-1 --> n-i ~ n-j-1
            for (int ii = j; ii < i; ii++) {
                if (chs[ii] != chs[n-i-j+ii]) return false;
            }
            return true;
        }
    }
}