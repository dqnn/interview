package hatecode._0001_0999;
import java.math.BigInteger;
import java.util.*;
class RepeatedStringMatch {
/*
686. Repeated String Match
Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If no such solution, return -1.

For example, with A = "abcd" and B = "cdabcdab".

Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of A repeated two times ("abcdabcd").
*/
    //thinking process: 
    
    //the problem is to say, given A, how many times repeated A can inlude B as substring
    //B's char must align with A's char in first string, we need repeat A to match the length of B, because if not, A can never 
    //has substring B, then since A is repeatable, so B either already there,or we just add one more A, if not, B is not substring of A
    //a->aa, length is the same
    //abcd, cdabcdab, B span 3 A
    //O(N)
    public int repeatedStringMatch_Brute_force(String A, String B) {
        if (A == null || B == null) return -1;
        
        if (A.indexOf(B) > -1) return 1;

        int time = 1;
        StringBuilder S = new StringBuilder(A);
        for (; S.length() < B.length(); time++) S.append(A);
        if (S.indexOf(B) > -1) return time;
        if (S.append(A).indexOf(B) > -1) return time+1;
        return -1;
    }
    
    
    public boolean check(int index, String A, String B) {
        for (int i = 0; i < B.length(); i++) {
            if (A.charAt((i + index) % A.length()) != B.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    //O(M+N) KMP, not understand, just for reference
    public int repeatedStringMatch(String A, String B) {
        int q = (B.length() - 1) / A.length() + 1;
        int p = 113, MOD = 1_000_000_007;
        int pInv = BigInteger.valueOf(p).modInverse(BigInteger.valueOf(MOD)).intValue();

        long bHash = 0, power = 1;
        for (int i = 0; i < B.length(); i++) {
            bHash += power * B.codePointAt(i);
            bHash %= MOD;
            power = (power * p) % MOD;
        }

        long aHash = 0; power = 1;
        for (int i = 0; i < B.length(); i++) {
            aHash += power * A.codePointAt(i % A.length());
            aHash %= MOD;
            power = (power * p) % MOD;
        }

        if (aHash == bHash && check(0, A, B)) return q;
        power = (power * pInv) % MOD;

        for (int i = B.length(); i < (q + 1) * A.length(); i++) {
            aHash -= A.codePointAt((i - B.length()) % A.length());
            aHash *= pInv;
            aHash += power * A.codePointAt(i % A.length());
            aHash %= MOD;
            if (aHash == bHash && check(i - B.length() + 1, A, B)) {
                return i < q * A.length() ? q : q + 1;
            }
        }
        return -1;
    }
    
}