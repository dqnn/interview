package leetcode;

import java.util.ArrayList;
import java.util.List;

public class ShortestDistanceToACharacter {
/*
 * 821. Shortest Distance to a Character
Given a string S and a character C, return an array of integers representing the shortest 
distance from the character C in the string.

Example 1:

Input: S = "loveleetcode", C = 'e'
Output: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
 

Note:

S string length is in [1, 10000].
C is a single character, and guaranteed to be in string S.
All letters in S and C are lowercase.
 */
    
    //O(n)/O(n), Best and 
    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        int[] res = new int[n];
        int pos = -n;
        //scan from left to right
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == c) pos = i;
            res[i] = i - pos;
        }
        //scan from right to left, remember last pos = last c Idx
        for (int i = n - 1; i >= 0; --i) {
            if (s.charAt(i) == c)  pos = i;
            res[i] = Math.min(res[i], Math.abs(i - pos));
        }
        return res;
    }
    
    //my self solution
    public int[] shortestToChar2(String s, char c) {
        if (s == null || s.length() < 1 || s.indexOf(c) == -1) {
            return null;
        }
        List<Integer> pos = new ArrayList<>();
        for(int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                pos.add(i);
            }
        }
        int[] res = new int[s.length()];
        for(int i = 0; i< res.length; i++) {
            int min = s.length();
            for(int temp: pos) {
                min = Math.min(min, Math.abs(temp - i));
            }
            res[i] = min;
        }
        return res;
    }
    
    
}