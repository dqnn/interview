package leetcode;

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
class EmployeeFreeTime {
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