package hatecode;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : NQueensII
 * Creator : duqiang
 * Date : Nov, 2017
 * Description : 52. N-Queens II
 */
public class NQueensII {
    /**
     * follow up: Given an integer n, return the number of distinct solutions to the n-queens puzzle.
     *

     time : O(n^n)
     space : O(n)
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],
 
 [1,3,0,2] means 1 combination
     * @param n
     * @return
     */

    int res = 0;
    // why this is shorter since we only requires return integer
    //Start row by row, and loop through columns. At each decision point, 
    //skip unsafe positions by using three boolean arrays.
    
/*常规n-queens解法, 数答案个数.
    用column标记此行之前的哪些column已经放置了queen. 棋盘坐标(row, col)对应column的第col位(LSB --> MSB, 下同).
    用diag标记此位置之前的哪些主对角线已经放置了queen. 棋盘坐标(row, col)对应diag的第(n - 1 + row - col)位.
    用antiDiag标记此位置之前的哪些副对角线已经放置了queen. 棋盘坐标(row, col)对应antiDiag的第(row + col)位.
    Start row by row, and loop through columns. 
    At each decision point, skip unsafe positions by using three boolean arrays.
*/
    public int totalNQueens(int n) {
        boolean[] cols = new boolean[n];// columns   |
        // we use one 2 * n dimension to store the 2D data
        boolean[] d1 = new boolean[2 * n]; // top left -> bottom right dialog
     // we use one 2 * n dimension to store the 2D data
        boolean[] d2 = new boolean[2 * n]; // top right -> bottom left, anti-dialogue
        helper(0, cols, d1, d2, n);
        return res;
    }
    //back tracking 
    public void helper(int row, boolean[] cols, boolean[] d1, boolean[] d2, int n) {
        // last row
        if (row == n) {
            res++;
            return;
        }
        for (int col = 0; col < n; col++) {
            // dialgue i -j = 0 always, dialgue here is not only the longest one but also include others
            int id1 = col - row + n;
            //if it is anti-dialogue, i + j = n - 1, always
            int id2 = col + row;
            if (cols[col] || d1[id1] || d2[id2]) continue;

            cols[col] = true; d1[id1] = true; d2[id2] = true;
            helper(row + 1, cols, d1, d2, n);
            cols[col] = false; 
            d1[id1] = false; 
            d2[id2] = false;
        }
    }
}
