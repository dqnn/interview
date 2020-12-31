package hatecode._1000_1999;
public class _1221_SplitAStringInBalancedStrings {
/*
1221. Split a String in Balanced Strings
Balanced strings are those who have equal quantity of 'L' and 'R' characters.

Given a balanced string s split it in the maximum amount of balanced strings.

Return the maximum amount of splitted balanced strings.

 

Example 1:

Input: s = "RLRRLLRLRL"
Output: 4
*/
    
    //thinking process: O(n)/O(1)
    
    //we use cnt to record the L/R count, when it comes to 0, then we have one more ++
    public int balancedStringSplit_Best(String s) {
        if (s == null || s.length() < 1) return 0;
        
        int res = 0;
        int cnt = 0;
        for(char c : s.toCharArray()) {
            cnt += c == 'L' ? 1:-1;
            if (cnt == 0) res++;
        }
        
        return res;
    }
    
    public int balancedStringSplit(String s) {
        if (s == null || s.length() < 1) return 0;
        
        int l = 0, r = 0;
        int res  = 0;
        for(char c: s.toCharArray()) {
            if (c =='L') l ++;
            else  r++;
            
            if (l == r)  res++;
        }
        
        return res;
    }
}