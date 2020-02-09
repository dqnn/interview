package hatecode._0001_0999;
public class LargestTriangleArea {
/*
812. Largest Triangle Area
You have a list of points in the plane. Return the area of the largest triangle that can be formed by any 3 of the points.

Example:
Input: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
Output: 2
*/
    public double largestTriangleArea(int[][] points) {
        if(points == null || points.length < 1) return 0.0;
        int n = points.length;
        double max = 0.0;
        for(int i = 0; i< n - 2; i++) {
            for(int j = i+1; j< n-1; j++) {
                for(int k = j + 1; k< n; k++) {
                    max = Math.max(max, area(points[i],points[j],points[k]));
                }
            }
        }
        return max;
    }
    //https://www.mathopenref.com/coordtrianglearea.html
    public double area(int[] pt1, int[] pt2, int[] pt3) {
        return Math.abs(pt1[0] * (pt2[1] - pt3[1]) + pt2[0] * (pt3[1] - pt1[1]) + pt3[0] * (pt1[1] - pt2[1])) / 2.0; 
    }
}