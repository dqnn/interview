package hatecode;
import java.util.*;
public class _1007MinimumDominoRotationsForEqualRow {
/*
1007. Minimum Domino Rotations For Equal Row
In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.  (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)

We may rotate the i-th domino, so that A[i] and B[i] swap values.

Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.

If it cannot be done, return -1.
Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
Output: 2

Input: A = [3,5,1,2,3], B = [3,6,3,3,4]
Output: -1

*/


/*
Solution 3
Find intersection set s of {A[i], B[i]}
s.size = 0, no possible result.
s.size = 1, one and only one result.
s.size = 2, it means all dominos are [a,b] or [b,a], try either one.
s.size > 2, impossible.
*/
    //thinking process:
    //interview friendly, so the problem is to say, given two arrays, A[i] and B[i], they are top and bottom
    //sides for one domino, so find the min flip to make one sides all have same value
    public int minDominoRotations_Better(int[] A, int[] B) {
        HashSet<Integer> s = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        int[] countA = new int[7], countB = new int[7];
        for (int i = 0; i < A.length; ++i) {
            //retainAll get intersection, s will only keep same elements in second set, and remove others
            s.retainAll(new HashSet<>(Arrays.asList(A[i], B[i])));
            countA[A[i]]++;
            countB[B[i]]++;
        }
        //s may be empty or only 1 element
        for (int i : s) return Math.min(A.length - countA[i], B.length - countB[i]);
        return -1;
    }
    
    //
    public int minDominoRotations(int[] A, int[] B) {
        int n = A.length;
        for (int i = 0, a = 0, b = 0; i < n && (A[i] == A[0] || B[i] == A[0]); ++i) {
            if (A[i] != A[0]) a++;
            if (B[i] != A[0]) b++;
            if (i == n - 1) return Math.min(a, b);
        }
        for (int i = 0, a = 0, b = 0; i < n && (A[i] == B[0] || B[i] == B[0]); ++i) {
            if (A[i] != B[0]) a++;
            if (B[i] != B[0]) b++;
            if (i == n - 1) return Math.min(a, b);
        }
        return -1;
    }
}