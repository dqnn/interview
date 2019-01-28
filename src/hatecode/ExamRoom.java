package hatecode;
import java.util.*;

public class ExamRoom {
/*
855. Exam Room
In an exam room, there are N seats in a single row, numbered 0, 1, 2, ..., N-1.

When a student enters the room, they must sit in the seat that maximizes the distance to the closest person.  If there are multiple such seats, they sit in the seat with the lowest number.  (Also, if no one is in the room, then the student sits at seat number 0.)

Return a class ExamRoom(int N) that exposes two functions: ExamRoom.seat() returning an int representing what seat the student sat in, and ExamRoom.leave(int p) representing that the student in seat number p now leaves the room.  It is guaranteed that any calls to ExamRoom.leave(p) have a student sitting in seat p.

Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
Output: [null,0,9,4,2,null,5]
Explanation:
ExamRoom(10) -> null
seat() -> 0, no one is in the room, then the student sits at seat number 0.
seat() -> 9, the student sits at the last seat number 9.
seat() -> 4, the student sits at the last seat number 4.
seat() -> 2, the student sits at the last seat number 2.
leave(4) -> null
seat() -> 5, the student sits at the last seat number 5.
*/
    PriorityQueue<Interval> pq;
    int N;

    class Interval {
        int x, y, dist;
        public Interval(int x, int y) {
            this.x = x;
            this.y = y;
            if (x == -1) {
                this.dist = y;
            } else if (y == N) {
                this.dist = N - 1 - x;
            } else {
                this.dist = Math.abs(x - y) / 2;    
            }
        }
    }
// seat() O(lgn), leave() O(n)
   //thinking process: 
   // give a row of seats with number 0->n-1 in a exam room, seat() will allocate one seat for a 
    //student so he has maxium distance to his/her closest student. leave(int ) means the student
    //left the room, the seat will be empty
    
    //so thinking from small example, it is empty, seat() will return 0, seat() return N-1 as second
    //then it is middle, then first middle.....
    
    //so the allocation is relatively fixed pattern. 
    public ExamRoom(int N) {
        this.pq = new PriorityQueue<>((a, b) -> a.dist != b.dist? b.dist - a.dist : a.x - b.x);
        this.N = N;
        pq.add(new Interval(-1, N));
    }

    // O(logn): poll top candidate, split into two new intervals
    public int seat() {
        int seat = 0;
        Interval interval = pq.poll();
        if (interval.x == -1) seat = 0;
        else if (interval.y == N) seat = N - 1;
        else seat = (interval.x + interval.y) / 2; 
        
        pq.offer(new Interval(interval.x, seat));
        pq.offer(new Interval(seat, interval.y));
            
        return seat;
    }
    
    // O(n)Find head and tail based on p. Delete and merge two ends
    public void leave(int p) {
        Interval head = null, tail = null;
        List<Interval> intervals = new ArrayList<>(pq);
        for (Interval interval : intervals) {
            if (interval.x == p) tail = interval;
            if (interval.y == p) head = interval;
            if (head != null && tail != null) break;
        }
        // Delete
        pq.remove(head);
        pq.remove(tail);
        // Merge
        pq.offer(new Interval(head.x, tail.y));
    }
}