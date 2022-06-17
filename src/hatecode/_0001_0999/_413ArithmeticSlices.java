package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ArithmeticSlices
 * Creator : professorX
 * Date : Nov, 2017
 * Description : 413. Arithmetic Slices
 * 
 * A sequence of number is called arithmetic if it consists of at least three 
 * elements and if the difference between any two consecutive elements is the same.

For example, these are arithmetic sequence:

1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9
The following sequence is not arithmetic.

1, 1, 2, 5, 7

A zero-indexed array A consisting of N numbers is given. 
A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.

A slice (P, Q) of array A is called arithmetic if the sequence:
A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, 
this means that P + 1 < Q.

The function should return the number of arithmetic slices in the array A.
 */
public class _413ArithmeticSlices {
    /**
     * Example:

     A = [1, 2, 3, 4]

     return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.

     数组	   等差数列的数目	    与上一数组的等差数列数目比较
     1 2 3	         1	            1 - 0 = 1
     1 2 3 4	     3	            3 - 1 = 2
     1 2 3 4 5	     6	            6 - 3 = 3
     1 2 3 4 5 6	10	            10 - 6 = 4
     1 2 3 4 5 6 7	15	            15 - 10 = 5

     time : O(n)
     space : O(1)

     * @param A
     * @return
     */

    // we start from index = 2 to detect,
    public int numberOfArithmeticSlices(int[] A) {
        if (A == null || A.length <= 2) {
            return 0;
        }
        int cur = 0, res = 0;// cur indicate res how many steps that res need to 
        //we start from 2 so we do not need to take care of i+1 index, 
        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                cur++; // add one more elements means we will have 2 more, 1,2,3--> 1,2,3,4
                res += cur;
            } else {
                cur = 0;
            }
        }
        return res;
    }
    //dp solution, not hard, but should be easy to understand 
    public int numberOfArithmeticSlices_DP(int[] A) {
        if (A == null || A.length < 3) return 0;
        int n = A.length;
        // dp[i] means the number of arithmetic slices ending with A[i]
        int[] dp = new int[n];
        // if the first three numbers are arithmetic or not, this is for the edge case
        if (A[2] - A[1] == A[1] - A[0]) dp[2] = 1;
        int res =dp[2];
 // if A[i-2], A[i-1], A[i] are arithmetic, then the number of arithmetic slices ending with A[i] (dp[i])
            // equals to:
            //      the number of arithmetic slices ending with A[i-1] (dp[i-1], all these arithmetic slices appending A[i] are also arithmetic)
            //      +
            //      A[i-2], A[i-1], A[i] (a brand new arithmetic slice)
            // it is how dp[i] = dp[i-1] + 1 comes     
        for(int i =3; i < n; i++) {
            if (A[i] - A[i-1] == A[i-1] - A[i-2]) {
                dp[i] = dp[i-1] + 1;
            }
            // accumulate all valid slices, so it is more like 1+2+3, this way
            res += dp[i];
        }
        return res;
    }
}
