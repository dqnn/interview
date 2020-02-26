package hatecode._0001_0999;
public class _696CountBinarySubstrings {
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
/*
Every time pre >= cur means there are more 0s before 1s, so could do count++ . 
(This was the tricky one, ex. 0011 when you hit the first '1', curRun = 1, preRun = 2, 
means 0s number is larger than 1s number, so we could form "01" at this time, count++ . 
When you hit the second '1', curRun = 2, preRun = 2, means 0s' number equals to 1s' number, 
so we could form "0011" at this time, that is why count++)
 */
            if (pre >= cur) res++;
        }
        return res;
    }
    
}