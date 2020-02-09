package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DrawCircle {
/*
Pure storage interview:
    Given a parameter r2, where the equation x^2+y^2=r2 holds.
    Return a list of points that 
        (1) x and y are both integers
        (2) fits the circle equation
 */
    //since they are integer, so we can bianry search
     class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x= x;
            this.y = y;
        }
        @Override
        public int hashCode() {
            return 31 * x + 37 * y;
        }
     }
    //start from x and smaller than y
    public List<Point> drawCircle(int r) {
        Set<Point> res = new HashSet<>();
        int x = 1, y = 0;
        while( x * x < r) {
            int ys = 0, ye = x;
            while(ys <= ye) {
                int ym = ys + (ye - ys) / 2;
                if (x * x + ym * ym == r) {
                    res.add(new Point(x, ym));
                    res.add(new Point(-x, ym));
                    res.add(new Point(x, -ym));
                    res.add(new Point(-x, ym));
                    res.add(new Point(-ym, x));
                    res.add(new Point(-ym, -x));
                    res.add(new Point(ym, x));
                    res.add(new Point(ym, -x));
                } else if (x * x + ym * ym < r) {
                    ys = ym + 1;
                } else {
                    ye = ym - 1;
                }
            }
            x += 1;
        }
        return new ArrayList<>(res);
    }
}
