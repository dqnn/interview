package hatecode._0001_0999;
public class _408ValidWordAbbreviation {
/*
408. Valid Word Abbreviation
tags: google
Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.

A string such as "word" contains only the following valid abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Notice that only the above abbreviations are valid abbreviations of the string "word". 
Any other string is not a valid abbreviation of "word".
*/
    //O(min(m,n))/O(1)
    //pretty straightforword, so just need to be cautious about how many cases we have
    //lower letters and digits
    //we use two pointer point to s and p, when we both reach the end of string, then we return true
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
            //first character could not be 0, 
            // p.charAt(j) <= '0' is tricky, bezu "he", "h01" should return false, so we would like to return false when first character is 0, how can we sure it is 
            //first because we here it must within n and m while not equals, so it must be integers. please note we use if just under while, so it will 
            //repeat detet the while condition upside
            if(p.charAt(j) <= '0' || p.charAt(j) > '9') return false;
            int start = j;
            // here to truncate the numbers, like w12l --> 12 out
            while(j < n && p.charAt(j) >='0' && p.charAt(j) <= '9') j++;
            int len = Integer.valueOf(p.substring(start, j));
            //move s pointer
            i += len;
        }
        
        return i == m && j == n;
    }
}