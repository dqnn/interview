package hatecode._0001_0999;
public class _643MaximumAverageSubarrayI {
    /*
    
    643. Maximum Average Subarray I
    You are given an integer array nums consisting of n elements, and an integer k.

Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value. Any answer with a calculation error less than 10-5 will be accepted.

 

Example 1:

Input: nums = [1,12,-5,-6,50,3], k = 4
Output: 12.75000
Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
    */
    
    //thinking process: O(n)/O(1)
    /*
     * the problem is to say: given one array, return max sum for length = k
     * sub array
     */
    
    //leverage A[i] - A[i-k]
    public double findMaxAverage(int[] A, int k) {
        int sum = 0;
         for(int i = 0; i<k;i++) sum += A[i];
         
         long res = sum;
         for(int i = k; i<A.length; i++) {
             sum += A[i] - A[i-k];
             res = Math.max(res, sum);
         }
         
         return 1.0 * res/k;
        
     }
    
    
    public double findMaxAverage_BF(int[] A, int k) {
        if (A == null ||A.length < 1) return 0.0;
        if (A.length == 1) return A[0]/1.0;
        
        
        long res = Long.MIN_VALUE;
        
        int[] sum = new int[A.length];
        sum[0] = A[0];
        for(int i = 1; i< A.length; i++) {
            sum[i] = sum[i-1] + A[i];
        }
        
        //System.out.println(Arrays.toString(sum));
        
        for(int i = 0; i<=sum.length - k; i++) {
            res = Math.max(res, sum[i+k-1] - sum[i] + A[i]);
        }
        
        return 1.0 * res /k;
    }
    
     
    
    
}