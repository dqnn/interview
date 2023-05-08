package _2000_2999;

import java.utils.*;


public class _2672NumberOfAdjacentElementsWithTheSameColor {
    /*
    2672. Number of Adjacent Elements With the Same Color
    There is a 0-indexed array nums of length n. Initially, all elements are uncolored (has a value of 0).
    
    You are given a 2D integer array queries where queries[i] = [indexi, colori].
    
    For each query, you color the index indexi with the color colori in the array nums.
    
    Return an array answer of the same length as queries where answer[i] is the number of adjacent elements with the same color after the ith query.
    
    More formally, answer[i] is the number of indices j, such that 0 <= j < n - 1 and nums[j] == nums[j + 1] and nums[j] != 0 after the ith query.
    
     
    
    Example 1:
    
    Input: n = 4, queries = [[0,2],[1,2],[3,1],[1,1],[2,1]]
    Output: [0,1,1,0,2]
    */

    /*
     * thinking process: O(n)/O(n)
     * 
     * the problem is to say: given one integer array, intialized as all 0,  for each query
     * [i,j], just A[i] = j, then count how many addjancet elements are the same as a pair.
     * 
     * [1,1,1] should have 2 
     * 
     *  just count each time if we change the value,  whether we increase or decrease, 
     * just need to take care index = 0 and index = n - 1, because no one is precedent or after.
     */
        public int[] colorTheArray(int n, int[][] queries) {
            if (n < 1) return new int[]{};
            
            int[] res = new int[queries.length];
            int[] d = new int[n];
            
            int cnt = 0;
            for(int i = 0; i< queries.length; i++) {
                int[] q = queries[i];
                int s = q[0], e = q[1];
                
                if (d[s] == e) {
                    res[i] = cnt;
                    continue;
                }
                int old = d[s];
                d[s] = e;
                
                //System.out.println(Arrays.toString(d));
                if (s > 0 && d[s-1] == d[s]) cnt++;
                if (s != n -1 && d[s] == d[s+1]) cnt++;
                
                if (s > 0 && old > 0 && old == d[s-1]) cnt--;
                if (s !=n-1  && old > 0 && old == d[s+1]) cnt--;
                res[i] = cnt;
                
            }
            
            return res;
        }
    }