package hatecode;
public class _1016BinaryStringWithSubstringsRepresenting1ToN {
/*
1016. Binary String With Substrings Representing 1 To N
Given a binary string S (a string consisting only of '0' and '1's) and a positive integer N, return true if and only if for every integer X from 1 to N, the binary representation of X is a substring of S.

 

Example 1:

Input: S = "0110", N = 3
Output: true
*/
    //thinking process:
    //
    public boolean queryString(String s, int N) {
        if (s == null || s.length() < 1) return false;
        
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            int x = i;
            while(x!= 0) {
                sb.append(x % 2);
                x /= 2;
            }
            String bin = sb.reverse().toString();
            if (s.indexOf(bin) < 0) return false;
            sb.setLength(0);
        }
        return true;
    }
}