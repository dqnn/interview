package hatecode._0001_0999;
import java.util.*;
class MyCalendarI {
/*
729. My Calendar I
implement a MyCalendar class to store your events. A new event can be added if adding the event will not cause a double booking.

Your class will have the method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.

A double booking happens when two events have some non-empty intersection (ie., there is some time that is common to both events.)

For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.

Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
Example 1:
MyCalendar();
MyCalendar.book(10, 20); // returns true
MyCalendar.book(15, 25); // returns false
MyCalendar.book(20, 30); // returns true
*/
    class Interval {
        int start, end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    List<Interval> list;
    public MyCalendarI() {
        this.list = new ArrayList<>();
    }
    //O(n^2)/O(n)
    public boolean book(int start, int end) {
        if (start >= end) return true;
        
        for(Interval interval : list) {
            if (!(end <= interval.start || start >= interval.end)) {
                return false;
            }
        }
        list.add(new Interval(start, end));
        return true;
    }
    //O(nlgn)/O(n)
    TreeMap<Integer, Integer> calendar;

    void MyCalendar2() {
        calendar = new TreeMap();
    }

    public boolean book2(int start, int end) {
        Integer prev = calendar.floorKey(start),
                next = calendar.ceilingKey(start);
        if ((prev == null || calendar.get(prev) <= start) &&
                (next == null || end <= next)) {
            calendar.put(start, end);
            return true;
        }
        return false;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */