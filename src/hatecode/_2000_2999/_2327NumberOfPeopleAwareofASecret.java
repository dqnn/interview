package hatecode._2000_2999;
public class _2327NumberOfPeopleAwareofASecret {
    /*
    2327. Number of People Aware of a Secret
    On day 1, one person discovers a secret.

You are given an integer delay, which means that each person will share the secret with a new person every day, starting from delay days after discovering the secret. You are also given an integer forget, which means that each person will forget the secret forget days after discovering it. A person cannot share the secret on the same day they forgot it, or on any day afterwards.

Given an integer n, return the number of people who know the secret at the end of day n. Since the answer may be very large, return it modulo 109 + 7.

 

Example 1:

Input: n = 6, delay = 2, forget = 4
Output: 5
    */
    //O(n)/O(n)
    
    //thinking process: 
    //dp[i] is on i-day, how many people freshly know the secret
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int mod = (int) 1e9 + 7;
        long[] dp = new long[n + 1];
        dp[1] = 1;
        
        //sliding windows
        long newShared = 0;
        for (int i = delay + 1; i <= n; i++) {
            long intoWindow = i - delay >= 1? dp[i - delay] : 0;
            long outWindow = i - forget >= 1? dp[i - forget] : 0;
            newShared = (newShared + intoWindow - outWindow + mod) % mod;
            dp[i] = newShared;
        }
        
        //only part of people will know on n-day, because after forget days, they
        //will all forget
        long res = 0;
        for (int i = n - forget + 1; i <= n; i++) {
            res = (res + dp[i]) % mod;
        }
        return (int) res;
    }
    
    
    
    
    //dp[i] means on day i, how many people know the secrets
    //dp[i] =  sum (dp[i - delay] + dp[i-delay+1]...dp[i-1]) - sum (dp[i- forget])
    public int peopleAwareOfSecret_BF(int n, int delay, int forget) {
        
        int MOD=1_000_000_000 + 7;
        
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for(int i = 1; i<= n; i++) {
            for(int j = i + delay ; j<i + forget;j++) {
                if (j > n) break;
                dp[j] += dp[i];
                dp[j] = dp[j] %MOD;
            }
        }
        
        int res = 0;
        for(int i = n -forget + 1; i<=n;i++) {
                res = (res + dp[i]) % MOD;
        }
        
        return res;
        
    }
}