package hatecode._0001_0999;

import java.util.HashMap;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LongestSubstringwithAtMostTwoDistinctCharacters
 * Creator : professorX
 * Date : Nov, 2017
 * Description : 159. Longest Substring with At Most Two Distinct Characters
 */
public class _159LongestSubstringwithAtMostTwoDistinctCharacters {
    /**
     * Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

     For example, Given s = “eceba”,

     T is "ece" which its length is 3.

     sliding window

     “eceba”

     time : O(n)
     space : O(n)

     * @param s
     * @return
     */

    //interview frinendly: 
    
    //notes: two pointers templates, note, this also can apply to K distinct chars
    public int lengthOfLongestSubstringTwoDistinct_Best(String s) {
        if (s == null || s.length() < 1) return 0;
        
        int k =2;
        int j = 0, cnt = 0,res = 0;
        int[] count =  new int[256];
        for(int i = 0; i< s.length(); i++) {
            if (count[s.charAt(i)]++ == 0) cnt++;
            if (cnt > k) {
                while(--count[s.charAt(j++)] > 0) {
                }
                cnt--;
            }
            res = Math.max(res, i-j+1);
        }
        return res;
    }
    
    public int lengthOfLongestSubstringTwoDistinct_TP(String s) {
        if (s == null) return 0;
        int n = s.length();
        if (n <= 2) return n;
        int maxLen = 2, cnt = 0;
        int[] map = new int[128];
        int left = 0;
        for (int right = 0; right < n; right++) {
            char c = s.charAt(right);
            map[c]++;
            if (map[c] == 1) cnt++;
            while (cnt > 2) {
                char leftC = s.charAt(left);
                map[leftC]--;
                if (map[leftC] == 0) cnt--;
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
    
    
    
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int start = 0, end = 0;
        int res = 0;
        while (end < s.length()) {
            if (map.size() <= 2) {
                map.put(s.charAt(end), end);
                end++;
            }
            if (map.size() > 2) {
                int leftMost = s.length();
                for (int num : map.values()) {
                    leftMost = Math.min(leftMost, num);
                }
                map.remove(s.charAt(leftMost));
                start = leftMost + 1;
            }
            res = Math.max(res, end - start);
        }
        return res;
    }
}
