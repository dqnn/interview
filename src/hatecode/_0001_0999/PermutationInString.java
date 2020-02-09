package hatecode._0001_0999;

import java.util.*;

public class PermutationInString {
/*
567. Permutation in String
Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.
Example 1:
Input:s1 = "ab" s2 = "eidbaooo"
Output:True
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:
Input:s1= "ab" s2 = "eidboaoo"
Output: False
Note:
The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].
*/
    //interview friendly version, use a map to store the unqiue character, 
    public boolean checkInclusion_Map(String s1, String s2) {
        if(s1 == null && s2 == null) return true;
        if(s1 == null || s2 ==null || s2.length() < s1.length()) return false;
        Map<Character, Integer> map = new HashMap<>();
        for(char ch : s1.toCharArray()) map.put(ch, map.getOrDefault(ch, 0) + 1);
        
        int l = 0;
        while(l <= s2.length() - s1.length()) {
           Map<Character, Integer> cmap = new HashMap<>(map);
           for(int i = l; i < l + s1.length(); i++) {
               
               char ch = s2.charAt(i);
               //System.out.println(ch + "---" + i);
               int cnt = cmap.getOrDefault(ch, 0);
               if(cnt == 1) cmap.remove(ch);
               else cmap.put(ch, cnt - 1);
           }
           if(cmap.size() == 0) return true;
           l++;
           
        }
        return false;
    }
    
    
    
    
    public boolean checkInclusion2(String s1, String s2) {
        if (s1 == null) return true;
        if (s2 == null || s2.length() < s1.length()) return false;
        
        int[] count = new int[26];
        for(char ch: s1.toCharArray()) {
            count[ch - 'a'] += 1;
        }
        //note how calculate the idx
        for(int i = 0, j = 0; i <= s2.length() - s1.length();) {
            int[] copy = Arrays.copyOf(count, 26);
            boolean flag = true;
            //also here how get j - i <= s1.length() - 1
            while(j - i <= s1.length() - 1) {
                if (--copy[s2.charAt(j++) - 'a'] < 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return true;
            }
            i++;
            j = i;
        }
        return false;
    }
    
    //this is awesome solution, and O(n)
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 > len2) return false;
        
        int[] count = new int[26];
        for (int i = 0; i < len1; i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        if (allZero(count)) return true;
        //note first s1 len pass already processed all count character
        //so if we let 1 char in then we should be able to verify all count 
        //are 0 or not.
        for (int i = len1; i < len2; i++) {
            count[s2.charAt(i) - 'a']--;
            count[s2.charAt(i - len1) - 'a']++;
            if (allZero(count)) return true;
        }
        
        return false;
    }
    
    private boolean allZero(int[] count) {
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) return false;
        }
        return true;
    }
}