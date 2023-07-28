package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class _759EmployeeFreeTime {
    /*
759. Employee Free Time

     * We are given a list schedule of employees, which represents the working time for each employee.

Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).  Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

 

Example 1:

Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
     */
    //interview friendly but maybe the mid part will be asked to remove in interview, see
    //employeeFreeTime2 for the best answer
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> res = new ArrayList<>();
        if (schedule == null || schedule.size() < 1) {
            return res;
        }
        Queue<Interval> q = new PriorityQueue<>((a, b)->(a.start - b.start));
        schedule.forEach(e->q.addAll(e));
        
        int start = q.peek().start;
        int end = q.peek().end;
        int size = q.size();
        List<Interval> mid = new ArrayList<>();
        for(int i =0; i< size;i++) {
            Interval interval = q.poll();
            if(interval.start <= end) {
                end = Math.max(end, interval.end);
            } else {
                mid.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }
        mid.add(new Interval(start, end));
        size = mid.size();
        for(int i = 0; i<= size - 2;i++) {
            res.add(new Interval(mid.get(i).end, mid.get(i+1).start));
        }
        return res;
    }
    
    //best one, note that when we visit the queue, we do not need start
    public List<Interval> employeeFreeTime2(List<List<Interval>> schedule) {
        List<Interval> res = new ArrayList<>();
        if (schedule == null || schedule.size() < 1) {
            return res;
        }
        Queue<Interval> q = new PriorityQueue<>((a, b)->(a.start - b.start));
        schedule.forEach(e->q.addAll(e));
        
        //we do not need start, since we want to free time,so start will be in iterator
        int end = q.peek().end;
        int size = q.size();
        for(int i =0; i< size;i++) {
            Interval interval = q.poll();
            if(interval.start <= end) {
                end = Math.max(end, interval.end);
            } else {
                res.add(new Interval(end, interval.start));
                end = interval.end;
            }
        }
        
        return res;
    }
}