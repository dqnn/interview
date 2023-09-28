package hatecode._0001_0999;
import java.util.*;
public class _994RottingOranges {
/*
994. Rotting Oranges
In a given grid, each cell can have one of three values:

the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.
*/
    class Node {
        int x, y;
        int val;
        public Node(int x, int y, int val) {
            this.x =x;
            this.y = y;
            this.val =val;
        }
    }
    
    //interview friendly, typical BSF probelm
    //but here has some tricks:
    //1. like 
    /*
       2   2 
       1   1
     if we add the two 1 1 after we poll, then 1 1 will be in visited, but when we pop from queue, first 1 will add second 1 into the queue which is incorrect,so we add these 2 1 into queue when visiting adjacent cells, but for first time, we add first time to visited by only adding if (!visited.contains) visited.add(), another way is to add before while loop, tried, both will work
    */
    public int orangesRotting(int[][] g) {
        if (g == null || g.length < 1 || g[0].length < 1) return 0;
        
        int r = g.length, c = g[0].length;
        
        Queue<Node> q = new LinkedList<>();
        int total = 0;
        for(int i = 0; i< r;i++) {
            for(int j = 0; j<c;j++) {
                if (g[i][j] == 1 || g[i][j] == 2) {
                    total ++;
                    if (g[i][j] == 2) {
                        Node n = new Node(i, j, 2);
                        q.offer(n);
                        //we can delay this into while loop, just for first time visit
                        //visited.add(i + "->" + j);
                    }
                }
            }
        }
        //we have no rotten orange to rottle or they all already are rotted
        if (total == 0 || total == q.size()) return 0;
        
        
        int res = -1;
        Set<String> visited = new HashSet<>();
        while(!q.isEmpty()) {
            res++;
            int size = q.size();
            for(int i = 0; i < size; i++) {
                Node node = q.poll();
                int x = node.x;
                int y = node.y;
                if (!visited.contains(x + "->" + y)) visited.add(x + "->" + y);
                
                int[][] dirs = {{-1,0},{1,0},{0,1},{0,-1}};
                for(int[] dir : dirs) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                     
                    if (nx>=0 && nx<r && ny>=0 && ny<c && !visited.contains(nx +"->" + ny) && g[nx][ny] == 1) {
                        Node n = new Node(nx, ny, 1);
                        visited.add(nx + "->" + ny);
                        q.offer(n);
                    }
                }
            }
        }
       
        return visited.size() == total ? res : -1;
    }
    
    private int rot(int[][] g, int i, int j, int d) {
      if (i < 0 || j < 0 || i >= g.length || j >= g[i].length || g[i][j] != 1) return 0;
      g[i][j] = d + 3;
      return 1;
}
/*
Complexity Analysis
Time: O(h * w * (h + w)), where h and w are the dimension of the grid. We are scanning h + w times (maximum distance between two cells) through all grid cells.
Memory: O(1).
*/
public int orangesRotting_tech_Better(int[][] g) {
  int fresh = 0, d = 0;
  for (int i = 0; i < g.length; ++i)
    for (int j = 0; j < g[i].length; ++j)
      if (g[i][j] == 1) ++fresh;
  for (int old_fresh = fresh; fresh > 0; ++d, old_fresh = fresh) {
    for (int i = 0; i < g.length; ++i)
      for (int j = 0; j < g[i].length; ++j)
        if (g[i][j] == d + 2)
          fresh -= rot(g, i + 1, j, d) + rot(g, i - 1, j, d) + rot(g, i, j + 1, d) + rot(g, i, j - 1, d);
    if (fresh == old_fresh) return -1;
  }
  return d;
}

}