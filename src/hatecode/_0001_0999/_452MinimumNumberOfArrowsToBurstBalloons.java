package hatecode._0001_0999;
import java.util.*;
public class _452MinimumNumberOfArrowsToBurstBalloons {
/*
452. Minimum Number of Arrows to Burst Balloons
There are a number of spherical balloons spread in two-dimensional space. For each balloon, provided input is the start and end coordinates of the horizontal diameter. Since it's horizontal, y-coordinates don't matter and hence the x-coordinates of start and end of the diameter suffice. Start is always smaller than end. There will be at most 104 balloons.

An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend. There is no limit to the number of arrows that can be shot. An arrow once shot keeps travelling up infinitely. The problem is to find the minimum number of arrows that must be shot to burst all balloons.

Example:

Input:
[[10,16], [2,8], [1,6], [7,12]]

Output:
2
*/
    //[10,16], [2,8], [1,6], [7,12]] -> after sort
    //[1,6],[2,8],[7,12],[10,16]
    public int findMinArrowShots(int[][] p) {
        if (p == null || p.length < 1) return 0;
        //please explain why we need to sort by second column, in many situations, 
        //this is not needed
        Arrays.sort(p, (a, b)->(a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]));
        int start = p[0][0], end = p[0][1];
        int res = 1;
        for(int i =1; i < p.length;i++) {
            // they have overlap
            if (end >= p[i][0]) {
                start = Math.max(start, p[i][0]);
                end = Math.min(end, p[i][1]);
            //no overlap
            } else {
                start = p[i][0];
                end = p[i][1];
                res++;
            }
            
        }
        return res;
    }
    //simple code, as follow up, to make it simpler
    public int findMinArrowShots2(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, (a, b) -> a[1] - b[1]);
        int arrowPos = points[0][1];
        int arrowCnt = 1;
        for (int i = 1; i < points.length; i++) {
            //means they have overlap, so continue,if not 
            //we have a new target
            if (arrowPos >= points[i][0]) {
                continue;
            }
            arrowCnt++;
            arrowPos = points[i][1];
        }
        return arrowCnt;
    }
}