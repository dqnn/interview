package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : BattleshipsinaBoard
 * Creator : professorX
 * Date : Nov, 2017
 * Description : 419. Battleships in a Board
 */
public class _419BattleshipsinaBoard {
    /**
     * Given an 2D board, count how many battleships are in it. The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:

     You receive a valid board, made of only battleships or empty slots.
     Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
     At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
     Example:
     X..X
     ...X
     ...X
     In the above board there are 2 battleships.

     Invalid Example:
     ...X
     XXXX
     ...X
     This is an invalid board that you will not receive - as battleships will always have a cell separating between them.

     time : O(m * n)
     space : O(1)

     * @param board
     * @return
     */
    
    
    
     //interview friendly, 
    //thinking process:
    //given a 2D board, count how many islands which on same row or column, 
    //and one row or column could only have 1 island, return how many are there. 
    /*
     * X..X
       ...X
       ...X
       
       above is only 2 battleships
       
     */
    //one battle ship may be horizontal placed or vertically placed, we would like try to
    //check up or left cell whether they are battleship or not, if yes, then we find a standalone
    //ship, if adjacent still x, then they are together
    //由于board中的战舰之间确保有'.'隔开，因此遍历board，若某单元格为'X'，
    // 只需判断其左边和上边的相邻单元格是否也是'X'。
//如果左邻居或者上邻居单元格是'X'，则说明当前单元格是左边或者上边战舰的一部分；
    //so if they are in one row or column we can only check its left side, because
    //with loop, we can check its value in next iteration
    public int countBattleships(char[][] A) {
        // edge case
        if (null == A || A.length < 1) {
            return 0;
        }

        int r = A.length, c = A[0].length;
        int cnt = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (A[i][j] == 'X') {
                    if (i > 0 && (A[i - 1][j] == 'X')) {
                        continue;
                    }
                    if (j > 0 && (A[i][j - 1] == 'X')) {
                        continue;
                    }
                    cnt++;
                }
            }
        }
        return cnt;
    }

    // DFS
    public void dfs(int i, int j, int[][] visited, char[][] nums) {
        if (i < 0 || j < 0 || i >= nums.length || j >= nums[0].length || visited[i][j] == 1) {
            return;
        }

        if (nums[i][j] == 'X') {
            visited[i][j] = 1;
            dfs(i - 1, j, visited, nums);
            dfs(i + 1, j, visited, nums);
            dfs(i, j - 1, visited, nums);
            dfs(i, j + 1, visited, nums);
        }
    }

    // DFS, 其实就是标记上下左右， 访问过了就算一个，因为隔开的不算
    public int countBattleships3(char[][] nums) {
        // edge case
        if (null == nums || nums.length < 1) {
            return 0;
        }

        int r = nums.length, c = nums[0].length;
        int cnt = 0;

        int[][] visited = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (visited[i][j] != 1 && nums[i][j] == 'X') {
                    cnt++;
                    dfs(i, j, visited, nums);
                }
            }
        }
        return cnt;
    }
    //this is interview friendly less code, if it required 
    //diag or anti-diag, then union find is better fit
    public int countBattleships2(char[][] board) {
        int res = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') continue;
                
                //one same column
                if (i > 0 && board[i - 1][j] == 'X') continue;
                //on same row
                if (j > 0 && board[i][j - 1] == 'X') continue;
                
               
                
                res++;
            }
        }
        return res;
    }
}
