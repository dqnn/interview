package hatecode._0001_0999;
import java.util.*;
public class _837New21Game {
/*
 * 837. New 21 Game
Alice plays the following game, loosely based on the card game "21".

Alice starts with 0 points, and draws numbers while she has less than K points.  During each draw, she gains an integer number of points randomly from the range [1, W], where W is an integer.  Each draw is independent and the outcomes have equal probabilities.

Alice stops drawing numbers when she gets K or more points.  What is the probability that she has N or less points?

Example 1:

Input: N = 21, K = 17, W = 10
Output: 0.73278
*/
     

    /*
     * thinking process: O(k+w)/O(k+w)
     * 
     * the problem is to say: Alice is can get points everytime from [1,w], each number will have same probablity 
     * if all points Alice get >= K, then stop, 
     * return probabiliy that Alice points not greater than N? 
     * 
     * we can the points Alice get is x, dp[x] as the probability that x < N, then  if x >= K & K <= N, then it is 1, else it is 0, 
     * it is determined since game over 
     * 
     * then if  0 <= x < k, then we can calculate as follow, 
     *  Alice continue play the game, she can get number from [1, w], then 
     * dp[x] = 1/w * (dp[x+1] + dp[x+2] + ... dp[x+w]) because it is evenly distribution, then since we already know the probability from [k, k+w)
     * 
     *  thinking about below cells
     * 
     * -0----1----2----3----4----5----...----k----k+1----k+2----...----k+w
     *                                            |______w_____________|
     *                                              window w, 
     * 
     * dp[k] = 1/w * (dp[k+1]+...dp[k+w]) 
     * 
     * we can calculate back to dp[0], which means
     * 
     */
    public double new21Game(int n, int k, int w) {
        double[] dp = new double[k + w];
        
        double s = 0;
        for(int i = k; i < k +w; i++) {
            dp[i] = (i <= n ? 1 : 0);
            s += dp[i];
        }
        
        for(int i = k - 1; i>=0; i--) {
            dp[i] = s/w;
            s=s-dp[i+w]+dp[i];
        }
        
        return dp[0];
        
    }
     

     //O(n)/O(n)
    //thinking process: 
    //so N, K, W 3 parameters, if score i < K, it will be 100% because we would not stop 
    //playing the game, if i>=K means there will probability, but since we only 1 chance to play
    //so the range of scores will be [K, K +W -1], we cannot reach K +W because if we have reached
    //K we would stop
    
    //so if N is not of the scope then it will be N < K, will be 0, N >= K +W - 1, 1
     public double new21Game2(int N, int K, int W) {
        if (K <= 0 || N >= K + W -1) return 1;
        //dp[i] means get points i probability
         //dp[i] = sum(last W dp values) / W
        //Wsum means  sum(last W dp values), we can maintain a sliding window with size at most K.
        double dp[] = new double[N + 1],  Wsum = 1, res = 0;
        dp[0] = 1;
        for (int i = 1; i <= N; ++i) {
            dp[i] = Wsum / W;
            if (i < K) Wsum += dp[i]; 
            else res += dp[i];
            
            if (i - W >= 0) Wsum -= dp[i - W];
        }
        return res;
    }
    //thinking process:
    
    // sum <= N probability, if sum >=K, then we stop, every time, we would get [1, W], probability for 
    //each number is same
    
    //
    public double new21Game(int N, int K, int W) {
        if(N >= K+W-1) return 1.0; // all possibilities of positions after alice stops playing are from [K, K+W-1]
    
    double p[] = new double[K+W];
    double prob = 1/(W+0.0); // single elem probability
    
    double prev = 0;
    //p[i] means sum = i probability
    p[0] = 1; // Since we start from 0, initialize it to 1
    
     //if i <= k,
     //p[i] = 1/W (p(x-1) + ....p(x-W))
    for(int i = 1; i <= K; i++) {
        prev = prev - (i-W-1 >= 0 ? p[i - W -1] : 0) + p[i-1];
        p[i] = prev*prob;
    }
    
    double req = p[K];
    
    // From K+1, we don't add the p[i-1] term here as it is >= K
    //if i > k,
     //p[i] = 1/W (p(K-1) + ....p(x-W))
    for(int i = K+1; i <= N; i++) {
        prev = prev - (i-W-1 >= 0 ? p[i - W -1] : 0);
        p[i] = prev * prob;
        req += p[i];
        //System.out.println(p[i]);
    }
    
    return req;
    }
    //brute force, not correct, just for thinking
    public double new21Game4(int N, int K, int W) {
        double res;
        Set<Integer> list = new HashSet<>();
        helper(W, K, 0, list);
        int count = 0; 
        for (int n : list) {
            if (n <= N) count++;
        }
        res = (double)count / (double)list.size();
        return res;
    }
    public void helper(int W, int K, int val, Set<Integer> set) {
        if (val >= K) {
            set.add(val);
            return;
        }
        for (int i = 1; i <= W; i++) {
            helper(W, K, val + i, set);
        }
    }
}