package hatecode._0001_0999;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description : 51. N-Queens
 */
public class _051NQueens {
//The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no 
    //two queens attack each other.
    /**
     * [
     [".Q..",  // Solution 1
     "...Q",
     "Q...",
     "..Q."],

     ["..Q.",  // Solution 2
     "Q...",
     "...Q",
     ".Q.."]
     ]
     * time : O(n^n) 不是很确定
     * space : O(n)

     * @param n
     * @return
     */
/*
Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' 
both indicate a queen and an empty space respectively.

Example:

Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 */
    // this is typical queens problems, so templates
    public List<List<String>> solveNQueens(int n) {
        char[][] chess = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chess[i][j] = '.';
            }
        }
        List<List<String>> res = new ArrayList<List<String>>();

        helper(res, chess, 0);
        return res;
    }
    private void helper(List<List<String>> res, char[][] chess, int i) {
        if (i == chess.length) {
            res.add(toString(chess));
            return;
        }
        //here is the key: 
        /*
         * we start from column 0 to n,  each time we will check if we place 
         * Q on [i,j] is valid, if it is, we continue place Q on next row. and so on
         */
        for (int j = 0; j < chess.length; j++) {
            if (valid(chess, i, j)) {
                chess[i][j] = 'Q';
                helper(res, chess, i + 1);
                chess[i][j] = '.';
            }
        }
    }
    
    //we only check 0->r && 0->c part
    private boolean valid(char[][] chess, int r, int c) {
        // check all cols
        for (int i = 0; i < r; i++) {
            if (chess[i][c] == 'Q') {
                return false;
            }
        }
        //check 45 degree
        for (int i = r - 1, j = c + 1; i >= 0 && j < chess.length; i--, j++) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }
        //check 135
        for (int i = r - 1, j = c - 1; i >= 0 && j >= 0; i--, j--) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
    private List<String> toString(char[][] chess) {
        return Arrays.stream(chess).map(e->new String(e)).collect(Collectors.toList());
    }
    
    
  //O(n * n!)
    
    //this is shorter solution, and with same complexity
    
    //so the question is to say to place n queues in a nxn matrix, so same row, column, dial and anti-dial
    //cannot have two queues
    
    //this is like Design toe and tie, we do not/do want cards/queue on same line, 
    //design toe is more clever on how to calculate the cards on sameline or not  with two players, 
    //use two arrays and +1, -1 for player1 and player2
    
    //we use 3 sets to store which column or row already occupied
    
    private Set<Integer> col = new HashSet<Integer>();
    // anti-dialg, /
    private Set<Integer> diag1 = new HashSet<Integer>();
    //  \ this dialoug, if i =j, then it would be 0
    private Set<Integer> diag2 = new HashSet<Integer>();
    
    public List<List<String>> solveNQueens_DFS(int n) {
        List<List<String>> res = new ArrayList<List<String>>();
        dfs(res,new ArrayList<String>(), 0, n);
        return res;
    }
    private void dfs(List<List<String>> res, List<String> list, int i, int n){
        if (i == n){
            res.add(new ArrayList<String>(list));
            return;
        }
        //this templates is used to solve the problem like same line is not allowed situations
        for (int j = 0; j < n; j++){
            if (col.contains(j) || diag1.contains(i + j) || diag2.contains(i - j)) continue;
            
            char[] charArray = new char[n];
            Arrays.fill(charArray, '.');
            charArray[j] = 'Q';
            String rowString = new String(charArray);
            
            list.add(rowString);
            col.add(j);
            diag1.add(i + j);
            diag2.add(i - j);
            
            dfs(res, list, i + 1, n);
            
            list.remove(list.size() - 1);
            col.remove(j);
            diag1.remove(i + j);
            diag2.remove(i - j);
        }
    }
}
