package hatecode._1000_1999;
public class _1573NumberOfWayyToSplitAString {
/*
1573. Number of Ways to Split a String
Given a binary string s (a string consisting only of '0's and '1's), we can split s into 3 non-empty strings s1, s2, s3 (s1+ s2+ s3 = s).

Return the number of ways s can be split such that the number of characters '1' is the same in s1, s2, and s3.

Since the answer may be too large, return it modulo 10^9 + 7.

 

Example 1:

Input: s = "10101"
Output: 4
Explanation: There are four ways to split s in 3 parts where each part contain the same number of letters '1'.
"1|010|1"
"1|01|01"
"10|10|1"
"10|1|01"
*/
    //thinking process:
    //the problem is to say: given one string s only conains 1 or 0, then try to 
    //cut two times to 3 substrings, each string has same count of 1
    
    //suppose there is no 1, then n-1 position, we would have (n-1)*(n-2)/2 ways
    //suppose there are ones, then we can count how many position we can have first
    //cut then how many position we can have 2nd cut, assume we throw first cut
    private static final int M = 1_000_000_007;
    public int numWays(String s) {
        if (s == null || s.length() < 1) return 0;
        int countOne = 0;
        for(char c: s.toCharArray()) {
            if (c == '1') countOne++;
        }
        
        if(countOne % 3 != 0) return 0;
        else if (countOne == 0) return (int)((s.length() - 1L) * (s.length() - 2L) /2 % M);
        
        int avgOne = countOne / 3;
        int countTemp = 0;
        long firstCut_count = 0, secondCut_count =0;
        for(char c: s.toCharArray()) {
            if (c == '1') countTemp++;
            if(countTemp == avgOne) firstCut_count++;
            else if (countTemp == 2 * avgOne) secondCut_count ++;
        }
        return (int) (firstCut_count * secondCut_count % M);
        
    }
}