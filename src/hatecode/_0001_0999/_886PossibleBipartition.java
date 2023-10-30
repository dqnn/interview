package hatecode._0001_0999;
import java.util.*;
public class _886PossibleBipartition {
/*
886. Possible Bipartition
Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.

Each person may dislike some other people, and they should not go into the same group. 

Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.

Return true if and only if it is possible to split everyone into two groups in this way.

 

Example 1:

Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
Output: true
Explanation: group1 [1,4], group2 [2,3]
Example 2:

Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
Output: false
*/
    //1->{2,3}, 2->{1,4},3->{1}, 4->{2}
    public boolean possibleBipartition(int N, int[][] dislikes) {
        int[] parent = new int[N + 1];
        for (int i = 0; i <= N; i++) parent[i] = i;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] pair : dislikes) {
            map.computeIfAbsent(pair[0], V->new ArrayList<>()).add(pair[1]);
            map.computeIfAbsent(pair[1], V->new ArrayList<>()).add(pair[0]);
        }
        //N people, from 1 to N
        for (int i = 1; i <= N; i++) {
            if (map.containsKey(i)) {
                int parent1 = find(parent, i);
                List<Integer> opponents = map.get(i);
                //here is to find another root number, cannot remove
                int parent2 = find(parent, opponents.get(0));
                if (parent1 == parent2) return false;
                for (int j = 1; j < opponents.size(); j++) {
                    int opponentParent = find(parent, opponents.get(j));
                    if (parent1 == opponentParent) return false;
                    //assign opponentParent parent is parent2
                    parent[opponentParent] = parent2;
                }
            }
        }
        return true;
    }
    
    private int find(int[] parent, int i) {
        while (i != parent[i]) {
            i = parent[parent[i]];
        }
        return i;
    }
    
    //another map solution like 785, Is Graph Bipartite, DFS
    //O(N + E)/O(N + E), E is the length os dislikes
    Map<Integer,List<Integer>> map = new HashMap<>();
    public boolean possibleBipartition2(int N, int[][] dislikes) {
        for (int[] pair : dislikes) {
            map.computeIfAbsent(pair[0], V->new ArrayList<>()).add(pair[1]);
            map.computeIfAbsent(pair[1], V->new ArrayList<>()).add(pair[0]);
        }
        //we mark each person as 1 or -1,when we dfs on this person, all its mapping 
        //should be put in -1, why N=1 because we start from 1
        int[] colors = new int[N+1];
        for (int i = 1; i <= N; i++){
            if (map.get(i) == null) continue;
            //0 means not visited or not colored, suppose i is in 1, then its mapping should 
            //be in -1
            if (colors[i] == 0 && !helper(i,colors,1)) return false;
        }
        return true;
    }
    //the recursive function return person index and its mapping correct or not
    public boolean helper(int index, int[] colors, int color) {
        //if already colored, then we just return it is in correct color or not
        if (colors[index] != 0) return color == colors[index];
        int len = map.get(index).size();
        //mark current person as 1
        colors[index] = color;
        for (int i = 0; i < len; i++) {
            if (!helper(map.get(index).get(i),colors,-1*color)) return false;
        }
        return true; 
    }


    /*
     * followup questions:
     * 
     * there are 2 type of follow up questions:
     * 1. could we assign n nodes with k colors in graph, return true if we can otherwise return false
     * 2. could we assign n nodes with k colors in tree, each node have to assign different color if distance is 1 or 2, 
     * return true if we can otherwise return false, 
     * 3. return how many ways of 1
     * 4. return how many ways of 2
     * 
     * 
     * 5. for 1 or 2, it can be more simpler for example, assign 2 colors to a tree
     * 
     * 
     * for 1, 2 and 5, we do not have to go through all possible solution, we can just start from node 1(0), mark one color, then 
     * 
     * 
     */
    //followup, if we want to divide the map with K colors 
    /*
     * n nodes in graph, k colors, will assume we can use k to color n nodes here, minimal color = k colorable 
     * 
     */

     /*
      * solution for 1 or 2
       n nodes, m color, A describe the edges
      */
    
      static int count = 0;
      public static int solve(int n, int m, int[][] A) {
        if(n == m || n <= 1) return 1;

        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int[] a :A) {
            map.computeIfAbsent(a[0], v->new HashSet<>()).add(a[1]);
            map.computeIfAbsent(a[1], v->new HashSet<>()).add(a[0]);
        }

        int[] memo = new int[n+1];
        //if we only need to know whether we can assign k colors to n nodes, we just use following code, 
        //but if we want to know how many, then we need to 
        //if(solve(0, n, m, memo, map)) return true;
        //else return false;
        //for(int i = 1; i<=m; i++) {
            solve(0, n, m, memo, map);
          //  Arrays.fill(memo, 0);
        //}
        
        return count * m;
      }

      private static boolean solve(int node, int n, int m, int[] memo,  Map<Integer, Set<Integer>> map) {
        if(node == n ) {
            count++;
            return true;
        }

        for(int i = 1; i<=m; i++) {
            if(isSafe(node, i, n, memo, map)) {
                memo[node] = i;
                if(solve(node+1, n, m, memo, map)) return true;
                memo[node] = 0;
            }
        }

        return false;

      }


      private static boolean isSafe(int node, int color, int n, int[] memo, Map<Integer, Set<Integer>> map) {
        for(int i = 0; i < n; i++) {
            if(node != i && map.get(node).contains(i) && memo[i] == color) return false;
        }

        return true;
      }


      public static void main(String[] args) {
        int n = 3;
        int m = 2;
        
        solve(n, m, new int[][]{{0,1}, {0,2}});
        System.out.println(count);
      }
     
     
     //3
     int res = 0;
     public int wayOfColor(int n, int k, int[][] A) {
        if(n <= 1) return 1;

        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int[] a :A) {
            map.computeIfAbsent(a[0], v->new HashSet<>()).add(a[1]);
            map.computeIfAbsent(a[1], v->new HashSet<>()).add(a[0]);
        }


        int res = 0;
        
        for(int i = 1; i<=n; i++) {
            for(int c = 1; c<=k; c++) {
                int[] color = new int[n+1];
                helper(i, color, map, k, c);
            }
        }

        return res;
     }


     private void helper(int node, int[] color, Map<Integer, Set<Integer>> map, int k, int c) {
        if(isFilled(color)) {
            res+=1;
            return;
        }
        if(!map.containsKey(node)) return;

        color[node] = c;
        for(int next: map.get(node)) {
            if(color[next] > 0 && color[next] == c) return;
            for(int i = 1; i<=k; i++) {
                if(c == i) continue;
                helper(next, color, map, k, i);
            }

        }
        color[node] = 0;

     }


     private boolean isFilled(int[] A) {
        for(int a : A) {
            if (a == 0) return false;
        }

        return true;
     }


     //color a tree is relateive easier 



}