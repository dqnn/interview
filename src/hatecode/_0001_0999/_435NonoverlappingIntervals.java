package hatecode._0001_0999;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Date : Aug, 2018
 * Description : 435. Non-overlapping Intervals
 */
public class _435NonoverlappingIntervals {
    /**
     * 
     * 56 Merge Intervals <- very similar, i did it with just 3 lines different
       252 Meeting Rooms
       253 Meeting Rooms II
       452 Minimum Number of Arrows to Burst Balloons
     * Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest 
     * of the intervals non-overlapping.

     Note:
     You may assume the interval's end point is always bigger than its start point.
     Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
     Example 1:
     Input: [ [1,2], [2,3], [3,4], [1,3] ]

     Output: 1

     Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
     Example 2:
     Input: [ [1,2], [1,2], [1,2] ]

     Output: 2

     Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
     Example 3:
     Input: [ [1,2], [2,3] ]

     Output: 0

     Explanation: You don't need to remove any of the intervals since they're already non-overlapping.

     [ [1,2], [2,3], [3,4], [1,3] ]


     time : O(nlogn)
     space : O(1)

     * @param intervals
     * @return
     */
    /*
     * thinking process: O(nlgn)/O(1)
     * 
     * given set of intervals, find the max number of intervals are not over lapping
     * we can just remove the overlapped ones, becuase some maybe long 
     * [[-73, -26], [-65, -11], [-63, 2], [-62, -49], [-52, 31], [-40, -26], 
     * [-31, 49], [30, 47], [58, 95], [66, 98], [82, 97], [95, 99]]
     * 
     * there some cases cannot merge easily, so we have use its oppsite to calculate
     * 
     * try to calculate which are not need merge
     * 
     * one variation of the problem is to see minimal meeting needs to be removed if we want to attend most of meetings.
     *  [ [1,2], [2,3], [3,4], [1,3] ], return 1, needs to remove [1,3]
     * 

     */
    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals.length == 0) return 0;

        Arrays.sort(intervals, (a, b) -> a.end - b.end);
        int end = intervals[0].end;
        int count = 1;
        for (int i = 1; i < intervals.length; i++) {
            //if no overlap
            if (intervals[i].start >= end) {
                end = intervals[i].end;
                count++;
            }
        }
        return intervals.length - count;
    }

    public int eraseOverlapIntervals2(Interval[] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.end != o2.end) return o1.end - o2.end;
                return o2.start - o1.start;
            }
        });
        int end = Integer.MIN_VALUE;
        int res = 0;
        for (Interval interval : intervals) {
            if (interval.start >= end) {
                end = interval.end;
            } else {
                res++;
            }
        }
        return res;
    }
}
