package hatecode._0001_0999;

import java.util.Arrays;

public class _875KokoEatingBananas {
/*
875. Koko Eating Bananas
Koko loves to eat bananas. There are n piles of bananas, 
the ith pile has piles[i] bananas. The guards have gone and will come 
back in h hours.

Koko can decide her bananas-per-hour eating speed of k. Each hour, she 
chooses some pile of bananas and eats k bananas from that pile. 
If the pile has less than k bananas, she eats all of them instead and will 
not eat any more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas 
before the guards return.

Return the minimum integer k such that she can eat all the bananas within 
h hours.

 

Example 1:

Input: piles = [3,6,7,11], h = 8
Output: 4
*/
    
    /*  thinking process: O(n)/O(1)
     *   
     *   the problem is to say: 
     *   given one integer array A, and hours as integer,  each integer A[i] means the 
     *   bananas in that pile, you can take k bananas each time, return min number of bananas need to eat one time, 
     *   
     *   if we make k too small, then we cannot eat all piles of bananas in h hours,
     *   if too big, then we need A.length hours,
     * 
     */
    public int minEatingSpeed(int[] A, int h) {
        if (A == null || A.length < 1) return -1;
       
        
        long r = Arrays.stream(A).max().getAsInt(), l = 1;
       
        
        while(l < r) {
            long m = l + (r -l)/2;
            if (helper(A, m, h)) {
                r = m;
            } else l = m + 1;
        }
        
        return (int)l;
    }
    
    private boolean helper(int[] A, long m, int h) {
        long res = 0;
        for(int a : A) {
            res += a/m;
            
            if ( a%m != 0) res += 1;
        }
        
        return res <=h;
    }
}