package hatecode._0001_0999;

import java.util.*;
/**
 * Created by duqiang on 25/07/2017.
 */
public class _014LongestCommonPrefix {
    /**
     * 14. Longest Common Prefix
     * Write a function to find the longest common prefix string amongst an array of strings.
     *
     *
     * case : "edwardshi", "edward", "edwar", "edwardshidd"
     * time : O(n);
     * space : O(1);
     *
     * @param strs
     * @return
     */
    //thinking process: interview friendly
    //given an array of stirngs, so we would like to find the longest common prefix for all strings
    //so the problem is to say to find the longest prefix, we can just start from the shortest, and run the same code
    //to find the longest common prefix.
    public String longestCommonPrefix(String[] A) {
        if(A == null || A.length < 1) return "";
        //Arrays.sort(A, (a, b)->(a.length() - b.length()));
        String res = A[0];
        for(int i =1; i< A.length;i++) {
            while (res.length() > 0 && A[i].indexOf(res) != 0) {
                res = res.substring(0, res.length() - 1);
            }
        }
        return res;
    }
    
    // this is just for reference
    public int findMaxPreFixStrLen(String str1, String str2) {
        //edge case
        if (str1 == null || str2 == null) {
            return 0;
        }
        int str1Len = str1.length(), str2Len= str2.length(), commonLen = 0;
        for(int i = 0; i< str1Len && i< str2Len; i++) {
            if (str1.charAt(i) == str2.charAt(i)) {
                commonLen ++;
            } else {
                break;
            }
        }
        
        return commonLen;
    }
    
    public String longestCommonPrefix2(String[] strs) {
        //edge case
        if (strs == null || strs.length == 0) {
            return "";
        }
        
        if (strs.length == 1) {
            return strs[0];
        }
    
        int len = strs.length, commonLen = strs[0].length();
        
        //process  
        for(int i =0; i+1 < len; i++) {
            int currentMaxPrefixLen = findMaxPreFixStrLen(strs[0], strs[i+1]);
            commonLen = Math.min(commonLen, currentMaxPrefixLen);
            if (commonLen == 0) break;
        }
        
        
        //return 
        return commonLen == 0 ? "" : strs[0].substring(0,commonLen);
    }
}
