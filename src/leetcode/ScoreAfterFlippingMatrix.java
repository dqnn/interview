package leetcode;

/*
 * 861. Score After Flipping Matrix
 * We have a two dimensional matrix A where each value is 0 or 1.

A move consists of choosing any row or column, and toggling each value in that row or column: changing all 0s to 1s, and all 1s to 0s.

After making any number of moves, every row of this matrix is interpreted as a binary number, and the score of the matrix is the sum of these numbers.

Return the highest possible score.

 

Example 1:

Input: [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
Output: 39
Explanation:
Toggled to [[1,1,1,1],[1,0,0,1],[1,1,1,1]].
0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
 

Note:

1 <= A.length <= 20
1 <= A[0].length <= 20
A[i][j] is 0 or 1.
 */
class ScoreAfterFlippingMatrix {

    /*
     * time O(m*n) space O(1) Greedy, we find first column we have to flip the rwo
     * which not start from 0, then for each column, we need to flip which sum(1) <
     * sum(0), then sum each row
     */
    public int matrixScore(int[][] A) {
        //edge case
        if (A == null || A.length < 1) {
            return 0;
        }
        
        int r = A.length, c = A[0].length;
        // 0 column visit
        for(int i = 0; i < r; i++) {
            if(A[i][0] == 0) {
                int j = 0;
                // row mode operations
                while (j < c) {
                    A[i][j] = A[i][j] == 0 ? 1: 0;
                    j++;
                }
            }
        }
        
        // from coloumn 1 to end
        
        for(int j = 1; j < c; j++) {
            int sum = 0;
            for(int i = 0; i < r; i++) {
                if (A[i][j] == 1) {
                    sum += 1;
                }
            }
            if (sum < (r - sum)) {
                int k = 0;
                while (k < r) {
                    A[k][j] = A[k][j] == 0 ? 1 :0;
                    k++;
                }
            }
        }
        
        int res = 0;
        for(int i = 0; i < r; i++) {
            int rSum = 0;
            for (int j = 0; j < c; j++) {
                rSum = rSum * 2 + A[i][j]; 
            }
            res += rSum;
        }
        
        return res;  
    }
}