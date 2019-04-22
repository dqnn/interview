package hatecode;

import java.util.*;
public class CountDifferentPalindromicSubsequences {
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
    
    public static void main(String[] args) {
        System.out.println(countPalindromicSubsequences("bccb"));
    }
    
}