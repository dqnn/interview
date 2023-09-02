package hatecode._0001_0999;

import java.util.*;

/**
 * Description : 253. Meeting Rooms II
 */
public class _253MeetingRoomsII {
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

    // O(nlgn)/O(n)
    //this is to use pq to merge intervals
    //there are 3 types of such kind question, 
    /*
    1. is to merge interval, another one is to see how many  
    2. to see how many meetings rooms needed
    3. to see how many meetings need to be cancelled 
     */ 
    //intervals to occupy space, like this problem
    
    //because we still to compare the interval to interval in later intervals, so 
    //we put them in PQ sorted by end

    /*
     * [[1,3],[2,5],[6,8]] example, 
     * 
     */
    public int minMeetingRooms2(Interval[] schedule) {
        Arrays.sort(schedule, (a, b) -> Integer.compare(a.start, b.start));
        //PQ is sorted by end time which means the earliest schedule that a room is available, also stands for meetings will be attended
        PriorityQueue<Interval> pq = new PriorityQueue<>(schedule.length, (a, b) -> Integer.compare(a.end, b.end));
        //we add smallest start time into queue
        pq.offer(schedule[0]);
        for (int i = 1; i < schedule.length; i++) {
            Interval interval = pq.poll();
            //we are at meeting i, the earliest room is available at inteval.end, if current meeting i start time bigger than 
            //earliest end time, then we can re-use the the room, if not, we need a new room, so we place the meeting i into PQ 
            if (schedule[i].start >= interval.end) {
                interval.end = schedule[i].end;
            } else {
                //have overlap, needs to add to PQ
                pq.offer(schedule[i]);
            }
            //original meeting still being pop put, and we need to put it back.
            pq.offer(interval);
        }
        return pq.size();
    }
    //latest LC change to array, but logic is the same
    //we should always use Integer.compare to avoid overflow
    
    //we mainly want to get the intervals which overlapped,
    //then we count how many intervals in pq last
    public int minMeetingRooms(int[][] A) {
        
        Arrays.sort(A, (a, b)->(Integer.compare(a[0], b[0])));
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->(Integer.compare(a[1], b[1])));
        pq.offer(A[0]);
        
        for(int i = 1; i<A.length; i++) {
            int[] cur = pq.poll();
            if (A[i][0] >= cur[1]) {
                cur[1] = A[i][1];
            } else pq.offer(A[i]);
            
            pq.offer(cur);
        }
        
        return pq.size();
    }

    /*
     interview friendly TC: O(n)/O(max)

     the problem is to use +1 -1 on endpoints,so we can have count how many overlapped intervals
    */
    public int minMeetingRooms_Best(int[][] A) {
        if (A == null || A.length < 1 || A[0].length < 1) {
            return 0;
        }
        
        int max = Integer.MIN_VALUE;
        for(int[] a :A) {
            max = Math.max(max, a[1]);
        }
        
        int[] s = new int[max+1], e = new int[max+1];
        
        for(int[] a : A) {
            s[a[0]]++;
            e[a[1]]++;
        }
        
        int res = 0, temp = 0;
        for(int i = 0; i<=max; i++) {
            temp += s[i];
            temp -= e[i];
            res = Math.max(res, temp);
        }
        
        return res;
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
