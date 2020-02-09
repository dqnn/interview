package hatecode._0001_0999;
import java.util.*;
class ZeroOneMatrix {
/*
542. 01 Matrix
Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.

 

Example 1:

Input:
[[0,0,0],
 [0,1,0],
 [0,0,0]]

Output:
[[0,0,0],
 [0,1,0],
 [0,0,0]]
*/
    public int[][] updateMatrix(int[][] m) {
        if (m == null || m.length < 1 || m[0].length < 1) return m;
        
        int r = m.length, c= m[0].length;
        Queue<int[]> q = new LinkedList<>();
        for(int i = 0; i< r;i++) {
            for(int j = 0; j< c;j++) {
                if (m[i][j] == 0) {
                    q.offer(new int[]{i, j});
                } else {
                    m[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
        int[][] dirs =  {{-1,0}, {1, 0}, {0,1}, {0,-1}};
        while(!q.isEmpty()) {
            int[] cell = q.poll();
            for(int[] dir:dirs) {
                int x = cell[0] + dir[0];
                int y = cell[1] + dir[1];
                //we defined 1 value will be Integer.MAX_VALUE, so if m[x][y] <= m[cell[0]][cell[1]] + 1
                //which means it is 0, so we would never update 0 cell and if m[x][y] is smaller then means we find a 
                //shorter path to this 1 cell
                if (x < 0 || x >=r || y < 0 || y >=c || m[x][y] <= m[cell[0]][cell[1]] + 1)
                    continue;
                q.offer(new int[]{x, y});
                m[x][y] = m[cell[0]][cell[1]] + 1;
            }
        }
        
        return m;
    }
}