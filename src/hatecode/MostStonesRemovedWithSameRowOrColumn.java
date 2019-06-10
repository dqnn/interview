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
     * 
     * 
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
    //O(nlgn)/O(n), union by rank will be a(n), 
    
    //so 
    public int removeStones2(int[][] stones) {
        int N = stones.length;
        DSU dsu = new DSU(20000);

        for (int[] stone: stones)
            dsu.union(stone[0], stone[1] + 10000);
        //here is how to calculate the components
        Set<Integer> seen = new HashSet<>();
        for (int[] stone: stones)
            seen.add(dsu.find(stone[0]));

        return N - seen.size();
    }


class DSU {
    int[] parent;
    public DSU(int N) {
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
    
    
    //DFS
    public int removeStones(int[][] stones) {
        int N = stones.length;

        // graph[i][0] = the length of the 'list' graph[i][1:]
        int[][] graph = new int[N][N];
        for (int i = 0; i < N; ++i)
            for (int j = i+1; j < N; ++j)
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    graph[i][++graph[i][0]] = j;
                    graph[j][++graph[j][0]] = i;
                }

        int ans = 0;
        boolean[] seen = new boolean[N];
        for (int i = 0; i < N; ++i) 
            if (!seen[i]) {
            Stack<Integer> stack = new Stack<>();
            stack.push(i);
            seen[i] = true;
            ans--;
            while (!stack.isEmpty()) {
                int node = stack.pop();
                ans++;
                for (int k = 1; k <= graph[node][0]; ++k) {
                    int nei = graph[node][k];
                    if (!seen[nei]) {
                        stack.push(nei);
                        seen[nei] = true;
                    }
                }
            }
        }

        return ans;
    }
    
    
}
