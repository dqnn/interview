package hatecode._1000_1999;
import java.util.*;
public class _1049LastStoneWeightII {
/*
1049. Last Stone Weight II
We have a collection of rocks, each rock has a positive integer weight.

Each turn, we choose any two rocks and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:

If x == y, both stones are totally destroyed;
If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
At the end, there is at most 1 stone left.  Return the smallest possible weight of this stone (the weight is 0 if there are no stones left.)

 

Example 1:

Input: [2,7,4,1,8,1]
Output: 1
*/
    /**
    This question eaquals to partition an array into 2 subsets whose difference is minimal
    (1) S1 + S2  = S
    (2) S1 - S2 = diff  

    ==> -> diff = S - 2 * S2  ==> minimize diff equals to  maximize S2 

    Now we should find the maximum of S2 , range from 0 to S / 2, 
    using dp can solve this

    dp[i][j]   = {true if some subset from 1st to j'th has a sum 
    equal to sum i, false otherwise}
        i ranges from (sum of all elements) {1..n}
        j ranges from  {1..n}

    same as 494. Target Sum
    */
    //thinking process： 
    //see above explanations
    //the model is knapsack, bag is sum, so we want to the max weight we can get 
    //from stones
    public int lastStoneWeightII(int[] stones) {
        int sum = 0, S2 = 0;
        for (int s : stones) sum += s;
        int n = stones.length;
        //dp[i][j] means for 0->j sum to i-->true else false
        boolean[][] dp = new boolean[sum + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            dp[0][i] = true;
        }
        for (int i = 1; i <= n; i++) {
            for (int s = 1; s <= sum / 2; s++) {
                if (dp[s][i - 1] 
                || (s >= stones[i - 1] && dp[s - stones[i - 1]][i - 1])) {
                    dp[s][i] = true;
                    S2 = Math.max(S2, s);
                }
            }
        }
        return sum - 2 * S2;
    }
    
    // brute force, but interesting brute force, it gave us one perspective
    //to simulate all possible combination sum as array grows
    //so suppose [1,2,3], first we add 1,-1 into set.
    //then for 2, we +2 for each in set, 3,1, -2->[-1,-3], then->[-3,-1,1,3]
    //continue, so we can see actually we are trying every combination sum and merge them
    //
    public int lastStoneWeightII_GoodOnes(int[] stones) {
        Set<Integer> set = new HashSet<>();
        set.add(stones[0]);
        set.add(-stones[0]);
        for(int i=1;i<stones.length;i++){
            Set<Integer> set2 = new HashSet<>();
            for(int item : set){
                set2.add(item + stones[i]);
                set2.add(item - stones[i]);
            }
            set = set2;
        }
        int min = Integer.MAX_VALUE;
        for(int item : set) min = Math.min(Math.abs(item), min);
        return min;
    }
}