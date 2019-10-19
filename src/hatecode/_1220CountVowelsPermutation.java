package hatecode;

import java.util.*;
public class _1220CountVowelsPermutation {
/*
1220. Count Vowels Permutation
Given an integer n, your task is to count how many strings of length n can be formed under the following rules:

Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
Each vowel 'a' may only be followed by an 'e'.
Each vowel 'e' may only be followed by an 'a' or an 'i'.
Each vowel 'i' may not be followed by another 'i'.
Each vowel 'o' may only be followed by an 'i' or a 'u'.
Each vowel 'u' may only be followed by an 'a'.
Since the answer may be too large, return it modulo 10^9 + 7.

 

Example 1:

Input: n = 1
Output: 5
*/
    
    //thinking process: O(n)/O(n)
    
    //the problem is to say: given an integer n, and 5 vowels rules
    //which means the next char rule, if we want a length =n string, how 
    //many strings we can have
    
    //given such rules about string, we use dp[i][char] means how many i length
    //strings there with last char is char, so for dp[i+1][char] we  can 
    //infer from the rules, for example, 
    //dp[2]['a'], so if last char is a, we can see there are e->a,i->a,u->a
    //so use this to finish the work
    public int countVowelPermutation(int n) {
        if(n <= 0) return 0;
        
        int MOD = (int) (1e9 + 7);
        long[][] dp = new long[n+1][5];
        for(int i = 0; i< 5; i++) dp[1][i] = 1;
        /*
            0: a,  a->e
            1: e,  e->i, e->a
            2: i,  i->a, i->e, i->o, i->u
            3: o   o->i, o->u,
            4: u   u->a
         */
        for(int i =1; i <n; i++) {
            dp[i+1][0] =(dp[i][1] + dp[i][2] + dp[i][4]) %MOD;
            dp[i+1][1] =(dp[i][0] + dp[i][2]) %MOD;
            dp[i+1][2] =(dp[i][1] + dp[i][3]) %MOD;
            dp[i+1][3] =(dp[i][2]) %MOD;
            dp[i+1][4] =(dp[i][2] + dp[i][3]) %MOD;
        }
        
        return  (int)(Arrays.stream(dp[n]).sum() % MOD);
        
        
    }
}