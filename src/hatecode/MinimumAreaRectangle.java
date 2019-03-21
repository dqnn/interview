package hatecode;
import java.util.*;
class MinimumAreaRectangle {
/*
939. Minimum Area Rectangle
Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel to the x and y axes.

If there isn't any rectangle, return 0.

 

Example 1:

Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
Output: 4
*/
    public class Point{
        int x;
        int y;
        Point(int a,int b) {x=a;y=b;}
        
        @Override
        public boolean equals(Object obj)
        { //System.out.println("inside");
            return (((Point) obj).x == this.x)&&(((Point) obj).y == this.y);
        }
        
        @Override
        public int hashCode(){
            return Objects.hash(x,y);
        }

    }
    //O(N^2)/O(N) 
    //thinking process, image P1 and P2 are dialoug, so we just want find out 
    //another two points which is has some x or y value. 
    public int minAreaRect(int[][] points) {
        int min=Integer.MAX_VALUE;
        Set<Point> ptset = new HashSet<>();
        for(int i=0;i<points.length;i++)
            ptset.add(new Point(points[i][0],points[i][1]));
        for(int i=0;i<points.length;i++)
            for(int j=i+1;j<points.length;j++) {
                int x1= points[i][0];
                int y1= points[i][1];
                int x2= points[j][0];
                int y2= points[j][1];
                if (x1 == x2 || y1 == y2) continue;
                Point pt3 = new Point(x1,y2);
                Point pt4 = new Point(x2,y1);
                if(ptset.contains(pt3) && ptset.contains(pt4)) {   
                    int area= Math.abs(x1-x2)*Math.abs(y1-y2);
                    min = Math.min(min, area);
                }
            }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}