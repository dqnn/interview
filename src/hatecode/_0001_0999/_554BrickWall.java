package hatecode._0001_0999;


import java.util.*;
public class _554BrickWall {
/*
554. Brick Wall
There is a rectangular brick wall in front of you with n rows of bricks. The ith row has some number of bricks each of the same height (i.e., one unit) but they can be of different widths. The total width of each row is the same.

Draw a vertical line from the top to the bottom and cross the least bricks. If your line goes through the edge of a brick, then the brick is not considered as crossed. You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks.

Given the 2D array wall that contains the information about the wall, return the minimum number of crossed bricks after drawing such a vertical line.

 

Example 1:


Input: wall = [[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]
Output: 2

*/

/*
 * thinking process: O(n^2)/O(n)
 * 
 * the problem is to say: given a 2D array, each row means a brick width, 
 * [1,2,2,1] means 4 bricks, 
 * 
 * so you want to cut into bricks by edges, return the least bricks which are cut off
 * 
 * 
 * 
 * 
 * 
 */
    public int leastBricks(List<List<Integer>> A) {
        if( A == null || A.size() < 1) return 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for(List<Integer> row : A) {
            int sum = 0;
            for(int i = 0; i< row.size() -1; i++) {
                sum += row.get(i);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
                count = Math.max(count, map.get(sum));
            }
        }
        
        return A.size() - count;
    }
}
