package hatecode;
import java.util.*;
public class NumberOfCornerRectangles {
/*
750. Number Of Corner Rectangles
Given a grid where each entry is only 0 or 1, find the number of corner rectangles.

A corner rectangle is 4 distinct 1s on the grid that form an axis-aligned rectangle. Note that only the corners need to have the value 1. Also, all four 1s used must be distinct.

 

Example 1:

Input: grid = 
[[1, 0, 0, 1, 0],
 [0, 0, 1, 0, 1],
 [0, 0, 0, 1, 0],
 [1, 0, 1, 0, 1]]
Output: 1
Explanation: There is only one corner rectangle, with corners grid[1][2], grid[1][4], grid[3][2], grid[3][4].
*/
    //O(RC^2)/O(C^2)
    public int countCornerRectangles2(int[][] grid) {
        Map<Integer, Integer> count = new HashMap<>();
        int ans = 0;
        for (int[] row: grid) {
            for (int c1 = 0; c1 < row.length; ++c1) if (row[c1] == 1) {
                for (int c2 = c1+1; c2 < row.length; ++c2) if (row[c2] == 1) {
                    int pos = c1 * 200 + c2;
                    int c = count.getOrDefault(pos, 0);
                    ans += c;
                    count.put(pos, c+1);
                }
            }
        }
        return ans;
    }
    //O(R^2C)/O(1)
    public int countCornerRectangles1(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length - 1; i++) {
            //next row
            for (int j = i + 1; j < grid.length; j++) {
                int counter = 0;
                //from col 0->C
                for (int k = 0; k < grid[0].length; k++) {
                    //same column, if previous row and current row are 1, then yes
                    if (grid[i][k] == 1 && grid[j][k] == 1) counter++;
                }
                if (counter > 0) ans += counter * (counter - 1) / 2;
            }
        }
        return ans;
    }
    //O(N*sqrt(N) *+ RC)/O(N+R+C^2), N is number of 1 in grid
    public int countCornerRectangles(int[][] grid) {
        List<List<Integer>> rows = new ArrayList<>();
        int N = 0;
        for (int r = 0; r < grid.length; ++r) {
            rows.add(new ArrayList<>());
            for (int c = 0; c < grid[r].length; ++c)
                if (grid[r][c] == 1) {
                    rows.get(r).add(c);
                    N++;
                }
        }

        int sqrtN = (int) Math.sqrt(N);
        int ans = 0;
        Map<Integer, Integer> count = new HashMap<>();

        for (int r = 0; r < grid.length; ++r) {
            if (rows.get(r).size() >= sqrtN) {
                Set<Integer> target = new HashSet<>(rows.get(r));

                for (int r2 = 0; r2 < grid.length; ++r2) {
                    if (r2 <= r && rows.get(r2).size() >= sqrtN)
                        continue;
                    int found = 0;
                    for (int c2: rows.get(r2))
                        if (target.contains(c2))
                            found++;
                    ans += found * (found - 1) / 2;
                }
            } else {
                for (int i1 = 0; i1 < rows.get(r).size(); ++i1) {
                    int c1 = rows.get(r).get(i1);
                    for (int i2 = i1 + 1; i2 < rows.get(r).size(); ++i2) {
                        int c2 = rows.get(r).get(i2);
                        int ct = count.getOrDefault(200*c1 + c2, 0);
                        ans += ct;
                        count.put(200*c1 + c2, ct + 1);
                    }
                }
            }
        }
        return ans;
    }
    
    
}