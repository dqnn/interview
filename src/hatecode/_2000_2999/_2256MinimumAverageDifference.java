package hatecode._2000_2999;

public class _2256MinimumAverageDifference {
/*
2256. Minimum Average Difference

You are given a 0-indexed integer array nums of length n.

The average difference of the index i is the absolute difference between the average of the first i + 1 elements of nums and the average of the last n - i - 1 elements. Both averages should be rounded down to the nearest integer.

Return the index with the minimum average difference. If there are multiple such indices, return the smallest one.

Note:

The absolute difference of two numbers is the absolute value of their difference.
The average of n elements is the sum of the n elements divided (integer division) by n.
The average of 0 elements is considered to be 0.
 

Example 1:

Input: nums = [2,5,3,9,5,3]
Output: 3
*/
    public int minimumAverageDifference(int[] A) {
        if (A == null || A.length < 1) return 0;
        
        //long sum = Arrays.stream(A).sum();
        long sum = 0;
        for(int a : A) sum += a;
        
        long res = Long.MAX_VALUE;
        int index = -1;
        long sumFront = 0, n = A.length;
        for(int i = 0; i< n; i++) {
            sumFront += A[i];
            sum -= A[i];
            long temp= Math.abs((sumFront/(i+1) )- (i == n-1 ? 0 : sum/(n-i-1)));
            if (temp < res) {
                res = temp;
                index = i;
            }
        }
        
        return index;
    }
}
