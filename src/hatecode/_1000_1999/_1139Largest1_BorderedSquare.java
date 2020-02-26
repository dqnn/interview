package hatecode._1000_1999;
public class _1139Largest1_BorderedSquare {
/*
1139. Largest 1-Bordered Square
764. Largest Plus Sign
Given a 2D grid of 0s and 1s, return the number of elements in the largest square subgrid that has all 1s on its border, or 0 if such a subgrid doesn't exist in the grid.

 

Example 1:

Input: grid = [[1,1,1],[1,0,1],[1,1,1]]
Output: 9
*/
    //thinking process: O(mn)/O(mn)
    
    //so the problem to say given 2D matrix, find the max area where the square
    //border all 1, only 1 or 0 for each cell
    
    //we use preSum to calculate the sum
    public int largest1BorderedSquare(int[][] m) {
        if (m == null || m.length < 1 || m[0].length < 1) return 0;
        
        int r = m.length, c = m[0].length;
        int[][] preSumU2D = new int[r+1][c+1];
        int[][] preSumL2R = new int[r+1][c+1];
        //we sum up to down, 1 + 1, 
        for(int j = 0;j<c;j++){
            for(int i = 0;i<r;i++){
                preSumU2D[i+1][j+1] = preSumU2D[i][j+1] + m[i][j];
            }
        }
        //we sum from left to right
        for(int i = 0;i<r;i++){
            for(int j = 0;j<c;j++){
                preSumL2R[i+1][j+1] = preSumL2R[i+1][j] + m[i][j];
            }
        }
        /*
         * so here we would like to use two presum 2D array to calculate the largest square, 
         * the square must be begin within len, because if exceeds len then we cannot form a square, 
         * we do not need to check one by one, we just need to check the sub array sum, 
         * 
         */
        int len = Math.min(r,c);
        while(len > 0){
            for(int i = 0;i<=r-len;i++){
                for(int j = 0;j<=c-len;j++){
                    if(preSumL2R[i+1][j+1+len-1] - preSumL2R[i+1][j] != len) continue;//check top side
                    if(preSumL2R[i+1+len-1][j+1+len-1] - preSumL2R[i+1+len-1][j] != len) continue;//check bottom side
                    if(preSumU2D[i+1+len-1][j+1] - preSumU2D[i][j+1] != len) continue;//check left side
                    if(preSumU2D[i+1+len-1][j+1+len-1] - preSumU2D[i][j+1+len-1] != len) continue;//check right side
                    return len*len;
                }
            }
            len--;
        }
        return 0;
    }
    
    public int largest1BorderedSquare_Best_Perf(int[][] grid) {
        int max = 0;
        int r = grid.length;
        int c = grid[0].length;
        int hor[][] = new int[r][c];
        int ver[][] = new int[r][c];
        hor[0][0] = ver[0][0] = 1;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 0)
                    ver[i][j] = hor[i][j] = 0;
                else {
                    hor[i][j] = (j == 0) ? 1 : hor[i][j - 1] + 1;
                    ver[i][j] = (i == 0) ? 1 : ver[i - 1][j] + 1;
                    max = 1;
                }
            }
        }

        for (int i = r - 1; i >= 1; i--) {
            for (int j = c - 1; j >= 1; j--) {
                int small = Math.min(hor[i][j], ver[i][j]);

                while (small > max) {
                    if (ver[i][j - small + 1] >= small && hor[i - small + 1][j] >= small) {
                        max = small;
                    }
                    small--;
                }
            }
        }
        return max * max;
    }
}