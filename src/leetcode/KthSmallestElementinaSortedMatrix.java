package leetcode;

import java.util.PriorityQueue;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : KthSmallestElementinaSortedMatrix
 * Creator : duqiang
 * Date : Jan, 2018
 * Description : 378. Kth Smallest Element in a Sorted Matrix
 */
public class KthSmallestElementinaSortedMatrix {

    /**
Given a n x n matrix where each of the rows and columns are sorted in ascending order, 
find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.

     min = 1 max = 15 mid = 8

     1, PriorityQueue : 链表
     2, Binary Search : 数个数

     * @param matrix
     * @param k
     * @return
     */

    // time : (nlogn) space : O(n)
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Tuple> pq = new PriorityQueue<>(matrix.length, (a, b) -> (a.val - b.val));
        for (int i = 0; i < matrix.length; i++) {
            pq.offer(new Tuple(0, i, matrix[0][i]));
        }
        for (int i = 0; i < k - 1; i++) {
            Tuple tuple = pq.poll();
            if (tuple.x == matrix.length - 1) continue;
            pq.offer(new Tuple(tuple.x + 1, tuple.y, matrix[tuple.x + 1][tuple.y]));
        }
        return pq.poll().val;
    }

    class Tuple {
        int x, y, val;
        public Tuple(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

    // time : O(n * log(max - min)) space : O(1)
    public int kthSmallest2(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        while (left + 1 < right) {
            int mid = (right - left) / 2 + left;
            int num = count(matrix, mid);
            if (num >= k) right = mid;
            else left = mid;
        }
        if (count(matrix, right) <= k - 1) return right;
        return left;
    }

    private int count(int[][] matrix, int target) {
        int n = matrix.length;
        int res = 0;
        int i = n - 1, j = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] < target) {
                res += i + 1;
                j++;
            } else i--;
        }
        return res;
    }
    
    public int kthSmallest3(int[][] matrix, int k) {
        int n = matrix.length;
        int lo = matrix[0][0], hi = matrix[n - 1][n - 1];
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int count = getLessEqual(matrix, mid);
            if (count < k) lo = mid + 1;
            else hi = mid - 1;
        }
        return lo;
    }
    
    private int getLessEqual(int[][] matrix, int val) {
        int res = 0;
        int n = matrix.length, i = n - 1, j = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] > val) i--;
            else {
                res += i + 1;
                j++;
            }
        }
        return res;
    }
    /*
     * It's O(n) where n is the number of rows (and columns), not the number of elements. So it's very efficient. 
     * The algorithm is from the paper Selection in X + Y and matrices with sorted rows and columns, 
     * which I first saw mentioned by @elmirap (thanks).

The basic idea: Consider the submatrix you get by removing every second row and every second column. 
This has about a quarter of the elements of the original matrix. And the k-th element (k-th smallest I mean) 
of the original matrix is roughly the (k/4)-th element of the submatrix. So roughly get the (k/4)-th element 
of the submatrix and then use that to find the k-th element of the original matrix in O(n) time. It's recursive, 
going down to smaller and smaller submatrices until a trivial 2×2 matrix. For more details I suggest checking out 
the paper, the first half is easy to read and explains things well. Or @zhiqing_xiao's solution+explanation.

Cool: It uses variants of saddleback search that you might know for example from the Search a 2D Matrix II problem. 
And it uses the median of medians algorithm for linear-time selection.

Optimization: If k is less than n, we only need to consider the top-left k×k matrix. Similar if k is almost n2. 
So it's even O(min(n, k, n^2-k)), I just didn't mention that in the title because I wanted to keep 
it simple and because those few very small or very large k are unlikely, most of the time k will be "medium" 
(and average n2/2).

Implementation: I implemented the submatrix by using an index list through which the actual matrix data gets accessed. 
If [0, 1, 2, ..., n-1] is the index list of the original matrix, then [0, 2, 4, ...] 
is the index list of the submatrix and [0, 4, 8, ...] is the index list of the subsubmatrix and so on. 
This also covers the above optimization by starting with [0, 1, 2, ..., k-1] when applicable.

Application: I believe it can be used to easily solve the Find K Pairs with Smallest Sums 
problem in time O(k) instead of O(k log n), which I think is the best posted so far. I might try that later 
if nobody beats me to it (if you do, let me know :-). Update: I did that now.
     */
    
}
