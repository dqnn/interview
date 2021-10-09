package hatecode._1000_1999;

import java.util.*;
public class _1239MaximumLengthOfAConcatenatedStringWithUniqueCharacters
 {
 /*
 1239. Maximum Length of a Concatenated String with Unique Characters
 You are given an array of strings arr. A string s is formed by the concatenation of a subsequence of arr that has unique characters.

Return the maximum possible length of s.

A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

 

Example 1:

Input: arr = ["un","iq","ue"]
Output: 4
Explanation: All the valid concatenations are:
- ""
- "un"
- "iq"
- "ue"
- "uniq" ("un" + "iq")
- "ique" ("iq" + "ue")
Maximum length is 4.
 */
    /*
     thinking process: 
    */
    public int maxLength2(List<String> A) {
        if(A == null || A.size() < 1) return 0;
        
        List<String> res = new ArrayList<>();
        res.add("");
        for(String s: A) {
            if (!isUnique(s)) continue;
            List<String> resList = new ArrayList<>();
            for(String pre: res) {
                String cur = pre+ s;
                if (isUnique(cur)) {
                    resList.add(cur);
                }
            }
            res.addAll(resList);
        }
        
        int ans = 0;
        for(String s: res) {
            ans = Math.max(ans, s.length());
        }
        return ans;
    }
    
    
    private boolean isUnique(String s) {
        int[] count = new int[26];
        for(char c: s.toCharArray()) {
            if (count[c-'a'] > 0) return false;
            count[c-'a'] ++;
        }
        
        return true;
    }
    

  //this is most optimal solution on speed,  
    public int maxLength(List<String> A) {
        int[] encode = new int[A.size()];
        
        //
        int[] len = new int[A.size()];
        for(int i=0; i<A.size(); i++) {
            encode[i] = encode(A.get(i));
            len[i] = (i==0?0:len[i-1]) + A.get(i).length();
        }
        
        return helper(A, encode, 0, 0, len);
    }
    
    //helper is used to calc the max length of string inside [start, A.length], 
    //
    int helper(List<String> A, int[] encode, int start, int cur, int[]len) {
        if(start>=encode.length) return 0;
        int mlen=0;
        for(int i=start; i<encode.length; i++) {
            if(encode[i]== (~0)) continue;
            int ncur = cur & encode[i];
            if(ncur ==0) {
                ncur = cur | encode[i];
                mlen = Math.max(mlen, A.get(i).length() + helper(A, encode, i+1, ncur, len));
                
                //if we found in this substring of A, we can connect them all,
                if(start>0 && mlen>=len[len.length-1] - len[start-1]) return mlen;
            }
        }
        return mlen;
    }
    
    int encode(String s) {
        boolean[] used = new boolean[26];
        int res=0;
        for(char c: s.toCharArray()) {
            if(used[c-'a']) return ~0;
            used[c-'a'] =true;
            res |= (1<<(c-'a'));
        }
        return res;
    }

}