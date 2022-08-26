package hatecode._1000_1999;

import java.util.*;

public class _1834SingleThreadedCPU {
/*
1834. Single-Threaded CPU
You are given n tasks labeled from 0 to n - 1 represented by a 2D integer array tasks, where tasks[i] = [enqueueTimei, processingTimei] means that the i-th task will be available to process at enqueueTimei and will take processingTimei to finish processing.

You have a single-threaded CPU that can process at most one task at a time and will act in the following way:

If the CPU is idle and there are no available tasks to process, the CPU remains idle.
If the CPU is idle and there are available tasks, the CPU will choose the one with the shortest processing time. If multiple tasks have the same shortest processing time, it will choose the task with the smallest index.
Once a task is started, the CPU will process the entire task without stopping.
The CPU can finish a task then start a new one instantly.
Return the order in which the CPU will process the tasks.

 

Example 1:

Input: tasks = [[1,2],[2,4],[3,2],[4,1]]
Output: [0,2,3,1]
*/
    
    //thinking process: O(nlgn)/O(n)
    
    /*
     * the problem is to say: 
     * given a 2D array,  [[]], each element [1, 2] means the task start from
     * time 1 and will take time 2 to finish, 
     * 
     * return an array of the process orders, for example
     * [[1,2],[2,4],[3,2],[4,1]] --> [0,2,3,1]
     * 
     * 
     * we use two PQs, first is to sort by enqueueTime asc, processtime asc, index asc.
     * 2nd PQ we will locally sort by process time only because all are qualified candidates 
     * to process,
     * 
     * but they are edge cases need to take care:
     * one is the cur time, it needs to be updated everytime, either 
     * from 2nd pq, or 1st pq.
     * 1. [[1,100],[10000,10000]]
     */
    public int[] getOrder(int[][] A) {
        if (A == null || A.length < 1) return new int[0];
        
        PriorityQueue<int[]> pq= new PriorityQueue<>((a, b)->(a[0] != b[0] ? Integer.compare(a[0], b[0]) 
                                                                           : a[1] == b[1] ? Integer.compare(a[2], b[2]) 
                                                                                          : Integer.compare(a[1], b[1])));
        
        PriorityQueue<int[]> temp= new PriorityQueue<>((a, b)->(a[1] != b[1] ? Integer.compare(a[1], b[1]) 
                                                                             : Integer.compare(a[2], b[2])));
        
        for(int i = 0; i<A.length; i++) {
            pq.offer(new int[]{A[i][0], A[i][1], i});
        }
        
        int[] res = new int[A.length];
        int idx = 0;
        int cur = pq.peek()[0];
        while(!pq.isEmpty() || !temp.isEmpty()) {
            while(!pq.isEmpty() && pq.peek()[0] <= cur) {
                temp.offer(pq.poll());
            }
            //System.out.println(pq);
            
            if (!temp.isEmpty()) {
                int[] task = temp.poll();
                res[idx++] = task[2];
                cur += task[1];
            } else {
                if (!pq.isEmpty()) {
                    cur = pq.peek()[0];
                }
            }
        }
        
        return res;
        
    }
}