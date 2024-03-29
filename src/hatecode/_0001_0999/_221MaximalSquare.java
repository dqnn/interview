package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MaximalSquare
 * Creator : professorX
 * Date : Aug, 2018
 * Description : 
 * 221. Maximal Square
 * 1139. Largest 1-Bordered Square
 */
public class _221MaximalSquare {

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
also this is the reason we want to start with (1,1)


how to understand the min of other 3 values, dp[i-1][j], dp[i-1][j-1], dp[i][j-1]
    
      3    4      
1.    4    ？   


      4    2
2.    3    ？


      3    4
3.    1    ？


for 1， we will see ? will be constrained by 3,  
for 2, we will see ? will be constrained by 2 
for 3, we will see ? will be constrained by 1
worst case is that itself is 1 
    
   
   
  */
    //O(mn)/O(mn)
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return 0;
        
        int r = matrix.length, c = matrix[0].length;
        int res = 0;
        //why the len = r+ 1 and c+1 because we do not want trivial code
        int[][] dp = new int[r + 1][c + 1];
        //we start from (1,1)
        for(int i =1; i <= r; i++) {
            for(int j = 1; j<=c;j++) {
                if (matrix[i-1][j-1] == '1') {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i][j-1], dp[i-1][j-1]), 
                                            dp[i-1][j]);
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res * res;
    }
    
    //here is the same solution but space complexity reduce to O(n), O(mn)/O(n)
    public int maximalSquare2(char[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return 0;
        
        int r = matrix.length, c = r > 0 ? matrix[0].length : 0;
        int[] dp = new int[c + 1];
        //pre = old dp[i-1]
        int maxsqlen = 0, prev = 0;
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
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
