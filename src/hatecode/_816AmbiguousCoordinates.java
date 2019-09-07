package hatecode;

import java.util.*;

public class _816AmbiguousCoordinates {
/*
816. Ambiguous Coordinates
We had some 2-dimensional coordinates, like "(1, 3)" or "(2, 0.5)".  
Then, we removed all commas, decimal points, and spaces, and ended up with the string S.  
Return a list of strings representing all possibilities for what our original coordinates 
could have been.

Our original representation never had extraneous zeroes, so we never started with numbers 
like "00", "0.0", "0.00", "1.0", "001", "00.01", or any other number that can be represented with less digits.  Also, a decimal point within a number never occurs without at least one digit occuring before it, so we never started with numbers like ".1".

The final answer list can be returned in any order.  Also note that all coordinates in the final answer have exactly one space between them (occurring after the comma.)

Example 1:
Input: "(123)"
Output: ["(1, 23)", "(12, 3)", "(1.2, 3)", "(1, 2.3)"]
*/
    //O(n^3)/O(n^3)
    
    //thinking process:
    //given a string, we can try to break the string into two dimension number, but no leading 0 and no tail 0, 
    //and ouput all possible combination
    
    //is is more like we have a cut in the string, and with the help of backtracking, we cut the string into two parts
    //left and right, for each one we need to use two loops to output all possible combination
    //and in backtracking helper, we need to filter the leading 0 and tail 0 cases. 
    
    //TODO: why we did not have a visited array to reduce the complexity?
    public List<String> ambiguousCoordinates(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() < 1) return res;
        
        for(int i = 2; i<s.length() - 1; i++) {
            for(String l : helper(s, 1,i)) {
                for(String r: helper(s, i, s.length()-1)) {
                    res.add("(" + l +", " + r +")");
                }
            }
        }
        return res;
    }
    
    private List<String> helper(String S, int s, int e) {
        List<String> res = new ArrayList<>();
        for(int d =1; d <= e- s; d++) {
            String l = S.substring(s, s+d);
            String r = S.substring(s+d, e);
            
            if ((!l.startsWith("0") || l.equals("0")) && !r.endsWith("0")) {
                res.add(l + (d < e-s ? "." : "") + r);
            }
        }
        return res;
    }
    
    
}