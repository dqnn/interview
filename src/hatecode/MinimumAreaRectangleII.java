package hatecode;
import java.util.*;

public class MinimumAreaRectangleII {
    /*
     * 963. Minimum Area Rectangle II
     * Given a set of points in the xy-plane, determine the minimum area of any
     * rectangle formed from these points, with sides not necessarily parallel to
     * the x and y axes.
     * 
     * If there isn't any rectangle, return 0.
     * Input: [[1,2],[2,1],[1,0],[0,1]]
Output: 2.00000
Explanation: The minimum area rectangle occurs at [1,2],[2,1],[1,0],[0,1], with an area of 2.
     */
    //O(n^2lgn)/O(lgn) or O(sqrt(n)), 
    //http://www.cs.uu.nl/research/techreps/repo/CS-1989/1989-10.pdf
    //thinking process:
    // one point to four point have same length, then it is rectangle,
    //then we use Map<String, List<int[]>> to store the dist + x + y as key, 
    //dist is the two points length, x,y middle point center
    //according to above pdf, the possible recangle is n^2lgn, upper is n^2sqrt(n)
    
    //so the process is to we find C(n,2) combinations middle points, and their distance to all
    //possible points
    //then next, for each possible distance,we calculate the 
    public double minAreaFreeRect(int[][] p) {
        if (p == null || p.length < 4) return 0.0;
        int len = p.length;
        double res = Double.MAX_VALUE;
        Map<String, List<int[]>> map = new HashMap<>();
        for(int i = 0; i< len; i++) {
            for(int j = i+1; j< len;j++) {
                double x = (p[i][0] + p[j][0])/2.0;
                double y = (p[i][1] + p[j][1])/2.0;
                int dis = dist(p[i], p[j]);
                map.computeIfAbsent(dis + "-" + x + "-" + y, 
                                   V-> new ArrayList<>()).add(new int[]{i, j});
            }
        }
        for(String key : map.keySet()) {
            //System.out.println(key +"=" + map.get(key));
            if (map.get(key).size() < 2) continue;
            List<int[]> list = map.get(key);
            for(int i = 0; i< list.size(); i++) {
                // here we have [[1,2],[2,1],[1,0],[0,1]]
                // in the list, [0, 2], it is dialoug point, so we want to find 
                // the rectangle both sides, so we can know the area
                // it could be following, also could be 
                //p1=list.get(i)[0],  p2=list.get(i)[1], p3=list.get(j)[0],
                // note, p1 and p2 are dialog, so we have to use 
                //p1p3 * p2p3
                for(int j = i+1;j< list.size();j++) {
                    int p1 = list.get(i)[0];
                    int p2 = list.get(j)[0];
                    int p3 = list.get(j)[1];
                    double l1 = Math.sqrt(dist(p[p1], p[p2]));
                    double l2 = Math.sqrt(dist(p[p1], p[p3]));
                    res = Math.min(res, l1 * l2);
                }
            }
        }
        
    return res == Double.MAX_VALUE ? 0 : res;
    }
    
    private int dist(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0])
            + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }
}