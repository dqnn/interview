package hatecode;

import java.util.*;
public class SnakesAndLadders {
/*
909. Snakes and Ladders
Input: [
[-1,-1,-1,-1,-1,-1],
[-1,-1,-1,-1,-1,-1],
[-1,-1,-1,-1,-1,-1],
[-1,35,-1,-1,13,-1],
[-1,-1,-1,-1,-1,-1],
[-1,15,-1,-1,-1,-1]]
Output: 4
Explanation: 
At the beginning, you start at square 1 [at row 5, column 0].
You decide to move to square 2, and must take the ladder to square 15.
You then decide to move to square 17 (row 3, column 5), and must take the snake to square 13.
You then decide to move to square 14, and must take the ladder to square 35.
You then decide to move to square 36, ending the game.
It can be shown that you need at least 4 moves to reach the N*N-th square, so the answer is 4.
*/
   public int snakesAndLadders(int[][] board) {
        int n = board.length;
        Set<String> visited = new HashSet<String>();
        Queue<int[]> pos = new LinkedList<int[]>();
        pos.offer(new int[] {n-1, 0});
        int steps = 0;
        while(!pos.isEmpty()){
            int size = pos.size();
            steps++;
            for(int p =0;p<size;p++){
                int[] next = pos.poll();
                visited.add(next[0] + "->" + next[1]);
                for (int i = 1; i <= 6; i++){
                    int[] step = takeStep(board, next[0], next[1], i); 
                    if (step[0] == n && step[1] == n) return steps;
                    if(board[step[0]][step[1]] != -1) {
                        step = getCord(n, board[step[0]][step[1]]);
                    }
                    if (step[0] == n && step[1] == n) return steps;
                    if (!visited.contains(step[0]+"->"+step[1])) pos.offer(step);
                }                
            }
        }
        return -1;
    }
    /*Take steps at row, col*/
    public int[] takeStep(int[][] board, int row, int col, int steps){
        int n = board.length;
        int next = 0;
        if ( (n + row) % 2 == 0) next = (n - row - 1) * n + n - col + steps;
        else next = (n - row - 1) * n + col + 1 + steps;
        return getCord(n, next);
    }
    
    private int[] getCord(int n, int value){
        if (value >= n * n) return new int[] {n,n} ;
        int row = n - (value - 1) / n - 1;
        int col = 0;
        if ((n + row) % 2 == 0){
            if (value % n == 0) col = 0;
            else col = n - ((value - 1) % n) - 1;
        }else{
            if (value % n == 0) col = n-1;
            else col = ((value -1) % n);
        }
        return new int[] {row, col};
    }
}