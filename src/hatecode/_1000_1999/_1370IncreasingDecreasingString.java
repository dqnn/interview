package hatecode._1000_1999;

import java.util.*;
public class _1370IncreasingDecreasingString {
/*
1370. Increasing Decreasing String
Given a string s. You should re-order the string using the following algorithm:

Pick the smallest character from s and append it to the result.
Pick the smallest character from s which is greater than the last appended character to the result and append it.
Repeat step 2 until you cannot pick more characters.
Pick the largest character from s and append it to the result.
Pick the largest character from s which is smaller than the last appended character to the result and append it.
Repeat step 5 until you cannot pick more characters.
Repeat the steps from 1 to 6 until you pick all characters from s.
In each step, If the smallest or the largest character appears more than once you can choose any occurrence and append it to the result.

Return the result string after sorting s with this algorithm.

 

Example 1:

Input: s = "aaaabbbbcccc"
Output: "abccbaabccba"
*/
    //thinking process: O(n)/O(26)
    
    //the problem is to say:given one string s, we have rules to get
    //each character out, 
    public String sortString(String s) {
        TreeMap<Character, Integer> map = new TreeMap<>();
        
        for(char c: s.toCharArray()) {
            map.put(c, 1+map.getOrDefault(c, 0));
        }
        
        StringBuilder sb = new StringBuilder();
        boolean asc = true;
        while (!map.isEmpty()) {
            for (char c : asc ? new TreeSet<>(map.keySet()) : new TreeSet<>(map.descendingKeySet())) {
                sb.append(c);
                map.put(c, map.get(c) - 1);
                map.remove(c, 0);
            }
            asc = !asc; // same as asc ^= true;
        }
        return sb.toString();
    }
}