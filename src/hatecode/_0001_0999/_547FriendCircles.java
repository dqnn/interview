package hatecode._0001_0999;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class _547FriendCircles {
/*
 * 547. Friend Circles
There are N students in a class. Some of them are friends, while some are not. 
Their friendship is transitive in nature. For example, if A is a direct friend of B, 
and B is a direct friend of C, then A is an indirect friend of C. And we defined a 
friend circle is a group of students who are direct or indirect friends.

Given a N*N matrix M representing the friend relationship between students in the class. 
If M[i][j] = 1, then the ith and jth students are direct friends with each other, 
otherwise not. And you have to output the total number of friend circles among all 
the students.

Example 1:
Input: 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
The 2nd student himself is in a friend circle. So return 2.
Example 2:
Input: 
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are 
direct friends, 
so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, 
so return 1.
Note:
N is in range [1,200].
M[i][i] = 1 for all students.
If M[i][j] = 1, then M[j][i] = 1.
 */
    //interview friendly, 
    //thinking process: 2D matrix m[i][j] =1 means i knew j, and knowship will be transitive, 
    //so j will know i, so on, return how many circles are there
    // 
    public int findCircleNum(int[][] m) {
        if (m == null || m.length < 1 || m[0].length < 1) {
            return 0;
        }
        int res = 0;
        int len = m.length;
        //store the person who we have visited
        int[] visited = new int[len];
        //queue is to store the person who i does not know
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < len; i++) {
            //already in one circle
            if (visited[i] == 1) continue;
            q.offer(i);
            while(!q.isEmpty()) {
                int s = q.poll();
                visited[s] = 1;
                //row scan, if someone s who know but not in visited, add to q
                for(int j = 0; j < len; j++) {
                    if (m[s][j] == 1 && visited[j] == 0) {
                        q.offer(j);
                    }
                }
            }
            //we come here means we have visited for all person that i knew, so we plus one
            res++;
        }
        return res;
    }
        
    // union-find, here m[i][j] = 1, then we find i and j common parent, if they are the same
    //then they are in same circle, easy to understand
        int find(int parent[], int i) {
            if (parent[i] == -1)
                return i;
            return find(parent, parent[i]);
        }

        void union(int parent[], int x, int y) {
            int xset = find(parent, x);
            int yset = find(parent, y);
            //if they are not same, we mark x parent is y
            if (xset != yset)
                parent[xset] = yset;
        }
        public int findCircleNum2(int[][] M) {
            int[] parent = new int[M.length];
            Arrays.fill(parent, -1);
            for (int i = 0; i < M.length; i++) {
                for (int j = 0; j < M.length; j++) {
                    if (M[i][j] == 1 && i != j) {
                        union(parent, i, j);
                    }
                }
            }
            int count = 0;
            for (int i = 0; i < parent.length; i++) {
                if (parent[i] == -1)
                    count++;
            }
            return count;
        }
}