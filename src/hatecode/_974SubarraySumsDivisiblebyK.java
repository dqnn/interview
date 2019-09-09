package hatecode;

import java.util.*;
public class _974SubarraySumsDivisiblebyK {
/*
974. Subarray Sums Divisible by K
Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.

 

Example 1:

Input: A = [4,5,0,-2,-3,1], K = 5
Output: 7
*/
    //prefix-sum, O(n)/O(n)
    //thinking process:
    //
    public int subarraysDivByK(int[] A, int K) {
        int[] map = new int[K];
		map[0] = 1;
        int count = 0, sum = 0;
        for(int a : A) {
            sum = (sum + a) % K;
            if(sum < 0) sum += K;  // Because -1 % 5 = -1, but we need the positive mod 4
            count += map[sum];
            map[sum]++;
        }
        return count;
    }
    
    public int subarraysDivByK_Map(int[] A, int K) {
        if(A == null ||A.length < 1) return 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        
        int count = 0, sum = 0;
        for(int a : A) {
            sum = (sum + a) % K;
            if(sum < 0) sum += K;
            count += map.getOrDefault(sum, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        
        return count;
    }
    
    
}