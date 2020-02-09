package hatecode._0001_0999;
import java.util.*;
public class WordSubsets {
/*
916. Word Subsets
We are given two arrays A and B of words.  Each word is a string of lowercase letters.

Now, say that word b is a subset of word a if every letter in b occurs in a, including multiplicity.  For example, "wrr" is a subset of "warrior", but is not a subset of "world".

Now say a word a from A is universal if for every b in B, b is a subset of a. 

Return a list of all universal words in A.  You can return the words in any order.

 

Example 1:

Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","o"]
Output: ["facebook","google","leetcode"]
*/
    public List<String> wordSubsets(String[] A, String[] B) {
        List<String> res = new ArrayList<>();
        if (A == null && B == null) return res;
        
        int[] count = new int[26], tmp = new int[26];
        for(String str : B) {
            tmp = counter(str);
            for(int i = 0; i< 26;i++) count[i] = Math.max(count[i], tmp[i]);
        }
        
        for(String str: A) {
            tmp = counter(str);
            int i = 0;
            for(; i< 26;i++) {
                if (tmp[i] < count[i]) break;
            }
            if (i == 26) res.add(str);
        }
        return res;
    }
    
    
    private int[] counter(String str) {
        if (str == null) return new int[0];
        
        int[] res = new int[26];
        for(char ch: str.toCharArray()) {
            res[ch - 'a']++;
        }
        return res;
    }
}