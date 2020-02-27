package hatecode._0001_0999;
public class _688KnightProbabilityInChessboard {
/*
688. Knight Probability in Chessboard
On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves. The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).

A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.

Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.

The knight continues moving until it has made exactly K moves or has moved off the chessboard. Return the probability that the knight remains on the board after it has stopped moving.

 

Example:

Input: 3, 2, 0, 0
Output: 0.0625
Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
From each of those positions, there are also two moves that will keep the knight on the board.
The total probability the knight stays on the board is 0.0625.
*/
    
    private int[][] dirs = new int[][]{{1, 2}, {2, 1}, {2, -1}, {1, -2}, 
        {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};
    public double knightProbability(int N, int K, int r, int c) {
        double[][][] dp = new double[K + 1][N][N];
        //here is 1, so we random pick it is 0.125, then start next
        dp[0][r][c] = 1;
        for (int step = 1; step <= K; step++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int[] dir : dirs) {
                        int x = dir[0] + i;
                        int y = dir[1] + j;
                        if (x < 0 || x >= N || y < 0 || y >= N) continue;
                        dp[step][i][j] += dp[step - 1][x][y] * 0.125;
                    }
                }
            }
        }
        double res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                res += dp[K][i][j];
            }
        }
        return res;
    }
    //TLE version, use DFS to get in and out numbers
    int out = 0;
    int in = 0;
    public double knightProbability2(int N, int K, int r, int c) {
        if (N < 0 || K < 0) return -1.0;
        
        int[][] b = new int[N][N];
        dfs(b, K + 1, r, c);
        return 1.0 * in/(in + out);
    }
    
    private void dfs(int[][] b, int K, int i, int j) {
        if (i < 0 || j < 0 || i >= b.length || j >= b[0].length) {
            out++;
            return;
        }
        if (K == 0) {
            in++;
            return;
        }
        int[][] dirs = {{-1,-2}, {-2,-1},{-2,1},{-1, 2},
                       {1,2},{2,1},{2,-1},{1,-2}};
        for(int[] dir : dirs){
            int ni = i + dir[0];
            int nj = j + dir[1];
            dfs(b, K, ni,nj);
        }
    }
    
}