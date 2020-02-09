package hatecode._0001_0999;
public class FlipStringToMonotoneIncreasing {
/*
926. Flip String to Monotone Increasing
A string of '0's and '1's is monotone increasing if it consists of some number of '0's (possibly 0), followed by some number of '1's (also possibly 0.)

We are given a string S of '0's and '1's, and we may flip any '0' to a '1' or a '1' to a '0'.

Return the minimum number of flips to make S monotone increasing.

 

Example 1:

Input: "00110"
Output: 1
Explanation: We flip the last digit to get 00111.
*/
    //thinking process: 
    public int minFlipsMonoIncr2(String S) {
        if (S == null || S.length() <= 1) return 0;
        //cntEndWithZero means if end is 0 and to be Monotone , the minimal flip, same to cntEndWithOne
        int cntEndWithZero = 0, cntEndWithOne = 0;
        for(char ch : S.toCharArray()) {
            //it means 
            cntEndWithOne = Math.min(cntEndWithZero, cntEndWithOne) + (ch == '1' ? 0 : 1);
            cntEndWithZero += (ch == '0'? 0: 1);
        }
        return Math.min(cntEndWithZero, cntEndWithOne);
    }
    
    
    //two PAss solution
    public int minFlipsMonoIncr(String S) {
        int n = S.length();
        int[] leftOne = new int[n];
        int[] rightZero = new int[n];
        if (S.charAt(0) == '1') {
            leftOne[0] = 1;
        }
        if (S.charAt(n - 1) == '0') {
            rightZero[n - 1] = 1;
        }
        for (int i = 1; i < n; i++) {
            leftOne[i] = S.charAt(i) == '1' ? leftOne[i - 1] + 1 : leftOne[i - 1];
            rightZero[n - 1 - i] = S.charAt(n - 1 - i) == '0' ? rightZero[n - i] + 1 : rightZero[n - i];
        }
        int min = n;
        for (int i = -1; i < n; i++) {
            int left = i == -1 ? 0 : leftOne[i];
            int right = i == n - 1 ? 0 : rightZero[i + 1];
            min = Math.min(min, left + right);
        }
        return min;
    }
    
    //solution 
    public int minFlipsMonoIncr3(String S) {
        int N = S.length();
        int[] P = new int[N + 1];
        for (int i = 0; i < N; ++i)
            P[i+1] = P[i] + (S.charAt(i) == '1' ? 1 : 0);

        int ans = Integer.MAX_VALUE;
        for (int j = 0; j <= N; ++j) {
            ans = Math.min(ans, P[j] + N-j-(P[N]-P[j]));
        }

        return ans;
    }
}