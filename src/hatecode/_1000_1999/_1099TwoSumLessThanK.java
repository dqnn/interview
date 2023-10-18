package hatecode._1000_1999;
public class _1099TwoSumLessThanK {
/*
1099. Two Sum Less Than K
Given an array A of integers and integer K, return the maximum S such that there 
exists i < j with A[i] + A[j] = S and S < K. If no i, j exist satisfying this equation, return -1.

Example 1:

Input: A = [34,23,1,24,75,33,54,8], K = 60
Output: 58
*/
    
    //thinking process: O(n)/O(max) interview friendly 
    
    //we use bucket sort to arrange all integers and to find k - a - 1 in the buckets, 
    //in the buckets, we store buckets[a] =a if a is from A, if not, we store previous smaller integer

    /*
     * we use bucket to sort the array, but still need to fill some slots which are empty 
     * 
     */
    public int twoSumLessThanK(int[] A, int k) {
        if (A == null || A.length < 1) return -1;
         
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int a : A) {
            min = Math.min(min, a);
            max = Math.max(a, max);
        }
         
        int[] b = new int[max+1];
        for(int a : A) {
            b[a]++;
        }

        int l =0, r = max;
        int res = Integer.MIN_VALUE;
        while(l <= r) {
            //skip empty bucket
            while(l <= r && b[l] == 0)l++;
            while(l <= r && b[r] == 0)r--;
            // avoid case like [1] k = 2, we should return -1
            if(l == r && b[l] == 1) break;
            if(l + r <k) {
                res = Math.max(res, l + r);
                l++;
            } else {
                r--;
            }
        }

        return res == Integer.MIN_VALUE? -1: res;
     
     }
}