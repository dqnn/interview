package hatecode._1000_1999;

import java.util.*;
public class _1284MinimumNumberOfFlipsToConvertBinaryMatrixToZeroMatrix
 {
    /*
     * 1284. Minimum Number of Flips to Convert Binary Matrix to Zero Matrix Given a
     * m x n binary matrix mat. In one step, you can choose one cell and flip it and
     * all the four neighbours of it if they exist (Flip is changing 1 to 0 and 0 to
     * 1). A pair of cells are called neighboors if they share one edge.
     * 
     * Return the minimum number of steps required to convert mat to a zero matrix
     * or -1 if you cannot.
     * 
     * Binary matrix is a matrix with all cells equal to 0 or 1 only.
     * 
     * Zero matrix is a matrix with all cells equal to 0.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: mat = [[0,0],[0,1]] Output: 3 Explanation: One possible solution is to
     * flip (1, 0) then (0, 1) and finally (1, 1) as shown.
     */
    
    //thinking process: O(rc*2^(rc))/O(2^(rc))
    
    //the problem is to say: given one 2D matrix, each cell only could be 1 or 0, then we can flip
    //one cell and its 4 neighbours, 0->1 or 1->0, then return min flips so that whole matrix can be all 0.
    
    //BFS and DFS.
    
    //NOTE: this problem is not typical 4-direction problem, because the final result is determined by the matrix,or 
    //by matrix state, not related to the cell track, so we need to figure out the state change path, 
    //if BFS, from original state, we have r*c states in step 2, so ....
    //TODO: if DFS, dfs is tricky compared to BFS, here since we have define the success state, then for steps
    
    
    public int minFlips_BFS(int[][] mat) {
       
        // Instantiate initial config with zero steps
        State init = new State(mat, 0); 
        if (init.isDone()) {
            return init.step;
        }
        
        // State seen so far
        Set<String> visited = new HashSet<>();
        visited.add( init.toString());
        
        // BFS
        Queue<State> q = new LinkedList<>();
        q.add(init);
        while(!q.isEmpty()) {
            State c = q.poll();
            for(State next: c.getNeighbours()) { // all configs generated by flipping once
                String nextStr = next.toString();
                if (!visited.contains(nextStr)) { // unvisited
                    //find correct path
                    if (next.isDone()) return next.step;
                    visited.add(nextStr);
                    q.add(next);
                }
            }
        }
        return -1; // not possible to reach destination from source
    } 
    
    // Internal class to represent matrix State
    class State {
        int[][] m;
        int step; // number of steps taken to generate current config from initial matrix
        
        // constructor
        State(int[][] m, int step) {
            this.m = m;
            this.step = step;
        }
        
        // check if matrix is zero matrix
        private boolean isDone() {
            for (int i=0; i<m.length; i++) {
                for (int val : m[i])
                    if (val != 0)
                        return false;
            }
            return true;
        }
        
        // generate all (m*n) possible configs by flipping ONCE in current matrix
        public List<State> getNeighbours() {
            List<State> neighbours = new ArrayList<>();
            int r = m.length, c = m[0].length;
            for (int i=0; i<r; i++)
                for(int j=0; j<c; j++)
                    neighbours.add(flip(i, j));
            return neighbours;
        }
        
        // next config by flipping value at (row,col) position (and neighbours)
        private State flip(int i, int j) {
            // create a new copy of matrix
         // create a new copy of matrix
            int[][] next = Arrays.stream(m).map(int[]::clone).toArray(int[][]::new);
            int r = next.length, c = next[0].length;
            
            next[i][j] = next[i][j] ^ 1;
            int[][] dirs = {{-1,0}, {0,-1}, {0,1}, {1,0}};
            for(int[] d: dirs) {
                int x = i + d[0];
                int y = j + d[1];
                if (x >= 0 && x < r && y >=0 && y < c) {
                    next[x][y] = next[x][y] ^ 1;
                }
            }

            // increment step by one in resultant matrix 
            return new State(next, step+1);
        }
		
		// generate string of 0s and 1s to represent matrix
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<m.length; i++) {
                for (int num: m[i])
                    sb.append(num);
            }
            return sb.toString();
        }
    }
    
    //bit set, faster but not generic, because int has length constrains
    private static final int[] d = {0, 0, 1, 0, -1, 0};
    public int minFlips_BFS2(int[][] mat) {
        int start = 0, m = mat.length, n = mat[0].length;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                start |= mat[i][j] << (i * n + j); // convert the matrix to an int.
        Queue<Integer> q = new LinkedList<>(Arrays.asList(start));
        Set<Integer> seen = new HashSet<>(q);
        for (int step = 0; !q.isEmpty(); ++step) {
            for (int sz = q.size(); sz > 0; --sz) {
                int cur = q.poll();
                if (cur == 0) // All 0s matrix found.
                    return step;
                for (int i = 0; i < m; ++i) { // traverse all m * n bits of cur.
                    for (int j = 0; j < n; ++j) {
                        int next = cur;
                        for (int k = 0; k < 5; ++k) { // flip the cell (i, j) and its neighbors.
                            int r = i + d[k], c = j + d[k + 1];
                            if (r >= 0 && r < m && c >= 0 && c < n)
                                next ^= 1 << (r * n + c);
                        }
                        if (seen.add(next)) // seen it before ?
                            q.offer(next); // no, put it into the Queue.
                    }
                }    
            }
        }
        return -1; // impossible to get all 0s matrix.
    }
    
    
    //DFS
    public int minFlips_DFS(int[][] m) {
        int r = m.length, c = m[0].length;
        HashMap<String, Integer> successStateStepsMap = new HashMap<>();
        int res = helper(m, r, c, new HashSet<>(), successStateStepsMap);
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    
    public static boolean check(int[][] m){
        int r = m.length, c = m[0].length;
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(m[i][j] == 1) return false;
            }
        }
        return true;
    }
    
    public static void flip(int[][] mat, int n, int m, int i, int j){
        mat[i][j] = mat[i][j] ^ 1;
        if(i - 1 >= 0) mat[i - 1][j] = mat[i - 1][j] ^ 1;
        if(j - 1 >= 0) mat[i][j - 1] = mat[i][j - 1] ^ 1;
        if(i + 1 < n) mat[i + 1][j] = mat[i + 1][j] ^ 1;
        if(j + 1 < m) mat[i][j + 1] = mat[i][j + 1] ^ 1;
    }
    
    public static int helper(int[][] mat, int n, int m, HashSet<String> set, HashMap<String, Integer> dp){
        if(check(mat)) return 0;
        String t = "";
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                t += Integer.toString(mat[i][j]);
            }
        }
        
        if(dp.containsKey(t)) return dp.get(t);
        if(set.contains(t)) return Integer.MAX_VALUE;
        
        set.add(t);
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                flip(mat, n, m, i, j);
                int small = helper(mat, n, m, set, dp);
                if(small != Integer.MAX_VALUE) min = Math.min(min, 1 + small);
                flip(mat, n, m, i, j);
            }
        }
        set.remove(t);
        dp.put(t, min);
        return min;
    }
    
}