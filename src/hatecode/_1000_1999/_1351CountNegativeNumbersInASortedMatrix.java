package hatecode._1000_1999;
public class _1351CountNegativeNumbersInASortedMatrix {
/*
1351. Count Negative Numbers in a Sorted Matrix
Given a m x n matrix grid which is sorted in non-increasing order both row-wise and column-wise, return the number of negative numbers in grid.


Example 1:

Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
Output: 8
*/
    //thinking process:O(m+n)/O(1)
    
    //the problem is to say: given one integer 2D array, 
    //column and rows are sorted non-increasing, find all negative numbers
    
    //we need to find the pattern in this question, since the cols and rows are sorted, 
    //so if we scan from bottom left, if it is negative, then all numbers on its right are all negative,
    //if it is positive, then it means all numbers above it are positive, so we can move,
    
    //so we find way to how to move i, j and count the neagtive numbers
    public int countNegatives(int[][] g) {
        int m = g.length, n = g[0].length, r = m -1, c = 0;
        int res = 0;
        while ( r>=0 &&  c>=0 ) {
            if (g[r][c] >=0) {
                if (c == n - 1) return res;
                else c++;
            } else {
                res += n - 1 - (c) + 1;
                r--;
            }
        }
        return res;
    }
}