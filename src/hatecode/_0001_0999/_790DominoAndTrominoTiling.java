package hatecode._0001_0999;
import java.util.*;
public class _790DominoAndTrominoTiling {
/*
790. Domino and Tromino Tiling
We have two types of tiles: a 2x1 domino shape, and an "L" tromino shape. These shapes may be rotated.

XX  <- domino

XX  <- "L" tromino
X
Example:
Input: 3
Output: 5
Explanation: 
The five different ways are listed below, different letters indicates different tiles:
XYZ XXZ XYY XXY XYY
XYZ YYZ XZZ XYY XXY
*/
    //optimized solution
    
    //this is a serial set of problems, so here is the notes how can get the optimized solution. 
/*
 last tile way:
 T(n) is how many ways of tiling, so for last tile we have 4 cases given 6 shape of tiles as follows
 1. _  
 2. |
 3. L
 4. _|
 5. 7
     _
 6. |   (mirror of 7)
 
 Now if we want to full fill the 2xN space,then last tile must be 1,2,3,6,
 for 1: in this case, the second to last tile ,must be same shape so they fill the space, so the wys 
 will equals to T(n-2)
 2. in this case, all left will fill the 2x(N-1) space, it will be T(n-1)
 6. rest tiles will fill the 2*(N-1) space, let's define: upper will have one more like 
 ---
 |
 --  so we define a new dp array, Tup(n-1)
 4.  same as 6, it will be like, Tdown(n-1) 
 so T(N) = T(N-1) + T(N-2) + T_up(N-1) + T_down(N-1)
 
 //T_up means we have 
 T_up(N) = T_down(N-1) + T(N-2)
 
 T_down(N-1) = T_up(N-2) + T(N-3)
 
 T(N) = T(N-1) + T(N-2) + T_down(N-2) + T(N-3) + T_up(N-2) + T(N-3)
T(N) = T(N-1) + T(N-3) + [T(N-2) + T(N-3) + T_up(N-2) + T_down(N-2)]
T(N) = 2 * T(N-1) + T(N-3).
 */
    public int numTilings_Optimized(int N) {
       if (N < 0) return 0;
        if (N <= 2) return N;
        long[] dp = new long[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        int mod = (int) 1e9 + 7;
        for (int i = 3; i <= N; i++) {
            dp[i] = (2 * dp[i - 1] + dp[i- 3]) % mod;
        }
        return (int) dp[N];
    }
/*
this is easy to understand while highe complexity
let;s 2XN space is like 
i -
j --
dp[i][j] means for two rows, i, j means the filled columns. 
Now when i==j, we have 4 choices to put a tile.

Put 1 tile vertically. So this will change (i,j) to (i+1,j+1). 
Because with putting one vertical tile, we will cover 1 extra column.
Put 2 tiles horizontally. We can’t put only 1 tile horizontally, 
it will increase the |i-j| dist by 2, which can only be filled by a 2x1 tile horizontally.
 (Bit confusing, but think through.)
Put 1 L shaped tile. (i,j) -> (i,j+1)
Put 1 flipping L shaped tile upside down. (i,j) -> (i+1,j)
Now when |i-j|==1, we have 2 choice. 

We put one L shaped domino. (i+1,j) -> (i+2, j+2) or (i,j+1) -> (i+2,j+2)
We put one horizontal 2x1 tile. (i+1,j) -> (i+1,j+2) or (i,j+1) -> (i+2,j+1)
If |i-j|>=2, this is an invalid solution. In this case there is no way we can fill the floor with tiles. Remember, in our recursion condition we’re ensuring we won’t reach this state where |i-j|>=2.


Base case:
if i>N or j>N : count is 0, it would be an invalid state.
if i==N or j==N : count will be increased by 1. 
 */
    public int numTilings_Recursive(int N) {
        if (N < 0) return 0;
        if (N <=2)return N;
        int[][] dp = new int[N+1][N+1];
        for(int[] arr: dp) Arrays.fill(arr, -1);
        
        
        return helper(0,0,N, dp);
        
    }
    long M = (long)1e9 + 7;
    private int helper(int i, int j, int N, int[][] dp) {
        if (i == N && j == N) return 1;
        if (i > N || j > N) return 0;
        
        if(dp[i][j]!=-1) return dp[i][j];
        //this has to be double since it will overflow for integer
        long res = 0;
        
        if (i == j) {
            //| style 
            res += helper(i+1, j+1, N, dp);
            // horizonte style
            res += helper(i+2,j+2,N,dp);
            //
            res += helper(i+2, j+1, N, dp);
            //
            res += helper(i+1, j+2, N,dp);
        } else if (i -j ==1) {
            res += helper(i+1,j+2,N,dp);
            res += helper(i,j+2,N,dp);
        } else if (j - i == 1) {
            res += helper(i+2,j+1,N,dp);
            res += helper(i+2,j,N,dp);
        } else res = 0;
        dp[i][j] = (int)(res % M);
        return dp[i][j];
    }
/*
 best doc to understand the solution
as above, 
dp[0] = 1 # {}
dp[1] = 1 # {|}
dp[2] = 2 # {||, =}
dp[3] = 5 # {|||, |=, =|, ⌊⌉, ⌈⌋} = dp[2] ⊗ {|} + dp[1] ⊗ {=} + dp[0] ⊗ {⌊⌉, ⌈⌋}
dp[4] = 11 # dp[3] ⊗ {|} + dp[2] ⊗ {=} + dp[1] ⊗ {⌊⌉, ⌈⌋} + dp[0] ⊗ {⌊¯⌋,⌈_⌉}
dp[5] = 24 # dp[4] ⊗ {|} + dp[3] ⊗ {=} + 2*(dp[2] + dp[1] + dp[0])
...
dp[n] = dp[n-1] + dp[n-2] + 2*(dp[n-3] + ... + dp[0])
      = dp[n-1] + dp[n-3] + [dp[n-2] + dp[n-3] + 2*(dp[n-4] + ... + dp[0])]
      = dp[n-1] + dp[n-3] + dp[n-1]
      = 2*dp[n-1] + dp[n-3]

 dp[i][j] means the for i, j we fully filled the 2*(i) board, i = j
dp[i][0]: ways to cover i cols, both rows of i-th col are covered
dp[i][1]:  ways to cover i cols, only top row of i-th col is covered
dp[i][2]:  ways to cover i cols, only bottom row of i-th col is covered
here is the graph explanations:
it is in doc/domino*.pdf
more doc: 
https://swarajk7.github.io/2018-02-25/tilecombinationcountingleetcode
https://web.stanford.edu/class/cs97si/04-dynamic-programming.pdf
 */
    
    public static int numTilings(int N) {
        if (N < 0) return 0;
        if (N <= 2) return N;
        
        long M = (long)1e9 + 7;
        long[][] dp = new long[N+1][3];
        dp[0][0] = 1;dp[1][0] = 1;
        
        for(int i = 2; i <= N; i++) {
            dp[i][0] = (dp[i-1][0] + dp[i-2][0]
                    + dp[i-1][1] + dp[i-1][2]) % M;
            dp[i][1] = (dp[i-2][0] + dp[i-1][2]) % M;
            dp[i][2] = (dp[i-2][0] + dp[i-1][1]) % M;
        }
        return (int)dp[N][0];
    }
    //follow up: m*n board
    /*二分图的最大匹配,匈牙利算法
    对于一张二维矩阵，我们可以将所有cell分为两个点集，(x, y), x+y为奇数，x+y为偶数。
    其中x+y为奇数被4个x+y为偶数包围，x+y为偶数被4个x+y为奇数包围，所以一条多米诺骨牌其实就是链接两个点集中的一条匹配边，
    这个题目就可以转化为删除两个点集中的一些点，求剩下点集的最大匹配，用匈牙利算法。

    code
    */
        // board[i][j] = 1 -> blocked, 0 -> unblocked
        List<List<Integer>>point;
        int rows;
        int cols;
        int[][] board;
        int[] link;
        boolean[] used;
        public int chessBoard(int[][] board) {
        // init parameters
            this.board = board;
            rows = board.length;
            cols = board[0].length;
            point = new ArrayList<>();
            link = new int[rows * cols];
            used = new boolean[rows * cols];
            for(int i = 0 ; i < rows * cols ; i++) {
                point.add(new ArrayList<>());
            }
            for(int i = 0 ; i < rows ; i++) {
                for(int j = 0 ; j < cols ; j++) {
                    if(i - 1 >= 0 && board[i-1][j] == 0) {
                        point.get(i * cols + j).add((i - 1) * cols + j);
                    }
                    if(i + 1 < rows && board[i + 1][j] == 0) {
                        point.get(i * cols + j).add((i + 1) * cols + j);
                    }
                    if(j + 1 < cols && board[i][j + 1] == 0) {
                        point.get(i * cols + j).add(i * cols + (j + 1));
                    }
                    if(j - 1 >= 0 && board[i][j - 1] == 0) {
                        point.get(i * cols + j).add(i * cols + (j - 1));
                    }
                }
            }
            Arrays.fill(link, -1);
            // hangary algorithm
            return hangary();
        }
        boolean find(int x) {
            for(int i = 0 ; i < point.get(x).size() ; i++) {
                int vertex = point.get(x).get(i);
                if(used[vertex]) continue;
                else {
                    used[vertex] = true;
                    if(link[vertex] == -1 || find(link[vertex])) {
                        link[vertex] = x;
                        return true;
                    }
                }
            }
            return false;
        }
        int hangary() {
            int ans = 0;
            for(int i = 0 ; i < rows ; i++) {
                for(int j = 0 ; j < cols ; j++) {
                    if(board[i][j] == 0) {
                        Arrays.fill(used, false);
                        if(find(i * cols + j)) {
                            ans++;
                        }
                    }
                }
            }
            return ans;
        }

}