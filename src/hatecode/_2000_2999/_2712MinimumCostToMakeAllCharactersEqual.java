package hatecode._2000_2999;

import java.util.*;

public class _2712MinimumCostToMakeAllCharactersEqual {
    /*
    2712. Minimum Cost to Make All Characters Equal
    
    You are given a 0-indexed binary string s of length n on which you can apply two types of operations:
    
    Choose an index i and invert all characters from index 0 to index i (both inclusive), with a cost of i + 1
    Choose an index i and invert all characters from index i to index n - 1 (both inclusive), with a cost of n - i
    Return the minimum cost to make all characters of the string equal.
    
    Invert a character means if its value is '0' it becomes '1' and vice-versa.
    
     
    
    Example 1:
    
    Input: s = "0011"
    Output: 2
    Explanation: Apply the second operation with i = 2 to obtain s = "0000" for a cost of 2. It can be shown that 2 is the minimum cost to make all characters equal.
    Example 2:
    
    Input: s = "010101"
    Output: 9
    */
        /*
        010101 
        
        （“110101”， 1）， （“101010”， 6） 
        （“100101”， 2）， （001010， 5， 
        
        
        Map<String, Integer> map, 
        dp[i] = dp[i-1] + min(i, n-i) if s[i] != s[i-1]
        dp[i] = dp[i-1] otherwise
        
        */
        public long minimumCost_DP(String s) {
            int n = s.length();
            long[] dp = new long[n];
            dp[0] = 0;
            for(int i = 1; i<n; i++) {
                if(s.charAt(i) != s.charAt(i-1)) {
                    dp[i] = dp[i-1] + Math.min(i, n-i);
                } else dp[i] = dp[i-1];
            }
            
            return dp[n-1];
        }
        /*
         * interview friendly O(n)/O(1)
         */
        public long minimumCost(String s) {
            int n = s.length();
            long res = 0;
            for(int i = 1; i< n; i++) {
                if (s.charAt(i) != s.charAt(i-1)) {
                    res += Math.min(i, n - i);
                }
            }
            
            return res;
        }
        
    }