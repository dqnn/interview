package hatecode._0001_0999;

import java.util.*;
public class SpiralMatrixIII {
/*
885. Spiral Matrix III
On a 2 dimensional grid with R rows and C columns, we start at (r0, c0) facing east.

Here, the north-west corner of the grid is at the first row and column, and the south-east corner of the grid is at the last row and column.

Now, we walk in a clockwise spiral shape to visit every position in this grid. 

Whenever we would move outside the boundary of the grid, we continue our walk outside the grid (but may return to the grid boundary later.) 

Eventually, we reach all R * C spaces of the grid.

Return a list of coordinates representing the positions of the grid in the order they were visited.

 

Example 1:

Input: R = 1, C = 4, r0 = 0, c0 = 0
Output: [[0,0],[0,1],[0,2],[0,3]]
*/
    
    //interview friendly:
    //thinking process:
    //this is pretty elegant code, we run same direction for len steps and we chagne steps after change 2
    //directions
    public int[][] spiralMatrixIII_Best(int R, int C, int r0, int c0) {
        int[][] dirt = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // east, south, west, north
        List<int[]> res = new ArrayList<>();
        int len = 0, d = 0; // move <len> steps in the <d> direction
        res.add(new int[]{r0, c0});
        while (res.size() < R * C) {
            if (d == 0 || d == 2) len++; // when move east or west, the length of path need plus 1 
            for (int i = 0; i < len; i++) {
                r0 += dirt[d][0];
                c0 += dirt[d][1];
                if (r0 >= 0 && r0 < R && c0 >= 0 && c0 < C) // check valid
                    res.add(new int[]{r0, c0});
            }
            d = (d + 1) % 4; // turn to next direction
        }
        return res.toArray(new int[R * C][2]);
    }

    /*thinking process: 
     * The idea here is that once we start at (r=r0, c=c0), we walk along the east,
     * then south, then west, and then north.
     * 
     * When we go east, we do c++ (column increases), when we go west, we do c--,
     * when we go south, we do r++ (row increases), and when we go north, we do r--.
     * 
     * After starting at (r0,c0), we need to walk in spirals, where the length of
     * the spiral increases after every two directions. For example 2, we start at
     * (r0=1, c0=4), then we go east by one length, we go south by one length.
     * Following that, we go west by 2 length and then, go north by 2 length. After
     * that, we go in next directions by 3 lengths, and so on.
     * 
     * The trick here is that we continue to walk in spiral, whether the current
     * (r,c) is valid or not. However, we add (r,c) to the result only if it is
     * valid.
     */
    //
    //1 every circle our len will, realize how we traverse the matrix, 
    //every approach two steps, we will change our direction
    int idx;
    int[][] ret;
    
    private void add (int r, int c, int R, int C) {
        if (r >= R || r < 0 || c >= C || c < 0) return;
        ret[idx][0] = r;
        ret[idx++][1] = c;
    }
    
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        int r = r0, c = c0, len = 1;
        ret = new int[R * C][2];
        while (idx < (R * C )) {         
            for (int k = 0; k < len; k++) add(r, c++, R, C);          
            for (int k = 0; k < len; k++) add(r++, c, R, C);
            len++;
            for (int k = 0; k < len; k++) add(r, c--, R, C);
            for (int k = 0; k < len; k++) add(r--, c, R, C);
            len++;   
        }
        return ret;
    }
}