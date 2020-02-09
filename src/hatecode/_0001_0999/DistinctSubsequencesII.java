package hatecode._0001_0999;
import java.util.*;
public class DistinctSubsequencesII {
/*
940. Distinct Subsequences II
Given a string S, count the number of distinct, non-empty subsequences of S .

Since the result may be large, return the answer modulo 10^9 + 7.

 

Example 1:

Input: "abc"
Output: 7
Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac", "bc", and "abc".
*/
    public int distinctSubseqII(String s) {
        //dp[i] means for for String s，we count from 0->i, how many distinct subsequence, 
        //so suppose "abab", 
        //dp[0]= {"", "a"} = 2
        //dp[1] = {"", "a", "b", "ab"} = 4
        //dp[2] = {"", "a", "b", "ab", "aba", "aa", "ba"} = 7, the extra is ignored since 
        //duplicated so last character s[i] is added previous set dp[i-1], 
        //suppose no duplicate, it would be 2* dp[i-1]，because set's principal, 2^n, previous was
        //2^(n-1)
        //eg: "baca", dp=[1, 2, 4, 8, 13], last['a'-'a'] = 1, dp[1] = 2, not a, it is the set count
        //before last 'a', so that part will double, and we substract one
        ////count[26] to remember for each char what's their count
        //dp[i] = 2 * dp[i-1] - dp[last[x]], last[x] means last same char in string and
        if (s == null || s.length() < 1) return 0;
        
        int M = (int)1e9 + 7;
        int n = s.length();
        int[] dp = new int[n+ 1];
        dp[0] = 1;
        int[] last = new int[26];
        Arrays.fill(last, -1);
        
        for(int i =0; i < n; i++) {
            int x = s.charAt(i) - 'a';
            dp[i+1] = dp[i] * 2 % M;
            if (last[x] >= 0) {
                //"baca", here last['a' - 'a'] = 0,  
                dp[i+1] -= dp[last[x]];
                dp[i+1] %= M;
            }
/*
Assume ith character is x, we want to subtract all the substrings that add 
x as the last character in other to avoid repetition. e.g. assume we have aba 
before the first time we add x. Then, in this time we want to add x we need 
to remove aba first and add x. If we don't do so, there will be two abax.
*/
                last[x] = i;

        }
        //remove empty string
        dp[n]--;
        if (dp[n] < 0) dp[n] += M;
        return dp[n];

    }
    //O(1) space version, it use prev to remember dp[i-1]
    public int distinctSubseqII_O1Space(String s) {
        final int M = (int)1e9 + 7;
        int pre = 1;//The number of subsequences till previous-location. Include empty string: ""
        int cur = 1;//The number of subsequences till now. Include empty string: ""
        int[] last_count = new int[26];//The number of subsequences that end with a character till now. Not include empty string: ""
        for(int i=0; i < s.length(); ++i)
        {
            int charIndex = s.charAt(i)-'a';
            cur = pre * 2 % M;//include-current-character  +  not-include-current-character
            cur -= last_count[charIndex];//Remove duplicated characters: previous subsequences that end with current character.
            cur = cur >= 0 ?  cur%M : cur+M;
            last_count[charIndex] = pre; //The number of subsequences that end with current character.
            pre = cur;
        }
        --cur;// remove the empty string: ""
        return cur;
    }
}