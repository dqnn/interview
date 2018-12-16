package hatecode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class KillProcess {
/*
582  Kill Process
Given n processes, each process has a unique PID (process id) and its PPID (parent process id).

Each process only has one parent process, but may have one or more children processes. 
This is just like a tree structure. Only one process has PPID that is 0, which means this process 
has no parent process. All the PIDs will be distinct positive integers.

We use two list of integers to represent a list of processes, where the first list contains PID 
for each process and the second list contains the corresponding PPID.

Now given the two lists, and a PID representing a process you want to kill, return a list of 
PIDs of processes that will be killed in the end. You should assume that when a process is killed, 
all its children processes will be killed. No order is required for the final answer.

Example 1:
Input: 
pid =  [1, 3, 10, 5]
ppid = [3, 0, 5, 3]
kill = 5
Output: [5,10]
Explanation: 
           3
         /   \
        1     5
             /
            10
Kill 5 will also kill 10.
Note:
The given kill id is guaranteed to be one of the given PIDs.
n >= 1.
 */
    //thinking process: given two arrays, pid is child process id, ppid is corresponding parent process id
    // with a kill process id, find all processes which would be killed.
    
    //so original idea is to use map (ppid, pid list), but each pid may point to other pid lists
    //so cannot use map to track more than two levels mapping, this requires DFS or recursive
    
    //we use Map<ppid, Queue<Pid>> to store for pid =  [1, 3, 10, 5] ppid = [3, 0, 5, 3]
    //we will setup 3->[1,5], 0->[3], 5->[10], so if kill = 5, we will DFS all values from 5
    //we will use BFS here to horizonte scan the tree
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill){
        List<Integer> res = new ArrayList<>();
        if (pid == null || pid.size() < 1 || ppid == null || ppid.size() < 1) {
            return res;
        }
        Map<Integer, Queue<Integer>> map = new HashMap<>();
        for(int i = 0; i< pid.size(); i++) {
            map.computeIfAbsent(ppid.get(i), v->new LinkedList<>()).offer(pid.get(i));
        }
        Queue<Integer> pq = map.get(kill);
        res.add(kill);
        while (pq != null && !pq.isEmpty()) {
            int size = pq.size();
            for(int i = 0; i< size;i++) {
                int child = pq.poll();
                res.add(child);
                if (map.containsKey(child)) {
                    for(int temp: map.get(child)) {
                        pq.offer(temp);
                    }
                }
            }
        }
        return res;
    }
}