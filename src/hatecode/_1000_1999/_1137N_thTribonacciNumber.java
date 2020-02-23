package hatecode._1000_1999;

public class _1137N_thTribonacciNumber {
/*
1137. N-th Tribonacci Number
The Tribonacci sequence Tn is defined as follows: 

T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.

Given n, return the value of Tn.

 

Example 1:

Input: n = 4
Output: 4
*/
/*
We have 3 equations:
        f(n)   = f(n-1) + f(n-2) + f(n-3)
        f(n-1) = f(n-1)
        f(n-2) =          f(n-2)

By turning them into matrix relation. we get:
        | f(n)   |     | 1 1 1 |     | f(n-1) |
        | f(n-1) |  =  | 1 0 0 |  *  | f(n-2) |
        | f(n-2) |     | 0 1 0 |     | f(n-3) |

Since we can compute an matrix exponent by O(log(n)), Simplify the relation into exponents
        | f(n)   |     | 1 1 1 |^(n-2)     | f(2) |
        | f(n-1) |  =  | 1 0 0 |       *   | f(1) |
        | f(n-2) |     | 0 1 0 |           | f(0) |
        
The matrix multiplication cost is k^3, k=3. So the total cost is O(k^3log(n))
*/
    
    
    public int tribonacci(int n) {
        if (n < 2) return n;
        int a = 0, b = 1, c = 1, d;
        while (n-- > 2) {
            d = a + b + c;
            a = b;
            b = c;
            c = d;
        }
        return c;
    }
    
    public int tribonacci_DP(int n) {
        if(n <= 0) return 0;
        if(n == 1 || n == 2) return 1;
        
        int[] dp = new int[n+1];
        dp[0] = 0; dp[1] = 1; dp[2] = 1;
        for(int i = 3; i <= n; i++) dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        
        return dp[n];
    }
    
    public int tribonacci_BF(int n) {
        if(n <= 0) return 0;
        if(n == 1 || n == 2) return 1;
        return tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
    }
}