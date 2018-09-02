package leetcode;

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
    public static boolean isOneEditDistance(String s, String t) {
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
        return Math.abs(s.length() - t.length()) == 1;
    }
}
