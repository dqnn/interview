package hatecode._0001_0999;

import java.util.*;
public class GoogleGenerateBingoGame {
/*
 * Goole interview, not from LC 
 * problem statement:
 * bingo game。让我随机生成一张5*5的棋盘，只有两个限制：
https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=452049
第一行的所有数字在1-15的范围内，第二行在16-30的范围内，以此类推
每一行的数字不能有重复
给了个api：random(min, max)，可以返回[min, max]内的随机数字
follow up是生成n张这种棋盘，保证n张棋盘中的同一行没有完全相同的一行
 */
    public int[][] generate() {
        int[][] board = new int[5][5];
        for (int i = 0; i < 5; i++) {
            Set<Integer> visited = new HashSet<>();
            for (int j = 0; j < 5; j++) {
                boolean filled = false;
                while (!filled) {
                    int val = random(i * 15 + 1, i * 15 + 15);
                    if (visited.add(val)) {
                        board[i][j] = val;
                        filled = true;
                    }
                }
            }
        }
        return board;
    }
    
    //generate n bingo game, one line should not have same line, so we need a
    //Map<Integer, Set<String>>, 0=[2, 3, 4, 5, 6] so same linie in different bingo game 
    //will be different
    
    private int random(int min, int max) {
        return 0;
    }

}
