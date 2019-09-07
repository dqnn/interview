package hatecode;
import java.util.*;
public class _616AddBoldTagInString {
/*
616. Add Bold Tag in String
758. Bold Words in String
Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict. If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.
Example 1:
Input: 
s = "abcxyz123"
dict = ["abc","123"]
Output:
"<b>abc</b>xyz<b>123</b>"
*/
    //this is best solution, but it may not easy to think through
    public String addBoldTag(String s, String[] dict) {
        if (dict == null || dict.length < 1 || s == null || s.length() < 1) return s;
        
        int prevStart = 0, end = -1;
        StringBuilder sb = new StringBuilder();
        for(int start = 0; start < s.length(); start++) {
            //this is for to make sure for position start, 
            //the end can be extend farthest position. 
            for(String d : dict) {
                if (s.startsWith(d, start)) {
                    end = Math.max(end, start + d.length());
                }
            }
                //confident that when start == end, (prevStart, end) need to be bold, no need extend, it does not contains
                // start char
            System.out.println(start + "----"+ end);
            //if end cannot go any farther, means frm prev to current position none of them need to be bold
            if (start == end) {
                sb.append("<b>" + s.substring(prevStart, start) + "</b>");
            }
            //notes, we here include start char
            //also prevStart move to next start, since start already recorded in the stringbuilder
            if (start >= end) {
                sb.append(s.charAt(start));
                prevStart = start + 1; 
            }
        }
        if (end >= s.length()) sb.append("<b>" + s.substring(prevStart) + "</b>");
        
        return sb.toString();
    }
    
    //this is most intution solution, so first we get all intervals then we merge them
    //and last we print them. also this is the idea  i think about then first sights on 
    //this question
    public String addBoldTag2(String s, String[] dict) {
        //this part is pretty useful to find all index intervals in string s
        List<Interval> intervals = new ArrayList<>();
        for (String d : dict) {
            int idx = -1;
            idx = s.indexOf(d, idx);
            while (idx != -1) {
                intervals.add(new Interval(idx, idx + d.length()));
                idx += 1;
                idx = s.indexOf(d, idx);
            }
        }
        //merge all intervals, like course schedule or interval merge
        System.out.println(Arrays.toString(intervals.toArray()));
        intervals = merge(intervals);
        System.out.println(Arrays.toString(intervals.toArray()));
        //this is to print, and prev is to rememebr last start position
        int prev = 0;
        StringBuilder sb = new StringBuilder();
        for (Interval interval : intervals) {
            sb.append(s.substring(prev, interval.start));
            sb.append("<b>");
            sb.append(s.substring(interval.start, interval.end));
            sb.append("</b>");
            prev = interval.end;
        }
        //this is edge case, print the rest of the strings
        if (prev < s.length()) {
            sb.append(s.substring(prev));
        }
        return sb.toString();
    }

    class Interval {
        int start, end;

        public Interval(int s, int e) {
            start = s;
            end = e;
        }

        public String toString() {
            return "[" + start + ", " + end + "]";
        }
    }

    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }
        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });

        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        List<Interval> res = new ArrayList<>();
        for (Interval i : intervals) {
            if (i.start <= end) {
                end = Math.max(end, i.end);
            } else {
                res.add(new Interval(start, end));
                start = i.start;
                end = i.end;
            }
        }
        res.add(new Interval(start, end));
        return res;
    }
}