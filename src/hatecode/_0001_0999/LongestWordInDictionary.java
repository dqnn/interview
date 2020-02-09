package hatecode._0001_0999;

import java.util.*;
public class LongestWordInDictionary {
/*
720. Longest Word in Dictionary
Given a list of strings words representing an English Dictionary, find the longest word in words that can be built one character at a time by other words in words. If there is more than one possible answer, return the longest word with the smallest lexicographical order.

If there is no answer, return the empty string.
Example 1:
Input: 
words = ["w","wo","wor","worl", "world"]
Output: "world"
*/
    public String longestWord(String[] words) {
        if (words == null || words.length < 1) return "";
        //the key is here, we sort words by string.comparesTo(b), so longer will be in latter positions
        Arrays.sort(words);
        Set<String> set = new HashSet<>();
        String res = "";
        for (String w : words) {
            //if length() == 1 or except last char 
            if (w.length() == 1 || set.contains(w.substring(0, w.length() - 1))) {
                res = w.length() > res.length() ? w : res;
                set.add(w);
            }
        }
        return res;  
    }
}