package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : WordBreakII
 * Date : Sep, 2018
 * Description : 140. Word Break II
 */
public class _140WordBreakII {
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
    // index--> possible strings, list represents from index->s.length()
    //the possible word break
    static HashMap<Integer, List<String>> map = new HashMap<>();

    // thinking process:
    // the problem is break s into words which should exist in dict， return all
    //possible sentences
    
    //so for example catsanddog ["cat", "cats", "and", "sand", "dog"]
    // start from 0, 
    public static List<String> wordBreak(String s, List<String> wordDict) {
        // last is index
        return helper(s, wordDict, 0);
    }
    // c a t s a n d d o g
   //        /           \ 
  //    cat|sanddog   cats|anddog
  //      /                 /   
//   cat|sand|dog       cats|and|dog
    public static List<String> helper(String s, List<String> wordDict, int start) {
        if (map.containsKey(start)) {
            return map.get(start);
        }
        List<String> res = new ArrayList<>();
        //this is necessary to bootstrap the word append in backtracking code, there is 
        //list loop, if list.size() ==0, then res has no chance to add the substring
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
                List<String> list = helper(s, wordDict, end);
                for (String temp : list) {
                    //here, suppose we end at cat, then it will send "sand dog" back
                    res.add(s.substring(start, end) + (temp.equals("") ? "" : " ") + temp);
                }
            }
        }
        map.put(start, res);
        return res;
    }
    
    public static void main(String[] args) {
        System.out.println(wordBreak("catsanddog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
    }
}
