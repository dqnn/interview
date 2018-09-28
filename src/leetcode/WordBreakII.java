package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : WordBreakII
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 140. Word Break II
 */
public class WordBreakII {
    /**
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
add spaces in s to construct a sentence where each word is a valid dictionary word. 
Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]


     A solution is ["cats and dog", "cat sand dog"].

     time : O(n^3)
     space : O(n^3)

     * @param s
     * @param wordDict
     * @return
     */
    // index--> possible strings
    HashMap<Integer, List<String>> map = new HashMap<>();

    // thinking process:
    // the problem is break s into words which should exists in dict
    
    //so 
    public List<String> wordBreak(String s, List<String> wordDict) {
        // last is index
        return dfs(s, wordDict, 0);
    }
    // c a t s a n d d o g
    // 
    public List<String> dfs(String s, List<String> wordDict, int start) {
        if (map.containsKey(start)) {
            return map.get(start);
        }
        List<String> res = new ArrayList<>();
        if (start == s.length()) {
            res.add("");
        }
        // every time we start to dfs when we found it in wordDicts
        // from start until end of s string, each time, we detect whether 
        //list contains such string or not, if yes, we continue dfs start = this cut
        //for the dfs returns, we add them into res, and return. 
        
        // this is more like tree thinking
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end))) {
                List<String> list = dfs(s, wordDict, end);
                for (String temp : list) {
                    res.add(s.substring(start, end) + (temp.equals("") ? "" : " ") + temp);
                }
            }
        }
        map.put(start, res);
        return res;
    }
}
