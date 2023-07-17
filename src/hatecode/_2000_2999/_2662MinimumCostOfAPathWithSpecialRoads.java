package hatecode._2000_2999;

import java.util.*;

public class _2662MinimumCostOfAPathWithSpecialRoads {
    /*
    2662. Minimum Cost of a Path With Special Roads
    
    You are given an array start where start = [startX, startY] represents your initial position (startX, startY) in a 2D space. You are also given the array target where target = [targetX, targetY] represents your target position (targetX, targetY).
    
    The cost of going from a position (x1, y1) to any other position in the space (x2, y2) is |x2 - x1| + |y2 - y1|.
    
    There are also some special roads. You are given a 2D array specialRoads where specialRoads[i] = [x1i, y1i, x2i, y2i, costi] indicates that the ith special road can take you from (x1i, y1i) to (x2i, y2i) with a cost equal to costi. You can use each special road any number of times.
    
    Return the minimum cost required to go from (startX, startY) to (targetX, targetY).
    
     
    
    Example 1:
    
    Input: start = [1,1], target = [4,5], specialRoads = [[1,2,3,3,2],[3,4,4,5,1]]
    Output: 5
    */

    /*
     * thinking process: O(n^lgn^2), n = specialRoads.length
     * 
     * the problem is to say: 
     * given one two points, start and end, 
     * 
     * [1,2,3,3,2]--> [1,2] -> [3,3] with cost 2
     * 
     * figure out min cost from s to e.
     * 
     * this is pretty common question, but here the difference is that it does not specify a map, you need to 
     * figure out a map(graph) then run BFS or dijstra on a PQ
     * 
     * PQ is dijstra algorithms 
     * 
     * Bellman-floyd is to use a Queue to visit each one, ans store the results into array, dist[n]
     * then ask for max in the array, 
     * 
     * 
     * 
     * 
     */
        public int minimumCost(int[] s, int[] e, int[][] roads) {
            if(s[0] == e[0] && s[1]== e[1]) return 0;
            
            
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->(Integer.compare(a[2], b[2])));
            pq.offer(new int[]{s[0], s[1], 0});
            
            Set<String> visited = new HashSet<>();
            
            while(!pq.isEmpty()) {
                int[] ele = pq.poll();
                
                //System.out.println(Arrays.toString(ele));
                
                if(ele[0] == e[0] && ele[1] == e[1]) return ele[2];
    
                String key = getKey(ele);
                if (visited.contains(key)) continue;
                visited.add(key);
                
                for(int[] r: roads) {
                   int cost = r[4] + ele[2] + Math.abs(r[0] - ele[0]) + Math.abs(r[1] - ele[1]);
                   pq.offer(new int[]{r[2], r[3], cost});
                }
                
                pq.offer(new int[]{e[0], e[1], ele[2] + Math.abs(e[0] - ele[0]) + Math.abs(e[1] - ele[1])});
                
                
            }
            
            return -1;
        }
        
        private String getKey(int[] A){
            return A[0] + "-" + A[1];
        }
    }