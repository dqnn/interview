package hatecode._0001_0999;

import java.util.Stack;

/**
 * Description : 84. Largest Rectangle in Histogram
 */
public class _084LargestRectangleinHistogram {
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
    /*
     * Do push all heights including 0 height.
      i - 1 - s.peek() uses the starting index where height[s.peek() + 1] >= height[tp], 
      because the index on top of the stack right now is the first index left of tp with height smaller than tp's height.
     */
    //easy to remember, interview friendly
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
            //because if h is bigger than peek means we should get bigger area, so we continue push,
            //else we should stop and verify what's the current max value, greedy 
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
    //TODO: take care about the explanations and complexity
    public int largestRectangleArea3(int[] heights) {
        // validate input
        if (heights == null || heights.length == 0) {
            return 0;
        }

        // init
        int n = heights.length;
        //// idx of the first bar the left that is lower than current
        int[] left = new int[n];
     // idx of the first bar the right that is lower than current
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
    /*
    For any bar i the maximum rectangle is of width r - l - 1 where r - is the last coordinate of the bar to the right 
    with height h[r] >= h[i] and l - is the last coordinate of the bar to the left which height h[l] >= h[i]

    So if for any i coordinate we know his utmost higher (or of the same height) neighbors to the right and to the left, 
    we can easily find the largest rectangle:

    int maxArea = 0;
    for (int i = 0; i < height.length; i++) {
        maxArea = Math.max(maxArea, height[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
    }
    The main trick is how to effectively calculate lessFromRight and lessFromLeft arrays. 
    The trivial solution is to use O(n^2) solution and for each i element first find his left/right heighbour 
    in the second inner loop just iterating back or forward:

    for (int i = 1; i < height.length; i++) {              
        int p = i - 1;
        while (p >= 0 && height[p] >= height[i]) {
            p--;
        }
        lessFromLeft[i] = p;              
    }
    The only line change shifts this algorithm from O(n^2) to O(n) complexity: we don't need to rescan 
    each item to the left - we can reuse results of previous calculations and "jump" through indices in quick manner:

    while (p >= 0 && height[p] >= height[i]) {
          p = lessFromLeft[p];
    }
    */
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
