package leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MaximalRectangle
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : 85. Maximal Rectangle
 */
public class MaximalRectangle {
    /**
     * Given a 2D binary matrix filled with 0's and 1's,
     * find the largest rectangle containing only 1's and return its area.

     For example, given the following matrix:

     1 0 1 0 0
     1 0 1 1 1
     1 1 1 1 1
     1 0 0 1 0
     Return 6.


     left[] ：从左到右，出现连续‘1’的string的第一个座标
     right[] ：从右到左, 出现连续‘1’的string的最后一个座标，
     height[] ： 从上到下的高度。
     res ： (right[j] - left[j]) * heights[j]
     
     height:
     1 0 1 0 0
     2 0 2 1 1
     3 1 3 2 2
     4 0 0 3 0

     left:
     0 0 2 0 0
     0 0 2 2 2
     0 0 2 2 2
     0 0 0 3 0

     right:
     1 5 3 5 5
     1 5 3 5 5
     1 5 3 5 5
     1 5 5 4 5

     time : O(m * n)
     space : O(n)

     * @param matrix
     * @return
     */

    public int maximalRectangle(char[][] m) {
        if (m == null || m.length < 1 || m[0].length < 1)  {
            return 0;
        }
        int r =m.length, c = m[0].length;
        int res = 0;
        int[] height = new int[c];
        int[] left = new int[c];
        int[] right = new int[c];
        Arrays.fill(right, c);
        
        for(int i = 0; i < r; i++) {
            int curLeft = 0, curRight = c;
            for(int j = 0; j < c; j++) {
                if (m[i][j] == '1') {
                    height[j] ++;
                    // left[j] means "1" string start position idx, so suppose we have 1 1 0 1 1 1, so 
                    // curLeft will be stay the same since we want first, left[j] initialized as 0 so 
                    // only if m[0][0] = 0 will be used.
                    left[j] = Math.max(curLeft, left[j]);
                } else {
                    height[j] = 0;
                    // for left, if it is not 1 then we mark it is 0
                    left[j] = 0;
                    // this means next 1 position, 
                    curLeft = j + 1;
                }
            }
            
            for(int j = c - 1; j>= 0; j--) {
                if (m[i][j] == '1') {
                    // so we scan from right to left, right is inialized as c, so if 1 1 0 1 1 1
                    // first we need to mark the end position is 1, and right[j] should stay last idx
                    // if it is not 1, then curRight will be c, and right will be j(because next is j--)
                    right[j] = Math.min(curRight, right[j]);
                } else {
                    right[j] = c;
                    curRight = j;
                }
            }
            for(int j = 0; j < c; j++) {
                res = Math.max(res, (right[j] - left[j]) * height[j]);
            }
            
        }
        return res;
        
    }

    /**
     time : O(m * n)
     space : O(n)

     * @param matrix
     * @return
     */

    public int maximalRectangle2(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int n = matrix[0].length;
        int[] height = new int[n + 1];
        height[n] = 0;
        int res = 0;

        for (int row = 0; row < matrix.length; row++) {
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < n + 1; i++) {
                if (i < n) {
                    if (matrix[row][i] == '1') {
                        height[i]++;
                    } else height[i] = 0;
                }
                if (stack.isEmpty() || height[stack.peek()] <= height[i]) {
                    stack.push(i);
                } else {
                    while (!stack.isEmpty() && height[i] < height[stack.peek()]) {
                        int cur = height[stack.pop()] * (stack.isEmpty() ? i : (i - stack.peek() - 1));
                        res = Math.max(res, cur);
                    }
                    stack.push(i);
                }
            }
        }
        return res;
    }
}
