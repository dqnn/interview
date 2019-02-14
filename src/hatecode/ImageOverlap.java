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
    //O(A2^2 * B2  + N^2 )/O(N^2)
    public int largestOverlap(int[][] A, int[][] B) {
        int N = A.length;
        List<Point> A2 = new ArrayList<>();
        List<Point> B2 = new ArrayList<>();
        //transform 2D to 1D array, but 
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

    //O(N^4)/O(N^2)， this is brutal force solution, 
    
    //so no matte what kind of overlap A and B, the x and y max is 2N, so we list 
    //2N +1  2D matrix and we use 4 for loop to calculate the occurrence 
    
    public int largestOverlap2(int[][] A, int[][] B) {
        int N = A.length;
        int[][] allinOneMatrix = new int[2*N+1][2*N+1];
        for (int i = 0; i < N; ++i)
            for (int j = 0; j < N; ++j)
                if (A[i][j] == 1)
                    //so we (i,j) as top left in A, how many same 1 in B with all kinds of move
                    for (int i2 = 0; i2 < N; ++i2)
                        for (int j2 = 0; j2 < N; ++j2)
                            //so if we foind another 1, we always +1
                            if (B[i2][j2] == 1) allinOneMatrix[i-i2 +N][j-j2 +N] += 1;

        int ans = 0;
        for (int[] row: allinOneMatrix)
            for (int v: row)
                ans = Math.max(ans, v);
        return ans;
    }
    
    
    //O(N^2 + AB)
/*
Assume index in A and B is [0, N * N -1].

Loop on A, if value == 1, save a coordinates i / N * 100 + i % N to LA.
Loop on B, if value == 1, save a coordinates i / N * 100 + i % N to LB.
Loop on combination (i, j) of LA and LB, increase count[i - j] by 1.
If we slide to make A[i] orverlap B[j], we can get 1 point.
Loop on count and return max values.
I use a 1 key hashmap. Assume ab for row and cd for col, I make it abcd as coordinate.
For sure, hashmap with 2 keys will be better for understanding.

Time Complexity:
O(N^2) for preparing, and O(AB) for loop.
O(AB + N^2) AB means how many 1 in A * count(1) in B

1.why 100?
100 is big enough and very clear.
For example, If I slide 13 rows and 19 cols, it will be 1319.

why not 30?
30 is not big enough.
For example: 409 = 13 * 30 + 19 = 14 * 30 - 11.
409 can be taken as sliding "14 rows and -11 cols" or "13 rows and 19 cols" at the same time.

How big is enough?
Bigger than 2N-1. Bigger than 2N-1. Bigger than 2N-1. N <= 30

Can we replace i / N * 100 + i % N by i?
No, it's wrong for simple test case [[0,1],[1,1]], [[1,1],[1,0]]
 */
    public int largestOverlap4(int[][] A, int[][] B) {
        int N = A.length;
        List<Integer> LA = new ArrayList<>(), LB = new ArrayList<>();
        HashMap<Integer, Integer> count = new HashMap<>();
        //so we map the 2D matrix to one array, 
        //TODO: underdtand more about the mapping, why we choose 100 , 2N -1 is enough
        for (int i = 0; i < N * N; ++i) 
            if (A[i / N][i % N] == 1) LA.add(i / N * 100 + i % N);
        
        for (int i = 0; i < N * N; ++i) 
            if (B[i / N][i % N] == 1) LB.add(i / N * 100 + i % N);
        
        for (int i : LA) 
            for (int j : LB)
                count.put(i - j, count.getOrDefault(i - j, 0) + 1);
        
        int res = 0;
        for (int i : count.values()) res = Math.max(res, i);
        return res;
    }
}