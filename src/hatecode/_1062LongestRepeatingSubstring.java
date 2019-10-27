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
    
    //so we use BS here to quickly locate which length of substring is repeated, searchFixedLength()
    //is find 
    public int longestRepeatingSubstring(String s) {
        // edge case
        if (s == null || s.length() < 1) return 0;
        // binary search the max length
        int l = 0, r = s.length() - 1;
        //if we find repeated substring len = mid, if we can find it, we want to make 
        //the length longer ,so we move l->mid, so m can be longer
        //other wise we make it smaller
        
        //another key point, this can only use 3rd template,
        //abbaba
        //this only has longest substring  = 2
        //but if we use 2nd template, we will see l m r as follow
        //0--3--6--> we cannot len = 3 substring, 0-1-3, we can 1
        //->2--2--3-->3--3--3then stop, so we can see 2 are skipped but it is the 
        //answer, because only 3rd template could return 2 answers, and this problem essence
        //could only use 3rd template
        while (l + 1 < r) {
            int m =  l + (r - l) / 2;
            if (searchFixedLen(s, m) != null) l = m;
            else r = m - 1;
        }
        String str = searchFixedLen(s, r);
        if (str != null) {
            return str.length();
        } else {
            return searchFixedLen(s, l).length();
        }
    }
    //search substring length = len, 
    //we use hash value as string value, we use a function to calc
    //the positive integer hash value 
    String searchFixedLen(String str, int len) {
        // rolling hash method
        if (len == 0) return "";
        else if (len >= str.length()) return null;

        Map<Long, List<Integer>> map = new HashMap<>();    // hashcode -> list of all starting idx with identical hash
        long p = (1 << 31) - 1;  // prime number， 13 also works
        long base = 256;
        long hash = 0;
        //calculate the string hash,the reason why we do not use substring is because 
        //substring is O(n) while we caculate each time is just to remove first
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
                idxList = new ArrayList<>();
                map.put(hash, idxList);
            } else {
                for (int idx: idxList) {
                    if (str.substring(s, e).equals(str.substring(idx, idx + len))) {
                        return str.substring(idx, idx + len);
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