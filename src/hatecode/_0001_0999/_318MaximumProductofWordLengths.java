package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MaximumProductofWordLengths
 * Creator : professorX
 * Date : Aug, 2018
 * Description : 318. Maximum Product of Word Lengths
 */
public class _318MaximumProductofWordLengths {
    /**
     * Given a string array words, find the maximum value of length(word[i]) * length(word[j])
     * where the two words do not share common letters. You may assume that each word will contain only lower case letters.
     * If no such two words exist, return 0.

     Example 1:
     Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
     Return 16
     The two words can be "abcw", "xtfn".

     Example 2:
     Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
     Return 4
     The two words can be "ab", "cd".

     Example 3:
     Given ["a", "aa", "aaa", "aaaa"]
     Return 0
     No such pair of words.

     val |= 1 << (words[i].charAt(j) - 'a');

     1 << 0  00001 = 1  a
     1 << 1  00010 = 2  b
     1 << 2  00100 = 4  c
     1 << 3  01000 = 8  d

     abc = 00111 = 7   ab = 00011 = 3

     bytes[i] & bytes[j] == 0

     "abc", "ba", "ef"

     time : O(n^2)
     space : O(n)

     * @param words
     * @return
     */
    public int maxProduct(String[] w) {
        if (w ==null || w.length < 2) {
            return 0;
        }
        
        int[] bytes = new int[w.length];
        for(int i = 0; i < w.length; i++) {
            int val = 0;
            for(int j = 0; j < w[i].length(); j++) {
                // this is the key, we use val to store all chars in one integer, this way we should remember,
                // a--> move 0, b--> move 1, c --> move 2 positions, so each letter will move different position
                //so when we |, it means sum them
                val = val | 1 << (w[i].charAt(j) - 'a');
            }
            bytes[i] = val;
        }
        int res = 0;
        for(int i = 0; i < bytes.length; i++) {
            for(int j = i + 1; j < bytes.length; j++) {
                if ((bytes[i] & bytes[j]) == 0) {
                    res = Math.max(res, w[i].length() * w[j].length());
                }
            }
        }
        
        return res;
    }
}
