package hatecode;
import java.util.*;
public class ImageOverlap {
/*
835. Image Overlap
Two images A and B are given, represented as binary, square matrices of the same size.  (A binary matrix has only 0s and 1s as values.)

We translate one image however we choose (sliding it left, right, up, or down any number of units), and place it on top of the other image.  After, the overlap of this translation is the number of positions that have a 1 in both images.

(Note also that a translation does not include any kind of rotation.)

What is the largest possible overlap?

Example 1:

Input: A = [[1,1,0],
            [0,1,0],
            [0,1,0]]
       B = [[0,0,0],
            [0,1,1],
            [0,0,1]]
Output: 3
Explanation: We slide A to right by 1 unit and down by 1 unit.
*/
    class Point {
        int x; int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        //we need equals and hashCode when in a hashSet collection 
        @Override
        public boolean equals(Object obj) { //System.out.println("inside");
            return (((Point) obj).x == this.x)&&(((Point) obj).y == this.y);
        }

        @Override
        public int hashCode(){
            return Objects.hash(x,y);
        }
       
    }
    //O(N^6)/O(N^2)
    public int largestOverlap(int[][] A, int[][] B) {
        int N = A.length;
        List<Point> A2 = new ArrayList<>();
        List<Point> B2 = new ArrayList<>();
        for(int i = 0; i< N * N; i++) {
            if (A[i/N][i%N] == 1) A2.add(new Point(i/N, i%N));
            if (B[i/N][i%N] == 1) B2.add(new Point(i/N, i%N));
        }

        Set<Point> BSet = new HashSet<>(B2);

        int res = 0;
        Set<Point> visited = new HashSet<>();
        for(Point a : A2) {
            for(Point b:B2) {
                Point delta = new Point(b.x - a.x, b.y - a.y);
                if (!visited.contains(delta)) {
                    visited.add(delta);
                    int cand = 0;
                    for(Point p :A2) {
                        if (BSet.contains(new Point(p.x + delta.x, p.y + delta.y))) cand++;
                    }
                    res = Math.max(res, cand);
                }
            }
        }
        return res;
    }

    //O(N^4)/O(N^2)
    public int largestOverlap2(int[][] A, int[][] B) {
        int N = A.length;
        int[][] allinOneMatrix = new int[2*N+1][2*N+1];
        for (int i = 0; i < N; ++i)
            for (int j = 0; j < N; ++j)
                if (A[i][j] == 1)
                    for (int i2 = 0; i2 < N; ++i2)
                        for (int j2 = 0; j2 < N; ++j2)
                            if (B[i2][j2] == 1)
                                allinOneMatrix[i-i2 +N][j-j2 +N] += 1;

        int ans = 0;
        for (int[] row: allinOneMatrix)
            for (int v: row)
                ans = Math.max(ans, v);
        return ans;
    }
}