package hatecode._1000_1999;

import java.util.*;
import java.util.stream.Collectors;
public class _1153StringTransformsIntoAnotherString {
/*
1153. String Transforms Into Another String
Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing zero or more conversions.

In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.

Return true if and only if you can transform str1 into str2.

 

Example 1:

Input: str1 = "aabcc", str2 = "ccdee"
Output: true
*/
    //thinking process: O(n)/O(1), since only 26 chars
    
    //given two strings, we can change one char in s1, so return true if we can 
    //change to str2. all strings length are the same
    
    //take aabcc->ccdee as example, we can see a->c means a must change to c, the only
    //difference is when to change a->c, so any change path if we cannot fulfil this, then it 
    //is incorrect
    
    //so we can consider a path, a->c, c->e such movements, the destination is str2, 
    //
    public boolean canConvert(String s1, String s2) {
        if(s1 == null || s1 == s2) return true;
        
        if (s1.equals(s2)) return true;
        Map<Character, Character> dp = new HashMap<>();
        for (int i = 0; i < s1.length(); ++i) {
            if (dp.getOrDefault(s1.charAt(i), s2.charAt(i)) != s2.charAt(i))
                return false;
            dp.put(s1.charAt(i), s2.charAt(i));
        }
        return new HashSet<Character>(dp.values()).size() < 26;
    }
}