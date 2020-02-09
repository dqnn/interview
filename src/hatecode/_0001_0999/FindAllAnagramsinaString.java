package hatecode._0001_0999;

import java.util.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : FindAllAnagramsinaString
 * Creator : duqiang
 * Date : July, 2018
 * Description : 438. Find All Anagrams in a String
 */
public class FindAllAnagramsinaString {
    
    /**tags: Two Pointers
     * 
     * 
 related probelems can be solved by this templates
https://leetcode.com/problems/minimum-window-substring/
https://leetcode.com/problems/longest-substring-without-repeating-characters/
https://leetcode.com/problems/substring-with-concatenation-of-all-words/
https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
https://leetcode.com/problems/find-all-anagrams-in-a-string/
     * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

     Strings consists of lowercase English letters only and the length of
     both strings s and p will not be larger than 20,100.

     The order of output does not matter.

     Example 1:

     Input:
     s: "cbaebabacd" p: "abc"

     Output:
     [0, 6]

     Explanation:
     The substring with start index = 0 is "cba", which is an anagram of "abc".
     The substring with start index = 6 is "bac", which is an anagram of "abc".

     0 1 2 3 4 5 6 7 8 9
     c b a e b a b a c d
                   e
             s

     time : O(n)
     space : O(n)


     * @param s
     * @param p
     * @return
     */
        //thinking process: two pointers
        // we change change to templates which use two while and get the answer, 
    //but here is more elegant version,
     //this is pretty good TP code questions
    //TP templates
    public List<Integer> findAnagrams(String s, String t) {
        //init a collection or int value to save the result according the question.
        List<Integer> res = new ArrayList<>();
        if(t.length()> s.length()) return res;
        
        //create a hashmap to save the Characters of the target substring.
        //(K, V) = (Character, Frequence of the Characters)
        Map<Character, Integer> map = new HashMap<>();
        for(char c : t.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        //maintain a counter to check whether match the target string.
        int counter = map.size();//must be the map size, NOT the string size because the char may be duplicate.
        
        //Two Pointers: begin - left pointer of the window; end - right pointer of the window
        int l = 0, r = 0;
        
        //the length of the substring which match the target string.
        int len = Integer.MAX_VALUE; 
        
        //loop at the begining of the source string, default we always move r to right
        while(r < s.length()){
            
            char c = s.charAt(r);//get a character
            
            if( map.containsKey(c) ){
                map.put(c, map.get(c)-1);// plus or minus one
                if(map.get(c) == 0) counter--;//modify the counter according the requirement(different condition).
            }
            r++;
            
            //increase begin pointer to make it invalid/valid again
            /* counter condition. different question may have different condition */
            //we only move left when counter ==0 because we have to move l
            //we need to remove one char from left, so count should increase and map should increase
            //
            while(counter == 0 ){
              //***be careful here: choose the char at begin pointer, NOT the end pointer
                char lc = s.charAt(l);
                if(map.containsKey(lc)){
                    map.put(lc, map.get(lc) + 1);//plus or minus one
                  //modify the counter according the requirement(different condition).
                  //because this is in while(count == 0), which means lc must be new
                    if(map.get(lc) > 0) counter++;
                }
                
                /* save / update(min/max) the result if find a target*/
                // result collections or result int value
                //although we saved unique character but still we use length, so it is fine
                if(r-l == t.length()){
                    res.add(l);
                }
                l++;
            }
        }
        return res;
    }
 }
