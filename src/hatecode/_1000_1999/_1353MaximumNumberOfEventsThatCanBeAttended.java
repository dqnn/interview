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

 

Example 1:
*/
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (a,b)->a[0]==b[0]? a[1]-b[1] : a[0]-b[0]);
        PriorityQueue<Integer> q = new PriorityQueue<>();//hold attandable events at each time t; 
        //meaning the ones that haven't ended yet
        int maxT = Integer.MIN_VALUE;
        for (int[] event : events) maxT = Math.max(maxT, event[1]);
        int minT = events[0][0];
        int eventId=0, res =0;
        for(int t=minT; t<=maxT; t++){
            while(eventId<events.length && events[eventId][0]<=t)//at each time, we add the new events that are now attendable
                q.add(events[eventId++][1]);
            while(!q.isEmpty() && q.peek()<t) q.poll();//we remove the ones that are not attendable anymore
            if(!q.isEmpty()){
                q.poll();
                res++;
            }
        }
        return res;
    }
}