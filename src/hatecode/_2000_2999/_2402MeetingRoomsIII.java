import java.util.*;

public class _2402MeetingRoomsIII {
    /*
    2402. Meeting Rooms III
    You are given an integer n. There are n rooms numbered from 0 to n - 1.

You are given a 2D integer array meetings where meetings[i] = [starti, endi] means that a meeting will be held during the half-closed time interval [starti, endi). All the values of starti are unique.

Meetings are allocated to rooms in the following manner:

Each meeting will take place in the unused room with the lowest number.
If there are no available rooms, the meeting will be delayed until a room becomes free. The delayed meeting should have the same duration as the original meeting.
When a room becomes unused, meetings that have an earlier original start time should be given the room.
Return the number of the room that held the most meetings. If there are multiple rooms, return the room with the lowest number.

A half-closed interval [a, b) is the interval between a and b including a and not including b.

 

Example 1:

Input: n = 2, meetings = [[0,10],[1,5],[2,7],[3,4]]
Output: 0


     
     
     
    */
    
    /*

    thinking process: O((mlgm + nlgn + mn * lgn)/O(n))
     m = A.length
     the problem is to say: given one integer n represents the meeting room, you have anothe array 
     A[i][j] means meeting start time and end time as interval,  there are some rules for the meeting schedule, 

     1. meeting will be scheduled from lowest number 
     2. if no meeting room available, then delay the meeting to earliest room, he duratin stay the same
     3. if two meeting room are available, thne pick the lowest room number

     return the meeting room which has the most meeting, if there is a tie, return the lower meeting room number

    

    we can easily simulate the process, say our pq 
    [0, 10, 1] means room 0 will be available a time 10, hold 1 meeting. 
    we sort each element by room[1], if there is a tie, smaller room wins
    we also need to sort the input array A, start time first, if there is a tie, end time earlier wins

    then we visit array A, note we use for loop, no i incremental in for loop line because we want to aviod if next meeting is far away

    while some meeting room potenitally will be re-used accoring to the rule, for example 
    4  [[2,13], [3,12],[7,10], [17,19],[18,19]] 

    we can see room 0 will take [2, 13] --> [0, 13, 1]
               room 1 will take [3, 12] --> [1, 12, 1]
               room 2 will take [7, 10] --> [2, 10, 1]

    so should room 3 take [17,19], NOOOOO! because we will see 0 will be available at 13, 1 at 12 and 2 at 10, we need to resort them, 

    that;s why line 90-92 came in. 

    one meeting room can take a room when room available time equals time start meeting. 

    so when we face this situation, we just mark the room available time same as next meeting start time, so they have chance to 
    race in pq
     



    3   [[1,20],[2,10],[3,5],[4,9],[6,8]]
     
     pq: 
     0 : [0,0,0], [1,0,0], [2,0,0]
     1 : [1,0,0], [2,0,0], [0, 20, 1]
     2 : [2,0,0], [1, 10, 1], [0, 20, 1]
     3 : [2, 5, 1], [1, 10, 1], [0, 20, 1]
     4 : [1, 10, 1], [2, 10, 2],[0, 20, 1]
     5. [2, 10, 2],[1, 12, 2], [0, 20, 1], 
    
    */
    public int mostBooked(int n, int[][] A) {
        if (n  <= 0 || A == null || A.length < 1 || A[0].length < 1) return -1;
        
        Arrays.sort(A, (a, b)->(a[0]== b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0])));
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b)->(a[1] == b[1]? Long.compare(a[0], b[0]) : Long.compare(a[1], b[1])));
        IntStream.range(0, n).forEach(i ->pq.offer(new long[]{i, 0, 0}));
        
        for(int i = 0; i< A.length;) {
            int[] a = A[i];
            int s = a[0], e = a[1];
            long[] room = pq.poll();
            if (room[1] < s) {
                room[1] = s;
            } else if (room[1] == s) {
                room[1]= e;  
                room[2] += 1;
                i++;
            } else {
                room[1] += e -s;
                room[2] += 1;
                i++;
            }
            
            
            pq.offer(room);
        }
        
        
        int res = n-1;
        long meetingCount = -1;
        while(!pq.isEmpty()) {
            long[] room = pq.poll();
            //System.out.println(Arrays.toString(room));
            if (room[2] > meetingCount || room[2] == meetingCount && res > room[0]) {
                res = (int)room[0];
                meetingCount = room[2];
            }
        }
                    
        return res;
    }
}