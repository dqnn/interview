package hatecode._0001_0999;

import java.util.*;
public class _730CountDifferentPalindromicSubsequences {
/*
730. Count Different Palindromic Subsequences
Given a string S, find the number of different non-empty palindromic subsequences in S, and return that number modulo 10^9 + 7.

A subsequence of a string S is obtained by deleting 0 or more characters from S.

A sequence is palindromic if it is equal to the sequence reversed.

Two sequences A_1, A_2, ... and B_1, B_2, ... are different if there is some i for which A_i != B_i.

Example 1:
Input: 
S = 'bccb'
Output: 6
*/
    static int div=1000000007;
    public static int countPalindromicSubsequences(String S) {    
        //stores the s.charAt(i) -'a', List of i
        TreeSet[] characters = new TreeSet[26];
        int len = S.length();
        
        for (int i = 0; i < 26; i++) characters[i] = new TreeSet<Integer>();
        
        for (int i = 0; i < len; ++i) {
            int c = S.charAt(i) - 'a';
            characters[c].add(i);
        }
        Integer[][] dp = new Integer[len+1][len+1];
         return memo(S,characters,dp, 0, len);
    }
    
    public static int memo(String S,TreeSet<Integer>[] characters,Integer[][] dp,int start,int end){
        if (start >= end) return 0;
        if(dp[start][end]!=null) return dp[start][end];
       
            long res = 0;
            
            for(int i = 0; i < 26; i++) {
              //>=start, the smallest one,  right direction and  nearest position same char
                Integer new_start = characters[i].ceiling(start);
              //<=end, the greatest one, left direction and nearest position same char
                Integer new_end = characters[i].lower(end);
              if (new_start == null || new_start >= end) continue;
              //no matter same position or not, at least we can have one
              res++;
              
              //like "bccb" 
              if (new_start != new_end) res++;
              res+= memo(S,characters,dp,new_start+1,new_end);
                
            }
            dp[start][end] = (int)(res%div);
            return dp[start][end];
    }
    
    
    public int countPalindromicSubsequences_Best(String S) {
        
        char[] chars = S.toCharArray();
        int n = chars.length;
        int[] counts = new int[n];
        
        for(int i = 0; i < n; i++) {
            char char1 = chars[i];
            counts[i] = 1;
            long sum = 0;
            int[] tmp = new int[26];
            
            for(int j = i - 1; j >= 0; j--) {
                char char2 = chars[j];
                int count = counts[j];
                
                if (char1 == char2) {
                    counts[j] = (int)((sum + 2) % div);
                }
                
                sum += count - tmp[char2 - 'a'];
                tmp[char2 - 'a'] = count;
            }
        }
        
        int[] nums = new int[26];
        
        for(int i = n - 1; i >= 0; i--) {
            nums[chars[i] - 'a'] = counts[i];
        }
        
        long sum = 0;
        
        for(int cnt : nums) {
            sum += cnt;
        }
        
        return (int)(sum % div);
    }
/*
 * dp[i][j] means the substring s[i, j]
 * bccb = bcc + ccb - cc, 
 * when s.charAt(i) != s.charAt(j):
dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];

When s.charAt(i) == s.charAt(j):
the situation get much more complex and I fix a lot the wrong answers. 
I have comment the branches where which kind of test cases are considered.


 */
    //just for reference, it is hard to understand when s[i] = s[j]
    public int countPalindromicSubsequences_DP(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];

        char[] chs = s.toCharArray();
        for(int i = 0; i < len; i++){
            dp[i][i] = 1;   // Consider the test case "a", "b" "c"...
        }

        for(int distance = 1; distance < len; distance++){
            for(int i = 0; i < len - distance; i++){
                int j = i + distance;
                if(chs[i] == chs[j]){
                    int l = i + 1;
                    int r = j - 1;

              /* Variable low and high here are used to get rid of the duplicate*/

                    while(l <= r && chs[l] != chs[j]){
                        l++;
                    }
                    while(l <= r && chs[r] != chs[j]){
                        r--;
                    }
                    if(l > r){
                        // consider the string from i to j is "a...a" "a...a"... where there is no character 'a' inside the leftmost and rightmost 'a'
                       /* eg:  "aba" while i = 0 and j = 2:  dp[1][1] = 1 records the palindrome{"b"}, 
                         the reason why dp[i + 1][j  - 1] * 2 counted is that we count dp[i + 1][j - 1] one time as {"b"}, 
                         and additional time as {"aba"}. The reason why 2 counted is that we also count {"a", "aa"}. 
                         So totally dp[i][j] record the palindrome: {"a", "b", "aa", "aba"}. 
                         */ 

                        dp[i][j] = dp[i + 1][j - 1] * 2 + 2;  
                    } else if(l == r){
                        // consider the string from i to j is "a...a...a" where there is only one character 'a' inside the leftmost and rightmost 'a'
                       /* eg:  "aaa" while i = 0 and j = 2: the dp[i + 1][j - 1] records the palindrome {"a"}.  
                         the reason why dp[i + 1][j  - 1] * 2 counted is that we count dp[i + 1][j - 1] one time as {"a"}, 
                         and additional time as {"aaa"}. the reason why 1 counted is that 
                         we also count {"aa"} that the first 'a' come from index i and the second come from index j. So totally dp[i][j] records {"a", "aa", "aaa"}
                        */
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 1;  
                    } else {
                        // consider the string from i to j is "a...a...a... a" where there are at least two character 'a' close to leftmost and rightmost 'a'
                       /* eg: "aacaa" while i = 0 and j = 4: the dp[i + 1][j - 1] records the palindrome {"a",  "c", "aa", "aca"}. 
                          the reason why dp[i + 1][j  - 1] * 2 counted is that we count dp[i + 1][j - 1] one time as {"a",  "c", "aa", "aca"}, 
                          and additional time as {"aaa",  "aca", "aaaa", "aacaa"}.  Now there is duplicate :  {"aca"}, 
                          which is removed by deduce dp[low + 1][high - 1]. So totally dp[i][j] record {"a",  "c", "aa", "aca", "aaa", "aaaa", "aacaa"}
                          */
                        dp[i][j] = dp[i + 1][j - 1] * 2 - dp[l + 1][r - 1]; 
                    }
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];  //s.charAt(i) != s.charAt(j)
                }
                dp[i][j] = dp[i][j] < 0 ? dp[i][j] + 1000000007 : dp[i][j] % 1000000007;
            }
        }

        return dp[0][len - 1];
    }
    
    public static void main(String[] args) {
        System.out.println(countPalindromicSubsequences("bccb"));
    }
    
}