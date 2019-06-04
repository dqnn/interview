package hatecode;
import java.util.*;

public class ExamRoom {
    /*
     * 855. Exam Room 
     * In an exam room, there are N seats in a single row, numbered
     * 0, 1, 2, ..., N-1.
     * 
     * When a student enters the room, they must sit in the seat that maximizes the
     * distance to the closest person. If there are multiple such seats, they sit in
     * the seat with the lowest number. (Also, if no one is in the room, then the
     * student sits at seat number 0.)
     * 
     * Return a class ExamRoom(int N) that exposes two functions: ExamRoom.seat()
     * returning an int representing what seat the student sat in, and
     * ExamRoom.leave(int p) representing that the student in seat number p now
     * leaves the room. It is guaranteed that any calls to ExamRoom.leave(p) have a
     * student sitting in seat p.
     * 
     * Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"],
     * [[10],[],[],[],[],[4],[]] Output: [null,0,9,4,2,null,5] Explanation:
     * ExamRoom(10) -> null seat() -> 0, no one is in the room, then the student
     * sits at seat number 0. seat() -> 9, the student sits at the last seat number
     * 9. seat() -> 4, the student sits at the last seat number 4. seat() -> 2, the
     * student sits at the last seat number 2. leave(4) -> null seat() -> 5, the
     * student sits at the last seat number 5.
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
    
    //so the allocation is relatively fixed pattern. if we thinking the thread is segment,so 
    //there are a lot of intervals when we call seat() and leave() functions. 
    // interval has start, end and len attributes. if we use this data structure how we implement 
    //seat() and leave, for seat, we want to allocate one postion which has max distance to closet
    //position, so if we sort the segment intervals by distance, so we would get conclusion that 
    //the middle one is the answer, if distance is the same, then we choose smaller x interval. 
    
    //seat() is to poll() the largest distance interval and break two parts and add to the PQ
    //leave(int) is to find the two interval which is start and end and remove these two from the 
    //pq, then merge these two and added to PQ
    
    //TODO: how to make leave(int) to lgn， the key is to have a map<Integer, List<Interval>>
    //so when we merge two Intervals should be easy
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
    //this is the both logn solution, need to modify 
    class ExamRoom_Logn_solution{
      //TreeMap, Key: length of the interval, value is the set of starting index, bcause we want use smaller index if there are 
        //two same length of interval
        TreeMap<Integer, TreeSet<Integer>> map;
        //set stores the seats we have allocated, index
        TreeSet<Integer> set;
        int N;
        
        public void ExamRoom(int N) {
            this.N = N;
            this.map = new TreeMap<>();
            this.set = new TreeSet<>();
        }
        
        //so if set size is 0, means we have allocated ever,so we just allocated 0, 
        //another case is only allocated 
        public int seat() {
            int res = 0;
            if (set.size() == 0) {
                res = 0;
            } else {
                int first = set.first(), last = set.last();
                Integer max = map.isEmpty() ? null : map.lastKey();
                if (max == null || first >= max / 2 || N - 1 - last > max / 2) {
                    if (first >= N - 1 - last) {
                        addInterval(0, first);
                        res = 0;
                    } else {
                        addInterval(last, N - 1 - last);
                        res = N - 1;
                    }
                } else {
                    TreeSet<Integer> temp = map.get(max);
                    int index = temp.first();
                    int next = set.higher(index);
                    int mid = (next + index) / 2;
                    removeInterval(index, max);
                    addInterval(index, mid - index);
                    addInterval(mid, next - mid);
                    res = mid;
                }
            }
            set.add(res);
            return res;
        }
        
        //we get p previous smallest index and next smallest biggest index, so we can know the interval start and end
        //and do the merge ops
        public void leave(int p) {
            Integer pre = set.lower(p);
            Integer next = set.higher(p);
            set.remove(p);
            if (next != null) {
                removeInterval(p, next - p);
            }
            if (pre != null) {
                removeInterval(pre, p - pre);
                if (next != null) {
                    addInterval(pre, next - pre);
                }
            }
        }
        //add interval, length=len and start from index, 
        //if len is even number, so we do not change, but if odd, then we change to even first, because 
        private void addInterval(int index, int len) {
            len -= len & 1;  // trim to even number
            map.putIfAbsent(len, new TreeSet<>());
            map.get(len).add(index);
        }
        
        private void removeInterval(int index, int len) {
            len -= len & 1;
            Set<Integer> temp = map.get(len);
            if (temp == null) {
                return;
            }
            temp.remove(index);
            if (temp.size() == 0) {
                map.remove(len);
            }
        }
    }
}