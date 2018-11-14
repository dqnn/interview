package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

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

    // this is to use stack to merge intervals
    public int minMeetingRooms2(Interval[] intervals) {
        Arrays.sort(intervals, (a, b) -> a.start - b.start);
        PriorityQueue<Interval> heap = new PriorityQueue<>(intervals.length, (a, b) -> a.end - b.end);
        heap.offer(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            Interval interval = heap.poll();
            if (intervals[i].start >= interval.end) {
                interval.end = intervals[i].end;
            } else {
                heap.offer(intervals[i]);
            }
            heap.offer(interval);
        }
        return heap.size();
    }
}
