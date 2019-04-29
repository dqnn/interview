package hatecode;

import java.util.*;
public class LongestUncommonSubsequenceII {
    /*
     * 522. Longest Uncommon Subsequence II 
     * Given a list of strings, you need to
     * find the longest uncommon subsequence among them. The longest uncommon
     * subsequence is defined as the longest subsequence of one of these strings and
     * this subsequence should not be any subsequence of the other strings.
     * 
     * A subsequence is a sequence that can be derived from one sequence by deleting
     * some characters without changing the order of the remaining elements.
     * Trivially, any string is a subsequence of itself and an empty string is a
     * subsequence of any string.
     * 
     * The input will be a list of strings, and the output needs to be the length of
     * the longest uncommon subsequence. If the longest uncommon subsequence doesn't
     * exist, return -1.
     * 
     * Example 1: Input: "aba", "cdc", "eae" Output: 3
     */
   //thinking process: 
    //the problem is to find the LUS among string arrays, the lUS is one of these strings and 
    //not the substring of any other strings, so find the longest one
    
    //so first sort strings by length, since they appear highly in shorter strings
    //first we get duplicates, because dup cannot be the LUS, because it is one of other strings
    //substring
    
    //so we use a two loop, which is O(n^2), and we find one string which is not other subsequence
    //of other strings
   public int findLUSlength(String[] strs) {
        
       Arrays.sort(strs,(a, b)->(b.length() - a.length()));
       //get duplicates of these strings
       Set<String> duplicates = getDuplicates(strs);
       // strs[i].length >= strs[j].length
       for(int i = 0; i < strs.length; i++) {
            if(!duplicates.contains(strs[i])) {
              //the longest one will be the answer, since longer, the better
                if(i == 0) return strs[0].length();
                for(int j = 0; j < i; j++) {
                    if(isSubsequence(strs[j], strs[i])) break;
                    //greater than i, but the smallest one 
                    if(j == i-1) return strs[i].length();
                }
            }
        }
        return -1;
    }
    //check b is subsequence of a, a.length() > b.length()
    public boolean isSubsequence(String a, String b) {
        int i = 0, j = 0;
        while(i < a.length() && j < b.length()) {
            if(a.charAt(i) == b.charAt(j)) j++;
            i++;
        }
        return j == b.length();
    }
    
    private Set<String> getDuplicates(String[] strs) {
        Set<String> set = new HashSet<String>();
        Set<String> duplicates = new HashSet<String>();
        for(String s : strs) {
            if(set.contains(s)) duplicates.add(s);
            set.add(s);
        }
        return duplicates;
    }
}