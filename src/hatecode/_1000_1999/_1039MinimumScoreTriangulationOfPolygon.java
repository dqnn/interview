package hatecode._1000_1999;
public class _1039MinimumScoreTriangulationOfPolygon {
/*
1039. Minimum Score Triangulation of Polygon
Given N, consider a convex N-sided polygon with vertices labelled A[0], A[i], ..., A[N-1] in clockwise order.

Suppose you triangulate the polygon into N-2 triangles.  For each triangle, the value of that triangle is the product of the labels of the vertices, and the total score of the triangulation is the sum of these values over all N-2 triangles in the triangulation.

Return the smallest possible total score that you can achieve with some triangulation of the polygon.

 

Example 1:

Input: [1,2,3]
Output: 6
*/
    //thinking process:O(n^2)/O(n^2)
    //given an array A, each number represents vertices value, 
    //for a convex polygon, so it will have n -2 triangles, so find 
    //out max value for each triangle, a * b * c max
    // 1,2,3-->only 1 triangle, so 1*2*3 = 6
    
    //[3,4,5,7], suppose we hold 3 and 7, we can choose two nodes in 4 and 5, 
    //and we calc the max value, and we do this for each possible combinations
    int[][] dp = new int[50][50];
    int res = 0;
    public int minScoreTriangulation(int[] A) {
        minScoreTriangulation(A, 0, A.length - 1);
        return dp[0][A.length - 1];
    }
    
    
    int minScoreTriangulation(int[] A, int i, int j) {
        if (i > j || i + 1 == j) return 0; 
        if (dp[i][j] != 0) return dp[i][j];
        for (int k = i+1; k < j; k++) {
            res = Math.min(res == 0 ? Integer.MAX_VALUE : res, 
            minScoreTriangulation(A, i, k) + A[i] * A[k] * A[j] + minScoreTriangulation(A, k, j));
        }
        dp[i][j] = res;
        return dp[i][j];
    }
}