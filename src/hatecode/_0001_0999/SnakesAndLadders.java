package hatecode._0001_0999;

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
    
   //given the graph, note the square number 
    //we always start from 1
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        //since we can visit n*n,so we need len = n*n + 1
        boolean[] visited = new boolean[n * n + 1];
        //new style of bfs loop
        for (int move = 0; !queue.isEmpty(); move++) {
            for (int size = queue.size(); size > 0; size--) {
                int num = queue.poll();
                if (visited[num]) continue;
                visited[num] = true;
                if (num == n * n) return move;
                for (int i = 1; i <= 6 && num + i <= n * n; i++) {
                    int next = num + i;
                    int value = getBoardValue(board, next);
                    if (value > 0) next = value;
                    if (!visited[next]) queue.offer(next);
                }
            }
        }
        return -1;
    }

    private int getBoardValue(int[][] board, int num) {
        int n = board.length;
        int r = (num - 1) / n;
        int x = n - 1 - r;
        int y = r % 2 == 0 ? num - 1 - r * n : n + r * n - num;
        return board[x][y];
    }
}