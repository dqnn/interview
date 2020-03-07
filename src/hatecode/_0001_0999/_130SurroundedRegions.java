package hatecode._0001_0999;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SurroundedRegions
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 130. Surrounded Regions
 */
public class _130SurroundedRegions {

    /**
     * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

     A region is captured by flipping all 'O's into 'X's in that surrounded region.

     For example,
     X X X X
     X O O X
     X X O X
     X O X X

     After running your function, the board should be:

     X X X X
     X X X X
     X X X X
     X O X X

     time : O(m * n)
     space : O(n)

     * @param board
     */
    //thinking process:
    
    // the problem is asking for to flip O to X when O is not on the edge 
    // or not connect to another o on the edge
    
    // since o on the edge is special, so we just surface the 4 edges 
    // we use DFS from each cell on the edge, we stop when i j is outlier
    // and not o, if it is o and and on the edge  we change to 1, so 
    //DFS will auto stop when it is not o, remember the DFS templates
    
    //dfs functionality only just to mark each '0' to '1' which is connected by every '0' inside the matrix
    //later when we visit matrix, we just need to flip '0' to 'X' and '1' to '0'
    public void solve(char[][] board){
        if (board == null || board.length == 0 || board[0].length == 0) return;

        int m = board.length - 1;
        int n = board[0].length - 1;

        for (int i = 0; i <= m; i++) {
            //first column
            if (board[i][0] == 'O') {
                dfs(board, i, 0);
            }
            //last column
            if (board[i][n] == 'O') {
                dfs(board, i, n);
            }
        }
        for (int i = 0; i <= n; i++) {
            //first row
            if (board[0][i] == 'O') {
                dfs(board, 0, i);
            }
            //last row
            if (board[m][i] == 'O') {
                dfs(board, m, i);
            }
        }
        //flip each cell
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '1') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != 'O') {
            return;
        }
        board[i][j] = '1';
        dfs(board, i, j + 1);
        dfs(board, i, j - 1);
        dfs(board, i + 1, j);
        dfs(board, i - 1, j);
    }

    class Point {
        int x;
        int y;

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    public void solve2(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;

        int m = board.length - 1;
        int n = board[0].length - 1;
        Queue<Point> queue = new LinkedList<>();

        //get all 'O's on the edge first
        for (int r = 0; r <= m; r++) {
            if (board[r][0] == 'O') {
                board[r][0] = '1';
                queue.add(new Point(r, 0));
            }
            if (board[r][n] == 'O') {
                board[r][n] = '1';
                queue.add(new Point(r, n));
            }
        }

        for (int i = 0; i < n; i++){
            if (board[0][i] == 'O') {
                board[0][i] = '1';
                queue.add(new Point(0, i));
            }
            if (board[m][i] == 'O') {
                board[m][i] = '1';
                queue.add(new Point(m, i));
            }
        }

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int row = p.x;
            int col = p.y;
            if (row - 1 >= 0 && board[row - 1][col] == 'O') {
                board[row - 1][col] = '1';
                queue.add(new Point(row - 1, col));
            }
            if (row + 1 < m && board[row + 1][col] == 'O') {
                board[row + 1][col] = '1'; queue.add(new Point(row + 1, col));
            }
            if (col - 1 >= 0 && board[row][col - 1] == 'O') {
                board[row][col - 1] = '1';
                queue.add(new Point(row, col - 1));
            }
            if (col + 1 < n && board[row][col + 1] == 'O') {
                board[row][col + 1] = '1';
                queue.add(new Point(row, col + 1));
            }
        }
        
        for (int i = 0; i<= m; i++){
            for (int j=0; j<= n; j++){
                if (board[i][j] == 'O') board[i][j] = 'X';
                if (board[i][j] == '1') board[i][j] = 'O';
            }
        }
    }

}
