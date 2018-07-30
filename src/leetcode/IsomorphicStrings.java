package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : IsomorphicStrings
 * Creator : duqiang
 * Date : Oct, 2017
 * Description : 205. Isomorphic Strings
 */
public class IsomorphicStrings {
    /**
     * For example,
     Given "egg", "add", return true.

     Given "foo", "bar", return false.

     Given "paper", "title", return true.

     egg add

     * @param s
     * @param t
     * @return
     */
    // time : O(n) space : O(n)
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null) return true;
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            char b = t.charAt(i);
            if (map.containsKey(a)) {
                if (map.get(a).equals(b)) continue;
                else return false;
            } else {
                if (!map.containsValue(b)) {
                    map.put(a, b);
                } else return false;
            }
        }
        return true;
    }


    // time : O(n) space : O(1)
    public boolean isIsomorphic2(String s, String t) {
        int[] sChars = new int[256];
        int[] tChars = new int[256];

        for (int i = 0; i < s.length(); i++) {
            if (sChars[s.charAt(i)] != tChars[t.charAt(i)]) {
                return false;
            } else {
                sChars[s.charAt(i)] = tChars[t.charAt(i)] = t.charAt(i);
            }
        }
        return true;
    }
    
    
    public boolean isIsomorphic3(String s, String t) {
        if (s == null &&  t == null) {
            return true;
        }
        
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        
        int[] sChar = new int[256];
        int[] tChar = new int[256];
        for(int i = 0; i < s.length(); i++) {
            if (sChar[s.charAt(i)] != tChar[t.charAt(i)]) {
                return false;
            } else {
                //// just one pointer to make sure the character appear on the same position
                sChar[s.charAt(i)] = i + 1; 
                tChar[t.charAt(i)] = i + 1;
            }
        }
         return true;
    }
    
    //set to store t char, map to store s char and t char
    // loop on s, if we find on same position, s and t have different char then exit false
    // else if set already have the t char which means t has one more different char than s. 
    public boolean isIsomorphic4(String s, String t) {
        Map<Character,Character> map=new HashMap<>();
        Set<Character> set=new HashSet<>();
        if(s==null) return true;
        for(int i=0;i<s.length();i++){
            
            if(map.containsKey(s.charAt(i))){
                if(map.get(s.charAt(i)) != t.charAt(i)) return false;
            } else{
                if(set.contains(t.charAt(i))) return false;
                map.put(s.charAt(i),t.charAt(i));
                set.add(t.charAt(i));
            }
        }
        return true;
    }
}
