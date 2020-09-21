package hatecode._1000_1999;

import java.util.*;
public class _1178NumberOfValidWordsforEachPuzzle {
/*
1178. Number of Valid Words for Each Puzzle
With respect to a given puzzle string, a word is valid if both the following conditions are satisfied:
word contains the first letter of puzzle.
For each letter in word, that letter is in puzzle.
For example, if the puzzle is "abcdefg", then valid words are "faced", "cabbage", and "baggage"; while invalid words are "beefed" (doesn't include "a") and "based" (includes "s" which isn't in the puzzle).
Return an array answer, where answer[i] is the number of words in the given word list words that are valid with respect to the puzzle puzzles[i].
 

Example :

Input: 
words = ["aaaa","asas","able","ability","actt","actor","access"], 
puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
Output: [1,1,3,2,4,0]
*/
    //thinking process: O( n * 2 ^ 7 + m * k) = O(n + mk)/O()
    
    //the problem is to say:given two string arrays words and puzzles, 
    //words[i] contains puzzles[j] first letter and every char in words[i] will be in
    //puzzles[j], then we say words[i] is puzzles[j]'s valid word
    
    //so return an array, res[i] means how many words in words are valid for puzzles
    
    //we encoded each word, 
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        if(words == null || words.length< 1) return new ArrayList<>();
        
        //here calculate the mask<-> words list
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for(String w : words) {
            int cur = encode(w);
            map.put(cur, map.getOrDefault(cur, 0) + 1);
        }
        //
        for(String p : puzzles) {
            int cur = encode(p);
            //first char
            int must = 1 << (p.charAt(0) - 'a');
            int sub = cur;
            int count = 0;
            while(sub != 0) {
                //contains first letter && 
                if((sub & must) > 0 && map.containsKey(sub)){
                    count += map.get(sub);
                }
                
                sub = (sub - 1) & cur;
            }
            
            res.add(count);
        }
        return res;
    }
    
    //we encode each char to this integer, 
    //we have 32 bit while s can only have 26 bit, so we can definitely get an 
    //integer which represent this string
    
    //but this integer can only represnet how many distinct character
    int encode(String s) {
        int res = 0;
        for(char c : s.toCharArray()) {
            res |= 1 << (c-'a');
        }
        return res;
    }
}