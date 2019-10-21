package hatecode;
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
    
    //thinking process: O(ns)/O(s) s= sum(A)
    //given an array of integers, we want to know for any two integers in array A
    //x ==y -> x and y will disappear, x !=y ->then it |y-x|
    //we want to know last stone weight, if the smallest possible 
    
    /**
    This question eaquals to partition an array into 2 subsets whose difference is minimal
    (1) S1 + S2  = S
    (2) S1 - S2 = diff  

    ==> -> diff = S - 2 * S2  ==> minimize diff equals to  maximize S2 

    Now we should find the maximum of S2 , range from 0 to S / 2, using dp can solve this

    dp[i][j]   = {true if some subset from 1st to j'th has a sum equal to sum i, false otherwise}
        i ranges from (sum of all elements) {1..n}
        j ranges from  {1..n}

    same as 494. Target Sum
    */
    public int lastStoneWeightII(int[] A) {
        if(A == null || A.length < 1) return 0;
        boolean[] dp = new boolean[1501];
        dp[0] = true;
        int sumA = 0;
        for (int a : A) {
            sumA += a;
            for (int i = Math.min(1500, sumA); i >= a; --i)
                dp[i] |= dp[i - a];
        }
        for (int i = sumA / 2; i > 0; --i)
            if (dp[i]) return sumA - i - i;
        return 0;
    }
    
    //interesting solutions
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