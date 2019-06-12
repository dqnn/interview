package hatecode;

import java.util.*;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MeetingRoomsII
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : 253. Meeting Rooms II
 */
public class MeetingRoomsII {
    /**
     * Given an array of meeting time intervals consisting of start and 
     * end times [[s1,e1],[s2,e2],...] 
     * (si < ei), find the minimum number of conference rooms required.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return 2.


this graph demonstrated how to solve this problem

     |___| |____|
       |_____| |___|

     start:
     | |   |   |
               i
     end :
         |   |  |  |
               end

     time : O(nlogn) space : O(n)

     * @param intervals
     * @return
     */
    // so 252 is a question which wants to know whether the meetings has 
    //conflicts or not, so here for conflicted ones, we need to merge them 
    public int minMeetingRooms(Interval[] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        // we sort them by start and end
        Arrays.sort(starts);
        Arrays.sort(ends);
        int res = 0;
        int end = 0;
        // this is more like two pointers 
        for (int i = 0; i < intervals.length; i++) {
            // if start < end means we find a new valid internal in result set, so 
            // res ++
            if (starts[i] < ends[end]) {
                res++;
            // if start > end means overlap between two meetings, we need to merge one so end moves to next
            } else end++;
        }
        return res;
    }

    // this is to use pq to merge intervals
    //there are two types of such kind question, one is merge interval, another one is to see how many 
    //intervals to occupy space, like this problem
    public int minMeetingRooms2(Interval[] schedule) {
        Arrays.sort(schedule, (a, b) -> a.start - b.start);
        //the PQ keeps how many meeting  rooms are needed for current meeting schedule
        PriorityQueue<Interval> pq = new PriorityQueue<>(schedule.length, (a, b) -> a.end - b.end);
        //we add smallest start time into queue
        pq.offer(schedule[0]);
        for (int i = 1; i < schedule.length; i++) {
            Interval interval = pq.poll();
            //we have a new schedule and want to check first room that can we merge the the schedule 
            //with current meeting,if the schedule start time bigger than current meeting room ends time then 
            //we can merge else we need to open another meeting
            if (schedule[i].start >= interval.end) {
                interval.end = schedule[i].end;
            } else {
                pq.offer(schedule[i]);
            }
            //original meeting still being pop put, and we need to put it back.
            pq.offer(interval);
        }
        return pq.size();
    }
    //latest LC change to array, but logic is the same
    public int minMeetingRooms(int[][] in) {
        if (in == null || in.length < 1) return 0;
        Arrays.sort(in, (a, b)->(a[0] - b[0]));
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->(a[1] - b[1]));
        pq.offer(in[0]);
        for(int i =1; i< in.length;i++) {
            int[] cur = pq.poll();
            if (in[i][0] >= cur[1]) {
                cur[1] = in[i][1];
            } else pq.offer(in[i]);
            pq.offer(cur);
        }
        
        return pq.size();
    }
    
    private static final int START = 1;

    private static final int END = 0;
    
    private class Event {
        int time;
        int type; // end event is 0; start event is 1

        public Event(int time, int type) {
            this.time = time;
            this.type = type;
        }
    }
    
    public int minMeetingRooms_Simulation(Interval[] intervals) {
        int rooms = 0; // occupied meeting rooms
        int res = 0;

        // initialize an event queue based on event's happening time
        Queue<Event> events = new PriorityQueue<>(new Comparator<Event>() {
            @Override
            public int compare(Event e1, Event e2) {
                // for same time, let END event happens first to save rooms
                return e1.time != e2.time ? 
                       e1.time - e2.time : e1.type - e2.type;
            }
        });

        // create event and push into event queue
        for (Interval interval : intervals) {
            events.offer(new Event(interval.start, START));
            events.offer(new Event(interval.end, END));
        }
        
        // process events
        while (!events.isEmpty()) {
            Event event = events.poll();
            if (event.type == START) {
                rooms++;
                res = Math.max(res, rooms);
            } else {
                rooms--; 
            }
        }
        
        return res;
    }
}
