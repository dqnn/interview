package leetcode;

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
     *

     time : O(n^n)
     space : O(n)

     * @param n
     * @return
     */

    int res = 0;
    // why this is shorter since we only requires return integer
    //Start row by row, and loop through columns. At each decision point, 
    //skip unsafe positions by using three boolean arrays.
    public int totalNQueens(int n) {
        boolean[] cols = new boolean[n];// columns   |
        boolean[] d1 = new boolean[2 * n]; // diagonals \
        boolean[] d2 = new boolean[2 * n]; // diagonals /
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
            int id1 = col - row + n;
            int id2 = col + row;
            if (cols[col] || d1[id1] || d2[id2]) continue;

            cols[col] = true; d1[id1] = true; d2[id2] = true;
            helper(row + 1, cols, d1, d2, n);
            cols[col] = false; d1[id1] = false; d2[id2] = false;
        }
    }
}
