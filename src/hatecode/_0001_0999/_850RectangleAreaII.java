package hatecode._0001_0999;
import java.util.*;
public class _850RectangleAreaII {
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
    //Rectangle has 3 type questions
    //1. given a list of coordinations, whether they have overlap or not
    //2 .calc the area no matter there were overalp or not
    //3. random choose coordinations in these rectangles and output, same as 528. Random Pick with Weight
    //and 497. Random Point in Non-overlapping Rectangles
    //thinking process:
    
    //first we sort the 4 rectangle points, if same x, the bigger one will come first then 
    //second one. so we use x = 0 to scan this array. 
    
    //preY means for next "x", what's previous Y value which can form the new rectangle. 
    public static int rectangleArea(int[][] rectangles) {
        int M = 1000000007;
        List<Point> data = new ArrayList<>();
        //1 means rectangle starts and -1 means rectangle ends
        for (int[] r : rectangles) {
            //left bottom
            data.add(new Point(r[0], r[1], 1));
            //left top
            data.add(new Point(r[0], r[3], -1));
            //right bottom
            data.add(new Point(r[2], r[1], -1));
            //right top
            data.add(new Point(r[2], r[3], 1));
        }
        //Collections.sort(data, (a, b) -> (a.x - b.x)); also works
        Collections.sort(data, (a, b) -> (a.x == b.x ? b.y -a.y : a.x - b.x));
        //store each node y value, and rec starts or end summary
        TreeMap<Integer, Integer> tree = new TreeMap<>();
        //some points 0 are valid value, so we use -1
        int preX = -1;
        int preY = -1;
        int result = 0;
        for (int i = 0; i < data.size(); i++) {
            Point p = data.get(i);
            tree.put(p.y, tree.getOrDefault(p.y, 0) + p.val);
            //we get next point to compare to current x,
            //data.get(i + 1).x > p.x means not on same x points
            if (i == data.size() - 1 || data.get(i + 1).x > p.x) {
                if (preX > -1) {
                    result += ((long)preY * (p.x - preX)) % M;
                    result %= M;
                }
                preY = calcY(tree);
                preX = p.x;
            }
/*
preX:-1, preY: -1, result: 0, map:{2=-1}
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
            System.out.println(String.format("preX:%s, preY: %s, result: %s, map:%s", preX, preY, result, tree));
        }
        return result;
    }
    //calculate left line length of y, this is pretty tricky usage of treeMap, 
/*
  2 ________4
   |        |
   |   6 ___|_____ 8
   |    |   |     |
 1 |____|___|3    |
        |         |
       5|_________|7


  data will save 8 Points as above graph as 1, 2, 5, 6, 3, 4, 7 ,8
     calcY will only be called when we at 2, 4, 6, 8 --even nodes
  each time calcY will go through all entries in treeMap, because if we have same p.y which will be overwritten,
  
  so the solution will be 
  1. calculate 1,2,5,6 area, when we visit 2, we calculate preY = 2.y-1.y, when we at 4, we got the area
  2. calculate 5,6,3,4 area, same as above
  3. calculate 3,4,7,8 area
  
 */
    private static int calcY(TreeMap<Integer, Integer> map) {
        int recYLen = 0, prePointY = -1, recStartsCount = 0;
        //for rectangle starts here then we need to count, but if some ends here then we should 
        //ignore, so summary the two cases here we just need to get the final result
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            //prePointY >= 0 can be removed
            if (prePointY >= 0 && recStartsCount > 0) {
                recYLen += e.getKey() - prePointY;
            }
            recStartsCount += e.getValue();
            prePointY = e.getKey();
        }
        return recYLen;
    }
/*     B
 *  _| |
   | | |_ C
  A|_|_|_|
 */
    public static void main(String[] args) {
        int[][] in = {{0,0,2,2}};
        // A B as above example as following order
        //int[][] in = {{0,0,2,2},{1,0,2,3},{1,0,3,1}};
        System.out.println(rectangleArea(in));
    }
    
    
    //O(n^2)
    public int rectangleArea_IntervalJoin(int[][] rectangles) {
        List<int[]> list = new LinkedList<>();
        long sum = 0;
        for (int[] cur : rectangles) {
            sum += join(list, cur);
            sum = sum % (1000000007);
        }
        return (int)sum;
    }
    public long join(List<int[]> list, int[] cur) {
        if (list.size() == 0) {
            list.add(cur);
            return area(cur);
        }
        Iterator<int[]> iter = list.iterator();
        List<int[]> cache = new LinkedList<>();
        long sum = 0;
        while (iter.hasNext()) {
            int[] tmp = iter.next();
            if (cur[2] >= tmp[0] && cur[0] <= tmp[2] && cur[3] >= tmp[1] && cur[1] <= tmp[3]) {
                sum -= area(tmp);
                iter.remove();
                if (cur[0] > tmp[0]) 
                    cache.add(new int[]{tmp[0], tmp[1], cur[0], tmp[3]});
                if (cur[3] < tmp[3]) 
                    cache.add(new int[]{Math.max(cur[0], tmp[0]), cur[3], tmp[2], tmp[3]});
                if (cur[1] > tmp[1])
                    cache.add(new int[]{Math.max(cur[0], tmp[0]), tmp[1], tmp[2], cur[1]});
                if (cur[2] < tmp[2])
                    cache.add(new int[]{cur[2], Math.max(tmp[1], cur[1]), tmp[2], Math.min(cur[3], tmp[3])});
            }
        }
        cache.add(cur);
        for (int[] x : cache) {
            list.add(x);
            sum += area(x);
            sum = sum % 1000000007;
        }
        return sum;
    }
    public long area(int[] cur) {
        return ((cur[2] - cur[0]) * (long)(cur[3] - cur[1]))  % 1000000007;
    }
    
    //segment tree solution, O(nlgn)/O(n)
    public int rectangleArea_SegmentTree(int[][] rectangles) {
        int OPEN = 1, CLOSE = -1;
        int[][] events = new int[rectangles.length * 2][];
        Set<Integer> Xvals = new HashSet<>();
        int t = 0;
        for (int[] rec: rectangles) {
            events[t++] = new int[]{rec[1], OPEN, rec[0], rec[2]};
            events[t++] = new int[]{rec[3], CLOSE, rec[0], rec[2]};
            Xvals.add(rec[0]);
            Xvals.add(rec[2]);
        }

        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

        Integer[] X = Xvals.toArray(new Integer[0]);
        Arrays.sort(X);
        Map<Integer, Integer> Xi = new HashMap<>();
        for (int i = 0; i < X.length; ++i)
            Xi.put(X[i], i);

        SegmenTreeNode active = new SegmenTreeNode(0, X.length - 1, X);
        long ans = 0;
        long cur_x_sum = 0;
        int cur_y = events[0][0];

        for (int[] event: events) {
            int y = event[0], typ = event[1], x1 = event[2], x2 = event[3];
            ans += cur_x_sum * (y - cur_y);
            cur_x_sum = active.update(Xi.get(x1), Xi.get(x2), typ);
            cur_y = y;

        }

        ans %= 1_000_000_007;
        return (int) ans;
    }
}

 class SegmenTreeNode {
    int start, end;
    Integer[] values;
    SegmenTreeNode left, right;
    int count;
    long total;

    public SegmenTreeNode(int start, int end, Integer[] X) {
        this.start = start;
        this.end = end;
        this.values = X;
        left = null;
        right = null;
        count = 0;
        total = 0;
    }

    public int getRangeMid() {
        return start + (end - start) / 2;
    }

    public SegmenTreeNode getLeft() {
        if (left == null) left = new SegmenTreeNode(start, getRangeMid(), values);
        return left;
    }

    public SegmenTreeNode getRight() {
        if (right == null) right = new SegmenTreeNode(getRangeMid(), end, values);
        return right;
    }

    public long update(int i, int j, int val) {
        if (i >= j) return 0;
        if (start == i && end == j) {
            count += val;
        } else {
            getLeft().update(i, Math.min(getRangeMid(), j), val);
            getRight().update(Math.max(getRangeMid(), i), j, val);
        }

        if (count > 0) total = values[end] - values[start];
        else total = getLeft().total + getRight().total;

        return total;
    }
    
}