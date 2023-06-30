import java.util.*;

public class _2448MinimumCostToMakeArrayEqual {
    /*
    2448. Minimum Cost to Make Array Equal
    
    You are given two 0-indexed arrays nums and cost consisting each of n positive integers.
    
    You can do the following operation any number of times:
    
    Increase or decrease any element of the array nums by 1.
    The cost of doing one operation on the ith element is cost[i].
    
    Return the minimum total cost such that all the elements of the array nums become equal.
    
     
    
    Example 1:
    
    Input: nums = [1,3,5,2], cost = [2,3,1,14]
    Output: 8
    */

        /*
         * thinking process: O(Mlgn) M = max - min
         * 
         * the problem is to say: given one integer array A and cost array cost, 
         * 
         * you can increment or decrement A[i] with 1, each move will have cost[i]
         * 
         * the goal is to make all elements equal, return the min cost
         * 
         * all are positive integers
         * 
         * 
         */
        public long minCost(int[] A, int[] cost) {
            if (A == null || A.length < 1) return -1;
            
            int min = A[0], max = A[0];
            for(int a: A) {
                min = Math.min(min, a);
                max = Math.max(max, a);
            }
            
            long l = min,  r = max;
            while(l < r) {
                long m = l + (r -l)/2;
                if (helper(A, m, cost) < helper(A, m+1, cost)) {
                    r = m;
                } else l = m + 1;
            }
            
            return helper(A, l, cost);
        }
        
        private long helper(int[] A, long m, int[] cost) {
            long res = 0;
            for(int i = 0; i< A.length; i++) {
                res += cost[i] * Math.abs(A[i] - m);
            }
            
            return res;
        }
    }