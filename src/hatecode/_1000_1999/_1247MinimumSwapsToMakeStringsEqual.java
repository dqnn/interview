package hatecode._1000_1999;

public class _1247MinimumSwapsToMakeStringsEqual {
    
    /*
    1247. Minimum Swaps to Make Strings Equal

You are given two strings s1 and s2 of equal length consisting of letters "x" and "y" only. Your task is to make these two strings equal to each other. You can swap any two characters that belong to different strings, which means: swap s1[i] and s2[j].

Return the minimum number of swaps required to make s1 and s2 equal, or return -1 if it is impossible to do so.

 

Example 1:

Input: s1 = "xx", s2 = "yy"
Output: 1
Explanation: Swap s1[0] and s2[1], s1 = "yx", s2 = "yx".
Example 2:

Input: s1 = "xy", s2 = "yx"
Output: 2
Explanation: Swap s1[0] and s2[0], s1 = "yy", s2 = "xx".
Swap s1[0] and s2[1], s1 = "xy", s2 = "xy".
Note that you cannot swap s1[0] and s1[1] to make s1 equal to "yx", cause we can only swap chars in different strings.
Example 3:

Input: s1 = "xx", s2 = "xy"
Output: -1
      
      
    */
    
    /*
     * thinking process: O(n)/O(1)
     * 
     * the problem is to say: given two strings s1 and s2, which only contains x and y, return the min swap to make s1 equals to s2. 
     * 
     * for example, s1="xx", s2="yy", only need one swap
     *   x    x 
     *      \
     *   y    y
     * 
     * another case: s1 =xy, s2=yx we need 2 swaps 
     * 
     * base on above facts, we use two counters counxy records how many x in s1 while y in s2 in same position, same as countyx 
     * 
     * we can apply 1st case, with countxy/2 +countyx/2, so we can minimize the swaps, 
     * 
     * "xyxyyx"
     *     |
       "yxyxxy"
     
        yyyx
        xxxy

        yyxx
        xxyy
        
        xyxx
        xyyy
        
        xyxy
        xyxy        



     */
    public int minimumSwap(String s1, String s2) {
        if (s1 == null && s2 == null) return 0;
        if (s1 == null || s2 == null || (s1.length() != s2.length())) return -1;
        
        int countxy = 0, countyx = 0;
        for(int i = 0; i<s1.length();i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
             if (c1 == c2) continue;
             if(c1 =='x' && c2 == 'y') {
                countxy++;
            } else countyx++;
        }
        
        if(countxy % 2 != countyx%2) return -1;
        
        int res = countxy/2+countyx/2;
        if(countxy % 2 == 1) res += 2;
        
        return res;
    }
}