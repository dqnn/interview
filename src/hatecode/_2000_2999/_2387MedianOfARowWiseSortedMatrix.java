package hatecode._2000_2999;

import java.util.PriorityQueue;

public class _2387MedianOfARowWiseSortedMatrix {
/*
2387. Median of a Row Wise Sorted Matrix
Given an m x n matrix grid containing an odd number of integers where each row is sorted in non-decreasing order, return the median of the matrix.

You must solve the problem in less than O(m * n) time complexity.

 

Example 1:

Input: grid = 
[ 
  [1,1,2],
  [2,3,3],
  [1,3,4]
]
Output: 2
Explanation: The elements of the matrix in sorted order are 1,1,1,2,2,3,3,3,4. The median is 2.
Example 2:

Input: grid = [[1,1,3,3,4]]
Output: 3
Explanation: The elements of the matrix in sorted order are 1,1,3,3,4. The median is 3.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 500
m and n are both odd.
1 <= grid[i][j] <= 106
*/
    
    /*
    interview friendly, 
    thinking process: O(m * lgn)/O(1)
    the problem si to say: given one matrix, sorted by row, find the median element of the matrix.
   
    since each element is in [1, 1e6] range, so we can guess one in that range and count how many 
    elements are <= than the guess number, 
    1. if it is greater than m *n/2+1 (include the median number),
    then guess number is too big, we should move r to left.
    2. if it is smaller than m*n/2+1, then guess number 

    */
    public int matrixMedian(int[][] A) {
    
        int m = A.length, n= A[0].length;
        int l = 1, r = (int) 1e6;
        int lelementsCnt = m * n / 2 + 1;
        
        while(l < r) {
            int mid = l + (r - l)/ 2;
            int count = helper(A, mid);
            //System.out.println("count--mid=" + count+ "---" + mid);
            
            if (lelementsCnt <= count) r = mid;
            else l = mid + 1;
        }
        
        return l;
    
    }
    
    private int helper(int[][] A, int t) {
        int m = A.length, n= A[0].length;
        int count = 0;
        
        for(int i = 0; i< m; i++) {
            int l = 0, r = n-1;
            while(l < r) {
                int mid = l+ (r - l)/2;
                if (A[i][mid] > t) r = mid;
                else l = mid+1;
            }
            //System.out.println("l --r=" + l +"----" + r);
            if (A[i][l] <= t) count += l + 1;
            else count += l;
        }
        
        return count;
    }
    
    public int matrixMedian_BF(int[][] A) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->(a[0] -b[0]));
        
        int r = A.length, c = A[0].length;
        
        for(int i = 0; i< r; i++) pq.offer(new int[]{A[i][0], i, 0});
        
        int last = 0, count = 0;
        
        while(!pq.isEmpty()) {
            if(count == r * c/2 + 1) return last;
            int[] e = pq.poll();
            last = e[0];
            count++;
            if (e[2] != c -1) {
                pq.offer(new int[]{A[e[1]][e[2] + 1], e[1], e[2] + 1});
            }
        }
            
         return -1;
        }
}