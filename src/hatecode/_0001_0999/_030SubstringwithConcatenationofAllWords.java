package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SubstringwithConcatenationofAllWords
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 30. Substring with Concatenation of All Words
 */
public class _030SubstringwithConcatenationofAllWords {
    /**
     * You are given a string, s, and a list of words that 
     * are all of the same length.
     * Find all starting indices of substring(s) in s that is a 
     * concatenation of each word
     * in words exactly once and without any intervening characters.

Input:
  s = "barfoothefoobarman",
  words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoo" 
and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.
Example 2:

Input:
  s = "wordgoodstudentgoodword",
  words = ["word","student"]
Output: []

     You should return the indices: [0,9].
     (order does not matter).

     time : O(km) k = words.length, m = s.length()
     space : O(n)

     * @param s
     * @param words
     * @return
     */
    // thinking process:
    // so the problem for string s, we want to find how many idx in s
    //that it has all substring which can be joined by words
    
    // so first straightword idea to my head is string indexOf, so 
    //we can try to get all permutations of words and use indexOf 
    // to get all idx. this would be O(k! + k! * n * m)
    // m is avg length of words and k is length of words
    
    // so we change to other solutions that if we substring from s and 
    //compare them each from words, if we find one, we can look for next 
    //whether it existed in string next string s or not, so we change 
    // the string look up divide and conqure 
    // we search in s from 0 to s.length() - len(words) since 
    // since if last match then we don/t need to continue
    // so we will look up in map and count + and -. if next 
    //visit and it already 0 means we have used this string, 
    //so it cannot be this idx we wanted
    
    //last we check whether k == n because we may break during the 
    //while loop
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() < 1 || words.length < 1) {
            return res;
        }
        Map<String, Integer> map = new HashMap<>();
        //len is dictionalry words lengths sum
        int len = 0;
        for(String temp : words) {
            map.put(temp, map.getOrDefault(temp, 0) + 1);
            len += temp.length();
        }
        int n = words.length;
        //
        for(int i = 0; i <= s.length() - len; i++) {
            Map<String, Integer> copy = new HashMap<>(map);
            //so we look for possible concating combinations for s
            int j = i, k = 0;
            while(k < n) {
                String temp = s.substring(j, j + words[k].length());
                //if we cannot find it or already used it we move s pointer to next position
                if (!copy.containsKey(temp) || copy.get(temp) <= 0) {
                    break;
                }
                copy.put(temp, copy.get(temp) - 1);
                j += words[k].length();
                k++;
            }
            //if we reached the last word means we find one combinations
            if ( k == n) res.add(i);
        }
        return res;
    }
}
