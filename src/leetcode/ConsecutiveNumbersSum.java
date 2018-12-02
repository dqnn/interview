package leetcode;
public class ConsecutiveNumbersSum {
/*
829. Consecutive Numbers Sum
Given a positive integer N, how many ways can we write it as a sum of consecutive 
positive integers?

Example 1:

Input: 5
Output: 2
Explanation: 5 = 5 = 2 + 3
Example 2:

Input: 9
Output: 3
Explanation: 9 = 9 = 4 + 5 = 2 + 3 + 4
 */
    //TLE, but straight forward one
    public int consecutiveNumbersSum2(int N) {
        if (N < 1) return 0;
        int end = N /2, res = 0;
        for(int i = 1; i<=end;i++) {
            int start = i, sum = 0;
            while(sum < N) {
                sum += start;
                start += 1;
            }
            if (sum == N) res++;
        }
        return res + 1;
    }
    // so  N = x + k + x + k + x ....+ k + x. so no matter starts, it can be written in such form,
    // N = kx + k(k-1)/2--> kx = N - k(k-1)/2, 
    // N = ki + i(i+1)/2 k = 0, 1,2, so N - (ki+i(i+1)/2) % i, then it 
    
    //O(sqrt(n)) O(1)
    public int consecutiveNumbersSum(int N) {
        int res = 1;
        for (int i = 2; i * (i + 1) / 2 <= N; ++i) {
            if ((N - i * (i + 1) / 2) % i == 0) ++res;
        }
        return res;
    }
}