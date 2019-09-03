package hatecode;

import java.util.*;
public class DeleteColumnsToMakeSortedIII {
/*
960. Delete Columns to Make Sorted III
We are given an array A of N lowercase letter strings, all of the same length.

Now, we may choose any set of deletion indices, and for each string, we delete all the characters in those indices.

For example, if we have an array A = ["babca","bbazb"] and deletion indices {0, 1, 4}, then the final array after deletions is ["bc","az"].

Suppose we chose a set of deletion indices D such that after deletions, the final array has every element (row) in lexicographic order.

For clarity, A[0] is in lexicographic order (ie. A[0][0] <= A[0][1] <= ... <= A[0][A[0].length - 1]), A[1] is in lexicographic order (ie. A[1][0] <= A[1][1] <= ... <= A[1][A[1].length - 1]), and so on.

Return the minimum possible value of D.length.

 

Example 1:

Input: ["babca","bbazb"]
Output: 3
Explanation: After deleting columns 0, 1, and 4, the final array is A = ["bc", "az"].
Both these rows are individually in lexicographic order (ie. A[0][0] <= A[0][1] and A[1][0] <= A[1][1]).
Note that A[0] > A[1] - the array A isn't necessarily in lexicographic order.
*/
    //thinking process: O(mn^2)/O(n), n=A.length, m =A[0].length()
    //the original problem is to say given array of strings, we want to
    //remove some chars so after these deletion movements, the rest strings are all
    //lex ordered. 
    
    //max increasing sequence, if we see only 1 string, after deletion, we want rest string
    //is max length and increasing lex
    //each column is an element,
    
    //first to know how many chars can be kept, 
    //for example, babca, bbazb, TODO: need to understand the loop
    //
    public int minDeletionSize(String[] A) {
        if (A == null || A.length < 1) return 0;
        int r = A.length, c = A[0].length(), res = c - 1, k;
        //dp[i] means for 0-i strings, the max len increase sub sequence length, 
        //For all j < i, if A[][j] < A[][i], 
        //then dp[j] = max(dp[j], dp[i] + 1)
        int[] dp = new int[c];
        Arrays.fill(dp, 1);
        //each row or each character
        for (int i = 0; i < c; ++i) {
            //0 - i chars
            for (int j = 0; j < i; ++j) {
                //we check all rows before column j, if we find 
                //one column not increase then we exit the row loop
                for (k = 0; k < r; ++k) {
                    if (A[k].charAt(j) > A[k].charAt(i)) break;
                }
                //if we loop to last row which means current situation,
                //all chars (0, j) are increasing chars
                if (k == r && dp[j] + 1 > dp[i])
                    dp[i] = dp[j] + 1;
            }
            //covert to chars to delete
            res = Math.min(res, c - dp[i]);
        }
        return res;
    }
}