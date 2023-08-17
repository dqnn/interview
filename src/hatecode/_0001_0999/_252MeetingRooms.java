package hatecode._0001_0999;

import java.util.Arrays;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MeetingRooms
 * Date : Agu, 2018
 * Description : 252. Meeting Roomπs
 */
public class _252MeetingRooms {
    /**
     * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
     * determine if a person could attend all meetings.

     For example,
     Given [[0, 30],[5, 10],[15, 20]],
     return false.

     time : O(nlogn)
     space : O(1)

     * @param intervals
     * @return
     */
   
     // interview friendly, use + - on endpoints, so we can operate the total
     public boolean canAttendMeetings(int[][] A) {
        if (A == null || A.length < 1 || A[0].length < 1) return true;
        
        int max = Integer.MIN_VALUE;
        for(int[] a:A) {
            max = Math.max(max, a[1]);
        }
        
        int[] s = new int[max+1], e = new int[max+1];
        
        for(int[] a : A) {
            s[a[0]]++;
            e[a[1]]++;
        }
        
        int total = 0;
        for(int i = 0; i<=max; i++) {
            total += s[i];
            total -= e[i];
            if (total > 1) return false;
        } 
        
        return true;
    }
    
    
    
    
     public boolean canAttendMeetings_BF(Interval[] intervals) {
        //if input is null or only 1 schedule then it is true
        if (intervals == null || intervals.length < 2) return true;
        // this is good, so we can sort by object easily, also PriorityQueue has similiar function
        // so we sort by start first, then if end has overlap then we cannot attend all meetings
        Arrays.sort(intervals, (x, y) -> x.start - y.start);
        for (int i = 1; i <intervals.length; i++) {
            if (intervals[i - 1].end > intervals[i].start) {
                return false;
            }
        }
        return true;
    }
    //LC changed the DS
    public boolean canAttendMeetings_Sort(int[][] ins) {
        if(ins == null || ins.length < 1) return true;;
        Arrays.sort(ins, (a, b)->(a[0] - b[0]));
        
        int s = ins[0][0], e = ins[0][1];
        for(int i = 1; i< ins.length;i++) {
            if(e > ins[i][0]) return false;
            else e = ins[i][1];
        }
        return true;
    }
}
