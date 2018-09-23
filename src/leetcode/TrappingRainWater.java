package leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : TrappingRainWater
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 42. Trapping Rain Water
 */
public class TrappingRainWater {
    /**
Given n non-negative integers representing an elevation map where the width of each bar is 1, 
compute how much water it is able to trap after raining.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of 
rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6

     0,1,0,2,1,0,1,3,2,1,2,1

                   *
           *       * *   *
       *   * *   * * * * * *
     0 1 2 3 4 5 6 7 8 9 0 1
                 l r

     time : O(n)
     space : O(1)

     *
     * @param height
     * @return
     */
    /*
    instead of calculating area by height*width, we can think it in a cumulative way. In other words, 
    sum water amount of each bin(width=1).
Search from left to right and maintain a max height of left and right separately, which is like a one-side wall 
of partial container. Fix the higher one and flow water from the lower part. For example, 
if current height of left is lower, we fill water in the left bin. Until left meets right, 
we filled the whole container.
*/
    public int trap(int[] height) {
        if (height == null || height.length < 1) {
            return 0;
        }
        
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int res = 0;
        while(left < right) {
            if (height[left] < height[right]) {
                leftMax = Math.max(height[left], leftMax);
                res += leftMax - height[left];
                left++;
            } else {
                rightMax = Math.max(height[right], rightMax);
                res += rightMax - height[right];
                right--;
            }
        }
        return res;
    }
    // this is double scan. 
    public int trap2(int[] height) {
        if (height == null || height.length < 1) {
            return 0;
        }
        
        int res = 0;
        int len = height.length;
        int[] la = new int[len];
        int[] ra = new int[len];
        la[0] = height[0];
        ra[len - 1] = height[len - 1];
        for(int i = 1; i < height.length; i++) {
            la[i] = Math.max(la[i-1], height[i]);
        }
            
        for(int i = len - 2; i >= 0; i--) {
            ra[i] = Math.max(ra[i + 1], height[i]);
        }
            
        for(int i = 0; i < len; i++)
            res += Math.min(la[i], ra[i]) - height[i];
        return res;
    }
    
    //stack solutions
/*
Indeed this question can be solved in one pass and O(1) space, but it's probably hard to come up with in a short 
interview. If you have read the stack O(n) solution for Largest Rectangle in Histogram, 
you will find this solution is very very similar.

The main idea is : if we want to find out how much water on a bar(bot), we need to find out the 
left larger bar's index (il), and right larger bar's index(ir), so that the water is 
(min(A[il],A[ir])-A[bot])*(ir-il-1), use min since only the lower boundary can hold water, 
and we also need to handle the edge case that there is no il.

To implement this we use a stack that store the indices with decreasing bar height, once we 
find a bar who's height is larger, then let the top of the stack be bot, the cur bar is ir, 
and the previous bar is il.
 */
    public int trap3(int[] heights) {
        if (heights == null || heights.length < 1) {
            return 0;
        }
        Stack<Integer> s = new Stack<Integer>();
        int i = 0, maxWater = 0, maxBotWater = 0;
        while (i < heights.length){
            if (s.isEmpty() || heights[i]<=heights[s.peek()]){
                s.push(i++);
            }
            else {
                int bot = s.pop();
                maxBotWater = s.isEmpty()? // empty means no il
                0:(Math.min(heights[s.peek()],heights[i])-heights[bot])*(i-s.peek()-1);
                maxWater += maxBotWater;
            }
        }
        return maxWater;
    }
}
