package hatecode._1000_1999;


import java.util.*;

public class _1197MinimumKnightMoves {
/*
1197. Minimum Knight Moves
In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].

A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.


Return the minimum number of steps needed to move the knight to the square [x, y]. It is guaranteed the answer exists.

 

Example 1:

Input: x = 2, y = 1
Output: 1
Explanation: [0, 0] → [2, 1]

*/
    
    //thinking process: 
    
    //the problem is to say, in an infinite board, a knight is at (0,0)
    //he can reachout to 8 directions as one step, return the min
    //steps is he arrived at (x, y)
    
    //typical BFS or DFS solution, 
    
    //interview friendly.
    public int minKnightMoves(int x, int y) {
        
        Queue<int[]> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.offer(new int[]{0, 0});
        visited.add("0,0");
        
        int step = 0;
        int[][] dirs ={{1,2}, {2,1}, {2,-1}, {1,-2}, 
                              {-1,-2}, {-2,-1}, {-2,1}, {-1,2}};
        while(!q.isEmpty()) {
            int size = q.size();
            while(size-- > 0) {
                int[] cur = q.poll();
                if (cur[0] == Math.abs(x) && cur[1] == Math.abs(y)) return step;
                for(int[] d: dirs) {
                    int nx = cur[0] + d[0];
                    int ny = cur[1] + d[1];
                   
                    if (!visited.contains(nx + "," + ny) && nx >= -1 && ny >= -1) {
                        visited.add(nx + "," + ny);
                        q.offer(new int[]{nx, ny});
                    }
                }
            }
            step++;
        }
        return -1;
    }
    
    /*
 y
 |  5 4 5 4 5 4 5 6
 |  4 3 4 3 4 5 4
 |  3 4 3 4 3 4
 |  2 3 2 3 4
 |  3 2 3 2
 |  2 1 4
 |  3 2
 |  0
 |  -----> x

     */
    
    //it is O(1) solution, it needs math 
    private static int[][] localRegion = {
            {0, 3, 2},
            {3, 2, 1},
            {2, 1, 4}
        };
        
        public int minKnightMoves_Best(int x, int y) {
            x = Math.abs(x);
            y = Math.abs(y);
            if (x < y) {
                int tmp = x;
                x = y;
                y = tmp;
            }
            if (x <= 2 && y <= 2)
                return localRegion[x][y];
            
            int groupId;
            if ((x - 3) >= (y - 3) * 2)
                groupId = (x - 1) / 2 + 1;
            else 
                groupId = (x + y - 2) / 3 + 1;
            
            return groupId + ((x + y + groupId) % 2);
        }
}