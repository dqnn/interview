package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : RangeSumQuery2DMutable
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 308. Range Sum Query 2D - Mutable
 */

/*
Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper 
left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), 
which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
update(3, 2, 2)
sumRegion(2, 1, 4, 3) -> 10
Note:
The matrix is only modifiable by the update function.
You may assume the number of calls to update and sumRegion function is distributed evenly.
You may assume that row1 ≤ row2 and col1 ≤ col2.
 */
public class RangeSumQuery2DMutable {

    int[][] tree;
    int[][] nums;
    int m;
    int n;

    // time : O(m * logm * n * logn)
    public RangeSumQuery2DMutable(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        m = matrix.length;
        n = matrix[0].length;
        tree = new int[m + 1][n + 1];
        nums = new int[m][n];
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j++) {
                update(i, j, matrix[i][j]);
            }
        }
    }

    // time : O(logm * logn)
    //this method is the key of the solution
/*
                      0
              /    /    \        \
            1     2     4         8
                 /    /   \    /  |   \
                3     5    6  9   10   12 
                           |      |
                           7      11
this is the index tree example, index is from 0- 12
parent(i) = i - (i & (-i))
so we always reserve first element in tree as the root(0) above, when tree initialized, all values are 
0, so we update each element by climbing the tree
*/
    public void update(int row, int col, int val) {
        if (m == 0 || n == 0) return;
        int diff = val - nums[row][col];
        nums[row][col] = val;
        for (int i = row + 1; i <= m; i += i & (-i)) {
            for (int j = col + 1; j <= n; j += j & (-j)) {
                tree[i][j] += diff;
            }
        }
    }

    // time : O(logm * logn)
    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (m == 0 || n == 0) return 0;
        return sum(row2 + 1, col2 + 1) + sum(row1, col1)
                - sum(row1, col2 + 1) - sum(row2 + 1, col1);
    }

    private int sum(int row, int col) {
        int sum = 0;
        for (int i = row; i > 0; i -= i & (-i)) {
            for (int j = col; j > 0; j -= j & (-j)) {
                sum += tree[i][j];
            }
        }
        return sum;
    }
    public static void main(String[] args) {
        int[][] in = 
               {{3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}};
        RangeSumQuery2DMutable table = new RangeSumQuery2DMutable(in);
        System.out.println(table.sumRegion(2, 1, 4, 3)); //should be 8
        table.update(3,3,2); //should be 8
        System.out.println(table.sumRegion(2, 1, 4, 3)); //should be 10
    }
}
