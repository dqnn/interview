package hatecode._1000_1999;

import java.util.*;
public class _1353MaximumNumberOfEventsThatCanBeAttended {
/*
1353. Maximum Number of Events That Can Be Attended
Given an array of events where events[i] = [startDayi, endDayi]. 
Every event i starts at startDayi and ends at endDayi.

You can attend an event i at any day d where startTimei <= d <= endTimei. 
Notice that you can only attend one event at any time d.

Return the maximum number of events you can attend.

 

Input: events= [[1,2],[2,3],[3,4],[1,2]]
Output: 4
*/
    //thinking process: O(nlgn + max(A) - min(A))/O(n)
    
    //the problem is to say: given list of [start, end] events, start here means the date-th number,
    //events= [[1,2],[2,3],[3,4],[1,2]],
    //[1,2],[1,2], we can attend 1st at day 1, 2nd attend day 2.
    //this problem is totally different than previous ones, previous is more 
    //ask on merge intervals, analysis relationships between intervals, here is more 
    //on the greedy on interval ends, we need to use a way to count how to attend most events.
    
    //we sort events asc by start, end event date, then get min and max time frame
    //firstly we add all attenable events into PQ, then remove ones we cannot attend.
    //then poll first one to attend, because everytime we should choose the time end earlier
    //it is like sweeping line 
    public int maxEvents(int[][] A) {
        Arrays.sort(A, (a,b)->a[0]==b[0]? a[1]-b[1] : a[0]-b[0]);
        PriorityQueue<Integer> q = new PriorityQueue<>();//hold attandable events at each time t; 
        //meaning the ones that haven't ended yet
        int maxT = Integer.MIN_VALUE;
        //get min and max time point of the array
        for (int[] event : A) maxT = Math.max(maxT, event[1]);
        int minT = A[0][0];
        int eventId=0, res =0;
        for(int t=minT; t<=maxT; t++){
          //add attend-able events
            while(eventId<A.length && A[eventId][0]<=t)
                q.add(A[eventId++][1]);
            //remove non-attendable events
            while(!q.isEmpty() && q.peek()<t) q.poll();//we remove the ones that are not attendable anymore
            
            //here means event end time >= t, we an attend
            if(!q.isEmpty()){
                q.poll();
                res++;
            }
        }
        return res;
    }
}