package leetcode;
public class LicenseKeyFormatting {
/*
482. License Key Formatting
You are given a license key represented as a string S which consists only alphanumeric 
character and dashes. The string is separated into N+1 groups by N dashes.

Given a number K, we would want to reformat the strings such that each group contains 
exactly K characters, except for the first group which could be shorter than K, 
but still must contain at least one character. Furthermore, there must be a dash inserted 
between two groups and all lowercase letters should be converted to uppercase.

Given a non-empty string S and a number K, format the string according to the rules 
described above.

Example 1:
Input: S = "5F3Z-2e-9-w", K = 4

Output: "5F3Z-2E9W"

*/  //note, the first group is same as before, this is the part i do not understand, but test 
    //case requires
    public String licenseKeyFormatting2(String s, int K) {
        if (s == null || s.length() < 1 || K < 0) return "";
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for(int i = s.length() - 1; i>=0;i--) {
            char ch = s.charAt(i);
            if (ch == '-') {
                continue;
            }else if (Character.isDigit(ch) || Character.isLetter(ch)) {
                String t = ("" + ch).toUpperCase();
                if (count < K) {
                    sb.append(t);
                    count++;
                } else {
                    sb.append("-" + t);
                    count = 1;
                }
            }
        }
        return sb.reverse().toString();
    }
    
    public String licenseKeyFormatting(String s, int K) {
        if (s == null || s.length() < 1 || K < 0) return "";
        StringBuilder sb = new StringBuilder(s.replace("-", "").toUpperCase());
        for(int i =sb.length() - K; i>0;) {
            sb.insert(i, "-");
            i = i -K;
        }
        return sb.toString();
    }
}