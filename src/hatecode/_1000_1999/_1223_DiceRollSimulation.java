package hatecode._1000_1999;
public class _1223_DiceRollSimulation {
/*
1223. Dice Roll Simulation
A dice simulator generates a random number from 1 to 6 for each roll. 
You introduced a constraint to the generator such that it cannot roll the number
 i more than rollMax[i] (1-indexed) consecutive times. 

Given an array of integers rollMax and an integer n, 
return the number of distinct sequences that can be obtained with exact n rolls.

Two sequences are considered different if at least one element differs from each other. 
Since the answer may be too large, return it modulo 10^9 + 7.

Example 1:

Input: n = 2, rollMax = [1,1,2,2,2,3]
Output: 34
Explanation: There will be 2 rolls of die, if there are no constraints on the 
die, there are 6 * 6 = 36 possible combinations. In this case, 
looking at rollMax array, the numbers 1 and 2 appear at most once consecutively, 
therefore sequences (1,1) and (2,2) cannot occur, so the final answer is 36-2 = 34.
*/
    
    //thinking process: 
    //the problem is to say: given one array rollMax and one integer n, rollMax means 
    //for each number i, it cannot give rollMax[i] times consecutively. n means how many times 
    //you can play(roll) the die. n = 2, rollMax = [1,1,2,2,2,3], means you can play 2 times, 
    //but 1 and 2 can not occur 2 times consecutively, so 6 x 6 =36, but (1,1) and (2, 2)
    //need to be excluded.
    
    //dp[i][j] means how many choices for total i dices and the last number is j.
    //dp[b][1] = sum(dp[b-1][1~6]) - sum(dp[a][2~6(except 1)])
    
    
    public int dieSimulator(int n, int[] rollMax) {
        int mod = (int)1e9 + 7;
        //dp[i][j] represents the number of distinct sequences that can be 
        //obtained when rolling i times and ending with j
        //The one more row represents the total sequences when rolling i times
        int[][] dp = new int[n + 1][7];
        //init for the first roll
        for (int i = 0; i < 6; i++) {
            dp[1][i] = 1;
        }
        dp[1][6] = 6;
        for (int i = 2; i <= n; i++) {
            int total = 0;
            for (int j = 0; j < 6; j++) {
                //If there are no constraints, the total sequences ending with 
                //j should be the total sequences from previous rolling
                dp[i][j] = dp[i - 1][6];
                //For xx1, only 111 is not allowed, so we only need to remove 1 
                //sequence from previous sum
                if (i - rollMax[j] == 1) {
                    dp[i][j]--;
                }
                //For axx1, we need to remove the number of a11 
                //(211 + 311 + 411 + 511 + 611) => (..2 + ..3 + ..4 + ..5 + ..6) => (sum - ..1)
                if (i - rollMax[j] >= 2) {
                    int reduciton = dp[i - rollMax[j] - 1][6] - dp[i - rollMax[j] - 1][j];
                    //must add one more mod because subtraction may introduce negative numbers
                    dp[i][j] = ((dp[i][j] - reduciton) % mod + mod) % mod;
                }
                total = (total + dp[i][j]) % mod;
            }
            dp[i][6] = total;
        }
        return dp[n][6];
    }
    
    
    int res = 0;
    
    public int dieSimulator_DFS_TLE(int n, int[] rollMax) {
        dfs(n, rollMax, -1, 0);
        return res;
    }
    
    // dieLeft : the number of dies
    // last : last number we rolled
    // curlen : current len of same number
    // This function tries to travel all the valid permutation
    private void dfs(int dieLeft, int[] rollMax, int last, int curlen){
        if(dieLeft == 0){
            res++;
            return;
        }
        //this DFS is interesting, one die could have 6 values, so it would be tree with 
        //6 branches and we continue from root
        for(int i=0; i<6; i++){
            //this means current number already reached max show time
            if(i == last && curlen == rollMax[i]) continue;
            dfs(dieLeft - 1, rollMax, i, i == last ? curlen + 1 : 1);
        }
    }
    
    //DFS + memo
    int[][][] dp = new int[5000][6][16];
    final int M = 1000000007;
    
    public int dieSimulator_DFS_MEMO(int n, int[] rollMax) {
        return helper(n, rollMax, -1, 0);
    }
    
    private int helper(int dieLeft, int[] rollMax, int last, int curlen){
        if(dieLeft == 0) return 1;
        if(last >= 0 && dp[dieLeft][last][curlen] > 0) return dp[dieLeft][last][curlen];
        
        int res = 0;
        for(int i=0; i<6; i++){
            if(i == last && curlen == rollMax[i]) continue;
            res = (res + helper(dieLeft - 1, rollMax, i, i == last ? curlen + 1 : 1)) % M;
        }
        
        if(last >= 0) dp[dieLeft][last][curlen] = res;
        return res;
    }
}