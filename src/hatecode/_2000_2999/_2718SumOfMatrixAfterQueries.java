import java.util.*;

public class _2718SumOfMatrixAfterQueries {
    
/*
2718. Sum of Matrix After Queries
You are given an integer n and a 0-indexed 2D array queries where queries[i] = [typei, indexi, vali].

Initially, there is a 0-indexed n x n matrix filled with 0's. For each query, you must apply one of the following changes:

if typei == 0, set the values in the row with indexi to vali, overwriting any previous values.
if typei == 1, set the values in the column with indexi to vali, overwriting any previous values.
Return the sum of integers in the matrix after all queries are applied.

 

Example 1:


Input: n = 3, queries = [[0,0,1],[1,2,2],[0,2,3],[1,0,4]]
Output: 23
Explanation: The image above describes the matrix after each query. The sum of the matrix after all queries are applied is 23. 
*/

/*
 thinking process: O(n)

 the problem is to say: given one matrix nxn, you have another array A, 
 A[i][3], A[i][0] means vertical or horizonte operation, overwrite all existing values, 
          A[i][1] is the index, row or column 
          A[1][2] is the new value.

return the sum of the matrix. 

the probelm is tricky because if you thinking from 0->n, you have to remember where you overrite, subsrate previous value and add new ones
you can have map to record, but it will not be linear algo

so we can think reversely, only last write is the winner, all previously are all removed from the results. 

we only counting the last write, if previous one has been overritten, it will be skipped.
we use rFlag[i] to record whether this row is been overriten later. 
   use cFlag[j] to record whether this col is been overwritten later

we use rRemaining because how many cells existed in that row. can used for calcualted sum 
*/
    public long matrixSumQueries(int n, int[][] A) {
        if (A == null || A.length < 1) return 0;
        
        long res = 0;
        int cRemain = n, rRemain = n;
        int[] rFlag = new int[n], cFlag = new int[n];
        Arrays.fill(rFlag, 1);
        Arrays.fill(cFlag, 1);
        for(int i = A.length - 1; i >=0; i--) {
            int idx = A[i][1];
            if (A[i][0] == 0  && rFlag[idx] == 1) {
                res += cRemain * A[i][2];
                rFlag[idx] = 0;
                rRemain--;
            }
            
            if (A[i][0] == 1 && cFlag[idx] == 1) {
                res += rRemain * A[i][2];
                cFlag[idx] = 0;
                cRemain --;
            }
        }
        
        return res;
    }
}