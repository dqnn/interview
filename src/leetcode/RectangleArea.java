package leetcode;

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
     *
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
        // calc the overlap one
        if (right > left && top > bottom) {
            overlap = (right - left) * (top - bottom);
        }
        return areaA + areaB - overlap;
    }
}
