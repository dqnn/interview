package hatecode;
import java.util.*;
public class RectangleAreaII {
    /*
     * 850. Rectangle Area II We are given a list of (axis-aligned) rectangles. Each
     * rectangle[i] = [x1, y1, x2, y2] , where (x1, y1) are the coordinates of the
     * bottom-left corner, and (x2, y2) are the coordinates of the top-right corner
     * of the ith rectangle.
     * 
     * Find the total area covered by all rectangles in the plane. Since the answer
     * may be too large, return it modulo 10^9 + 7.
     */
    static class Point {
        int x, y, val;
        Point(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
    public static int rectangleArea(int[][] rectangles) {
        int M = 1000000007;
        List<Point> data = new ArrayList<>();
        for (int[] r : rectangles) {
            data.add(new Point(r[0], r[1], 1));
            data.add(new Point(r[0], r[3], -1));
            data.add(new Point(r[2], r[1], -1));
            data.add(new Point(r[2], r[3], 1));
        }
        //Collections.sort(data, (a, b) -> (a.x - b.x)); also works
        Collections.sort(data, (a, b) -> (a.x == b.x ? b.y -a.y : a.x - b.x));
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int preX = -1;
        int preY = -1;
        int result = 0;
        for (int i = 0; i < data.size(); i++) {
            Point p = data.get(i);
            map.put(p.y, map.getOrDefault(p.y, 0) + p.val);
            //we get next point to compare to current x,
            if (i == data.size() - 1 || data.get(i + 1).x > p.x) {
                if (preX > -1) {
                    result += ((long)preY * (p.x - preX)) % M;
                    result %= M;
                }
                preY = calcY(map);
                preX = p.x;
            }
/*preX:-1, preY: -1, result: 0, map:{2=-1}
preX:0, preY: 2, result: 0, map:{0=1, 2=-1}
preX:0, preY: 2, result: 0, map:{0=1, 2=-1, 3=-1}
preX:0, preY: 2, result: 0, map:{0=1, 1=-1, 2=-1, 3=-1}
preX:0, preY: 2, result: 0, map:{0=2, 1=-1, 2=-1, 3=-1}
preX:1, preY: 3, result: 2, map:{0=3, 1=-1, 2=-1, 3=-1}
preX:1, preY: 3, result: 2, map:{0=3, 1=-1, 2=-1, 3=0}
preX:1, preY: 3, result: 2, map:{0=3, 1=-1, 2=0, 3=0}
preX:1, preY: 3, result: 2, map:{0=2, 1=-1, 2=0, 3=0}
preX:2, preY: 1, result: 5, map:{0=1, 1=-1, 2=0, 3=0}
preX:2, preY: 1, result: 5, map:{0=1, 1=0, 2=0, 3=0}
preX:3, preY: 0, result: 6, map:{0=0, 1=0, 2=0, 3=0}
 */
            System.out.println(String.format("preX:%s, preY: %s, result: %s, map:%s", preX, preY, result, map));
        }
        return result;
    }
    private static int calcY(TreeMap<Integer, Integer> map) {
        int result = 0, pre = -1, count = 0;
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            //pre >= 0 can be removed
            if (pre >= 0 && count > 0) {
                result += e.getKey() - pre;
            }
            count += e.getValue();
            pre = e.getKey();
        }
        return result;
    }
/*     B
 *  _| |
   | | |_ C
  A|_|_|_|
 */
    public static void main(String[] args) {
        //int[][] in = {{0,0,2,2}};
        // A B as above example as following order
        int[][] in = {{0,0,2,2},{1,0,2,3},{1,0,3,1}};
        System.out.println(rectangleArea(in));
    }
}