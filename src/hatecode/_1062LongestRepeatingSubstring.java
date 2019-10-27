package hatecode;

import java.util.*;
public class _1062LongestRepeatingSubstring {
/*
1062. Longest Repeating Substring
Given a string S, find out the length of the longest repeating substring(s). Return 0 if no repeating substring exists.

 

Example 1:

Input: "abcd"
Output: 0
Explanation: There is no repeating substring.
*/
    
    //thinking process: O(nlgn)/O(n)
    //the problem is to say: given string s, find the longest repeated substring
    //this is the same as 1044
    public int longestRepeatingSubstring(String S) {
        // edge case
        if (S == null) {
            return 0;
        }
        // binary search the max length
        int l = 0, r = S.length() - 1;
        int mid;
        //if we find repeated substring then we move mid ->right, so to find a longer
        //substring, if we cannot which means we should lower down the length
        while (l + 1 < r) {
            mid = (l + r) / 2;
            if (searchForLength(S, mid) != null) l = mid;
            else r = mid - 1;
        }
        String str = searchForLength(S, r);
        if (str != null) {
            return str.length();
        } else {
            return searchForLength(S, l).length();
        }
    }
    //search substring length = len, 
    //we use hash value as string value, we use a function to calc
    //the positive integer hash value 
    String searchForLength(String str, int len) {
        // rolling hash method
        if (len == 0) return "";
        else if (len >= str.length()) return null;

        Map<Long, List<Integer>> map = new HashMap<>();    // hashcode -> list of all starting idx with identical hash
        long p = (1 << 31) - 1;  // prime number， 13 also works
        long base = 256;
        long hash = 0;
        //calculate the string hash,the reason why we do not use substring is because 
        //substirng is O(n) while we caculate each time is just to remove first
        //and add last new character so we reduce O(n) complexity
        for (int i = 0; i < len; ++i) {
            hash = (hash * base + str.charAt(i)) % p;
        }
        long multiplier = 1;
        for (int i = 1; i < len; ++i) {
            multiplier = (multiplier * base) % p;
        }
        // first substring
        //TODO, just use string.hash() to rewrite this part code，but will be less efficient than current because 
        //we use first and last char character to reduce the computation
        
        //this is like bloom filter
        //
        List<Integer> idxList = new ArrayList<Integer>();
        idxList.add(0);
        map.put(hash, idxList);
        // we use win = len to search whether it has repeated substring in s
        int s = 0;
        int e = len;
        while (e < str.length()) {
            hash = ((hash + p - multiplier * str.charAt(s++) % p) * base + str.charAt(e++)) % p;
            idxList = map.get(hash);
            if (idxList == null) {
                idxList = new ArrayList<Integer>();
                map.put(hash, idxList);
            } else {
                for (int i0: idxList) {
                    if (str.substring(s, e).equals(str.substring(i0, i0 + len))) {
                        return str.substring(i0, i0 + len);
                    }
                }
            }
            idxList.add(s);
        }
        return null;
    }
  //BF, TLE O(n^3)/O(n^2)
    public int longestRepeatingSubstring_BF(String s) {
        if(s == null || s.length() < 1) return 0;
        
        Set<String> set = new HashSet<>();
        int res = 0;
        for(int i =1; i <=s.length(); i++) {
            for(int j = 0; j< i; j++) {
                String str = s.substring(j, i);
                if(set.contains(str)) res = Math.max(res, str.length());
                else set.add(str);
            }
        }
        
        return res;
    }
}