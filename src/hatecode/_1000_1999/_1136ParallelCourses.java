package hatecode._1000_1999;

import java.util.*;
public class _1136ParallelCourses {
/*
1136. Parallel Courses
There are N courses, labelled from 1 to N.

We are given relations[i] = [X, Y], representing a prerequisite relationship between course X and course Y: course X has to be studied before course Y.

In one semester you can study any number of courses as long as you have studied all the prerequisites for the course you are studying.

Return the minimum number of semesters needed to study all courses.  If there is no way to study all the courses, return -1.

Input: N = 3, relations = [[1,3],[2,3]]
Output: 2
Explanation: 
In the first semester, courses 1 and 2 are studied. In the second semester, course 3 is studied.
*/
    //thinking process:
    
    //the problem is to say: given int N as N courses, int[][] relations means [X, Y]
    //Y has to be studied before X, so return the mini semesters have to take?
    
    //the key thing in mind is how we start and how to process, given the conditions, 
    //we would like to find the least dependency node, start from there, bsf visit all 
    //nodes which has same count of indegree, 
    public int minimumSemesters_TopSort(int N, int[][] relations) {
        if (relations == null || relations.length < 1) return -1;
         
        Map<Integer, List<Integer>> g = new HashMap<>(); // key: prerequisite, value: course list. 
        int[] inDegree = new int[N + 1]; // inDegree[i]: number of prerequisites for i.
        for (int[] r : relations) {
            g.computeIfAbsent(r[0], l -> new ArrayList<>()).add(r[1]); // construct graph.
            inDegree[r[1]]++; // count prerequisites for r[1].
        }
        Queue<Integer> q = new LinkedList<>(); // save current 0 in-degree vertices.
        for (int i = 1; i <= N; i++) if (inDegree[i] == 0) q.offer(i);

        int semester = 0;
        while (!q.isEmpty()) { // BFS traverse all currently 0 in degree vertices.
            for (int sz = q.size(); sz > 0; sz--) { // sz is the search breadth.
                int c = q.poll();
                N--;
                if (!g.containsKey(c)) continue; // c's in-degree is currently 0, but it has no prerequisite.
                for (int course : g.remove(c))
                    if (--inDegree[course] == 0) // decrease the in-degree of course's neighbors.
                        q.offer(course); // add current 0 in-degree vertex into Queue.
            }
            semester++; // need one more semester.
        }
        return N == 0 ? semester : -1;
    }
    
    //this is the best performance one
    public int minimumSemesters(int N, int[][] relations) {
        int[] cnt = new int[N];
        Arrays.fill(cnt, 1);
        List<Integer> adj[] = new ArrayList[N];
        
        for(int i = 0; i < N; i++) adj[i] = new ArrayList<>();
        for(int[] rel: relations) adj[rel[0]-1].add(rel[1]-1);
        int[] visited = new int[N];
        for(int i = 0; i < N; i++){
            if(!dfs(visited, adj, i, cnt))
                return -1;
        }
        
        int max = 1;
        for(int c: cnt) max = Math.max(max, c);
        
        return max;
    }
    
    public boolean dfs(int[] visited, List<Integer>[] adj, int v, int[] cnt){
        if(visited[v] == 2) return true;
        else if(visited[v] == 1) return false;
        
        visited[v] = 1;
        
        for(int next: adj[v]){
            if(!dfs(visited, adj, next, cnt)){
                return false;
            }
            cnt[v] = Math.max(cnt[v], cnt[next] + 1);
        }

        visited[v] = 2;
        return true;
    }
}