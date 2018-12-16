package hatecode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrickWall {
/*
There is a brick wall in front of you. The wall is rectangular and has several rows of bricks. 
The bricks have the same height but different width. You want to draw a vertical line from the 
top to the bottom and cross the least bricks.

The brick wall is represented by a list of rows. Each row is a list of integers representing the 
width of each brick in this row from left to right.

If your line go through the edge of a brick, then the brick is not considered as crossed. You need 
to find out how to draw the line to cross the least bricks and return the number of crossed bricks.

You cannot draw a line just along one of the two vertical edges of the wall, in which case the line 
will obviously cross no bricks.

 

Example:

Input: [[1,2,2,1],
        [3,1,2],
        [1,3,2],
        [2,4],
        [3,1,2],
        [1,3,1,1]]

Output: 2
 */
    // so we want to know most common right idx, just get the one who has most appearance
    
    //only one case is that if each row has only one brick, so we have cross them all
    public int leastBricks(List<List<Integer>> wall) {
        if (wall == null || wall.size() < 1) {
            return 0;
        }
        
        int res = Integer.MIN_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        for(List<Integer> row : wall) {
            int start = 0;
            for(int i = 0; i < row.size() -1;i++) {
                start += row.get(i);
                int count = map.getOrDefault(start, 0) + 1;
                map.put(start, count);
                res = Math.max(res, count);
            }
        }
        //like only 1 brick each row, so for loop will not run
        return res == Integer.MIN_VALUE ? wall.size() : wall.size() - res;
    }
}