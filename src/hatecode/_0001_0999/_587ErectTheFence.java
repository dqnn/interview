package hatecode._0001_0999;

import java.util.*;
public class _587ErectTheFence {
/*
587. Erect the Fence
There are some trees, where each tree is represented by (x,y) coordinate in a two-dimensional garden. Your job is to fence the entire garden using the minimum length of rope as it is expensive. The garden is well fenced only if all the trees are enclosed. Your task is to help find the coordinates of trees which are exactly located on the fence perimeter.

Input: [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
Output: [[1,1],[2,0],[4,2],[3,3],[2,4]]

这道题本质上是给定一堆点求convex hull也就是凸包的问题。我们首先分析凸多边形的性质，凸多边形中任意两个顶点相连的边都仍然在凸多边形内，不会穿过边界。那么我们可以根据这个思路写出brute force的做法：我们遍历所有的边，其他所有点都在当前边的一侧，那么这条边肯定为凸包的一条边。我么有N^2条边，check每条边需要O(N)的时间，总的时间复杂度O(N^3)。
显然这个时间复杂度太高了，我们需要其他的解法。我们下面介绍两种求凸包的方法：

Jarvis Algorithm

这个算法是对上面暴力思路的一个优化，试想我们找到最左边的一个点(如果有多个随便取一个)，那么其一定是凸包上的一个点。那么我们可以从这个点开始check其他任意点u和当前点v连成的边，如果所有其他点都在这条边的一侧，那么点v也是凸包上的点，然后我们在移到下一点v继续这个过程。那么如何check其他点是否位于这条边的两侧呢？我们用Convex Polygon里求向量叉积的方法来判断是不是所有的点都是"向左/右拐"。详细的算法和示意图可以参考这篇文章，我们不过多深入。时间复杂度O(n * h)，n为所有点的数目，h为在convex hull上点的数目。

Graham Scan

Graham Scan是一个在时间复杂度上更优的解法。如果我们取y值最小的点为基准点(如果有多个我们取x轴小的)，其一定在凸包上，那么其他所有在凸包上的点，其斜率是是遵循一定规律的。如果我们从基准点开始逆时针走，当前点u和基准点v组成的斜率：

如果斜率大于零，递增
如果斜率小于零，递减
所以我们可以把其他所有点按照斜率的这个规律排序，然后我们依次遍历并且check当前的点是否在convex hull上。那么如何判断呢，我们也是用上文提到的叉积的方法，因为我们是逆时针遍历，我们用栈来存当前在凸包上的点，假设当前点为u，栈顶为v，栈顶第二个点为w：
如果w->v->u是一直"向左拐"的，我们把u也压入栈
否则，一直pop栈顶，指导栈顶两个点和u形成"左拐"
这样的时间复杂度就取决于sort的时间复杂度，为O(n * log n)。值得注意的几个细节：
如果多个点斜率相同，我们把距离基准较远的放在前面，这样的话基准点会成为最后一个被遍历的点(反过来也可以，但是下一点很重要)
第一条边上有多个点的话，会出现一个问题，因为我们是把距离较远的放在前面，那么遍历完第一条边上的点之后我们会发现当前向量的方向是和我们预期的方向相反的(我们预期的是从较近点指向较远点的向量)。为了避免这个问题，我们要把开头在一条直线上的点给反过来。(如果上面是把距离基准点较近的点放在前面，那么最后一条边也会出现同样的问题，注意处理即可)
*/
    public List<Point> outerTrees(Point[] points) {
        Set<Point> result = new HashSet<>();
        
        // Find the leftmost point
        Point first = points[0];
        int firstIndex = 0;
        for (int i = 1; i < points.length; i++) {
            if (points[i].x < first.x) {
                first = points[i];
                firstIndex = i;
            }
        }
        result.add(first);
        
        Point cur = first;
        int curIndex = firstIndex;
        do {
            Point next = points[0];
            int nextIndex = 0;
            for (int i = 1; i < points.length; i++) {
                if (i == curIndex) continue;
                int cross = crossProductLength(cur, points[i], next);
                if (nextIndex == curIndex || cross > 0 ||
                        // Handle collinear points
                        (cross == 0 && distance(points[i], cur) > distance(next, cur))) {
                    next = points[i];
                    nextIndex = i;
                }
            }
            // Handle collinear points
            for (int i = 0; i < points.length; i++) {
                if (i == curIndex) continue;
                int cross = crossProductLength(cur, points[i], next);
                if (cross == 0) {
                    result.add(points[i]);
                }
            }

            cur = next;
            curIndex = nextIndex;
            
        } while (curIndex != firstIndex);
        
        return new ArrayList<Point>(result);
    }
    
    private int crossProductLength(Point A, Point B, Point C) {
        // Get the vectors' coordinates.
        int BAx = A.x - B.x;
        int BAy = A.y - B.y;
        int BCx = C.x - B.x;
        int BCy = C.y - B.y;
    
        // Calculate the Z coordinate of the cross product.
        return (BAx * BCy - BAy * BCx);
    }

    private int distance(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }
}