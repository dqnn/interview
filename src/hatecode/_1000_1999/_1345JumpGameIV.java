package hatecode._1000_1999;

import java.util.*;
import java.util.stream.IntStream;

public class _1345JumpGameIV {
    /*
    1345. Jump Game IV
    Given an array of integers arr, you are initially positioned at the first index of the array.
    
    In one step you can jump from index i to index:
    
    i + 1 where: i + 1 < arr.length.
    i - 1 where: i - 1 >= 0.
    j where: arr[i] == arr[j] and i != j.
    Return the minimum number of steps to reach the last index of the array.
    
    Notice that you can not jump outside of the array at any time.
    
     
    
    Example 1:
    
    Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
    Output: 3
    */

    /*
     * thinking process: 
     * start at 1st element, each step you can jump to 3 indexes, 
     * (i+1, i-1, j), j means A[i] = A[j],  return the min number if you can reach the last index
     * 
     * single source --> single destination 
     * 
     * 
     * 
     */
        public int minJumps_BFS(int[] A) {
            Map<Integer, List<Integer>> map = new HashMap<>();
            
            int n = A.length;
            IntStream.range(0, n).forEach(i ->map.computeIfAbsent(A[i], v->new ArrayList<>()).add(i));
            
            Queue<Integer> q = new LinkedList<>();
            q.offer(0);
            
            int step = 0;
            Set<Integer> visited = new HashSet<>();
            while(!q.isEmpty()) {
                int size = q.size();
                
                while(size-- > 0) {
                    int idx = q.poll();
                    if (idx == n - 1) return step;
                    
                    List<Integer> next = map.get(A[idx]);
                    next.add(idx - 1);next.add(idx + 1);
                    for(int pos : next) {
                        if (pos >=0 && pos < n && !visited.contains(pos)) {
                            q.offer(pos);
                            visited.add(pos);
                        }
                    }
                    //next.clear();
                }
                step ++;
            }
            
            return -1;
        }
        
        //bi-BFS
        public int minJumps(int[] A) {
            Map<Integer, List<Integer>> map = new HashMap<>();
            
            int n = A.length;
            IntStream.range(0, n).forEach(i ->map.computeIfAbsent(A[i], v->new ArrayList<>()).add(i));
            
            Queue<Integer> fwd = new LinkedList<>(), back = new LinkedList<>();
            fwd.offer(0);
            back.offer(n-1);
            
            
            int step = 0;
            Set<Integer> visited = new HashSet<>();
            while(true) {
                if (fwd.size() > back.size()) {
                    Queue<Integer> temp = fwd;
                    fwd = back;
                    back = temp;
                }
                
                int size = fwd.size();
                while(size-- > 0) {
                    int i = fwd.poll();
                    if (i == n -1) return step;
                    List<Integer> next = map.get(A[i]);
                    next.add(i + 1);
                    next.add(i - 1);
                    for(int j : next) {
                        if (j >=0 &&j <n && !visited.contains(j)) {
                            fwd.offer(j);
                            visited.add(j);
                        }
                    }
                    next.clear();
                }
                step++;
            }
        }
        
        
    }