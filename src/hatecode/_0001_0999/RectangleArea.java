package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : RectangleArea
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 223. Rectangle Area
 */
public class RectangleArea {

    /**
Find the total area covered by two rectilinear rectangles in a 2D plane.

Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.

point (A, B), (C, D), (E, F), (G, H)
     * time : O(1)
     * space : O(1)
     *
     * @param A
     * @param B
     * @param C
     * @param D
     * @param E
     * @param F
     * @param G
     * @param H
     * @return
     */
// just draw a map so we can get it
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int areaA = (C - A) * (D - B);
        int areaB = (G - E) * (H - F);
        // we choose two recs max left 
        int left = Math.max(A, E);
        // we choose min of two recs
        int right = Math.min(C, G);
        int top = Math.min(D, H);
        int bottom = Math.max(B, F);

        int overlap = 0;
        // calc the overlap one,there maybe no overlapped area
        if (right > left && top > bottom) {
            overlap = (right - left) * (top - bottom);
        }
        return areaA + areaB - overlap;
    }
}
