package hatecode._0001_0999;
public class _1037ValidBoomerang {
/*
1037. Valid Boomerang
A boomerang is a set of 3 points that are all distinct and not in a straight line.

Given a list of three points in the plane, return whether these points are a boomerang.

 

Example 1:

Input: [[1,1],[2,3],[3,2]]
Output: true
*/
    public boolean isBoomerang(int[][] p) {
       return (p[0][0] - p[1][0]) * (p[0][1] - p[2][1]) != (p[0][0] - p[2][0]) * (p[0][1] - p[1][1]); 
    }
}