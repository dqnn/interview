package hatecode._0001_0999;

import java.util.*;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LongestSubstringWithoutRepeatingCharacters
 * Creator : professorX
 * Date : Aug, 2018
 * Description : TODO
 */
public class _003LongestSubstringWithoutRepeatingCharacters {
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
    //thinking process: O(n)/O(n)
    //the problem is to say: given one string m find the max length of one substring which 
    //has longest length, abcabcbb ---> abc
    // we use a map to store char-> idx, so we use two pointers i and j.
    // when we scan from left to right, 
    // if find the char in the map, then we know the 
    //left pointer position max(j, map.get(ch) + 1
    // and we update new char since left pointer move one more step， the length of the string is i -j +1
    // the key of the problem is to understand how we move the left and right pointers
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        Map<Character, Integer> map = new HashMap<>();
        int res = 0;
        //here is one way to initialize i and j when using TP
        for (int l = 0, r = 0; r < s.length(); r++) {
            //move l to t   ion, since it is already dup, so we have to find next 
            //qualified substring, faster， thinking about r is in the middle of the substring
            if (map.containsKey(s.charAt(r))) {
                l = Math.max(l, map.get(s.charAt(r)) + 1);
            }
            //put new character into the map
            map.put(s.charAt(r), r);
            res = Math.max(res, r - l + 1);
        }
        return res;
    }
   //this is TP sliding window templates, interview friendly
    
    //so r would advanced to right always but also when we want to move left, 
    //the key is if we find the map.size() is smaller than substring length which means 
    //we have duplicate character in the substring, so we want to move the l to right
    //after each adjustment, we would like to see the substring length
    public static int lengthOfLongestSubstring_SlidingWindow(String s) {
        if (s == null || s.length() < 1) return 0;
        
        int l = 0, r =0;
        int res = 1;
        Map<Character, Integer> map = new HashMap<>();
        while(r < s.length()) {
            char rc = s.charAt(r++);
            map.put(rc, map.getOrDefault(rc, 0) + 1);
            System.out.println(map.size() + "---" + (r- l));
            //the loop condition is the key, because if we have dup which means we have to 
            //move l to the correct position, r is controller by outter loop
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
    
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcabc"));
    }
}
