package hatecode;
import java.util.*;
public class KClosestPointsToOrigin {
/*
973. K Closest Points to Origin
Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation: 
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
*/
    class Point {
        int x, y;
        long dis;
        public Point(int x, int y) {
            this.x= x;
            this.y = y;
            this.dis = (long)(x * x +  y * y);
        }
    }
    public int[][] kClosest2(int[][] p, int K) {
        if (p == null || p.length < 1 || p[0].length != 2 || p.length <= K) return p;
        
        //PriorityQueue<int[]> pq = new PriorityQueue<>(); can be reduce code 
        PriorityQueue<Point> pq = new PriorityQueue<>((a, b)->((int)(b.dis - a.dis)));
        for(int[] point : p) {
            pq.offer(new Point(point[0], point[1]));
            if (pq.size() > K) pq.poll();
        }
        int[][] res = new int[K][2];
        int idx = 0;
        while(!pq.isEmpty()) {
            Point temp = pq.poll(); 
            res[idx][0] = temp.x;
            res[idx++][1] = temp.y;
        }
        return res;
    }
    
    //O(n) solution, quick sort
    public int[][] kClosest(int[][] points, int K) {
        int len =  points.length, l = 0, r = len - 1;
        while (l <= r) {
            int mid = helper(points, l, r);
            if (mid == K) break;
            if (mid < K) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, K);
    }

    private int helper(int[][] A, int l, int r) {
        int[] pivot = A[l];
        while (l < r) {
            while (l < r && compare(A[r], pivot) >= 0) r--;
            A[l] = A[r];
            while (l < r && compare(A[l], pivot) <= 0) l++;
            A[r] = A[l];
        }
        A[l] = pivot;
        return l;
    }

    private int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
    }
    
    //O(N) solution, quick sort
    private Random random = new Random();
    public int[][] kClosest_RandomPartition(int[][] points, int K) {
        int start = 0, end = points.length - 1;
        int idx = 0;
        while (start <= end) {
            idx = partition(points, start, end);
            if (idx == K) {
                break;
            }
            if (idx > K) end = idx - 1;
            else start = idx + 1;
        }
        int[][] res = new int[idx][2];
        for (int i = 0; i < idx; i++) {
            res[i] = points[i];
        }
        return res;
    }
    
    private int partition(int[][] points, int start, int end) {
        int rd = start + random.nextInt(end - start + 1);
        int[] target = points[rd];
        swap(points, rd, end);
        int left = start, right = end - 1;
        while (left <= right) {
            while (left <= right && !isLarger(points[left], target)) left++;
            while (left <= right && isLarger(points[right], target)) right--;
            if (left <= right) {
                swap(points, left, right);
                left++;
                right--;
            }
        }
        swap(points, left, end);
        return left;
    }
    
    private void swap(int[][] points, int i1, int i2) {
        int[] temp = points[i1];
        points[i1] = points[i2];
        points[i2] = temp;
    }
    
    // return true if p1 dist is larger than p2
    private boolean isLarger(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] > p2[0] * p2[0] + p2[1] * p2[1];
    }
}