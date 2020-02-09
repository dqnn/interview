package hatecode._0001_0999;

import java.util.*;
/**
 * Project Name : Leetcode Package Name : leetcode File Name : BestMeetingPoint
 * Creator : duqiang Date : July, 2018 Description : 296. Best Meeting Point
 */
public class _296BestMeetingPoint {

    /**
296. Best Meeting Point
A group of two or more people wants to meet and minimize the total travel
     * distance. You are given a 2D grid of values 0 or 1, where each 1 marks the
     * home of someone in the group. The distance is calculated using Manhattan
     * Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
     * 
     * Example:

Input: 

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

Output: 6 

Explanation: Given three people living at (0,0), (0,4), and (2,2):
             The point (0,2) is an ideal meeting point, as the total travel distance 
             of 2+2+2=6 is minimal. So return 6.
     * 
     * A C E D B ------------------------
     * 
     * time : O(m * n) space : O(n) Bruth-force solution
     * @param grid
     * @return
     */
    //thinking process:
    //given a 2D matrix, 1 means people, they want to find a place where total travel would be min, 
    // so return min.  distance is dx + dy
    
    // min path would be 1, the smallest point must be x or y are the same line with other people
    //or we can always find another point is smaller than this point
    
    //|x1-m| + |x2-m| + |xi - m|, we want to get minimal, its d, 1+-1, ...we want 1 and -1 count are
    //the same so m must between them, 
    //if n = even, then any point between most innerst will be ok
    //if n = odd, then it must be middle one, so we always choose the median as we compute
    public int minTotalDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        List<Integer> I = new ArrayList<>();
        List<Integer> J = new ArrayList<>();
        //example here I ={0,0,2}
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    I.add(i);
                }
            }
        }
        //example here would be J = {0, 2, 4}
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (grid[i][j] == 1) {
                    J.add(j);
                }
            }
        }

        return min(I) + min(J);
    }

    private int min(List<Integer> list) {
        int i = 0, j = list.size() - 1;
        int sum = 0;
        while (i < j) {
            //same row or column, for exampple, [0,0,2]--> 2
            // column {0,2,4}, would be 4
            sum += list.get(j--) - list.get(i++); // median is shortest,
            // project all point into x,
        }
        return sum;
    }
    
    //another solution, we get each 1's i and j. we look for median in each list
    //interview friendly,  need to know its principal first
    public int minTotalDistance2(int[][] grid) {
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    rows.add(row);
                    cols.add(col);
                }
            }
        }
        int row = rows.get(rows.size() / 2);
        Collections.sort(cols);
        int col = cols.get(cols.size() / 2);
        return minDistance1D(rows, row) + minDistance1D(cols, col);
    }

    private int minDistance1D(List<Integer> points, int origin) {
        int distance = 0;
        for (int point : points) {
            distance += Math.abs(point - origin);
        }
        return distance;
    }
}
