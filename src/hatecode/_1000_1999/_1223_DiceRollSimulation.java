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
    
    //
    public int dieSimulator(int n, int[] rollMax) {
        long divisor = (long)Math.pow(10, 9) + 7;
        long[][] dp = new long[n][7];
        for(int i = 0; i < 6; i++) {
            dp[0][i] = 1;
        }
        dp[0][6] = 6;
        for(int i = 1; i < n; i++) {
            long sum = 0;
            for(int j = 0; j < 6; j++) {
                dp[i][j] = dp[i - 1][6];
                if(i - rollMax[j] < 0) {
                    sum = (sum + dp[i][j]) % divisor;
                }
                else {
                    if(i - rollMax[j] - 1 >= 0) dp[i][j] = (dp[i][j] - (dp[i - rollMax[j] - 1][6] - dp[i - rollMax[j] - 1][j])) % divisor + divisor;
                    else dp[i][j] = (dp[i][j] - 1) % divisor;
                    sum = (sum + dp[i][j]) % divisor;
                }

            }
            dp[i][6] = sum;
        }
        return (int)(dp[n - 1][6]);
    }
}