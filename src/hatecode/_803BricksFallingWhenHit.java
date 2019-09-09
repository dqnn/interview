package hatecode;
public class _803BricksFallingWhenHit {
    /*
     * 803. Bricks Falling When Hit We have a grid of 1s and 0s; the 1s in a cell
     * represent bricks. A brick will not drop if and only if it is directly
     * connected to the top of the grid, or at least one of its (4-way) adjacent
     * bricks will not drop.
     * 
     * We will do some erasures sequentially. Each time we want to do the erasure at
     * the location (i, j), the brick (if it exists) on that location will
     * disappear, and then some other bricks may drop because of that erasure.
     * 
     * Return an array representing the number of bricks that will drop after each
     * erasure in sequence.
     * 
     * Example 1: Input: grid = [[1,0,0,0],[1,1,1,0]] hits = [[1,0]] Output: [2]
     * Explanation: If we erase the brick at (1, 0), the brick at (1, 1) and (1, 2)
     * will drop. So we should return 2.
     */
    //O(RC∗Q∗α(RC∗Q))/O(n) Q=len(hits)
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int m = grid.length;
        int n = grid[0].length;
        //这里的 + 1主要是多一个0来表示顶，所有的第一排的砖在unionfind的时候都会直接与这个0相连。
        UnionFind uf = new UnionFind(m * n + 1);
        //首先把所有要打的砖块标记为2.
        for (int[]hit : hits) {
            if (grid[hit[0]][hit[1]] == 1) {
                grid[hit[0]][hit[1]] = 2;
            }
        }
        //然后对打掉后的数组中的砖块进行四个方向的union
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    unionAround(i, j, grid, uf);
                }
            }
        }
        //这个count就是打完后一定会剩下的砖块数量.
        int count = uf.size[uf.find(0)];
        int[] res = new int[hits.length];
        //反向遍历hits数组，一个一个复原回board
        for (int i = hits.length - 1; i >= 0; i--) {
            int[] hit = hits[i];
            if (grid[hit[0]][hit[1]] == 2) {
                // 对于需要复原的这个砖块做四个方向union，主要是为了得到有多少砖必须通过这块砖才能连接到顶部。
                unionAround(hit[0], hit[1], grid, uf);
                //由于是从后向前，做完要把这块砖重新标记回来
                grid[hit[0]][hit[1]] = 1;
            }
            //newSize就是复原这块砖之后，有多少与顶部相连的砖块.
            int newSize = uf.size[uf.find(0)];
            // 打掉当前砖块会掉的数量就是  复原后的数量- 开始时的数量 - 1 （本身）
            res[i] = (newSize - count > 0) ? newSize - count - 1 : 0;
            //由于是从后向前，所以下次循环时，这个砖还没掉，更新count。
            count = newSize;
        }
        return res;
    }
    private void unionAround(int x, int y, int[][] grid, UnionFind uf) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dirs = {{-1,0},{1,0},{0,1},{0,-1}};
        for(int[] dir: dirs) {
            int nextX = x + dir[0];
            int nextY = y + dir[1];
            if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) continue;
            if (grid[nextX][nextY] == 1) {
                uf.union(x * n + y + 1, nextX * n + nextY + 1);
            }
        }

        //第一排的直接与顶相连。
        if (x == 0) {
            uf.union(x * n + y + 1, 0);
        }
    }
}
class UnionFind {
    int[] id;
    int[] size;
    public UnionFind(int len) {
        id = new int[len];
        size = new int[len];
        for (int i = 0; i < len; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }
    public int find(int toFind) {
        while (id[toFind] != toFind) {
            id[toFind] = id[id[toFind]];//path compression
            toFind = id[toFind];
        }
        return toFind;
    }
    public void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            id[rootA] = rootB;
            size[rootB] += size[rootA];
        }
    }
}