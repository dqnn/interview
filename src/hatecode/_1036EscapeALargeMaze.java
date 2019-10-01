package hatecode;

import java.util.*;
public class _1036EscapeALargeMaze {
/*
1036. Escape a Large Maze
In a 1 million by 1 million grid, the coordinates of each grid square 
are (x, y) with 0 <= x, y < 10^6.

We start at the source square and want to reach the target square.  Each move, we can walk to a 4-directionally adjacent square in the grid that isn't in the given list of blocked squares.

Return true if and only if it is possible to reach the target square through a sequence of moves.

 

Example 1:

Input: blocked = [[0,1],[1,0]], source = [0,0], target = [0,2]
Output: false
*/
    //thinking process: depends on the length of blocked = B, O(B^2)/O(B^2)
    //we need to track from source to target and target to source
    //the reason is we have limited steps so maybe src->dst is short
    //than dst-> src
    final int MAX_VISIT = 20000;

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        Set<String> blockedSet = new HashSet<>();
        for (int[] ij : blocked) {
            String key = getKey(ij[0], ij[1]);
            blockedSet.add(key);
        }
        return canVisit(blockedSet, source, target) && canVisit(blockedSet, target, source);
    }
    
    boolean canVisit(Set<String> blocked, int[] start, int[] end) {
        Set<String> visited = new HashSet<>();
        return dfs(blocked, start[0], start[1], end[0], end[1], visited);
    }
    
    boolean dfs(Set<String> blocked, int i, int j, int m, int n, Set<String> visited) {
        visited.add(getKey(i, j));
        if (i == m && j == n || visited.size() >= MAX_VISIT) { return true; }
        int[][] dirs = {{-1, 0}, {1, 0}, {0,1}, {0,-1}};
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            String nextKey = getKey(x,y);
            if (x >= 0 && y >= 0 && x < 1e6 && y < 1e6 && !blocked.contains(nextKey) && !visited.contains(nextKey)) {
                if (dfs(blocked, x, y, m, n, visited)) { return true; }
            }
        }
        return false;
    }
    
    private String getKey(int i, int j) {
        return i + "->" + j;
    }
}