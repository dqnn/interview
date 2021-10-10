package hatecode._1000_1999;

import java.util.*;
public class _1234ReplaceTheSubstringForBalancedString {
/*
1234. Replace the Substring for Balanced String
You are given a string containing only 4 kinds of characters 'Q', 'W', 'E' and 'R'.

A string is said to be balanced if each of its characters appears n/4 times where n is the length of the string.

Return the minimum length of the substring that can be replaced with any other string of the same length to make the original string s balanced.

Return 0 if the string is already balanced.

 

Example 1:

Input: s = "QWER"
Output: 0
*/
    //thinking process: O(n)/O(1)
    
    //the problem is to say: give a string only contains 
    //"Q","W","E","R", length will be multiple times of 4, so
    //return the min of substring length which needs to replaced so
    //each character count will be the same.
    
    //we move left pointer when 
    public int balancedString(String s) {
        if (s == null || s.length() < 4) return 0;
        
        int[] count = new int[128];
        for(char c: s.toCharArray()) {
            count[c]++;
        }
        
        int l = 0, r = 0, k = s.length()/4, res = s.length();
        //exit early, may not need it
        if (count['Q'] <= k && count['W'] <= k && count['E'] <= k && count['R'] <= k) {
            return 0;
        }
        
        while(r < s.length()) {
            count[s.charAt(r)]--;
          
            //here is the key for sliding window:
            while(l <= r && count['W'] <=k && count['Q'] <=k && count['E'] <=k&& count['R'] <=k) {
                res = Math.min(res, r-l+1);
                count[s.charAt(l)]++;
                l++;
            }
            r++;
            
        }
        
        return res;
        
    }
}