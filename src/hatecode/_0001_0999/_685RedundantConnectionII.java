package hatecode._0001_0999;
import java.util.*;
public class _685RedundantConnectionII {
/*
685. Redundant Connection II
In this problem, a rooted tree is a directed graph such that, 
there is exactly one node (the root) for which all other nodes are descendants of this node, 
plus every node has exactly one parent, except for the root node which has no parents.

The given input is a directed graph that started as a rooted tree with N nodes (with distinct 
values 1, 2, ..., N), with one additional directed edge added. The added edge has two different 
vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] 
that represents a directed edge connecting nodes u and v, where u is a parent of child v.

Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. 
If there are multiple answers, return the answer that occurs last in the given 2D-array.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given directed graph will be like this:
  1
 / \
v   v
2-->3
 */
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
    //1 double parents, see case 1

    public  int[] findRedundantDirectedConnection(int[][] edges) {
        if (edges == null || edges.length < 1) return new int[]{};
        // 因为可能有节点存在两个父亲节点，此时答案必定在这两条边之中
        int[] candidate1 = new int[]{-1, -1};
        int[] candidate2 = new int[]{-1, -1};
        int N = edges.length;
        // 记录各个节点的父亲节点
        int[] parent = new int[N + 1];
        //we may have multiple answers and we will keep the last
        //this only find out the last 2 candidate which has double parents for  node v
        //like 1->2->3->4->5, 5->3, 5->1 then first parent[3] = 2, then next we found parent[3] =5, 
        //then can1 ={2,3}, can2={5,3} and we void edge 5->3,then change to 1->2->3->4->5->1
        //
        for (int[] edge : edges) {
            //direct graph, u->v
            int u = edge[0], v = edge[1];
            // this means v has two parents, one is u, another is parent[v], we remember first, 
            //this loop will keep the last one
            if (parent[v] != 0) {
                candidate1 = new int[]{parent[v], v};
                candidate2 = new int[]{u, v};
                // void the second edge (trick), 1->2->3->4->5->3, 5->1, so this will void 5->3 only
                //[[1, 2], [2, 3], [3, 4], [4, 5], [-1, -1], [5, 1]]
                edge[0] = -1;
                edge[1] = -1;
            }
            parent[v] = u;
        }

        // Do Union Find,if already voided edge, just continue,
        //if after void, we still have a circle, this means
        //example 1->2->3->4->5->3, 5->1, 5 point to 1 and 3, so will mark
        //candidate 1= [2,3] candidate 2=[5,3], so we will find 5 still connect to the network, 
        //in this case, we will return 
        DUS dus = new DUS(N + 1);
        for (int[] edge : edges) {
            // 如果遇到被无效化的边直接跳过
            if (edge[0] == -1 && edge[1] == -1) {
                continue;
            }
/*
case 1: cycle with no double parents
5 <- 1 -> 2
     ^    |
     |    v
     4 <- 3
 the previous loop cannot find anything because there was no double parent
candidate1={-1,-1}, it will return [4,1]
case 2: [[2,1],[3,1],[4,2],[1,4]]  cycle with double parents
  <-----
  |     |
  4->2->1
       /
      3
 candidate1={2,1}, candidate2={3,1}, then it will become [[2,1],[-1,-1],[4,2],[1,4]]
 when go into second loop we find the cycle, finally will return candidate1
 */         //case 1 will return edge, case 2 will return candidate1
            if (!dus.union(edge[0], edge[1])) { 
                return candidate1[0] == -1 ? edge : candidate1;
            }
        }
/*
 * case: just double parents, no cycle
    1
  /   \
 2     3
 \     /
    4
 this will return candidate2={3,4}
 */
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
    
    
    public static void main(String[] args) {
        int[][] g = new int[][] {{1,2},{2,3},{3,4},{5,3},{5,1}};
        System.out.println(Arrays.toString(new _685RedundantConnectionII().findRedundantDirectedConnection(g)));
    }
}