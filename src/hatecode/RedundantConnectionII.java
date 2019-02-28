package hatecode;
import java.util.*;
public class RedundantConnectionII {
    class DUS {
        int[] parent;
        int[] size;
        public DUS(int size) {
            this.parent = new int[size];
            this.size = new int[size];
            for(int i = 0; i< size;i++) parent[i] = i;
            Arrays.fill(this.size, 1);
        }
        
        public int find(int x) {
            while(x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }
        
        public boolean union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x == y) return false;
            if (size[x] < size[y]) {
                parent[x] = y;
                size[y] += size[x];
            } else {
                parent[y] = x;
                size[x] += size[y];
            }
            return true;
        }
    }
    //O(n) 2 pass, 
    //thinking process: so graph becomes directed graph and we need to remove one edge so 
    //graph will become tree which has N child, so this means, two nodes can only have 1 
    //directed edge and there should be no cycle in graph
    //there are 3 cases: 
    //1 same as RCI, no  >=2 parent nodes, in this case, we just use UF to find out
    //2 we have >=2 parent node but no cycle, like  follow, 2-->3, 1->3, so 3 has 2 parent
/*
           1
         /   \
        2---->3
 */
    // 3 >=2 parent node with cycle, 3->1->4->2->1, here has a cycle 1 4 2 1 
    //and 1 has two parents
    public int[] findRedundantDirectedConnection(int[][] edges) {
        if (edges == null || edges.length < 1) return new int[]{};
        // 因为可能有节点存在两个父亲节点，此时答案必定在这两条边之中
        int[] candidate1 = new int[]{-1, -1};
        int[] candidate2 = new int[]{-1, -1};
        int N = edges.length;
        // 记录各个节点的父亲节点
        int[] parent = new int[N + 1];
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            // 如果节点 v 的父亲不唯一，则记录下两个可能的答案
            if (parent[v] != 0) {
                candidate1 = new int[]{parent[v], v};
                candidate2 = new int[]{u, v};
                // 无效化第二条边 (trick)
                edge[0] = -1;
                edge[1] = -1;
            }
            parent[v] = u;
        }

        // Do Union Find
        DUS dus = new DUS(N + 1);
        for (int[] edge : edges) {
            // 如果遇到被无效化的边直接跳过
            if (edge[0] == -1 && edge[1] == -1) {
                continue;
            }
            if (!dus.union(edge[0], edge[1])) {
                // 如果在无效化了第二条边之后（存在的话）
                // 仍然遇到了环，那么要么是无重复父亲节点，由该条边产生
                // 要么是 存在重复父亲节点，并且由 第一条边 产生
                return candidate1[0] == -1 ? edge : candidate1;
            }
        }
        // 对应 Case2.1 与 Case2.2
        return candidate2;
            
        }
    
    //O(n) one pass
    public int[] findRedundantDirectedConnection2(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n+1], ds = new int[n+1];
        Arrays.fill(parent, -1);
        int first = -1, second = -1, last = -1;
        for(int i = 0; i < n; i++) {
            int p = edges[i][0], c = edges[i][1];
            if (parent[c] != -1) {
                first = parent[c];
                second = i;
                continue;
            }
            parent[c] = i;
            
            int p1 = find(ds, p);
            if (p1 == c) last = i;
            else ds[c] = p1;
        }

        if (last == -1) return edges[second]; // no cycle found by removing second
        if (second == -1) return edges[last]; // no edge removed
        return edges[first];
    }
    
    private int find(int[] ds, int i) {
        return ds[i] == 0 ? i : (ds[i] = find(ds, ds[i]));
    }
}