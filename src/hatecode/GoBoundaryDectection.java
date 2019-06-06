package hatecode;

import java.util.*;
public class GoBoundaryDectection {
/*
 * Googl interview question:
 * Problem statement:
 * 输入出任意 一个 棋盘位置， 判断是否那个位置的棋子处于被包围状 
态-上下左右都是不同颜色地棋子。被包围的有可能是相同颜色地一组棋子，只要给定 
位置属于那个组 也返回true. 
比如 (.代表没有棋子，B代表黑，W代表白) 
..B.. 
.BWB. 
.BWB. 
..B.. 
 */
    //1-black, 2-white, 0-empty (i, j) means the position, color means the 
    public boolean isSurrounded(int[][] g, int i, int j) {
        if (g == null || g.length < 1 || g[0].length < 1) return true;
        int r = g.length, c=g[0].length;
        if (i < 0 || i >= r || j < 0 || j >= c || g[i][j] == 0) return false;
        //1 visited, do not know result
        //2 visited, result is it is surrounded
        //3 visited, result is not surrounded
        int[][] visited = new int[r][c];
        visited[i][j] = 1;
        return helper(g, visited, i, j, g[i][j]);
    }
    //there were 1 edge question:
    //suppose color is white, so if white on edge are considered surrounded, should not
    //
    private boolean helper(int[][] g, int[][] visited, int i, int j, int color) {
        if (visited[i][j] == 1 || g[i][j] == 0) return false;
        visited[i][j] = 1;
        int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        for(int[] dir: dirs) {
            int ni = i + dir[0];
            int nj = j + dir[1];
            if (ni >=0 && ni < g.length && nj >= 0 && nj < g[0].length) {
                if (helper(g, visited, ni, nj, color)) {
                    return true;
                }
            }
        }
        //tried 
        return false;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
