package hatecode._0001_0999;
public class _887SuperEggDrop {
/*
887. Super Egg Drop
You are given K eggs, and you have access to a building with N floors from 1 to N. 

Each egg is identical in function, and if an egg breaks, you cannot drop it again.

You know that there exists a floor F with 0 <= F <= N such that any egg dropped at a floor higher than F will break, and any egg dropped at or below floor F will not break.

Each move, you may take an egg (if you have an unbroken one) and drop it from any floor X (with 1 <= X <= N). 

Your goal is to know with certainty what the value of F is.

What is the minimum number of moves that you need to know with certainty what F is, regardless of the initial value of F?

 

Example 1:

Input: K = 1, N = 2
Output: 2
*/
    //TLE, 
    public int superEggDrop_DP(int k, int n) {
        int[][] memo = new int[k+1][n+1];
        
        return helper_DP(k, n, memo);
    }
    //simulate this move, n is the end floor, 1 is starting the floor, 
    //As i increases, left goes up while right goes down. You can draw a picture of that as in Solution approach 1. The minimum point of Math.max(left, right) is when left and right meet.
    //dp[k][n] = min(1 + max(dp[k - 1][i - 1], dp[k][n - i])) i = 1...n
    private int helper_DP(int k, int n, int[][] memo) {
        //k = 1, means you have to try every floor
        //n <=1 means you just need to test 1 more time to get result, either 0 or 1
        if (n <= 1 || k == 1) return n;
        
        if (memo[k][n] > 0) return memo[k][n];
        
        int min = n;
        //suppose i am on floor i and 
        //TODO: need to understand left and right
        for(int i = 1; i<=n; i++) {
            //for floor 1, if egg break, then we have k-1 eggs and i-1 floors
            int left = helper(k-1, i-1, memo);
            //if egg does not break, then we have k eggs and n-i floors
            int right = helper(k, n-i, memo);
            min = Math.min(min, Math.max(left, right) + 1);
        }
        memo[k][n] = min;
        
        return memo[k][n];
    }
    
    public int superEggDrop_BS(int K, int N) {
        int[][] memo = new int[K + 1][N + 1];
        return helper(K, N, memo);
    }
    private int helper(int K, int N, int[][] memo) {
        if (N <= 1 || K == 1) {
            return N;
        }
       
        if (memo[K][N] > 0) return memo[K][N];
        
        int low = 1, high = N, result = N;
        while (low < high) {
            int mid = low + (high - low) / 2;
            int left = helper(K - 1, mid - 1, memo);
            int right = helper(K, N - mid, memo);
            result = Math.min(result, Math.max(left, right) + 1);
            if (left == right) {
                break;
            } else if (left < right) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        memo[K][N] = result;
        return result;
    }
    
    /*
    firstly, if we have k eggs and s steps to detect a building with Q(k, s) floors, 

    secondly, we use 1 egg and 1 step to detect one floor, 
    if egg break, we can use (k-1) eggs and (s-1) to detect with Q(k-1, s-1), 
    if egg isn't broken, we can use k eggs and (s-1) step to detech with Q(k, s-1),
    So, Q(k, s) = 1 + Q(k, s-1) + Q(k-1, s-1);

    dp[i] is max floors we can use i eggs and s step to detect.
    */
        
        public int superEggDrop(int k, int n) {
            int[] dp = new int[k+1];
            int steps = 0;
            for(;dp[k] < n; steps++) {
                for (int i = k; i > 0; i--) {
                    dp[i] = 1 + dp[i] + dp[i-1];
                }
            }
            return steps;
        }
}