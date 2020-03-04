package hatecode._0001_0999;

import java.util.*;
public class _519RandomFlipMatrix {
/*
519. Random Flip Matrix
You are given the number of rows n_rows and number of columns n_cols of a 2D binary matrix where all values are initially 0. Write a function flip which chooses a 0 value uniformly at random, changes it to 1, and then returns the position [row.id, col.id] of that value. Also, write a function reset which sets all values back to 0. Try to minimize the number of calls to system's Math.random() and optimize the time and space complexity.

Note:

1 <= n_rows, n_cols <= 10000
0 <= row.id < n_rows and 0 <= col.id < n_cols
flip will not be called when the matrix has no 0 values left.
the total number of calls to flip and reset will not exceed 1000.
Example 1:

Input: 
["Solution","flip","flip","flip","flip"]
[[2,3],[],[],[],[]]
Output: [null,[0,1],[1,2],[1,0],[1,1]]
*/
    //simple solution, but the way how we remember the changed value is awesome, the improvement on the 
    //space complexity is cool, another key note is the how we use nextInt
    Map<Integer, Integer> map;
    int rows, cols, total;
    Random rand;
    
    public _519RandomFlipMatrix(int n_rows, int n_cols) {
        map = new HashMap<>();
        rand = new Random();
        rows = n_rows; 
        cols = n_cols; 
        total = rows * cols;
    }
    
    public int[] flip() {
        //we would not flip previous point
        int r = rand.nextInt(total--);
        int x = map.getOrDefault(r, r);
        map.put(r, map.getOrDefault(total, total));
        return new int[]{x / cols, x % cols};
    }
    
    public void reset() {
        map.clear();
        total = rows * cols;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(n_rows, n_cols);
 * int[] param_1 = obj.flip();
 * obj.reset();
 */