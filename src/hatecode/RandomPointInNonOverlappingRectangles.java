package hatecode;
import java.util.*;
public class RandomPointInNonOverlappingRectangles {
    /*
     * 497. Random Point in Non-overlapping Rectangles Given a list of
     * non-overlapping axis-aligned rectangles rects, write a function pick which
     * randomly and uniformily picks an integer point in the space covered by the
     * rectangles.
     * 
     * Input: ["Solution","pick","pick","pick","pick","pick"]
     * [[[[-2,-2,-1,-1],[1,0,3,0]]],[],[],[],[],[]] Output:
     * [null,[-1,-2],[2,0],[-2,-1],[3,0],[-2,-2]]
     */
    int[][] rects;
    //prefix sum
    List<Integer> psum;
    int tot = 0;
    Random rand;

    public RandomPointInNonOverlappingRectangles(int[][] rects) {
        this.rects = rects;
        this.rand = new Random();
        this.psum = new ArrayList<>();
        for (int[] x : rects){
            tot += (x[2] - x[0] + 1) * (x[3] - x[1] + 1);
            psum.add(tot);
        }
    }

    public int[] pick() {
        int targ = rand.nextInt(tot);

        int l = 0;
        int r = rects.length - 1;
        while (l != r) {
            int mid = (l + r) / 2;
            if (targ >= psum.get(mid)) l = mid + 1;
            else r = mid;
        }

        int[] x = rects[l];
        int width = x[2] - x[0] + 1;
        int height = x[3] - x[1] + 1;
        int base = psum.get(l) - width * height;
        return new int[]{x[0] + (targ - base) % width, x[1] + (targ - base) / width};
    }
    //this is better solution, we don't need to write binary search
    class RandomPointInNonOverlappingRectangles_TreeMap {
        private int[][]                   rects;
        private Random                    r;
        private TreeMap<Integer, Integer> tree;
        private int                       totalArea;

        public RandomPointInNonOverlappingRectangles_TreeMap(int[][] rects) {
            this.rects = rects;
            r = new Random();
            tree = new TreeMap<>();
            totalArea = 0;
            for (int i = 0; i < rects.length; i++) {
                totalArea += (rects[i][2] - rects[i][0] + 1) * (rects[i][3] - rects[i][1] + 1);
                tree.put(totalArea, i);
            }
        }

        public int[] pick() {
            int randInt = r.nextInt(totalArea);
            //return 
            int key = tree.higherKey(randInt); //ceilingKey() + 1 == higherKey
            int[] rect = rects[tree.get(key)];
            int x = rect[0] + (key - randInt - 1) % (rect[2] - rect[0] + 1);
            int y = rect[1] + (key - randInt - 1) / (rect[2] - rect[0] + 1);
            return new int[] {x, y};
        }
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(rects);
 * int[] param_1 = obj.pick();
 */