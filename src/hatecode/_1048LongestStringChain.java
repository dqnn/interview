package hatecode;

import java.util.*;
public class _1048LongestStringChain {
/*
1048. Longest String Chain
Given a list of words, each word consists of English lowercase letters.

Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".

A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.

Return the longest possible length of a word chain with words chosen from the given list of words.

 

Example 1:

Input: ["a","b","ba","bca","bda","bdca"]
Output: 4
*/
    //thinking process:
    //define a predecessor word, is that abc->abdc, then abc is abdc's 
    //predecessor word, because we add d into abc, any palce in abc is working
    
    //so given array of strings, we would like to longest string chain which pre is 
    //next's predecessor
    
    //so from the example, each next word length will +1. so we can easily think about 
    //
    public int longestStrChain(String[] words) {
        if(words == null || words.length < 1) return 0;
        
        Arrays.sort(words, (a, b)->(a.length() - b.length()));
        Map<String, Integer> dp = new HashMap<>();
        int res = 0;
        for(String w : words) {
            int lst = 0;
            for(int j = 0; j< w.length(); j++) {
                String cur = w.substring(0, j) +  w.substring(j+1);
                lst = Math.max(lst, dp.getOrDefault(cur, 0) + 1);
            }
            dp.put(w, lst);
            res = Math.max(res, lst);
        }
        
        return res;
    }

    public int longestStrChain_Fastest(String[] words) {
        List<List<String>> strings = new ArrayList<>();
        for (int i = 0; i < 16; i++)
            strings.add(new ArrayList<>());
        for (String word : words)
            strings.get(word.length() - 1).add(word);
        int res = 0;
        for (int i = 0; i < 16 && res < 16 - i; i++) {
            for (String cur : strings.get(i))
                res = Math.max(res, test(cur, i, strings));
        }
        return res;
    }
    
    private boolean match(String cur, String next) {
        if (next.length() - cur.length() != 1) return false;
        int i = 0, j = 0;
        boolean inserted = false;
        while (i < cur.length()) {
            if (cur.charAt(i) != next.charAt(j)) {
                if (inserted) return false;
                inserted = true;
                j++;
            } else {
                i++;
                j++;
            }
        }
        return true;
    }

    private int test(String cur, int idx, List<List<String>> strings) {
        if (idx == 15) return 1;
        int res = 1;
        for (String next : strings.get(idx + 1)) {
            if (match(cur, next))
                res = Math.max(res, 1 + test(next, idx + 1, strings));
        }
        return res;
    }

    
}