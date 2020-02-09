package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/*
Given two strings A and B of lowercase letters, return true if and only if we can swap 
two letters in A so that the result equals B.

 

Example 1:

Input: A = "ab", B = "ba"
Output: true
Example 2:

Input: A = "ab", B = "ab"
Output: false
Example 3:

Input: A = "aa", B = "aa"
Output: true
Example 4:

Input: A = "aaaaaaabc", B = "aaaaaaacb"
Output: true
Example 5:

Input: A = "", B = "aa"
Output: false
 

Note:

0 <= A.length <= 20000
0 <= B.length <= 20000
A and B consist only of lowercase letters.
 */
public class BuddyStrings {
    public boolean buddyStrings(String A, String B) {
        if (A == null && B == null) return false;
        if (A == null || B == null || A.length() != B.length() || A.length() < 2) return false;
        
        if (A.equals(B)) {
            int[] temp = new int[26];
            for(int i = 0; i < A.length(); i++) {
                if (++temp[A.charAt(i) - 'a'] >= 2) {
                    return true;
                }
            }
            return false;
        }
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i< A.length(); i++) {
            if (A.charAt(i) != B.charAt(i)) 
                list.add(i);
        }
        
        if (list.size() != 2) return false;
        int i = list.get(0), j = list.get(1);
        StringBuilder sb = new StringBuilder(A);
        char temp = A.charAt(i);
        sb.setCharAt(i, A.charAt(j));
        sb.setCharAt(j, temp);
        return sb.toString().equals(B);
    }
    
    public boolean buddyStrings2(String A, String B) {
        if (A.length() != B.length()) return false;
        if (A.equals(B)) {
            Set<Character> s = new HashSet<Character>();
            for (char c : A.toCharArray()) s.add(c);
            return s.size() < A.length();
        }
        List<Integer> dif = new ArrayList<>();
        for (int i = 0; i < A.length(); ++i) if (A.charAt(i) != B.charAt(i)) dif.add(i);
        return dif.size() == 2 && A.charAt(dif.get(0)) == B.charAt(dif.get(1)) && A.charAt(dif.get(1)) == B.charAt(dif.get(0));
    }
}