package hatecode;
public class StudentAttendanceRecordII {
/*
552. Student Attendance Record II
Given a positive integer n, return the number of all possible attendance records with length n, which will be regarded as rewardable. The answer may be very large, return it after mod 109 + 7.

A student attendance record is a string that only contains the following three characters:

'A' : Absent.
'L' : Late.
'P' : Present.
A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

Example 1:
Input: n = 2
Output: 8 
*/
    //O(3^n)/O(n^n), we have n depth tree, in the leaf, we still have to go through n chars for isQualified() functions
    int res;
    public int checkRecord_BruteForce(int n) {
       if (n <= 0) return 1;
       
       int M = (int)Math.pow(10, 9) + 7;
       res = 0;
       helper("", n);
       return res % M;
    }
    
    public void helper(String s, int n) {
        //System.out.println(n + ":" + s);
        if (n == 0 && isQualified(s)) {
            res ++;
        } else {
            if (n > 0) {
                helper(s +"A", n-1);
                helper(s +"L", n-1);
                helper(s +"P", n-1);
            }
        }
    }
    
    public boolean isQualified(String s) {
        int cnt = 0;
        for(char ch : s.toCharArray()) {
            if (ch == 'A') cnt++;
            if (cnt == 2) break;
        }
        if (s.indexOf("LLL") > -1 || cnt == 2) return false;
        return true;
    }
/*
interview friendly,so remember we can always to divide a complicated DP probelm into several 
DP forumla and the forumla is easy to understand
    T(n) is the total number of all possible attendance records with length n.
P(n) is the total number of all possible attendance records ended with 'P' with length n.
L(n) is the total number of all possible attendance records ended with 'L' with length n.
A(n) is the total number of all possible attendance records ended with 'A' with length n.

P(n) = A(n - 1) + P(n - 1) + L(n - 1), n ≥ 2.
L(n) = A(n - 1) + P(n - 1) + A(n - 2) + P(n - 2), n ≥ 3.
A(n) = A(n - 1) + A(n - 2) + A(n - 3), n ≥ 4.
*/
    public int checkRecord(int n) {
        if (n <= 0) return 1;
        if(n == 1) return 3;
        if(n == 2) return 8;
       
       int m = (int)Math.pow(10, 9) + 7;
        int[] A = new int [n];
        int[] P = new int [n];
        int[] L = new int [n];
        
        P[0] = 1;
        L[0] = 1;
        L[1] = 3;
        A[0] = 1;
        A[1] = 2;
        A[2] = 4;
        
        
        
        for(int i = 1; i < n; i++) {
            A[i - 1] %= m;
            P[i - 1] %= m;
            L[i - 1] %= m;
            
            P[i] = ((A[i - 1] + P[i - 1]) % m + L[i - 1]) % m;
            
            if(i > 1) L[i] = ((A[i - 1] + P[i - 1]) % m + (A[i - 2] + P[i - 2]) % m) % m;
            
            if(i > 2) A[i] = ((A[i - 1] + A[i - 2]) % m + A[i - 3]) % m;
        }
        
        return ((A[n - 1] % m + P[n - 1] % m) % m + L[n - 1] % m) % m;
    }
    
    static final int M = 1000000007;
    //optimized DP solution, 
/*
P[i] means ends with "P" with length i, while PorL[i - 1] means ends with P or L with length i -1, so we append "P" after PorL[ i -1], we will get a new String with length i ends with "P", which is P[i], so we can say : P[i] = PorL[i - 1];
the same logic for "PorL[i] = (P[i] + P[i - 1] + P[i - 2]) % M".....
*/
    public int checkRecord_optimized(int n) {
    long[] PorL = new long[n + 1]; // ending with P or L, no A
    long[] P = new long[n + 1]; // ending with P, no A
    PorL[0] = P[0] = 1; PorL[1] = 2; P[1] = 1;

    for (int i = 2; i <= n; i++) {
        P[i] = PorL[i - 1];
        PorL[i] = (P[i] + P[i - 1] + P[i - 2]) % M;
    }
    
    long res = PorL[n];
    for (int i = 0; i < n; i++) { // inserting A into (n-1)-length strings
    	long s = (PorL[i] * PorL[n - i - 1]) % M;
        res = (res + s) % M;
    }
    
    return (int) res;
}
    
    
}