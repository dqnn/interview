package hatecode;

import java.util.*;
public class _1072FlipColumnsForMaximumNumberOfEqualRows {
/*
1072. Flip Columns For Maximum Number of Equal Rows
Given a matrix consisting of 0s and 1s, we may choose any number 
of columns in the matrix and flip every cell in that column.  
Flipping a cell changes the value of that cell from 0 to 1 or 
from 1 to 0.

Return the maximum number of rows that have all values equal 
after some number of flips.

 

Example 1:

Input: [[0,1],[1,1]]
Output: 1
*/
    //thinking process:O(r(c + 1) + r)O(rc)
    //given a matrix, only 1 or 0 for each cell, then we flip each
    //column, no matter how many times flip, return max row count which
    //has all same value
    
    //each cell only has 1 or 0, so for two rows, at some columns 
    //they must change at the same time, 
/* change 1-3 column
 *  0001 --->  1111
 *  1110 --->  0000
 *  so we can know these two has same pattern,use hashmap to store 
 *  all the possibilities
 */
    public int maxEqualRowsAfterFlips(int[][] m) {
        if(m == null || m.length < 1 || m[0].length < 1) return 0;
        Map<String, Integer> map = new HashMap<>();
        
        for(int[] rows : m) {
            StringBuilder sb1 = new StringBuilder(), sb2 = new StringBuilder();
            for(int r: rows) {
                sb1.append(r);
                sb2.append(1-r);
            }
            String key1 = sb1.toString(), key2 = sb2.toString();
            map.put(key1, map.getOrDefault(key1, 0) + 1);
            map.put(key2, map.getOrDefault(key2, 0) + 1);
        }
        int res = 0;
        for(int r: map.values()) res = Math.max(res, r);
        
        return res;
    }
    
    public int maxEqualRowsAfterFlips2(int[][] matrix) {
        if(matrix == null || matrix.length < 1 || matrix[0].length < 1) return 0;
        int res = 0;
        int r = matrix.length, c = matrix[0].length;
        int[] flip = new int[c];
        for(int i = 0; i < r; i++) {
            int cnt = 0;
            for(int j = 0; j < c; j++) flip[j] = 1 - matrix[i][j];
            for(int k = 0; k < r; k++) {
                if(Arrays.equals(matrix[k], matrix[i]) || Arrays.equals(matrix[k], flip)) cnt++;
            }
            res = Math.max(res, cnt);
        }
        return res;
    }
    
    public int maxEqualRowsAfterFlips_Fastest(int[][] matrix) {
        int rows = matrix.length;
        int len = matrix[0].length;
        
        int mask = (1 << len) - 1;
        
        Map<Integer, Integer> f = new HashMap<>();
        
        for (int r = 0; r < rows; r += 1) {
            int n = 0;
            for (int d = 0; d < len; d += 1) {
                n = (n << 1) | matrix[r][d];
            }
            n = Math.min(n, (~n) & mask);
            f.put(n, f.getOrDefault(n, 0) + 1);
        }
        
        int max = 0;
        for (int n : f.values()) if (n > max) max = n;
        
        return max;
    }
}