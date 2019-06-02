package hatecode;
public class CountBinarySubstrings {
/*
696. Count Binary Substrings
Give a string s, count the number of non-empty (contiguous) substrings that have the same number of 0's and 1's, and all the 0's and all the 1's in these substrings are grouped consecutively.

Substrings that occur multiple times are counted the number of times they occur.

Example 1:
Input: "00110011"
Output: 6
*/
    //thinking process: 
    //given a string, return the count of substring which its 0 and 1 has same count and 
    //consecutive
    
    //group the consecutive, 
    public int countBinarySubstrings_group(String s) {
        if (s == null || s.length() < 1) return 0;
        int n = s.length();
        int[] g = new int[n];
        int idx = 0;
        g[0] = 1;
        for(int i =1; i < n; i++) {
            if (s.charAt(i-1) != s.charAt(i)) {
                g[++idx] = 1;
            } else g[idx]++;
        }
        int res = 0;
        for(int i = 1; i< n; i++) res += Math.min(g[i-1], g[i]);
        return res;
    }
    //use prev and cur as rolling array
    public int countBinarySubstrings(String s) {
        if (s == null || s.length() < 1) return 0;
        int n = s.length();
        int pre = 0, cur = 1, res = 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i - 1) == s.charAt(i)) cur++;
            else {
                pre = cur;
                cur = 1;
            }
            // The last if statement is for checking how many valid substrings you have seen
            // so far in the iteration. For any value of pre > 1, we can only have pre
            // valid substrings up until pre is manipulated (this happens when we see
            // mismatching digits)
            // TODO: how to understand this?
            if (pre >= cur) res++;
        }
        return res;
    }
    
}