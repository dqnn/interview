package hatecode._0001_0999;

import java.util.*;
public class _630CourseScheduleIII {
/*
630. Course Schedule III
There are n different online courses numbered from 1 to n. Each course has some duration(course length) t and closed on dth day. A course should be taken continuously for t days and must be finished before or on the dth day. You will start at the 1st day.

Given n online courses represented by pairs (t,d), your task is to find the maximal number of courses that can be taken.

Example:

Input: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
Output: 3
*/
    //give a list of course scheule(last time, end time), so max courses we can take
    //we sort array by end date, from early to late, 
    //then loop in that array, has a variable starts from 0, if we can find 
    //current > its end data, then we did not take this course and remove from q.
    //q.size() is the courses numbers

    // key points:
    /* Greedy: 
     * 1.  choose course which ends earlier.
     * 2.  if ends same time, choose course which has shorter duration
     */
    public int scheduleCourse(int[][] A) {
        if(A == null || A.length < 1) return 0;
        
        Arrays.sort(A, (a, b)->Integer.compare(a[1], b[1]));
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b)->Integer.compare(b, a));
        int time = 0;
        for(int[] c : A)  {
            time += c[0];
            q.offer(c[0]);
            if (time > c[1]) time -= q.poll();
        }
        
        return q.size();
    }
}