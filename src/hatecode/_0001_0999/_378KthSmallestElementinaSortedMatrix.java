package hatecode._0001_0999;

import java.util.PriorityQueue;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : KthSmallestElementinaSortedMatrix
 * Creator : professorX
 * Date : Jan, 2018
 * Description : 378. Kth Smallest Element in a Sorted Matrix
 */
public class _378KthSmallestElementinaSortedMatrix {

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
    // the scan is: first rwo, then from each element 
    //we scan column by column until we find the element
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length < 1 || k < 1) {
            return -1;
        }
        //using lambda to as comparator, first parameter is the length, this is optimization
        PriorityQueue<Tuple> pq = new PriorityQueue<>(matrix.length, (a, b) -> (a.val - b.val));
        // we add first row first and sorted， this is nxn, so we 
        //used m.length to replace m[0].length
        for (int i = 0; i < matrix.length; i++) {
            pq.offer(new Tuple(0, i, matrix[0][i]));
        }
        // why we use k - 1, max of i is k - 2, 
        // we want to have kth smallest number in matrix, we want to make change to k - 1 elements，so no 
        // matter how many we put previously, we will process k - 1 elments in this loop and make k
        // at the front of the queue
        for (int i = 0; i < k - 1; i++) {
            Tuple tuple = pq.poll();
            //if the column is last elment, then w skip
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
    // The key point for any binary search is to figure out the "Search Space". 
    //For me, I think there are two kind of "Search Space" -- 
    //index and range(the range from the smallest number to the biggest number). 
    //Most usually, when the array is sorted in one direction, we can use 
    //index as "search space", when the array is unsorted and we are going to 
    //find a specific number, we can use "range".

//Let me give you two examples of these two "search space"

//index -- A bunch of examples -- https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/ 
    //( the array is sorted)
//range -- https://leetcode.com/problems/find-the-duplicate-number/ (Unsorted Array)
// The reason why we did not use index as "search space" for this problem is the matrix is sorted in two directions,
    //we can not find a linear way to map the number and its index.
/* 
 * l         m          r 
 * ---------------------
 * |.count().|
 * 
 * count() is smaller than m, because l= 0, r =n - 1, m should be more to l compared to r, 
 * 1  5  9
 * 10 11 13
 * 12 13 15
 * 
 * 1st round: l = 1, r = 15,  m = 8, count(A, 8) = 2, l = 8,
 * 2st round:  l=8, r = 15, m =12, count(A, 12) = 5, 
 * 3rd round: l = 12,r=15, m =13, count(A,13) = 6, 
 * 4rd round: l = 13, r=15, m=14, count(A,14) = 8,
 * if num > k = 8, then we will miss the correct answer
 * 
 */
    /*thinking process: O((m +n) * logD), D = max(m) - min(m)
    //the problem is to find the k-the smallest element. 
    
    //we use binary search to find the element, search space is r = max(m), l =min(m);
    
    //we want to find an integer m, which can make count(m)==k, M[i][j] <=m, there maybe duplicate 
    //elements in the matrix, but the count must <= k.
    
    why we count elements < m in the matrix? 
    
    */
    public int kthSmallest2(int[][] matrix, int k) {
        int n = matrix.length;
        int l = matrix[0][0];
        // asc by row and column, so we can conclude that m[0][0] is min m[r][c] is max
        int r = matrix[n - 1][n - 1];
        // left + 1 ? 
        while (l + 1 < r) {
            int m = (r - l) / 2 + l;
            int num = countLessThanM(matrix, m);
            //we want prioritize right side boundary
            if (num >= k) r = m;
            else l = m;
        }
        if (countLessThanM(matrix, r) <= k - 1) return r;
        return l;
    }
    // count how many elements smaller than target
    //we can scan row by row but that would be slower then column
    private int countLessThanM(int[][] matrix, int target) {
        int n = matrix.length;
        int res = 0;
        int i = n - 1, j = 0;
        while (i >= 0 && j < n) {
            // we scan the matrix from bottom to up, from n-1 --> 0
            // for column
            if (matrix[i][j] < target) {
                res += i + 1; // i + 1 means how many elements each column, this will decide where i is
                //scan horizon
                j++; // we move to right on same row
            } else i--; // which means we jump to row above
        }
        return res;
    }
    
    // another binary search solutions, embedded while in for loop to find how 
    //many numbers before mid
    public int kthSmallest4(int[][] matrix, int k) {
        // num of rows and cols in matrix
        int rows = matrix.length, cols = matrix[0].length;
        // get the lowest and highest possible num, will shrink search space according to the two nums
        // [lo, hi] is our initial search range
        int lo = matrix[0][0], hi = matrix[rows - 1][cols - 1] ;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int count = 0,  maxNum = lo;
            // for each row, we r going to find # of nums < mid in that row
            for (int r = 0, c = cols - 1; r < rows; r++) {
                while (c >= 0 && matrix[r][c] > mid) c--;   // this row's c has to be smaller than the c found in last row due to the sorted property of the matrix 
                if (c >= 0) {
                    count += (c + 1); // count of nums <= mid in matrix
                    maxNum = Math.max(maxNum, matrix[r][c]); // mid might be value not in matrix, we need to record the actually max num;
                }
            }
            // adjust search range
            if (count == k) return maxNum;
            else if (count < k) lo = mid + 1;
            else hi = mid - 1;
        }
        // 1) Q: Why we return lo at the end:
        //    A: Here lo=hi+1, for hi, we found <k elems, 
        //   for lo, we found >=k elem, lo must have duplicates 
        //    in matrix, return lo
        // 2) Q: Why lo exist in the matrix
        //    A: for lo which is only 1 more than hi, 
        //we could find some extra nums in matrix so that 
        //there r >=k elems, so lo it self must exist in the 
        // matrix to meet the requirement
        return lo;
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
