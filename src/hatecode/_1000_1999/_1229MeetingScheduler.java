package hatecode._1000_1999;

import java.util.*;
public class _1229MeetingScheduler {
/*
 * 1229. Meeting Scheduler
    Given the availability time slots arrays slots1 and slots2 
    of two people and a meeting duration duration, return the 
    earliest time slot that works for both of them and is of 
    duration duration.
    
    If there is no common time slot that satisfies the 
    requirements, return an empty array.
    
    The format of a time slot is an array of two elements 
    [start, end] representing an inclusive time range from 
    start to end.  
    
    It is guaranteed that no two availability slots of the 
    same person intersect with each other. That is, for any 
    two time slots [start1, end1] and [start2, end2] of the same 
    person, either start1 > end2 or start2 > end1
    
    Input: slots1 = [[10,50],[60,120],[140,210]], 
           slots2 = [[0,15],[60,70]], 
           duration = 8
    Output: [60,68]
    
    Input: slots1 = [[10,50],[60,120],[140,210]], 
           slots2 = [[0,15],[60,70]], 
           duration = 12
    Output: []
    
    1 <= slots1.length, slots2.length <= 10^4
    slots1[i].length, slots2[i].length == 2
    slots1[i][0] < slots1[i][1]
    slots2[i][0] < slots2[i][1]
    0 <= slots1[i][j], slots2[i][j] <= 10^9
    1 <= duration <= 10^6 
*/
    //thinking process; O(nlgn)/O(n)
    
    //the problem is to say: given two free time slots with a during integer, find the 
    //common free time, return a list of idle interval
    
    //use a priorityqueue to store all slots which has duration bigger than duration.
    //sort them by start time so we can have all in a sorted order, then we poll first and compare 2nd
    //in pq, then we can get all qualified candidates
    List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        List<Integer> res = new ArrayList<>();
        if (slots1 == null || slots2 == null) return res;
        
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b)->(a[0] - b[0]));
        for(int[] slot : slots1) {
            if (slot[1] - slot[0] >= duration) pq.add(slot);
        }
        
        for(int[] slot : slots2) {
            if (slot[1] - slot[0] >= duration) pq.add(slot);
        }
        
        while(!pq.isEmpty()) {
            int[] slot = pq.poll();
            if (slot[1] > pq.peek()[0] + duration) {
                res.add(slot[0]);
                res.add(slot[0] + duration);
            }
        }
        return res;
    }
}

