import java.util.*;

public class _1548TheMostSimilarPathInAGraph {
    /*
    1548. The Most Similar Path in a Graph
    We have n cities and m bi-directional roads where roads[i] = [ai, bi] connects city ai with city bi. Each city has a name consisting of exactly three upper-case English letters given in the string array names. Starting at any city x, you can reach any city y where y != x (i.e., the cities and the roads are forming an undirected connected graph).
    
    You will be given a string array targetPath. You should find a path in the graph of the same length and with the minimum edit distance to targetPath.
    
    You need to return the order of the nodes in the path with the minimum edit distance. The path should be of the same length of targetPath and should be valid (i.e., there should be a direct road between ans[i] and ans[i + 1]). If there are multiple answers return any one of them.
    
    The edit distance is defined as follows:
    
    Example 1:
    
    
    Input: n = 5, roads = [[0,2],[0,3],[1,2],[1,3],[1,4],[2,4]], names = ["ATL","PEK","LAX","DXB","HND"], targetPath = ["ATL","DXB","HND","LAX"]
    Output: [0,2,4,2]
    Explanation: [0,2,4,2], [0,3,0,2] and [0,3,1,2] are accepted answers.
    [0,2,4,2] is equivalent to ["ATL","LAX","HND","LAX"] which has edit distance = 1 with targetPath.
    [0,3,0,2] is equivalent to ["ATL","DXB","ATL","LAX"] which has edit distance = 1 with targetPath.
    [0,3,1,2] is equivalent to ["ATL","DXB","PEK","LAX"] which has edit distance = 1 with targetPath.
    Example 2:
    
    
    Input: n = 4, roads = [[1,0],[2,0],[3,0],[2,1],[3,1],[3,2]], names = ["ATL","PEK","LAX","DXB"], targetPath = ["ABC","DEF","GHI","JKL","MNO","PQR","STU","VWX"]
    Output: [0,1,0,1,0,1,0,1]
    Explanation: Any path in this graph has edit distance = 8 with targetPath.
    */

    /*
     * thinking process: O()
     * 
     * the problem is to say: given a graph with n nodes and one target path,  you need to return a 
     * path with min edit distance
     * 
     * edit distance means you can replace, 
     * if the node does not exist in graph, then you will replace to one exists in the graph.
     * 
     * 2 examples as following:
     * n = 5, roads = [[0,2],[0,3],[1,2],[1,3],[1,4],[2,4]], targetPath =[0, 3,4,2]
     * 
     * we have to try all possible combinations, for example, if node not in list, then you will need to repalce it with 
     * one in list, which one should we pick? then we have to add each one. 
     * 
     * Pair<Integer, List<Integer>>, key is the nodeId, value is the visited path, we will add all possbile 
     * pairs to queue, if it matches the node in target path, then we add to queue front, if not, add to bottom, so 
     * it will be like DFS to get the result. 
     * 
     * we also used boolean[m][n] visited to record all possible paths, 
     * visited[i][j] means for node i with path[0,1,2..j-1] we already done the computation. 
     * 
     * why we choose visited[i][j] because our goal is to return a path with min edit distance, 
     * 
     * 
     * 
     * n = 4, roads = [[1,0],[2,0],[3,0],[2,1],[3,1],[3,2]], targetPath= [-1,-1,-1,-1,-1]
     * 
     * 
     * 
     */
        public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
            if (targetPath == null || targetPath.length < 1) return new ArrayList<>();
            
            Map<Integer, List<Integer>> map = new HashMap<>();
            for(int[] r : roads) {
                map.computeIfAbsent(r[0], v->new ArrayList<>()).add(r[1]);
                map.computeIfAbsent(r[1], v->new ArrayList<>()).add(r[0]);
            }
            
    
            
            Deque<Pair<Integer, List<Integer>>> q = new ArrayDeque<>();
            //for each node, we add to the queue, if matches first node in targetPath, then we add to 
            //front of the queue
            for(int i = 0; i<n; i++) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                if (targetPath[0].equals(names[i])) {
                    q.offerFirst(new Pair(i, list));
                } else q.offer(new Pair(i, list));
                    
            }
            
            int m = targetPath.length;
            boolean[][] visited = new boolean[n][m];
            
            while(!q.isEmpty()) {
                Pair<Integer, List<Integer>> e = q.poll();
                int city = e.getKey();
                List<Integer> path = e.getValue();
                
                if (path.size() == m) return path;
                
                for(int nCity: map.get(city)) {
                    //we processed previous node, so it will be path.size() - 1
                    if (visited[nCity][path.size()-1]) continue;
                    visited[nCity][path.size()-1] = true;
                    
                    List<Integer> temp = new ArrayList<>(path);
                    temp.add(nCity);
                    
                    //if path city matches, then we add to path
                    if (targetPath[temp.size()-1].equals(names[nCity])) {
                        q.offerFirst(new Pair(nCity, temp));
                    } else q.offer(new Pair(nCity, temp));
                }
            }
            
            return new ArrayList<>();
        }
    }