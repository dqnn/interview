package companies;

import java.util.Arrays;

public class _GoogleInterviewTopLeftToRightBottomLongestPath {

    /*
     * given a 2D matrix, 0 means empty, 1 is obstache, you can move
     * left, down or right, in one arrow, if you turn left, you have to always 
     * left, same for right.
     * 
     * return the minimal steps which you can move first row to last row.
     */
    
    //thinking process: O(n^2)/O(n^2)
    
    //given a 2D matrix with 0 and 1, 1 is obstacle, return max 
    //steps from top left to right bottom,
    
    //dp[i][j][3] means on point (i,j), the max steps from UP(0), left(1) and 
    //right[2]. 
    public static int getMaxSteps(int[][] g) {
        
        int r = g.length, c=g[0].length;
        
        //dp[i][j][0] from up, dp[i][j][1] from left,
        //dp[i][j][2] from right
        int[][][] dp = new int[r][c][3];
        
        //initialize dp
        for(int j = 0; j < c;j++) {
            if (g[0][j] == 1) continue;

            dp[0][j][1]= j > 0 ? dp[0][j-1][1] + 1 : 1;
            dp[0][j][0]=Math.max(dp[0][j][0], j > 0 ? dp[0][j-1][1] + 1 : 1);
        }
        
        for(int j = c-1; j >=0;j--) {
            if (g[0][j] == 1) continue;
            
            dp[0][j][2]= j !=c-1 ? dp[0][j+1][2] + 1 : 1;
            dp[0][j][0]=Math.max(dp[0][j][0], j !=c-1 ? dp[0][j+1][2] + 1 : 1);
        }

        for(int i = 1; i< r;i++) {
            for(int j =0; j<c; j++) {
                if (g[i][j] == 1) continue;
                
                //update from up
                if (g[i-1][j] == 0 && dp[i-1][j][0] > 0) {
                    dp[i][j][0] = Math.max(dp[i-1][j][0] + 1, dp[i][j][0]);
                    dp[i][j][1] = Math.max(dp[i-1][j][0] + 1, dp[i][j][1]);
                    dp[i][j][2] = Math.max(dp[i-1][j][0] + 1, dp[i][j][2]);
                }
                
                //updates from left
                if (j > 0 && g[i][j-1] == 0 && dp[i][j-1][1] > 0) {
                    //dp[i][j][1] maybe updated from up
                    dp[i][j][1] = Math.max(dp[i][j-1][1] + 1, dp[i][j][1]);
                    //get max
                    dp[i][j][0] = Math.max(dp[i][j][0], dp[i][j][1]);
                }
                
                System.out.println(Arrays.deepToString(dp));
            }
            
            for(int j =c-1; j>=0; j--) {
              if (g[i][j] == 1) continue;
              //updates from right
              if (j < c-1 && g[i][j+1] == 0 && dp[i][j+1][2] > 0) {
                    //dp[i][j][2] maybe updated from up
                    dp[i][j][2] = Math.max(dp[i][j+1][2] + 1, dp[i][j][2]);
                    //get max
                    dp[i][j][0] = Math.max(dp[i][j][0], dp[i][j][2]);
              }
            }
            //System.out.println(Arrays.deepToString(dp));
        }
        
        
        
        int res = 0;
        for(int j = 0;  j< c;j++) {
            for(int k = 0; k<3; k++) {
                res = Math.max(res, dp[r-1][j][k]);
            }
        }
        
        return res;
    }
    
    
    
    public static void main(String[] args) {
        int[][] in = {{1,1,0,0,1,0}, {1,1,1,0,0,0}, {0,0,0,0,0,0}};
        System.out.println(getMaxSteps(in));

    }

}
