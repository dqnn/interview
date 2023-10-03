package _2000_2999;

import java.util.*;

public class _2062CountVowelSubstringsOfAString {
/*
2062. Count Vowel Substrings of a String

A substring is a contiguous (non-empty) sequence of characters within a string.

A vowel substring is a substring that only consists of vowels ('a', 'e', 'i', 'o', and 'u') and has all five vowels present in it.

Given a string word, return the number of vowel substrings in word.

 

Example 1:

Input: word = "aeiouu"
Output: 2
Explanation: The vowel substrings of word are as follows (underlined):
- "aeiouu"
- "aeiouu"
Example 2:

Input: word = "unicornarihan"
Output: 0


"cuaieuouac"

c uaieu ouac
c uaieuo uac

*/

/*
 * thinking process: O(n)/O(1)
 * 
 * the problem is to say: given one string it contains vows and non-vows,  return how many substring it contains thr 5 vows only
 * - "aeiouu"
      aeiou
      aeiouu

      so there are 2 substrings satifsy requirements, so we return 2



 * 
 * this is easiest solution, 
 */
    public int countVowelSubstrings2(String s) {
        if(s == null || s.length() < 1) return 0;
        
        Map<Character, Integer> map = new HashMap<>();
        map.put('a', -1);
        map.put('e', -1);
        map.put('i', -1);
        map.put('o', -1);
        map.put('u', -1);
        
        int count = 0;
        for(int i = 0, invalidP = -1; i < s.length(); i++) {
            char c = s.charAt(i);
            if(map.containsKey(c)) {
                map.put(c, i);
                count += Math.max(Collections.min(map.values()) - invalidP, 0);
            } else invalidP = i;
        }
        return count;
    }
    
    /*
     * 
     */
    public int countVowelSubstrings(String s) {
        if(s == null || s.length() < 1) return 0;
        
        int[] cnt = new int[26];
        int l = 0, r = 0;
        
        int distcnt = 0;
        int res = 0;
        int mid = 0;
        for(; r <s.length(); r++) {
            char c = s.charAt(r);
            if(c =='a' || c=='e' || c=='i' || c=='o' || c== 'u') {
                cnt[c-'a']++;
                if(cnt[c-'a'] == 1) distcnt++;
                
            } else {
                l = r + 1;
                mid = l;
                Arrays.fill(cnt, 0);
                distcnt = 0;
                continue;
            }
            
            
            while(distcnt == 5) {
                cnt[s.charAt(mid) -'a']--;
                if(cnt[s.charAt(mid) -'a'] == 0) {
                    distcnt--;
                }
                mid++;
            }
            res += (mid - 1) - l + 1;
        }
        
        return res;
        
    }
}