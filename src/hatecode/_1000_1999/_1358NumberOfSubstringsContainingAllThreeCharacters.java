package hatecode._1000_1999;
public class _1358NumberOfSubstringsContainingAllThreeCharacters {
/*
1358. Number of Substrings Containing All Three Characters
Given a string s consisting only of characters a, b and c.

Return the number of substrings containing at least one occurrence of all these characters a, b and c.

 

Example 1:

Input: s = "abcabc"
Output: 10
*/
    //thinking process:
    public int numberOfSubstrings_LastPosition(String s) {
        int res = 0, n = s.length();
        int[] last = {-1,-1,-1};
        
        for(int i = 0; i< n; i++) {
            last[s.charAt(i) - 'a'] = i;
            res += 1 + Math.min(last[0], Math.min(last[1], last[2]));
        }
        
        return res;
    }
    
    //sliding windows
    public int numberOfSubstrings(String s) {
        int res = 0, n = s.length();
        int l = 0;
        int[] count = new int[3];
        
        for(int r = 0; r< n; r++) {
            char c = s.charAt(r);
            count[c-'a']++;
            while(count[0] > 0 && count[1] > 0 && count[2] > 0) {
                --count[s.charAt(l++) -'a'];
            }
            res += l;
        }
        
        return res;
        
    }
}