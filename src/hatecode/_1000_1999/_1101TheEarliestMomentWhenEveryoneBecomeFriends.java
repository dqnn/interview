package hatecode._1000_1999;

import java.util.*;
import java.util.stream.*;
public class _1101TheEarliestMomentWhenEveryoneBecomeFriends {
/*
1101. The Earliest Moment When Everyone Become Friends
In a social group, there are N people, with unique integer ids from 0 to N-1.

We have a list of logs, where each logs[i] = [timestamp, id_A, id_B] contains a non-negative integer timestamp, and the ids of two different people.

Each log represents the time in which two different people became friends.  Friendship is symmetric: if A is friends with B, then B is friends with A.

Let's say that person A is acquainted with person B if A is friends with B, or A is a friend of someone acquainted with B.

Return the earliest time for which every person became acquainted with every other person. Return -1 if there is no such earliest time.

 

Example 1:

Input: logs = [[20190101,0,1],[20190104,3,4],[20190107,2,3],[20190211,1,5],[20190224,2,4],[20190301,0,3],[20190312,1,2],[20190322,4,5]], N = 6
Output: 20190301
*/
    //thinking process:
    
    //given a list of logs, log[i] = [20190101,0,1], 0 and 1 person know each 
    //other on 20190101
    //return the earliest time when all people know each other
    
    //union find and sort
    public int earliestAcq(int[][] logs, int N) {
        if(logs == null || logs.length < 1 ) return 0;

        Arrays.sort(logs, (a, b)->(Integer.compare(a[0], a[1])));
        DSU dsu = new DSU(N);
        
        for(int[] log : logs) {
            dsu.union(log[1], log[2]);
            if(dsu.size() == 1) return log[0];
        }
        return -1;
    }
    
    class DSU {
        private int[] parent;
        private int size;
        public DSU(int n) {
            size = n;
            parent = new int[n];
            IntStream.range(0, n).forEach(e->parent[e] = e);
        }
        
        public int find(int x) {
            while( x!= parent[x]) {
                x = parent[x];
            }
            return x;
        }
        
        public void union(int x, int y) {
            x = find(x);
            y = find(y);
            if(x == y) return;
            else parent[x] = y;
            size--;
        }
        
        public int size() {return size;}
    }
}