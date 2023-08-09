package hatecode._2000_2999;

public class _2060CheckIfAnOriginalStringExistsGivenTwoEncodedStrings{
    
/*
2060. Check if an Original String Exists Given Two Encoded Strings

An original string, consisting of lowercase English letters, can be encoded by the following steps:

Arbitrarily split it into a sequence of some number of non-empty substrings.
Arbitrarily choose some elements (possibly none) of the sequence, and replace each with its length (as a numeric string).
Concatenate the sequence as the encoded string.
For example, one way to encode an original string "abcdefghijklmnop" might be:

Split it as a sequence: ["ab", "cdefghijklmn", "o", "p"].
Choose the second and third elements to be replaced by their lengths, respectively. The sequence becomes ["ab", "12", "1", "p"].
Concatenate the elements of the sequence to get the encoded string: "ab121p".
Given two encoded strings s1 and s2, consisting of lowercase English letters and digits 1-9 (inclusive), return true if there exists an original string that could be encoded as both s1 and s2. Otherwise, return false.

Note: The test cases are generated such that the number of consecutive digits in s1 and s2 does not exceed 3.

 

Example 1:

Input: s1 = "internationalization", s2 = "i18n"
Output: true
*/
    /*
     * thinking process: O(mnD)/O(mnD) D = 10^3
     * 
     * // dp[i][j][diff] means if s1[i:] truncated by <diff> characters if diff > 0 
        // and s2[j:] truncated by <-diff> characters if diff < 0 are equal
     */
    int m, n;
    char[] s1, s2;
    Boolean[][][] dp;

    public boolean possiblyEquals(String s1, String s2) {
        this.m = s1.length();
        this.n = s2.length();
        this.s1 = s1.toCharArray();
        this.s2 = s2.toCharArray();
        dp = new Boolean[m + 1][n + 1][2000];
        return helper(0, 0, 0);
    }

    private boolean helper(int i, int j, int d) {
        // 边界条件: 两个字符串完全匹配
        if (i == m && j == n && d == 0) return true;
        if (dp[i][j][d + 999] != null) return dp[i][j][d + 999];
        boolean res = false;
        // 1: 字母与字母匹配
        if (d == 0 && i < m && j < n && Character.isLetter(s1[i]) && Character.isLetter(s2[j]) && s1[i] == s2[j]) res = helper(i + 1, j + 1, 0);
        // 2-1: s1任意字母和s2数字展开匹配
        if (d > 0 && i < m && Character.isLetter(s1[i])) res = helper(i + 1, j, d - 1);
        // 2-2: s2任意字母和s1数字展开匹配
        if (d < 0 && j < n && Character.isLetter(s2[j])) res = helper(i, j + 1, d + 1);
        // 3-1: s1展开数字
        for (int k = i, num = 0; k < Math.min(m, i + 3) && Character.isDigit(s1[k]); k++) {
            num += s1[k] - '0';
            res |= helper(k + 1, j, d - num);
            num *= 10;
        }
        // 3-2: s2展开数字
        for (int k = j, num = 0; k < Math.min(n, j + 3) && Character.isDigit(s2[k]); k++) {
            num += s2[k] - '0';
            res |= helper(i, k + 1, d + num);
            num *= 10;
        }
        dp[i][j][d + 999] = res;
        return res;
    }
}