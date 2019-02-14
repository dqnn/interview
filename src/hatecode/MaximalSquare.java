package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MaximalSquare
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : 221. Maximal Square
 */
public class MaximalSquare {

    /**
     * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

     For example, given the following matrix:

     1 0 1 0 0
     1 0 1 1 1
     1 1 1 1 1
     1 0 0 1 0
     Return 4.

     time : O(m * n)
     space : O(m * n)

     * @param matrix
     * @return
     */
    //thinking process:
    //given a char matrix, '0' and '1' find all '1' square area
    
    //so if we know the square length then we know its area, so we would like to know how many 
    //'1's there, for dp we have several steps. subproblems/result, formual, initilize, compute
    
    //dp[i][j] means square as (i,j) as bottom right coordination, so given 
    //matrix, the max square in matrix is dp[r-1][c-1], 
 /*
 understand the formula
Square ending at i,j is intersection of

Square ending at i-1,j-1
Square ending at i-1,j
Square ending at i,j-1
So maximum size of square ending at i,j will be minimum of the above three squares plus 1
  */
    //O(mn)/O(mn)
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return 0;
        
        int r = matrix.length, c = matrix[0].length;
        int res = 0;
        int[][] dp = new int[r + 1][c + 1];
        //we start from (1,1)
        for(int i =1; i <= r; i++) {
            for(int j = 1; j<=c;j++) {
                if (matrix[i-1][j-1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i][j-1], dp[i-1][j-1]), dp[i-1][j]) + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res * res;
    }
    
    //here is the same solution but space complexity reduce to O(n)
    public int maximalSquare2(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[] dp = new int[cols + 1];
        int maxsqlen = 0, prev = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                int temp = dp[j];
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }
        return maxsqlen * maxsqlen;
    }
}
