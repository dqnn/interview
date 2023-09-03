package hatecode._1000_1999;

public class _1901FindAPeakElementII {
/*
1901. Find a Peak Element II

A peak element in a 2D grid is an element that is strictly greater than all of its adjacent neighbors to the left, right, top, and bottom.

Given a 0-indexed m x n matrix mat where no two adjacent cells are equal, find any peak element mat[i][j] and return the length 2 array [i,j].

You may assume that the entire matrix is surrounded by an outer perimeter with the value -1 in each cell.

You must write an algorithm that runs in O(m log(n)) or O(n log(m)) time.

 

Example 1:



Input: mat = [[1,4],[3,2]]
Output: [0,1]
*/
/*
 * thinking process: O(mlgn) row*lg(cols)
 * 
 * the problem is to say: given a 2D maxtrix A, you need to return any peak element index(i, j), the element needs to be bigger than its 
 *  4  direction elements, if they are on edge, then they are bigger already.
 * 
 * compare to 162. Find Peak Element
 * 
 * here it changed to 2D, but the problem itself does not change, in 162, we use binary search to find the element, since any adjacent
 * elements are different, so we can compare A[m] and A[m-1], 
 * if A[m-1] > A[m] we move r = m , else l = m, we should make sure the bigger value is in range{l ,r}
 * 
 * here the trick is 
 */
    public int[] findPeakGrid(int[][] A) {
        if (A == null || A.length < 1 || A[0].length < 1) return new int[]{-1,-1};
        
        int m = A.length, n = A[0].length;
        
        int l = 0, r = n - 1;
        
        while(l <= r) {
            int mid = l + (r -l)/2;
            int max_row = 0, max = A[0][mid];
            for(int i = 0; i<m;i++) {
                if(A[i][mid] > max) {
                    max = A[i][mid];
                    max_row = i;
                }
            }
            
            if((mid == 0 || A[max_row][mid] > A[max_row][mid-1]) && (mid == n -1 || A[max_row][mid] > A[max_row][mid+1])) return new int[]{max_row, mid};
            else if(mid > 0 && A[max_row][mid-1] > A[max_row][mid]) {
                r = mid - 1;
            } else l = mid + 1;
        }
        
        return new int[]{-1,-1};
    }
}
