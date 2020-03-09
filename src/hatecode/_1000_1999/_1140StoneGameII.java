package hatecode._1000_1999;
public class _1140StoneGameII {
/*
1140. Stone Game II
Alex and Lee continue their games with piles of stones.  There are a number 
of piles arranged in a row, and each pile has a positive integer number 
of stones piles[i].  The objective of the game is to end with the most stones. 

Alex and Lee take turns, with Alex starting first.  Initially, M = 1.

On each player's turn, that player can take all the stones in the 
first X remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).

The game continues until all the stones have been taken.

Assuming Alex and Lee play optimally, return the maximum number of stones Alex can get.

 

Example 1:

Input: piles = [2,7,9,4,4]
Output: 10
Explanation:  If Alex takes one pile at the beginning, Lee takes two piles, 
then Alex takes 2 piles again. Alex can get 2 + 4 + 4 = 10 piles in total. 
If Alex takes two piles at the beginning, then Lee can take all three piles left.
 In this case, Alex get 2 + 7 = 9 piles in total. So we return 10 since it's
  larger. 
 
*/
    //thinking process:  (n^2)/O(n^2)
    //so since we use DP to memo, it is equals how many subarrays we can have, it is n^2
    
    //first explain the game with an example, piles = [2,7,9,4,4]
    //M = 1, so Alex 1<=X<=2*1, so Alex has 2 options ,1 or 2, so Alex can get 2 or 2+7, 
    //if Alex take 2, then Lee's M = max(M, X) = 1, so he also can take 7/7+9, or 9/9+4/9+4+4, since 
    //X<=4, 
    //so if Alex want to get largest stones, he needs to go through all possible paths, 
    /*
     *              [2,7,9,4,4]
     *               /      \
     *         [7,9,4,4]      [9,4,4]    Alex can get 2/2+7
     *          /    \          / |  \
     *      [9,4,4]  [4,4] [4,4] [4]  []  Lee could get 7/7+9 first, second: 9/9+4/9+4+4, Done
     *      /  \     / | \    
     */
    
    //the problems is the same as Can I win or other game recursion questions
    public int stoneGameII(int[] A) {
        if (A == null || A.length < 1) return 0;
        int n = A.length;
        // First we calculate the sum of all piles from the end to the begging 
        // in order to be able to retrieve quickly how many stones a player has
        // if for example is in position i of piles and can take 3 piles.
        // The answer is sums[i] - (the number of stones the other player will
        // take with max 6 piles (2*M))
        int[] sums = new int[n];
        sums[n - 1] = A[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            sums[i] = sums[i + 1] + A[i]; // the sum from piles[i] to the end
        }
        // We create the memorization vector where dp[i][j] is the optimal choice  
        // in the i position of piles with max 2*j piles. 
        int[][] dp = new int[n][n];
        //we also return helper(A, 0, 1, dp, sums);
        helper(A, 0, 1, dp, sums);
        
        // Alex starts first and so he is at position 0 of piles and he begins with 
        // max 2*1 options.
        return dp[0][1];
    }
    
    // i means the pile index, M is the parameter decides X scope, 1<=X<=2M,
    private int helper(int[] A, int i, int M, int[][] dp, int[] sums) {
        //reach end of pile
        if (i == A.length) return 0;
        
        // If 2*M is equal of more of the rest of the
        // piles then the player takes them all because
        // every player plays optimally.
        if (2 * M >= A.length - i) return sums[i];
        
        // We might have been in this stage before with the same or 
        // the other player and so we already know the optimal
        // choice from this position.
        if (dp[i][M] != 0) return dp[i][M];
        
        int min = Integer.MAX_VALUE;// the min value the next player can get
        // We calculate the more stones the next player will get for every choice of M we will make
        // and we choose the minimun of those!
        for (int x = 1; x <= 2 * M; x++) {
            min = Math.min(min, helper(A, i + x, Math.max(M, x), dp, sums));
        }
        // The stones we will get are those in the sums position we stand minus the ones the next
        // player will get!
        dp[i][M] = sums[i] - min; // max stones = all the left stones - the min stones next player can get
        return dp[i][M];
    }
}