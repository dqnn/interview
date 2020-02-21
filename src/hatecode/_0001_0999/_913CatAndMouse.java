package hatecode._0001_0999;
import java.util.*;
public class _913CatAndMouse {
/*
913. Cat and Mouse
A game on an undirected graph is played by two players, Mouse and Cat, who alternate turns.

The graph is given as follows: graph[a] is a list of all nodes b such that ab is an edge of the graph.

Mouse starts at node 1 and goes first, Cat starts at node 2 and goes second, and there is a Hole at node 0.

During each player's turn, they must travel along one edge of the graph that meets where they are.  
For example, if the Mouse is at node 1, it must travel to any node in graph[1].

Additionally, it is not allowed for the Cat to travel to the Hole (node 0.)

Then, the game can end in 3 ways:

If ever the Cat occupies the same node as the Mouse, the Cat wins.
If ever the Mouse reaches the Hole, the Mouse wins.
If ever a position is repeated (ie. the players are in the same position as a previous turn, and it is the same player's turn to move), the game is a draw.
Given a graph, and assuming both players play optimally, return 
1 if the game is won by Mouse, 
2 if the game is won by Cat, 
0 if the game is a draw.

Input: [[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]
Output: 0
Explanation:
4---3---1
|   |
2---5
 \ /
  0
*/
    //thinking process:
    //the problem is give a 2D matrix, mouse at 1 and Cat at 2, hole is 0, graph shows the path where mouse or cat 
    //can move, if they meet, Cat win, if mouse arrived at 0 then mouse win,if they in a pre-showed position together,
    //then draw. 
    
    //Cat and Mouse have several choices when move, each would have different outcome, so 
    //current we use DP to solve this game,
    //1 dp[i][j] means if mouse starts i and Cat starts j, the result, then whole result would be dp[1][2]
    // the subproblem is how many possible ways for mouse and cat to move, how many combinations, and how many results
    //2 dp[i][j] = {dp[p1][q1], dp[p2][q2], dp[p3][q3]....} p1 q1 means connected to i and j,  we want to find 2 or 1 but 
    //sine mouse go fist, if mouse have priority if we can see mouse win, if not, cat win
    //3 boundaries: we are easy to transform the conditions into dp[0][i] and dp[i][i], 
    //4 to calc, so we starts from 2 and 1, starting dfs, note we can re-use the dp, top-down dp
    
    //TODO: fix  [[6],[4,11],[9,12],[5],[1,5,11],[3,4,6],[0,5,10],[8,9,10],[7],[2,7,12],[6,7],[1,4],[2,9]] bug
    public int catMouseGame(int[][] graph) {
        int size = graph.length;
        //to record the result, res[1][2] means mouse starts 1, cats starts 2 's result
        int dp[][] = new int[size][size];
        
        for (int i = 0; i < size; i++) Arrays.fill(dp[i], -1);
        //initialize the boundary
        for (int i = 0; i < size; ++i) {
            dp[0][i] = 1;   // mouse reached home, mouse win
            dp[i][i] = 2;   // cat met mouse, cat win
        }
        //mouse starts 1 and cat starts 2
        return helper(graph, 1, 2, dp);
    }

    public int helper(int[][] graph, int mouse, int cat, int dp[][]) {
        if (dp[mouse][cat] != -1) return dp[mouse][cat];  // use cached value

        dp[mouse][cat] = 0;  // if there is a cycle, draw
        int mouseDefault = 2;  //  default cat win, try to update this number to 1 or 0
        int[] mouseMovieList = graph[mouse], catMoveList = graph[cat];
        
        for (int mouseMove : mouseMovieList) {
            if (mouseMove == cat) continue;   // I'm a mouse, why go for a cat?

            int catDefault = 1;  //  default mouse win, try to update this number to 2 or 0
            for (int catMove : catMoveList) {
                if (catMove == 0) continue;  // cannot go to hole
                int gameRes = helper(graph, mouseMove, catMove, dp);
                //if cat wins, then we will choose this way, and break, no need to continue
                if (gameRes == 2) {
                    catDefault = 2;
                    break;
                }
                if (gameRes == 0) {   // at least it's a draw
                    catDefault = 0;
                }
            }
            //mouse always pick the best way, so no need to continue
            //from internal loop result, we can know cat try all ways the best result
            if (catDefault == 1) {  // if mouse can win in this path, no need to continue
                mouseDefault = 1;
                break;
            }
            if (catDefault == 0) {  // at least it's a draw
                mouseDefault = 0;
            }
        }
        dp[mouse][cat] = mouseDefault;
        return dp[mouse][cat];
    }
    
    public static void main(String[] args) {

        int[][] graph = {{2,5},{3},{0,4,5},{1,4,5},{2,3},{0,2,3}};
        int[][] graph2 = {{6},{4},{9},{5},{1,5},{3,4,6},{0,5,10},{8,9,10},{7},{2,7},{6,7}};
        _913CatAndMouse catandMouse = new _913CatAndMouse();
        System.out.println(catandMouse.catMouseGame(graph));
        System.out.println(catandMouse.catMouseGame(graph2));

    }
 /*
this is DFS search detail, need to draw a tree to replace following output
mouse-cat: 3-0
mouse-cat: 3-4 starts from here, we do deeper
mouse-cat: 1-2
mouse-cat: 1-3
mouse-cat: 5-2
mouse-cat: 0-0
mouse-cat: 0-4
mouse-cat: 0-5
mouse-cat: 5-3
mouse-cat: 0-1
mouse-cat: 0-4
mouse-cat: 0-5
mouse-cat: 3-5 here we go back to previous, mouse at 3
mouse-cat: 1-0
mouse-cat: 1-2
mouse-cat: 1-3
mouse-cat: 4-0
mouse-cat: 4-2
mouse-cat: 3-0
mouse-cat: 3-4
mouse-cat: 3-5
mouse-cat: 4-3
mouse-cat: 2-1
mouse-cat: 0-3
mouse-cat: 2-4
mouse-cat: 0-2
mouse-cat: 0-3
mouse-cat: 2-5
mouse-cat: 0-0
mouse-cat: 0-2
mouse-cat: 0-3
  */
    //only correct toplogic solution, colored 
    public int catMouseGame2(int[][] graph) {
        int n = graph.length;
        // (cat, mouse, mouseMove = 0)
        int[][][] color = new int[n][n][2];
        int[][][] outdegree = new int[n][n][2];
        for (int i = 0; i < n; i++) { // cat
            for (int j = 0; j < n; j++) { // mouse
                outdegree[i][j][0] = graph[j].length;
                outdegree[i][j][1] = graph[i].length;
                for (int k : graph[i]) {
                    if (k == 0) {
                        outdegree[i][j][1]--;
                        break;
                    }
                }
            }
        }
        Queue<int[]> q = new LinkedList<>();
        for (int k = 1; k < n; k++) {
            for (int m = 0; m < 2; m++) {
                color[k][0][m] = 1;
                q.offer(new int[]{k, 0, m, 1});
                color[k][k][m] = 2;
                q.offer(new int[]{k, k, m, 2});
            }
        }
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cat = cur[0], mouse = cur[1], mouseMove = cur[2], c = cur[3];
            if (cat == 2 && mouse == 1 && mouseMove == 0) {
                return c;
            }
            int prevMouseMove = 1 - mouseMove;
            for (int prev : graph[prevMouseMove == 1 ? cat : mouse]) {
                int prevCat = prevMouseMove == 1 ? prev : cat;
                int prevMouse = prevMouseMove == 1 ? mouse : prev;
                if (prevCat == 0) {
                    continue;
                }
                if (color[prevCat][prevMouse][prevMouseMove] > 0) {
                    continue;
                }
                if (prevMouseMove == 1 && c == 2 || prevMouseMove == 0 && c == 1
                    || --outdegree[prevCat][prevMouse][prevMouseMove] == 0) {
                    color[prevCat][prevMouse][prevMouseMove] = c;
                    q.offer(new int[]{prevCat, prevMouse, prevMouseMove, c});
                }
            }
        }
        return color[2][1][0];
    }
}