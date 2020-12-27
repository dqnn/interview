package hatecode._1000_1999;
public class _1191K_ConcatenationMaximumSum {
    /*
    1191. K-Concatenation Maximum Sum
    Given an integer array arr and an integer k, modify the array by repeating it k times.

For example, if arr = [1, 2] and k = 3 then the modified array will be [1, 2, 1, 2, 1, 2].

Return the maximum sub-array sum in the modified array. Note that the length of the sub-array can be 0 and its sum in that case is 0.

As the answer can be very large, return the answer modulo 10^9 + 7.

 

Example 1:

Input: arr = [1,2], k = 3
Output: 9
    */
    
    
    //thinking process: 
    
    private static final int mod  = (int) Math.pow(10,9)+7;
    
    // TC: O(N)
    // based on kadane's algorithm
    public int kConcatenationMaxSum(int[] nums, int k) {
        // STEP 1: run the kadane's algorithm
        long currentSum=0;
        long maxSum=Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            currentSum = currentSum > 0 ? currentSum + nums[i]: nums[i];
            maxSum= Math.max(currentSum,maxSum);
        }
        long kAlgoMaxSum= maxSum< 0? 0: maxSum;
        
        // base case
        if(k == 1){
            return (int) kAlgoMaxSum;
        }
        
        // STEP 2: get the maximum prefix sum, suffix sum and total sum
        int n = nums.length;
        // zeroIndexSum[i+1] = sum (a[0]....a[i])
        int[] zeroIndexSum = new int[n+1];
        for(int i =0;i < n;i++)
            zeroIndexSum[i+1] = zeroIndexSum[i] + nums[i];

        long suffixSum = 0;
        long prefixSum = 0;
        for(int i = n;i >= 0;i--){
            suffixSum = Math.max(suffixSum, zeroIndexSum[n] - zeroIndexSum[i]);
            prefixSum = Math.max(prefixSum, zeroIndexSum[i]);

        }
        long sum= zeroIndexSum[n];
        
        // STEP 3: 
        // case 1: total sum is +ve
        if(sum > 0){
            // remeber the formula
            return (int) Math.max(
                sum * (k-2) % mod + suffixSum + prefixSum,
                kAlgoMaxSum) % mod;
            // case 2: sum is negative
        } else{
            return (int) Math.max(
                prefixSum + suffixSum,
                kAlgoMaxSum) % mod;
        }

    }
}