package hatecode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LongestSubstringwithAtLeastKRepeatingCharacters
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : 395. Longest Substring with At Least K Repeating Characters
 */
public class LongestSubstringwithAtLeastKRepeatingCharacters {
    /**
     * Find the length of the longest substring T of a given string (consists of lowercase letters only)
     * such that every character in T appears no less than k times.

     Example 1:

     Input:
     s = "aaabb", k = 3

     Output:
     3

     The longest substring is "aaa", as 'a' is repeated 3 times.
     Example 2:

     Input:
     s = "ababbc", k = 2

     Output:
     5

     The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.


     * @param s
     * @param k
     * @return
     */

    // time : O(n) space : O(1)
    /*
     * For h=1:26, we are going to use sliding window (left i, right j) to 
     * find the "longest window which contains exactly h unique characters 
     * and for each character, there are at least K repeating ones".
For example, when h=3, K=5, we are going to find the longest window contains exactly 3 unique 
characters and each repeating 5 times.
     */
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() < 1 || k < 1) {
            return 0;
        }
        int res = 0;
        for(int i = 1; i <= 26; i++) {
            res = Math.max(res, helper(s, k, i));
        }
        
        return res;
    }
     // numUniqueTareget = 3, means we want to find 3 unique char in S which has longest length and each of them
    // occurrency no less than k
    public int helper(String s, int k, int targetUniqueNum) {
        int[] count = new int[128];
        int start = 0, end = 0;
        int numUnique = 0, numNoLessThanK = 0;
        int res = 0;
        
        while(end < s.length()) {
            if (count[s.charAt(end)] ++ == 0) {
                numUnique ++;
            }
            
            if (count[s.charAt(end++)] == k) {
                numNoLessThanK++;
            }
            //if the unique number more than we want, so we want to re-find the length of the string
            // until unique number equals to the target we want to find
            while(numUnique > targetUniqueNum) {
                // if we find one char 's count more than k, then we count down
                if (count[s.charAt(start)]-- == k) {
                    numNoLessThanK--;
                }
                // if we have one new char count still 0 which means it is unqiue one, so we count down
                if (count[s.charAt(start++)] == 0) {
                    numUnique --;
                }
            }
            if (numUnique == targetUniqueNum && numUnique == numNoLessThanK) {
                res = Math.max(res, end - start);
            }
        }
        
        return res;
    }
    
    
    // not understand this solution
    public int longestSubstring2(String s, int k) {
        char[] str = s.toCharArray();
        return helper(str,0,s.length(),k);
    }
    private int helper(char[] str, int start, int end,  int k){
        if (end - start < k) return 0;//substring length shorter than k.
        int[] count = new int [26];
        for (int i = start; i<end; i++) {
            int idx = str[i] - 'a';
            count[idx]++;
        }
        for (int i=0; i<26; i++) {
            if (count[i] < k && count[i] > 0) { //count[i]=0 => i+'a' does not exist in the string, skip it.
                for (int j = start; j<end; j++) {
                    if (str[j] == i+'a') {
                        //divide into two parts
                        int left = helper(str, start, j, k);
                        int right = helper(str, j+1, end, k);
                        return Math.max(left, right);
                    }
                }
            }
        }
        return end - start;
    }
    
    //recursive one, so 
    public int longestSubstring3(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        char[] chars = new char[26];
        // record the frequency of each character
        for (int i = 0; i < s.length(); i += 1) chars[s.charAt(i) - 'a'] += 1;
        boolean flag = true;
        for (int i = 0; i < chars.length; i += 1) {
            if (chars[i] < k && chars[i] > 0) flag = false;
        }
        // return the length of string if this string is a valid string
        if (flag == true) return s.length();
        int result = 0;
        int start = 0, cur = 0;
        // otherwise we use all the infrequent elements as splits
        while (cur < s.length()) {
            if (chars[s.charAt(cur) - 'a'] < k) {
                //
                result = Math.max(result, longestSubstring3(s.substring(start, cur), k));
                start = cur + 1;
            }
            cur++;
        }
        result = Math.max(result, longestSubstring3(s.substring(start), k));
        return result;
    }
}
