package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : PaintFence
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 161. One Edit Distance
 */
public class OneEditDistance {
    /**
     * 161. One Edit Distance
Given two strings s and t, determine if they are both one edit distance apart.

Note: 

There are 3 possiblities to satisify one edit distance apart:

Insert a character into s to get t
Delete a character from s to get t
Replace a character of s to get t
Example 1:

Input: s = "ab", t = "acb"
Output: true
Explanation: We can insert 'c' into s to get t.
Example 2:

Input: s = "cab", t = "ad"
Output: false
Explanation: We cannot get t from s by only one step.
Example 3:

Input: s = "1203", t = "1213"
Output: true
Explanation: We can replace '0' with '1' to get t.

     1, abcre abere
     2, abdc abc
     3, abc abdc

     abc
     abcd

     time : O(n^2)
     space : O(1)

     * @param s
     * @param t
     * @return
     */
    //thinking process: 
    //the problem is to two only 1 edit distance, if we can equals the two strings
    
    //since one edit distance so the length max(diff) should be <=1,  so thinking about two 
    //pointers sp and tp in s and t, if s.charAt(sp) == t.charAt(tp) then we continue move, 
    //if not, we need to check which string is longer, if not still both move forward, if not 
    //longer move forward
    public boolean isOneEditDistance2(String s, String t) {
        if (s == null || t == null || s.equals(t) || Math.abs(s.length() - t.length()) > 1)  {
            return false;
        }
        //sp point to s, tp point to t
        int sp = 0, tp = 0, diffCount = 0;
        while(sp < s.length() && tp < t.length() && diffCount < 2) {
            if (s.charAt(sp) == t.charAt(tp)) {
                sp++;
                tp++;
                continue;
            }
            diffCount++;
            //so if 
            if (s.length() == t.length()) {
                tp++;
                sp++;
            } else if (s.length() > t.length()) {
                sp++;
            } else {
                tp++;
            }
        }
        return diffCount > 1 ? false: true;
    }
    
    //this solution is more straightforward that we find the character not equals.
    //then we compare the rest, since it is one edit distance, so that;s the last chance, left
    //must be the same to be true. one thing need to note is the longer should be i + 1 while 
    //shorter keeps i
    
    //the reason why we focus on length of s and t because the problem is only ask whether s and t 
    //is one edit distance or not. 
    
    //similiar to LC. 72. Edit Distance
    public static boolean isOneEditDistance(String s, String t) {
        if (s == null || t == null || s.equals(t) || Math.abs(s.length() - t.length()) > 1) {
            return false;
        }
        for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
            //only when it is different so we can compare
            if (s.charAt(i) != t.charAt(i)) {
                // if rest are the same, we can replace s with a char in at i 
                if (s.length() == t.length()) {
                    return s.substring(i + 1).equals(t.substring(i + 1));
                // if s > t, so we will compare the rest are the same, if yes, then we can 
                // replace ?
                } else if (s.length() > t.length()) {
                    return s.substring(i + 1).equals(t.substring(i));
                // smaller than t, need to insert a new character at this point
                } else {
                    return t.substring(i + 1).equals(s.substring(i));
                }
            }
        }
      //All previous chars are the same, the only possibility 
        //is deleting the end char in the longer one of s and t 
        return Math.abs(s.length() - t.length()) == 1;
    }
}
