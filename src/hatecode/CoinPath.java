package hatecode;

import java.util.*;

public class CoinPath {
/*
656. Coin Path
Given an array A (index starts at 1) consisting of N integers: A1, A2, ..., AN and an integer B. The integer B denotes that from any place (suppose the index is i) in the array A, you can jump to any one of the place in the array A indexed i+1, i+2, …, i+B if this place can be jumped to. Also, if you step on the index i, you have to pay Ai coins. If Ai is -1, it means you can’t jump to the place indexed i in the array.

Now, you start from the place indexed 1 in the array A, and your aim is to reach the place indexed N using the minimum coins. You need to return the path of indexes (starting from 1 to N) in the array you should take to get to the place indexed N using minimum coins.

If there are multiple paths with the same cost, return the lexicographically smallest such path.

If it's not possible to reach the place indexed N then you need to return an empty array.

Example 1:

Input: [1,2,4,-1,2], 2
Output: [1,3,5]
*/
    //TODO: thinking about the difference between frog jump/jump game I/II, oddEven jump and coin path
    
    //Brute force solution, 
    //so given an array A starting from 1, each value is cost, we start from position 1, 
    //each time we have B steps to jump, return minimal cost to each position N. 
    //since this asked for minimal lexi order, so DFS on position i is best since 1,2,3.. is better than 1.3.. even latter
    //is shorter
    //one different is we pass i and in loop, we start from i +1, because we want to know from i+1 to 
    //N, the cost as the definition of helper, and we compare each time
    
    
    //best solution, basically it is the same as memo, so we defined dp[i] means the minimal cost from
    //i to N, the difference here is from back to front. so for dp[j] foruma is 
    //dp[i] = min(dp[0],...dp[i-1])
    //another edge is last position is -1, so no way, 
    public List<Integer> cheapestJump_DP(int[] A, int B) {
        List<Integer> res = new ArrayList<>();
        //note: last position is -1 we need exclude this edge case
        if (A == null ||A.length < 1 || A[A.length -1] < 0) return res;
        
        int n = A.length;
        int[] dp = new int[n], pos = new int[n];
        Arrays.fill(pos, -1);
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[n-1] = A[n-1];
        
        //scan from right to left
        for(int i = n -2; i>=0;i--) {
            if (A[i] == -1) continue;
            
            for(int j = i+1; (j <= i + B) && j < n; j++) {
                if (dp[j] == Integer.MAX_VALUE) continue;
                if (A[i] + dp[j] < dp[i]) {
                    dp[i] = A[i] + dp[j];
                    pos[i] = j;
                }
            }
        }
        if(dp[0] == Integer.MAX_VALUE) return res;
        int k = 0;
        while(k != -1) {
            res.add(k +1);
            k = pos[k];
        }
        
        return res;
    }
    
    
    
    
    
    
    
    
    
    
    
    //TLE back tracking, O(a ^(n-B))/O(n) a is not decided yet
    //T(n) = T(n-1)+T(n-2)+...T(n-B)---> if B = 2, we can know it is T(n)= ((sqrt(5)) + 1) /2) ^n 
    //for 1 it has B children, and it has n layers, the top we have n elements
    
    //but if we add memo, it will be accepted, 
    //with N element and B times in helper, so the complexity will become O(nB) 
    public List<Integer> cheapestJump(int[] A, int B) {
        int n = A.length;
        //for position i we start to position N, next[i] means the minimal cost next position
        int[] next = new int[n];
        int[] memo = new int[A.length];
        Arrays.fill(next, -1);
        Arrays.fill(memo, Integer.MAX_VALUE);
        
        helper(A, B, 0, next);
        //this is better
        //helper(A, B, 0, next, memo);
        //we already got next, then start to process,
        List<Integer> res = new ArrayList<>();
        int i = 0;
        //we add each position into res, and stop if next[i] < 0 means we cannot find a path
        for (;i < n && next[i] > 0; i = next[i]) res.add(i + 1);
        //we didnot find a path
        if (i == n - 1 && A[i]>= 0) res.add(n);
        else return new ArrayList<Integer>();
        return res;
    }
    public int helper(int[] A, int B, int i, int[] next) {
        if (i == A.length - 1 && A[i] >= 0) return A[i];
        int min_cost = Integer.MAX_VALUE;
        //this is the difference, we start from i +1, not i, because 
        //we already stand on i, then what we need to know from next stop to N's the cost
        for (int j = i + 1; j <= i + B && j < A.length; j++) {
            if (A[j] >= 0) {
                int cost = A[i] + helper(A, B, j, next);
                if (cost < min_cost) {
                    min_cost = cost;
                    next[i] = j;
                }
            }
        }
        return min_cost;
    }
    
    public int helper(int[] A, int B, int i, int[] next, int[] memo) {
        if (memo[i] != Integer.MAX_VALUE) return memo[i];
        if (i == A.length - 1 && A[i] >= 0) return A[i];
        for (int j = i + 1; j <= i + B && j < A.length; j++) {
            if (A[j] >= 0) {
                int cost = A[i] + helper(A, B, j, next, memo);
                if (cost < memo[i]) {
                    memo[i] = cost;
                    next[i] = j;
                }
            }
        }
        return memo[i];
    }

}