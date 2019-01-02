package hatecode;
import java.util.*;
public class TheMazeII {
    /*
     * 505. The Maze II There is a ball in a maze with empty spaces and walls. The
     * ball can go through empty spaces by rolling up, down, left or right, but it
     * won't stop rolling until hitting a wall. When the ball stops, it could choose
     * the next direction.
     * 
     * Given the ball's start position, the destination and the maze, find the
     * shortest distance for the ball to stop at the destination. The distance is
     * defined by the number of empty spaces traveled by the ball from the start
     * position (excluded) to the destination (included). If the ball cannot stop at
     * the destination, return -1.
     * 
     * The maze is represented by a binary 2D array. 1 means the wall and 0 means
     * the empty space. You may assume that the borders of the maze are all walls.
     * The start and destination coordinates are represented by row and column
     * indexes.
     * 
Example 1:

Input 1: a maze represented by a 2D array

0 0 1 0 S
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 D

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: 12
*/
    //int[][] visited array cannot help here because we even we re-visit 
    // the array, the way we visit is not the same, so we cannot simply use 
    //visit[i][j] = 1 to mark it or we will lose some optimized paths
    //it is easy to find cases in above example
    //O(mn*Max(m,n))/O(mn) since we can travel in column or row mode
    public int shortestDistance(int[][] m, int[] src, int[] dst) {
        if (m == null || m.length < 1 || m[0].length < 1) return -1;
        int r = m.length, c=m[0].length;
        if (src[0] < 0 || src[0] >= r || src[1] < 0 || src[1] >= c
            || dst[0] < 0 || dst[0] >= r || dst[1] < 0 || dst[1] >= c) return -1;
        int[][] dist = new int[r][c];
        //this is to avoid start point has adjacent wall but helper will overflow. 
        dist[src[0]][src[1]] = 1;
        helper(m, src[0], src[1], dst, dist);
        return dist[dst[0]][dst[1]] == 0 ? -1 : dist[dst[0]][dst[1]] - 1;
    }
    
    private void helper(int[][] m, int i, int j, int[] dst, int[][] dist) {
        if (i == dst[0] && j == dst[1])  {
            return;
        }
        int r = m.length, c = m[0].length;
        int[][] dirs = {{-1,0},{0,-1},{1,0},{0,1}};
        for(int[] dir : dirs){
            int path = dist[i][j];
            int x = i + dir[0];
            int y = j + dir[1];
            //we cannot put visited here because the ball is rolling no matter we 
            //visited it or not
            //this while loop is mimic the rolling behavior
            while(x >= 0 && y >= 0 && x < r && y < c && m[x][y] ==0) {
                x += dir[0];
                y += dir[1];
                path++;
            }
            x = x- dir[0];
            y = y - dir[1];
            if (dist[x][y] > 0 && path >= dist[x][y]) continue;
            dist[x][y] = path;
            helper(m, x, y, dst, dist);
        }
    }
    
    class Point {
        int x,y,l;
        public Point(int _x, int _y, int _l) {x=_x;y=_y;l=_l;}
    }
    public int shortestDistance2(int[][] maze, int[] start, int[] destination) {
        int m=maze.length, n=maze[0].length;
        int[][] dist = new int[m][n]; // record length
        for (int i=0;i<m;i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
        int[][] dir=new int[][] {{-1,0},{0,1},{1,0},{0,-1}};
        
        //sort asc
        PriorityQueue<Point> list=new PriorityQueue<>((o1,o2)->o1.l-o2.l); // using priority queue
        list.offer(new Point(start[0], start[1], 0));
        while (!list.isEmpty()) {
            Point p=list.poll();
            if (dist[p.x][p.y]<=p.l) continue; // if we have already found a route shorter
            dist[p.x][p.y]=p.l;
            for (int i=0;i<4;i++) {
                int xx=p.x, yy=p.y, l=p.l;
                while (xx>=0 && xx<m && yy>=0 && yy<n && maze[xx][yy]==0) {
                    xx+=dir[i][0];
                    yy+=dir[i][1];
                    l++;
                }
                xx-=dir[i][0];
                yy-=dir[i][1];
                l--;
                list.offer(new Point(xx, yy, l));
            }
        }
        return dist[destination[0]][destination[1]]
                ==Integer.MAX_VALUE?-1:dist[destination[0]][destination[1]];
    }
}