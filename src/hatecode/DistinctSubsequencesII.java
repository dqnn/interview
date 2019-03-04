package hatecode;
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
        //but there are dup, eg, "ab"->"aba", "aa" should be only once, we need to know 
        //the how many we should substract, it is dp[prev a index], 
        //reason: "****bab", if we add "a" to this sequence, if we don't care about dup, then it would 
        //add just add each sequence "a" and append to that list, so the only part we dup is the prev "a"
        //end 's sub sequence, "***aa" is not doubled, but "***ba" and "***ba" is dup, the count of this 
        //part is dp[prev a index], because that number describe with a as end and without a, 
        //if we add a new 
        //count[26] to remember for each char what's their count
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