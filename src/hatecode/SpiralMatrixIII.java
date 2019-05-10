package hatecode;
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
    /*
The idea here is that once we start at (r=r0, c=c0), we walk along the east, then south, then west, and then north.

When we go east, we do c++ (column increases), when we go west, we do c--, when we go south, we do r++ (row increases), and when we go north, we do r--.

After starting at (r0,c0), we need to walk in spirals, where the length of the spiral increases after every two directions. For example 2, we start at (r0=1, c0=4), then we go east by one length, we go south by one length. Following that, we go west by 2 length and then, go north by 2 length. After that, we go in next directions by 3 lengths, and so on.

The trick here is that we continue to walk in spiral, whether the current (r,c) is valid or not. However, we add (r,c) to the result only if it is valid.
    */
    //thinking processs: 
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