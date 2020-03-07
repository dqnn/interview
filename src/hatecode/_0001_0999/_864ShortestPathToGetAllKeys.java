package hatecode._0001_0999;
import java.util.*;
public class _864ShortestPathToGetAllKeys {
/*
864. Shortest Path to Get All Keys
We are given a 2-dimensional grid. "." is an empty cell, "#" is a wall, "@" is the starting point, ("a", "b", ...) are keys, and ("A", "B", ...) are locks.

We start at the starting point, and one move consists of walking one space in one of the 4 cardinal directions.  We cannot walk outside the grid, or walk into a wall.  If we walk over a key, we pick it up.  We can't walk over a lock unless we have the corresponding key.

For some 1 <= K <= 6, there is exactly one lowercase and one uppercase letter of the first K letters of the English alphabet in the grid.  This means that there is exactly one key for each lock, and one lock for each key; and also that the letters used to represent the keys and locks were chosen in the same order as the English alphabet.

Return the lowest number of moves to acquire all keys.  If it's impossible, return -1.

 

Example 1:

Input: ["@.a.#","###.#","b.A.B"]
Output: 8
*/
    
    class State {
        int key;
        int i, j;
        public State(int key, int i, int j) {
            this.key = key;
            this.i = i;
            this.j = j;
        }
        
        public String toString() {
            return key + "" + i + "" + j;
        }
    }
   //O(RC*2^m),m is count of keys
    public int shortestPathAllKeys(String[] g) {
        if (g == null || g.length< 1) return 0;
        
        int r = g.length, c = g[0].length();
        int max = -1;
        int sx = -1, sy = -1;
        for(int k = 0; k< r; k++) {
            for(int i = 0; i< c; i++) {
                char ch = g[k].charAt(i);
                if (ch == '@') {
                    sx = k;
                    sy = i;
                }
                if (ch >= 'a' && ch <= 'f') {
                    max = Math.max(max, ch - 'a' + 1);
                }
            }
        }
        
        Set<String> visited = new HashSet<>();
        Queue<State> q = new LinkedList<>();
        q.offer(new State(0, sx, sy));
        visited.add(q.peek().toString());
        
        int step = -1;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0,1}};
        while(!q.isEmpty()) {
            System.out.println(q);
            step ++;
            int size = q.size();
            while(size-- > 0) {
                State cur = q.poll();
                if (cur.key == (1<<max) - 1) return step;
                
                for(int[] dir : dirs) {
                    int x = cur.i + dir[0];
                    int y = cur.j + dir[1];
                    int key = cur.key;
                    if (x >= 0 && x < r && y >=0 && y < c) {
                        char ch = g[x].charAt(y);
                        if (ch == '#') continue;
                        if (ch >= 'a' && ch <= 'f') {
                            key |=  (1 << (ch - 'a'));
                        }

                        if (ch >= 'A' && ch <= 'F' && ((key >> (ch-'A')) & 1) == 0) {
                            continue;
                        }
                        String nKey = key + "->" + x + "->" + y;
                        
                        if (!visited.contains(nKey)) {
                            visited.add(nKey);
                            q.offer(new State(key, x, y));
                        }
                    }
                }
            }
        }
        return -1;
    }
}