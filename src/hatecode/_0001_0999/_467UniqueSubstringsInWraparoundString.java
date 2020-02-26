package hatecode._0001_0999;
import java.util.*;
public class _467UniqueSubstringsInWraparoundString {
/*
467. Unique Substrings in Wraparound String
Example 1:
Input: "a"
Output: 1

Explanation: Only the substring "a" of string "a" is in the string s.
Example 2:
Input: "cac"
Output: 2
Explanation: There are two substrings "a", "c" of string "cac" in the string s.
*/
    //give a string,find all contious substring with string, like "zab", "cac"->"a" and "c", at least
    //each one will be one, just return the size of all continous substring
    
    //so we use count[26] to record each character, what's the max length of contious substring, then eg "zab", 
    //3 + 2 + 1, this is pretty like arithestic slices1 and 2
   public int findSubstringInWraproundString(String p) {
        // count[i] is the maximum unique substring end with ith letter.
        // 0 - 'a', 1 - 'b', ..., 25 - 'z'.
        int[] count = new int[26];
        
        // store longest contiguous substring ends at current position.
        int maxLengthCur = 0; 

        for (int i = 0; i < p.length(); i++) {
            //"ab" or "za"
            if (i > 0 && (p.charAt(i) - p.charAt(i-1)== 1 || (p.charAt(i-1) - p.charAt(i)  == 25))) {
                maxLengthCur++;
            } else maxLengthCur = 1; //at least you have 1
            
            int index =p.charAt(i) - 'a';
            count[index] = Math.max(count[index], maxLengthCur);
        }
        
        return Arrays.stream(count).sum();
    }
    
    //why this complexity is better?
    //1 we loop from i->j, we did not look back again, but previous solution will look back,
    //2 array lookup is faster than charAt
    public int findSubstringInWraproundString_Reference_BestTime(String p) {
        char[] chars = p.toCharArray();  
        int[] maxLen = new int[26];
        int n = chars.length; 
        int i = 0;
        while (i < n) {
            int j = i + 1;
            while (j < n && areNeighbors(chars[j - 1], chars[j]))
                ++j;
            for (int q = i; q < j; ++ q) {
                maxLen[chars[q] - 'a'] = Math.max(maxLen[chars[q] - 'a'], j - q);
            }
            i = j;
        }      
        int ret = 0;  
        for (i = 0; i < 26; ++ i)
            ret += maxLen[i];
        return ret;
    }
    //here is the trick how to find it is contious or not in alphbet 
    private static boolean areNeighbors(char a, char b) {
        return ((b - a + 26) % 26 == 1);
    }
}