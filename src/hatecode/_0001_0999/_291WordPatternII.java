package hatecode._0001_0999;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : WordPatternII
 * Creator : professorX
 * Date : Sep, 2018
 * Description : 291. Word Pattern II
 */
public class _291WordPatternII {
    /**
     * Given a pattern and a string str, find if str follows the same pattern.

     Here follow means a full match, such that there is a bijection
     between a letter in pattern and a non-empty substring in str.

     Examples:
     pattern = "abab", str = "redblueredblue" should return true.
     pattern = "aaaa", str = "asdasdasdasd" should return true.
     pattern = "aabb", str = "xyzabcxzyabc" should return false.


     pattern = "abab", str = "redblueredblue" "redres


     time : O(n * C(n^m))  m = p.length(), n = s.length()  

     you need to slice the string n into m pieces, how many ways, (n-1)*(n-2)*(n-3)*..(n-m)
     space : O(n*m) how deep we call isMatch

     * @param pattern
     * @param str
     * @return
     */
//thinking process:
    
    // the problem is to verify pattern and str is the same, str is the string and pattern is the pattern
    
    //we don't know any pattern in str and seems the only way is brute force, so backtracking, 
    
    // we start from one character by one character, suppose one character is the most basic string so we figure out the 
    //exit condition we both visit the pat and str end postion at the same time, if not then it is false. 
    
    // so we need a map to store the pattern, when we visit the map, if we found a match, then we just can step further, 
    // try next match means next char in pattern
    
    
    //if we cannot find pattern in map which means this is new, but we don't know where is the ends, so we need a for loop to 
    // detect where is the end, each is one more character, we here use map and set to assit have we reached this string before. 
    public boolean wordPatternMatch(String pattern, String str) {
        if (pattern == null && str == null) {
            return true;
        }
        if (pattern == null || str == null) {
            return false;
        }
        
        Set<String> set = new HashSet<>();
        Map<Character, String> map = new HashMap<>();
        
        return helper(str, 0, pattern, 0, map, set);
    }
    
    public boolean helper(String str, int i, String pat, int j, Map<Character, String> map, Set<String> set) {
        if (i ==str.length() && j == pat.length()) return true;
        if (i == str.length() || j == pat.length()) return false;
        char ch = pat.charAt(j);
        if (map.containsKey(ch)) {
            String temp = map.get(ch);
            //here we use the map to do the pattern check, if same char has different pattern which would 
            //prove the first herpothesis is wrong
            if (!str.startsWith(temp, i)) {
               return false;
            }
            //if match is done, then we come to next part of str and next char of pat, if 
            //we previous already visit the string, then means it is not qualified, just skip it. this is for 
            //improve the performance. 
            return helper(str, i + temp.length(), pat, j + 1, map, set);
        }
        //why we start from i, not i + 1? because str = d, pattern = e usecase
        //we start from i always to start from very first element in str
        for(int k = i; k < str.length(); k++) {
            String temp = str.substring(i, k + 1);
            if (set.contains(temp)) {
                continue;
            }
            map.put(ch, temp);
            set.add(temp);
            //we always start next
            if (helper(str, k + 1, pat, j + 1, map, set)) {
                return true;
            }
            //this is retreat to previous status
            map.remove(ch);
            set.remove(temp);
        }
        return false;
    }
}
