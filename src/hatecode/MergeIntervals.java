package hatecode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MergeIntervals
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : 56. Merge Intervals
 */
public class MergeIntervals {
    /**
Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considerred overlapping.

                sta     end
     |___|       |____|
       |_____|       |___|

     time : O(nlogn) space : O(n)

     * @param intervals
     * @return
     */
    // interview friendly solutions:
    //
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if (intervals == null || intervals.size() < 2) {
            return intervals;
        }
        //sort by start
        Collections.sort(intervals, (a, b)->(a.start - b.start));
        // these two variable is to store the last start and max end
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        
        for(Interval interval : intervals) {
            // so each interval start is smaller than previous end which means they have overlap
            // they need to nerge
            if (interval.start <= end) {
                end = Math.max(end, interval.end);
            // if start > end means we have full result, so we don't need to merge, we just add to result set and 
            //update latest start and end
            } else {
                // notes; end here means previous interval max end, so here add previous result into reset set
                res.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }
        // this is come to the situation, for example, [1,3] and [2, 6], we cannot add to result set since 
        // we still do not know whether we need to merge more, we can only know when we exit the loop
        // for [1,3], [4,6], so we still have one left to add. last one, [4,6]
        res.add(new Interval(start, end));
        return res;
    }
    
    //another solution
    /**
    这题立马让人想到了Calendar II里面的用1和-1代替interal，那题在TreeMap里面遍历value，当2个value>2时候，表示有overlap
     * Definition for an interval.
     * public class Interval {
     *     int start;
     *     int end;
     *     Interval() { start = 0; end = 0; }
     *     Interval(int s, int e) { start = s; end = e; }
     * }
     */
    public List<Interval> merge2(List<Interval> intervals) {
        List<Interval> ret = new ArrayList<>();
          int max = 0, start = -1, total = 0;
          for(Interval it : intervals){
              if(it.end > max) 
                  max = it.end;//遍历数组，获取所有interval里面最大值
          }
          //新建两个数组
          int[] memo_start = new int[max+1];
          int[] memo_end = new int[max+1];
          
          
          //遍历interval数组，在memo_start和memo_end数组里记录他们的位置。。。
          //这样都不用sort了，妈的，太牛逼了！！！
          //这样这两个数组根据系数自然sort了
          
      
          for(Interval it : intervals){
              memo_start[it.start]++;
              memo_end[it.end]++;
          }
          
          ///然后遍历其中一个memo数组
          for(int i = 0; i < memo_start.length; i++){
              if(total == 0)
                  start = i;
              
              total += memo_start[i];
              
              if(total == memo_end[i] && memo_end[i] != 0){//如果没有overlap，那么start and end应该具有相同的index
                  ret.add(new Interval(start, i));//这里的start应该是之前的start了，i是新的end，如果有overlap，只有前面interval的start
              }//和后面interval的end才 会算进res
              
              total -= memo_end[i];
          }
          return ret;
      }
}
