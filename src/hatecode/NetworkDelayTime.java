package hatecode;
import java.util.*;
import java.util.stream.*;
public class NetworkDelayTime {
/*
743. Network Delay Time
There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

Note:
*/
    public int networkDelayTime_BellManFord(int[][] times, int N, int K) {
        List<List<int[]>> graph = new ArrayList<>();
        // we add 0->N+ 1 array list, why we choose N + 1 since node is from 1->N, 
        //we do not like i + 1 nasty code when we loop
        IntStream.range(0, N + 1).forEach(e->graph.add(new ArrayList<>()));
        //for times array, we 
        Arrays.stream(times).forEach(e->graph.get(e[0]).add(new int[]{e[1],e[2]}));
        
        int[] disTo = new int[N+1];
        Arrays.fill(disTo, Integer.MAX_VALUE);
        
        Queue<Integer> q = new LinkedList<>();
        
        boolean[] onQ = new boolean[N + 1];
        q.offer(K);
        disTo[K] = 0;
        //onQ[K] = true;
        
        while(!q.isEmpty()) {
            int u = q.poll();
            onQ[u] =false;
            for(int[] e: graph.get(u)) {
                int v =e[0], w=e[1];
                if (disTo[u] != Integer.MAX_VALUE && disTo[u] + w < disTo[v]) {
                    disTo[v] = disTo[u] + w;
                    if (!onQ[v]) {
                        q.offer(v);
                        onQ[v] = true;
                    }
                }
            }
        }
        
        int res = 0;
        for(int i = 1; i<=N;i++) res = Math.max(res, disTo[i]);
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    //dp solution, but it is the Bellman ford solution
    public int networkDelayTime(int[][] times, int N, int K) {
    if (times == null || times.length == 0) return -1;


    /* Subproblem: dp(i) represents minimum distance from K to i (iteratively update dp(i) when we find another shorter distance from K to i)*/
    int[] dp = new int[N + 1];

    /* Base case 1: initialize MAX value as initial minimum distance from K to every point*/
    /* Base case 2: initialize 0 as min distance to K itself*/
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[K] = 0;

    /* traverse all destinations*/
    for (int i = 0; i < N; i++) {
        for (int[] edge : times) {
            int u = edge[0], v = edge[1], w = edge[2];

            /* if dp[u] (starting point of current edge) has already been visited, and new distance from u to v is smaller than previous saved distance
            we should update minimum distance from u to v*/
            if (dp[u] != Integer.MAX_VALUE && dp[u] + w < dp[v]) dp[v] = dp[u] + w;
        }
    }

    /* after getting minimum distance (travel time) to all points, we should retrieve the max distance from these minimum distance, 
     * to ensure that we can travel all points in a specific travel time
    */
    int res = IntStream.range(1, N+1).map(i->dp[i]).max().getAsInt();
    
    /* if we do not visit all points, we should return -1*/
    return res == Integer.MAX_VALUE ? -1 : res;
}         
}