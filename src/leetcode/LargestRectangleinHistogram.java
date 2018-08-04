package leetcode;

import java.util.Stack;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LargestRectangleinHistogram
 * Creator : duqiang
 * Date : Dec, 2017
 * Description : 84. Largest Rectangle in Histogram
 */
public class LargestRectangleinHistogram {
    /**
     * For example,
     Given heights = [2,1,5,6,2,3],
     return 10.

     stack : 1，升序，2，小于，计算
      0,1,2,3,4,5,6
     [2,1,5,6,2,3,0]

     stack : 1
     2 : push
     1 : height = 2 start = -1 res = 2
     5 : push
     6 : push
     2 : height = 6 start = 2 area = 6 res = 6
         height = 5 start = 1 area = 10 res = 10
     3 push
     0 : height = 2 start = 1 area = 8
         height = 1 start = -1 area = 6

     res = 10

     time : O(n)
     space : O(n)


     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i <= heights.length; i++) {
            // so we make i == 0 because if height is [1,2,3,4], we want last to visit back all numbers /
            //when i = 4
            int h = i == heights.length ? 0 : heights[i];
            //only when previous is bigger than current h, we will go to pop stack
            // but the are is only from 0 ~ i - 1, if it is ascend array and h[i] < h[i - 1]
            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int start = stack.isEmpty() ? -1 : stack.peek();
                //The width = index of right boundary - index of left boundary + 1
                //= (i - 1) - (stack.peek() + 1) + 1 = i - 1 - stack.peek(). which means the width
                int area = height * (i - start - 1);
                res = Math.max(res, area);
            }
            stack.push(i);
        }
        return res;
    }
    // interview friendly one, TODO: take care about the explanations and complexity
    public int largestRectangleArea3(int[] heights) {
        // validate input
        if (heights == null || heights.length == 0) {
            return 0;
        }

        // init
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
       

        // we scan from left 
        left[0] = 0;
        for (int i = 1; i < n; i++) {
            int currentLeft = i - 1;
            while (currentLeft >= 0 && heights[currentLeft] >= heights[i]) {
                //we use the index as pointer to store the idx
                currentLeft = left[currentLeft] - 1;
            }
            // 
            left[i] = currentLeft + 1;
        }

        // build right
        right[n - 1] = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            int currentRight = i + 1;
            while (currentRight < n && heights[currentRight] >= heights[i]) {
                currentRight = right[currentRight] + 1;
            }

            right[i] = currentRight - 1;
        }
        int result = 0;
        // compare height
        for (int i = 0; i < n; i++) {
            result = Math.max(result, (right[i] - left[i] + 1) * heights[i]);
        }

        // return
        return result;
    }
    // this is just for FYI, not easy to understand
    public static int largestRectangleArea2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int[] lessFromLeft = new int[height.length]; // idx of the first bar the left that is lower than current
        int[] lessFromRight = new int[height.length]; // idx of the first bar the right that is lower than current
        lessFromRight[height.length - 1] = height.length;
        lessFromLeft[0] = -1;

        for (int i = 1; i < height.length; i++) {
            int p = i - 1;

            while (p >= 0 && height[p] >= height[i]) {
                p = lessFromLeft[p];
            }
            lessFromLeft[i] = p;
        }

        for (int i = height.length - 2; i >= 0; i--) {
            int p = i + 1;

            while (p < height.length && height[p] >= height[i]) {
                p = lessFromRight[p];
            }
            lessFromRight[i] = p;
        }

        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            maxArea = Math.max(maxArea, height[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
        }

        return maxArea;
    }
}
