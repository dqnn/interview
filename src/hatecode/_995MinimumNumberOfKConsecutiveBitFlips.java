package hatecode;

import java.util.*;
public class _995MinimumNumberOfKConsecutiveBitFlips {
/*
995. Minimum Number of K Consecutive Bit Flips
In an array A containing only 0s and 1s, a K-bit flip consists of choosing a (contiguous) subarray of length K and simultaneously changing every 0 in the subarray to 1, and every 1 in the subarray to 0.

Return the minimum number of K-bit flips required so that there is no 0 in the array.  If it is not possible, return -1.

 

Example 1:

Input: A = [0,1,0], K = 1
Output: 2
*/
    //thinking process: O(nK)/O(1) brute force
    //Given an array A and integer K, A only conains 0 or 1, K <= A.length,
    //so each time we can flip K length of sun array in A, return the min steps
    //which we can 
    public int minKBitFlips_BF(int[] A, int K) {
        if(A == null || A.length < 1) return 0;
        
        int res = 0;
        for(int i = 0; i <= A.length - K; i++) {
            if(A[i] == 1) continue;
            res++;
            for(int j = 0; j<K;j++) A[i+j] ^= 1;
        }
        
        for(int i = A.length - K + 1; i < A.length; i++) {
            if(A[i] == 0) return -1;
        }
        
        return res;
    }
    //optimized solution, O(n)/O(n)
    //isFlipped array is to 
    public int minKBitFlips(int[] A, int K) {
        int n = A.length, flipped = 0, res = 0;
        int[] isFlipped = new int[n];
        for (int i = 0; i < A.length; ++i) {
            if (i >= K)
                flipped ^= isFlipped[i - K];
            if (flipped == A[i]) {
                if (i + K > A.length)
                    return -1;
                isFlipped[i] = 1;
                flipped ^= 1;
                res++;
            }
        }
        return res;
    }
    
    //O(n)/O(1)
     public int minKBitFlips_Best(int[] A, int K) {
        int cur = 0, res = 0, n = A.length;
        for (int i = 0; i < n; ++i) {
            if (i >= K && A[i - K] > 1) {
                cur--;
                A[i - K] -= 2;
            }
            if (cur % 2 == A[i]) {
                if (i + K > n) return -1;
                A[i] += 2;
                cur++;
                res++;
            }
        }
        return res;
    }
    
}