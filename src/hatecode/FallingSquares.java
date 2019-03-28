package hatecode;
import java.util.*;
public class FallingSquares {
/*
699. Falling Squares
Input: [[1, 2], [2, 3], [6, 1]]
Output: [2, 5, 5]
On an infinite number line (x-axis), we drop given squares in the order they are given.

The i-th square dropped (positions[i] = (left, side_length)) is a square with the left-most point being positions[i][0] and sidelength positions[i][1].

The square is dropped with the bottom edge parallel to the number line, and from a higher height than all currently landed squares. We wait for each square to stick before dropping the next.

The squares are infinitely sticky on their bottom edge, and will remain fixed to any positive length surface they touch (either the number line or another square). Squares dropped adjacent to each other will not stick together prematurely.


Return a list ans of heights. Each height ans[i] represents the current highest height of any square we have dropped, after dropping squares represented by positions[0], positions[1], ..., positions[i].
*/
    
    class Interval {
        int s, e, h;
        
        public Interval(int s, int e, int h) {
            this.s = s;
            this.e = e;
            this.h = h;
        }
    }
    //O(n^2), we can improve to O(nlgn) by segment tree or others
    public List<Integer> fallingSquares(int[][] positions) {
        List<Interval> intervals = new ArrayList<>();
        
        List<Integer> res = new ArrayList<>();
        int h = 0;
        for(int[] p : positions) {
            //ticky part is to set end = p[0] + p[1] - 1 so if two square are on the same edge then latter cannot stand upon
            //first one
            Interval cur = new Interval(p[0], p[0] + p[1] - 1, p[1]);
            h = Math.max(h, getHeight(intervals, cur));
            res.add(h);
        }
        
        return res;
    }
    
    private int getHeight(List<Interval> intervals, Interval cur) {
        int pre = 0;
        
        for(Interval i : intervals) {
            if (i.e < cur.s) continue;
            if (i.s > cur.e) continue;
            pre = Math.max(pre, i.h);
        }
        
        cur.h += pre;
        intervals.add(cur);
        return cur.h;
    }
    
    
    
    
    
    //just another solution for reference O(nlgn)
    
    class SegmentTree {
        int N, H;
        int[] tree, lazy;

        SegmentTree(int N) {
            this.N = N;
            H = 1;
            while ((1 << H) < N) H++;
            tree = new int[2 * N];
            lazy = new int[N];
        }

        private void apply(int x, int val) {
            tree[x] = Math.max(tree[x], val);
            if (x < N) lazy[x] = Math.max(lazy[x], val);
        }

    private void pull(int x) {
        while (x > 1) {
            x >>= 1;
            tree[x] = Math.max(tree[x * 2], tree[x * 2 + 1]);
            tree[x] = Math.max(tree[x], lazy[x]);
        }
    }

    private void push(int x) {
        for (int h = H; h > 0; h--) {
            int y = x >> h;
            if (lazy[y] > 0) {
                apply(y * 2, lazy[y]);
                apply(y * 2 + 1, lazy[y]);
                lazy[y] = 0;
            }
        }
    }

    public void update(int L, int R, int h) {
        L += N; R += N;
        int L0 = L, R0 = R, ans = 0;
        while (L <= R) {
            if ((L & 1) == 1) apply(L++, h);
            if ((R & 1) == 0) apply(R--, h);
            L >>= 1; R >>= 1;
        }
        pull(L0); pull(R0);
    }

    public int query(int L, int R) {
        L += N; R += N;
        int ans = 0;
        push(L); push(R);
        while (L <= R) {
            if ((L & 1) == 1) ans = Math.max(ans, tree[L++]);
            if ((R & 1) == 0) ans = Math.max(ans, tree[R--]);
            L >>= 1; R >>= 1;
        }
        return ans;
    }
}
    
    
    //just for segment tree, how to use
    public List<Integer> fallingSquares_Segment(int[][] positions) {
        Set<Integer> coords = new HashSet();
        for (int[] pos: positions) {
            coords.add(pos[0]);
            coords.add(pos[0] + pos[1] - 1);
        }
        List<Integer> sortedCoords = new ArrayList(coords);
        Collections.sort(sortedCoords);

        Map<Integer, Integer> index = new HashMap();
        int t = 0;
        for (int coord: sortedCoords) index.put(coord, t++);

        SegmentTree tree = new SegmentTree(sortedCoords.size());
        int best = 0;
        List<Integer> ans = new ArrayList();

        for (int[] pos: positions) {
            int L = index.get(pos[0]);
            int R = index.get(pos[0] + pos[1] - 1);
            int h = tree.query(L, R) + pos[1];
            tree.update(L, R, h);
            best = Math.max(best, h);
            ans.add(best);
        }
        return ans;
    }
}