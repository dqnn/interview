package hatecode._0001_0999;
import java.util.*;
public class _992SubarraysWithKDifferentIntegers {
    /*
     * 992. Subarrays with K Different Integers 
     * Given an array A of positive
     * integers, call a (contiguous, not necessarily distinct) subarray of A good if
     * the number of different integers in that subarray is exactly K.
     * 
     * (For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)
     * 
     * Return the number of good subarrays of A.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: A = [1,2,1,2,3], K = 2 Output: 7
     */
    
    //thiking process:
    
    //given array and k, find how many sub array in the array which have k distinct integer
    
    //so traditional sliding window has the problem，for example 1,2,1,2,3
    //r will continue to right until our map.size() > k, then l start to move to left, 
    //first l = 0, r = 2, map start to move l =1, then r = 3, we will ignore the 1,2,1 etc subarray
    //because TP here cannot move left and right at the same, but they maybe the answers
    
    //so r -l + 1 means how many elements in current sub array, and the subarray they have k distinct integers
    //but 1,2,1, we have 3 subarray in this K =2, but we will have 3 accorinding to r - l + 1. so 
    //we decide to subtract k = 1
    
    //the reason why helper(A, k) - helper(A, k -1) is because r -l + 1 means how many subarrays, 1 + 2 + 3 + 4..+ k, 
    //there is a lot of dup adding, so we minus 1=2+3..+ k - 1, 
    
    //helper() means how many contious subarrays length <=k
    public int subarraysWithKDistinct(int[] A, int k) {
        if (A == null ||A.length < 1 || k < 1) return 0; 
        return helper(A, k) - helper(A, k -1);
    }
    
    private int helper(int[] A, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int l = 0, r =0;
        int res = 0;
        while(r < A.length) {
            map.put(A[r], map.getOrDefault(A[r], 0) + 1);
            r++;
            
            while(map.size() > k) {
                int cnt = map.get(A[l]);
                if (cnt == 1) map.remove(A[l]);
                else map.put(A[l], cnt-1);
                l++;
            }
            res += r - l + 1;
        }
        return res;
    }
}