package hatecode;
import java.util.*;
public class MostStonesRemovedWithSameRowOrColumn {
    /*
     * 947. Most Stones Removed with Same Row or Column 
     * On a 2D plane, we place stones at some integer coordinate points. Each coordinate 
     * point may have at most one stone.
     * 
     * Now, a move consists of removing a stone that shares a column or row with
     * another stone on the grid.
     * 
     * What is the largest possible number of moves we can make?
     * 
     * Example 1:
     * 
     * Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]] Output: 5
     * 
     * Problem: we can remove a stone if and only if, there is another stone in the
     * same column OR row. We try to remove as many as stones as possible.

     * One sentence to solve: Connected stones can be reduced to 1 stone, the
     * maximum stones can be removed = stones number - islands number. so just count
     * the number of "islands".
     * 
     * 1. Connected stones Two stones are connected if they are in the same row or
     * same col. Connected stones will build a connected graph. It's obvious that in
     * one connected graph, we can't remove all stones.
     * 
     * We have to have one stone left. An intuition is that, in the best strategy,
     * we can remove until 1 stone.
     * 
     * I guess you may reach this step when solving the problem. But the important
     * question is, how?
     * 
     * 2. A failed strategy Try to remove the least degree stone Like a tree, we try
     * to remove leaves first. Some new leaf generated. We continue this process
     * until the root node left.
     * 
     * However, there can be no leaf. When you try to remove the least in-degree
     * stone, it won't work on this "8" like graph: [[1, 1, 0, 0, 0], [1, 1, 0, 0,
     * 0], [0, 1, 1, 0, 0], [0, 0, 1, 1, 1], [0, 0, 0, 1, 1]]
     * 
     * The stone in the center has least degree = 2. But if you remove this stone
     * first, the whole connected stones split into 2 parts, and you will finish
     * with 2 stones left.
     * 
     * 3. A good strategy In fact, the proof is really straightforward. You probably
     * apply a DFS, from one stone to next connected stone. You can remove stones in
     * reversed order. In this way, all stones can be removed but the stone that you
     * start your DFS.
     * 
     * One more step of explanation: In the view of DFS, a graph is explored in the
     * structure of a tree. As we discussed previously, a tree can be removed in
     * topological order, from leaves to root.
     * 
     * 4. Count the number of islands We call a connected graph as an island. One
     * island must have at least one stone left. The maximum stones can be removed =
     * stones number - islands number
     * 
     * The whole problem is transferred to: What is the number of islands?
     * 
     * You can show all your skills on a DFS implementation, and solve this problem
     * as a normal one.
     * 
     * 5. Unify index Struggle between rows and cols? You may duplicate your codes
     * when you try to the same thing on rows and cols. In fact, no logical
     * difference between col index and rows index.
     * 
     * An easy trick is that, add 10000 to col index. So we use 0 ~ 9999 for row
     * index and 10000 ~ 19999 for col.
     * 
     * 6. Search on the index, not the points When we search on points, we
     * alternately change our view on a row and on a col.
     * 
     * We think: a row index, connect two stones on this row a col index, connect
     * two stones on this col.
     * 
     * In another view： A stone, connect a row index and col.
     * 
     * Have this idea in mind, the solution can be much simpler. The number of
     * islands of points, is the same as the number of islands of indexes.
     * 
     * 7. Union-Find I use union find to solve this problem. As I mentioned, the
     * elements are not the points, but the indexes.
     * 
     * for each point, union two indexes. return points number - union number Copy a
     * template of union-find, write 2 lines above, you can solve this problem in
     * several minutes.
     */
    //O(nlgn)/O(n), union by rank will be a(n), Inverse-Ackermann function
    
/*
是应该用什么顺序拿，才能保证能拿最多 (这个follow up应该怎么解呢)尽量先把一个component里的都去掉？
answer: 优先拿掉不导致component数量增加的棋子。
 */
    //thinking process: 
    
    //on a big grid, we placed stone on coordinate in stones as 2D array
    //a move action is just to remove a stone which share same col or row with other stones
    //so one move can only remove one stone, and if we want most moves, then the strategy is we did
    //not increase the components count
    
    
    //another follow up for this solution is the DSU size, suppose the 2D is infinite, how can we deal
    //with it? 
    
    //interview friendly and best
    //one move can only remove the stones which connect to a island as problem statement
    //so we have to make sure there was no components increase when we remove, make sure every island last remove
    //is two stones
    //for this question, because DSU traditionally needs N, so we cannot directly use array based 
    //DSU then, so we need to use Map<Integer, Integer>, 
    //here is a trick that since we use Map to do union find, then we has no worry about the size,the example 
    //is following
    public int removeStones(int[][] stones) {
        int N = stones.length;
        DSU dsu = new DSU();

        for (int[] stone: stones)
            dsu.union(stone[0], ~stone[1]);

        return N - dsu.getComponents();
    }

class DSU {
    private Map<Integer, Integer> f;
    private int components = 0;
    public DSU(){
        this.f = new HashMap<>();
    }
    public int find(int x) {
        if (f.putIfAbsent(x, x) == null) components++;
        if (x != f.get(x))
            f.put(x, find(f.get(x)));
        return f.get(x);
    }

    public void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            f.put(x, y);
            components--;
        }
    }
    
    public int getComponents(){return components;}
}
    
    public int removeStones_REference_OLD(int[][] stones) {
        int N = stones.length;
        //20000 is from notes, max 20K 
        DSU_OLD dsu = new DSU_OLD(20000);

        for (int[] stone: stones)
            dsu.union(stone[0], stone[1] + 10000);
        //here is how to calculate the components
        Set<Integer> seen = new HashSet<>();
        for (int[] stone: stones)
            seen.add(dsu.find(stone[0]));

        return N - seen.size();
    }


class DSU_OLD {
    int[] parent;
    public DSU_OLD(int N) {
        parent = new int[N];
        for (int i = 0; i < N; ++i)
            parent[i] = i;
    }
    public int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }
    public void union(int x, int y) {
        parent[find(x)] = find(y);
    }
}
    //DFS, this is a bit different compared to this question, but it is a reference for fast code writing
    int     count;
    int     totalCount;
    int     rows;
    int     cols;
    int[][] graph;

    // 0是空格，1是marble
    int removeMarble(int[][] graph) {
        // sanity check, 这里只是说了下corner case，面试官说不用写
        rows = graph.length;
        cols = graph[0].length;
        this.graph = graph;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (graph[i][j] == 1) {
                    dfs(i, j);
                    count++;
                }
            }
        }
        return totalCount - count;
    }

    void dfs(int row, int col) {
        totalCount++;
        graph[row][col] = 0;
        for (int i = 0; i < rows; i++) {
            if (graph[i][col] == 1) {
                dfs(i, col);
            }
        }
        for (int j = 0; j < cols; j++) {
            if (graph[row][j] == 1) {
                dfs(row, j);
            }
        }
    }
}
