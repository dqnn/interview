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
     * followup, 
     * 1. if we want to divide the map with K colors, how many ways, 
     * 2. if the graph is tree, child and parent cannot share the same color, how many are they? or return true if we can color them
     * 3. return true or if we can color n nodes with k colors
     * 
     */
    //followup, if we want to divide the map with K colors 
    /*
     * n nodes in graph, k colors, will assume we can use k to color n nodes here, minimal color = k colorable 
     * 
     */


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