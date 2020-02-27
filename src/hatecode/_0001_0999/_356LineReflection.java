package hatecode._0001_0999;

import java.util.HashSet;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LineReflection
 * Creator : duqiang
 * Date : Jan, 2018
 * Description : 356. Line Reflection
 */
public class _356LineReflection {
    /**
     * Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given set of points.

Example 1:
Given points = [[1,1],[-1,1]], return true.

Example 2:
Given points = [[1,1],[-1,-1]], return false.

Follow up:
Could you do better than O(n2)?

Hint:

Find the smallest and largest x-value for all points.
If there is a line then it should be at y = (minX + maxX) / 2.
For each point, make sure that it has a reflected point in the opposite side.

     x1 + x2 = c
     min max

     x1 = c - x2

     平行于y轴 : x : 对称 (两个点)
                y : 相同
首先我们找到所有点的横坐标的最大值和最小值，那么二者的平均值就是中间直线的横坐标，
然后我们遍历每个点，如果都能找到直线对称的另一个点，则返回true，反之返回false
     HashSet
     1, 找出关于哪个轴对称
     2, check

     "1,1" "-1,1"

     x2 = sum - pair

     time : O(n)
     space : O(n)

     * @param points
     * @return
     */
    public boolean isReflected(int[][] points) {
        HashSet<String> set = new HashSet<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int[] pair : points) {
            set.add(pair[0] + "," + pair[1]);
            min = Math.min(min, pair[0]);
            max = Math.max(max, pair[0]);
        }
        int sum = min + max;
        for (int[] pair : points) {
            if (!set.contains(sum - pair[0] + "," + pair[1])) {
                return false;
            }
        }
        return true;
    }
    // anothe soluton is to 下面这种解法没有求最大值和最小值，而是把所有的横坐标累加起来，然后求平均数，基本思路都相同
}
