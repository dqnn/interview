package hatecode._0001_0999;
public class _955DeleteColumnsToMakeSortedII {
/*
955. Delete Columns to Make Sorted II
We are given an array A of N lowercase letter strings, all of the same length.

Now, we may choose any set of deletion indices, and for each string, we delete all the characters in those indices.

For example, if we have an array A = ["abcdef","uvwxyz"] and deletion indices {0, 2, 3}, then the final array after deletions is ["bef","vyz"].

Suppose we chose a set of deletion indices D such that after deletions, the final array has its elements in lexicographic order (A[0] <= A[1] <= A[2] ... <= A[A.length - 1]).

Return the minimum possible value of D.length.

 

Example 1:

Input: ["ca","bb","ac"]
Output: 1
*/
/*
Intuition
Solve it with a greed algorithme.

Initial N empty string.
For each column,
add the character to each row,
and check if all rows are still sorted.

If not, we have to delete this column.


Explanation
Initial a boolean array sorted,
sorted[i] = true if and only if A[i] < A[i + 1],
that is to say A[i] and A[i + 1] are sorted.

For each col, we check all unsorted rows.
If A[i][j] > A[i + 1][j], we need to deleted this col, res++.

Otherwise, we can keep this col, and update all sorted rows.
*/
    //O(mn)
    //thinking process: 
    //given a string array, 
    public int minDeletionSize(String[] A) {
        if (A == null || A.length < 1) return 0;
        //j is string length, all strings have same length
        int res = 0, n = A.length, m = A[0].length(), i, j;
        boolean[] sorted = new boolean[n - 1];
        for (j = 0; j < m; ++j) {
            for (i = 0; i < n - 1; ++i) {
                if (!sorted[i] && A[i].charAt(j) > A[i + 1].charAt(j)) {
                    res++;
                    break;
                }
            }
            if (i < n - 1) continue;
            for (i = 0; i < n - 1; ++i)
                if (A[i].charAt(j) < A[i + 1].charAt(j))
                    sorted[i] = true;
        }
        return res;
    }
}