package hatecode;
import java.util.*;
public class SumOfDistancesInTree {
/*
834. Sum of Distances in Tree
Explanation:
0. Let's solve it with node 0 as root.

Initial an array of hashset tree, tree[i] contains all connected nodes to i.
Initial an array count, count[i] counts all nodes in the subtree i.
Initial an array of res, res[i] counts sum of distance in subtree i.

Post order dfs traversal, update count and res:
count[root] = sum(count[i]) + 1
res[root] = sum(res[i]) + sum(count[i])

Pre order dfs traversal, update res:
When we move our root from parent to its child i, count[i] points get 1 closer to root, 
n - count[i] nodes get 1 futhur to root.
res[i] = res[root] - count[i] + N - count[i]

return res, done.

Time Complexity:
dfs: O(N)
dfs2: O(N)
*/
    int[] res, count; ArrayList<HashSet<Integer>> tree; int n;
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        tree = new ArrayList<HashSet<Integer>>();
        res = new int[N];
        count = new int[N];
        n = N;
        for (int i = 0; i < N ; ++i ) tree.add(new HashSet<Integer>());
        for (int[] e : edges) {tree.get(e[0]).add(e[1]); tree.get(e[1]).add(e[0]);}
        dfs(0, new HashSet<Integer>());
        dfs2(0, new HashSet<Integer>());
        return res;
    }

    public void dfs(int root, HashSet<Integer> seen) {
        seen.add(root);
        for (int i : tree.get(root))
            if (!seen.contains(i)) {
                dfs(i, seen);
                count[root] += count[i];
                res[root] += res[i] + count[i];
            }
        count[root]++;
    }


    public void dfs2(int root, HashSet<Integer> seen) {
        seen.add(root);
        for (int i : tree.get(root))
            if (!seen.contains(i)) {
                res[i] = res[root] - count[i] + n - count[i];
                dfs2(i, seen);
            };
    }

    
    //easier to understand but higher complexity on space
    public int[] sumOfDistancesInTree2(int N, int[][] edges) {
        if (N <= 0 || edges == null || edges.length < 1 || edges[0].length < 1) return new int[0];
        
        //store the graph
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int i = 0; i< edges.length; i++) {
            map.computeIfAbsent(edges[i][0], v->new HashSet<>()).add(edges[i][1]);
            map.computeIfAbsent(edges[i][1], v->new HashSet<>()).add(edges[i][0]);
        }
        //store the "1->2 : 3" entry
        Map<String, Integer> distMap = new HashMap<>();
        for(int i = 0; i< N; i++) {
            for(int j = 0; j< N; j++) {
                if (i == j) continue;
                helper(map, i, i, j, 0, distMap);
            }
        }
        
        int[] res = new int[N];
        for(int i = 0; i< N;i++) {
            final int p = i;
            res[i] = distMap.entrySet().stream().filter(e->e.getKey().startsWith(p +"->")).mapToInt(e->e.getValue()).sum();
        }
        
        return res;
    }
    
    private void helper(Map<Integer, Set<Integer>> map, int origin, int cur, int end, int preDis, Map<String, Integer> distMap) {
        if (distMap.containsKey(cur + "->" + end) || distMap.containsKey(end + "->" + cur)) return;
        
        for(int node : map.get(cur)) {
            if (node == end) {
                 distMap.put(origin + "->" + end, preDis);
                 distMap.put(end + "->" + origin, preDis);
                 break;
            }
            distMap.put(cur + "->" + node, 1);
            distMap.put(node + "->" + cur, 1);
            helper(map, origin, node, end, preDis + 1, distMap);
        }
    }
}