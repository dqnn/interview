package hatecode._1000_1999;

import java.util.*;
public class _1165Single_RowKeyboard {
/*
1165. Single-Row Keyboard
There is a special keyboard with all keys in a single row.

Given a string keyboard of length 26 indicating the layout of the keyboard (indexed from 0 to 25), initially your finger is at index 0. To type a character, you have to move your finger to the index of the desired character. The time taken to move your finger from index i to index j is |i - j|.

You want to type a string word. Write a function to calculate how much time it takes to type it with one finger.

 

Example 1:

Input: keyboard = "abcdefghijklmnopqrstuvwxyz", word = "cba"
Output: 4
*/
    //simple solution
    public int calculateTime(String keyboard, String word) {
        if(keyboard == null || keyboard.length() < 1 || word == null || word.length() < 1) return 0;
        
        Map<Character, Integer> map = new HashMap<>();
        for(int i =0;i< keyboard.length(); i++) map.put(keyboard.charAt(i), i);
        
        int res = map.get(word.charAt(0));
        for(int i =1; i< word.length(); i++) {
            res += Math.abs(map.get(word.charAt(i)) - map.get(word.charAt(i-1)));
        }
        
        return res;
    }
}