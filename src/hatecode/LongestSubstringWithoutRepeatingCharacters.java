package hatecode;

import java.util.*;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LongestSubstringWithoutRepeatingCharacters
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : TODO
 */
public class LongestSubstringWithoutRepeatingCharacters {
    /**
     * 3. Longest Substring Without Repeating Characters
     * Examples:

     Given "abcabcbb", the answer is "abc", which the length is 3.

     Given "bbbbb", the answer is "b", with the length of 1.

     Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring,
     "pwke" is a subsequence and not a substring.


     time : O(n)
     space : O(n)
     * @param s
     * @return
     */
    // we use a map to store char-> idx, so we use two pointers i and j.
    // when we scan from left to right, 
    // if find the char in the map, then we know the 
    //left pointer position max(j, map.get(ch) + 1
    // and we update new char since left pointer move one more step， the length of the string is i -j +1
    // the key of the problem is to understand how we move the left and right pointers
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
   //this is TP sliding window templates
    
    //so r would advanced to right always but also when we want to move left, 
    //the key is if we find the map.size() is smaller than substring length which means 
    //we have dup character in the susbtring, so we want to move the l to right
    //after each adjustment, we would like to see the substring length
    public int lengthOfLongestSubstring_SlidingWindow(String s) {
        if (s == null || s.length() < 1) return 0;
        
        int l = 0, r =0;
        int res = Integer.MIN_VALUE;
        Map<Character, Integer> map = new HashMap<>();
        while(r < s.length()) {
            char rc = s.charAt(r++);
            map.put(rc, map.getOrDefault(rc, 0) + 1);
            System.out.println(map.size() + "---" + (r- l));
            while(map.size() < (r - l)) {
                char lc = s.charAt(l);
                //System.out.println(l +"--" + r + "  lc=" + lc);
                int cnt = map.get(lc);
                if(cnt > 1) map.put(lc,cnt - 1);
                else map.remove(lc);
                l++;
            }
            res = Math.max(res, r - l);
        }
        return res;
    }
}
