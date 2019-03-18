package hatecode;
public class ValidWordAbbreviation {
/*
408. Valid Word Abbreviation
Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.

A string such as "word" contains only the following valid abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a valid abbreviation of "word".
*/
    //O(min(m,n))/O(1)
    //pretty straightforword, so just need to be cautious about how many cases we have
    public boolean validWordAbbreviation(String s, String p) {
        if (s == null && p == null) return true;
        if (s == null || p == null) return false;
        
        int m = s.length(), n = p.length();
        int i = 0, j= 0;
        while(i < m && j < n) {
            if (s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
                continue;
            }
            if(p.charAt(j) <= '0' || p.charAt(j) >= '9') return false;
            int start = j;
            while(j < n && p.charAt(j) >='0' && p.charAt(j) <= '9') j++;
            
            int len = Integer.valueOf(p.substring(start, j));
            i += len;
        }
        
        return i == m && j == n;
    }
}