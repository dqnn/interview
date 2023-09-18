package hatecode._1000_1999;

import java.util.PriorityQueue;

public class _1439FindTheKthSmallestSumOfAMatrixWithSortedRows {
/*
1439. Find the Kth Smallest Sum of a Matrix With Sorted Rows

You are given an m x n matrix mat that has its rows sorted in non-decreasing order and an integer k.

You are allowed to choose exactly one element from each row to form an array.

Return the kth smallest array sum among all possible arrays.

 

Example 1:

Input: mat = [[1,3,11],[2,4,6]], k = 5
Output: 7
*/

/*
 * thinking process: O(m *k *lgk)/O(k)
 * 
 * the problem is to say: given a 2D positive matrix, each row is asc, and you can pick each element from each row to form a array,  
 * return the Kth smallest sum of the whole. 
 * 
 * for example A = [
    *                 [1,3,11],
    *                 [2,4,6]
 *                 ], k = 5
 * 
 * 
 * [1,2], [1,4], [3,2], [3,4], [1,6]. Where the 5th sum is 7.
 * 
 * 
 * we pick first row and 2nd row out, calculate its K smallest sum of array, then we continue with 3rd array, note: K may be bigger than one row even k
 * =m^n, the sum of all numbers in last column
 * 
 * 
 * [[1,10,10],[1,4,5],[2,3,6]], k = 7
 * 
 * helper([1,10,10], [1,4,5], 7) ---> res[7] = [1,5,6,11,11,14,14]
 * 
 * helper([1,5,6,11,11,14,14], [2,3,6]) ---> res = [3,4,7,7,8,8,9] ---> 9
 * 
 * 
 */
    public int kthSmallest(int[][] A, int k) {
        if(A ==null || A.length < 1 ||A[0].length < 1 || k <= 0) return -1;
        
        int[] row = A[0];
        for(int i = 1; i<A.length; i++) {
            row = helper(row, A[i], k);
        }
        
        return row[k-1];
    }
    
    /*
     *  return array which contains k, up to m*n sorted sum of A and B, each row pick one 
     */
    private int[] helper(int[] A, int[] B, int k) {
        PriorityQueue<int[]> pq =new PriorityQueue<>((a, b)->Integer.compare(A[a[0]]+B[a[1]], A[b[0]] +B[b[1]]));
        int m = A.length, n = B.length;
        k= Math.min(k, m * n);
        for(int i = 0; i<Math.min(m, k); i++) {
            pq.offer(new int[]{i, 0});
        }
                
        
        int[] res =new int[k];
        int id = 0;
        while(k-- >0 && !pq.isEmpty()) {
            int[] e = pq.poll();
            int i =e[0], j = e[1];
            res[id++] = A[i] + B[j];
            if(j < n-1) pq.offer(new int[]{i, j+1});
        }
        
        return res;
    }
}
