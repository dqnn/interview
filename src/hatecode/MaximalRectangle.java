package hatecode;

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
    /*
     * left(i,j) = max(left(i-1,j), cur_left), cur_left 
     * can be determined from the current row

right(i,j) = min(right(i-1,j), cur_right), c
ur_right can be determined from the current row

height(i,j) = height(i-1,j) + 1, if matrix[i][j]=='1';

height(i,j) = 0, if matrix[i][j]=='0'
a matrix example:

[
   ["1","0","1","0","0"],
   ["1","0","1","1","1"],
   ["1","1","1","1","1"],
   ["1","0","0","1","0"]
 ]
height: we record successive '1' count from top to down
[1, 0, 1, 0, 0]
[2, 0, 2, 1, 1]
[3, 1, 3, 2, 2]
[4, 0, 0, 3, 0]

left: we always keep most left '1' index of a substring '111111'
[0, 0, 2, 0, 0]
[0, 0, 2, 2, 2]
[0, 0, 2, 2, 2]
[0, 0, 0, 3, 0]

right: we always keep most right '1' index of 
[1, 5, 3, 5, 5]
[1, 5, 3, 5, 5]
[1, 5, 3, 5, 5]
[1, 5, 5, 4, 5]

策略: 把matrix看成多个直方图, 每一行及其上方的数据都构成一个直方图, 需要考察matrix.size()个直方图
对于每个点(row, col), 我们最后都计算以这个点上方的连续的'1'往left, right方向延申可以得到的最大的矩形的面积
通过这种方法获取的矩形一定会把最大的矩形包含在内
height[row][col]记录的是(row, col)这个坐标为底座的直方图柱子的高度, 如果这个点是'0', 那么高度当然是0了
left[row][col]记录的是(row, col)这个坐标点对应的height可以延申到的最左边的位置
right[row][col]记录的是(row, col)这个坐标点对应的height可以延申到的最右边的位置+1
以上面的matrix为例,
对于(row=2, col=1)这个点, left=0, right=5, height=1
对于(row=2, col=2)这个点, left=2, right=3, height=3
(2,2)这个点与(2,1)紧挨着,left和right却已经变化如此之大了, 这是因为left和right除了受左右两边的'1'影响, 还受到了其上方连续的'1'的制约
由于点(2,2)上有height=3个'1', 这几个'1'的left的最大值作为当前点的left, 这几个'1'的right的最小值作为当前点的right
因此, 实际上, 我们是要找以hight对应的这条线段往左右两边移动(只能往全是'1'的地方移动), 可以扫过的最大面积
当hight与目标最大矩形区域的最短的height重合时, 最大矩形的面积就找到了, 如上面的例子, 就是点(2,3)或(2,4)对应的height
     */
    public int maximalRectangle(char[][] m) {
        if (m == null || m.length < 1 || m[0].length < 1)  {
            return 0;
        }
        int r =m.length, c = m[0].length;
        int res = 0;
        //height[i] is to say from previous up to i-1, we have height[i]'s 1 horiontely 
        //if scan next row, and we will add previous row height[i-1], so we can always re-add them into
        //one, this is like a thread to scan the rectangles in side the matrix
        int[] height = new int[c];
        //left is we record the for a contious '1', left[i] will record the first 1's index for this sub
        //'1' array
        int[] left = new int[c];
        //record the '1' from right
        int[] right = new int[c];
        Arrays.fill(right, c);
        
        for(int i = 0; i < r; i++) {
            int curLeft = 0, curRight = c;
            for(int j = 0; j < c; j++) {
                if (m[i][j] == '1') {
                    height[j] ++;
                    //two case for left: 
                    // 1: same row, left[j] means "1" string start position idx, so suppose we have 1 1 0 1 1 1, so 
                    // curLeft will be stay the same since we want first, left[j] initialized as 0 so 
                    // only if m[0][0] = 0 will be used.
                    
                    //2. change row: if we changed row, then if previous left[j] > 0 then we would get pervious value
                    left[j] = Math.max(curLeft, left[j]);
                } else {
                    height[j] = 0;
                    // for left, if it is not 1 then we mark it is 0
                    left[j] = 0;
                    // this means next 1 position,  we assume element is 1
                    curLeft = j + 1;
                }
            }
            
            for(int j = c - 1; j>= 0; j--) {
                if (m[i][j] == '1') {
                    // so we scan from right to left, right is inialized as c, so if 1 1 0 1 1 1
                    // first we need to mark the end position is 1, and right[j] should stay last idx
                    // if it is not 1, then curRight will be c, and right will be j(because next is j--)
                    
                    //if we change row, so we compare to previous row,if they also '1', the value will
                    //be the  same
                    right[j] = Math.min(curRight, right[j]);
                } else {
                    right[j] = c;
                    curRight = j;
                }
            }
            for(int j = 0; j < c; j++) {
                //the length is end - start + 1 since we already have 1 there
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
    //this solution is to use stack to compute the area
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
