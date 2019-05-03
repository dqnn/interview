package hatecode;

import java.util.*;
public class ReachingPoints {
/*
780. Reaching Points
A move consists of taking a point (x, y) and transforming it to either (x, x+y) or (x+y, y).

Given a starting point (sx, sy) and a target point (tx, ty), return True if and only if a sequence of moves exists to transform the point (sx, sy) to (tx, ty). Otherwise, return False.

Examples:
Input: sx = 1, sy = 1, tx = 3, ty = 5 all > 0
Output: True
Explanation:
One series of moves that transforms the starting point to the target is:
(1, 1) -> (1, 2)
(1, 2) -> (3, 2)
(3, 2) -> (3, 5)

Input: sx = 1, sy = 1, tx = 2, ty = 2
Output: False
*/
    //TLE even with visited
    public boolean reachingPoints_BFS(int sx, int sy, int tx, int ty) {
        Set<int[]> visited = new HashSet<>();
        Queue<int[]> q = new LinkedList<>();
        int[] src = new int[]{sx, sy};
        visited.add(src);
        q.offer(src);
        
        while(!q.isEmpty()) {
            int size = q.size();
            while(size-- > 0) {
                int[] p  = q.poll();
                int x = p[0];
                int y = p[1];
                if (x == tx && y == ty) return true;
                int[] p1 = new int[]{x+y, y}, p2 = new int[]{x, x+y};
                if (!visited.contains(p1)) {
                    visited.add(p1);
                    q.offer(p1);
                }
                if (!visited.contains(p2)) {
                    visited.add(p2);
                    q.offer(p2);
                }
            }
            
        }
        return false;
    }
    
    //O(log(max(tx,ty))), O(1)
    //thinking process: 
/*
我们的目标是将tx和ty分别缩小到sx和sy，不可能一步就缩小到位，那么这肯定是一个循环，
终止条件是tx和ty中任意一个小于了sx和sy，那么在循环内部，我们想要缩小tx或ty，
先缩小两者中较大的那个，若tx大于ty，我们可以尝试缩小tx，但是如果此时ty等于sy了话，
我们可以迅速判断出结果，通过计算此时tx和sx的差值是否是ty的倍数，因为此时ty不能改变了，
只能缩小tx，若能通过减去整数倍数个ty得到sx的，就表示可以到达。如果ty不等于sy的话，
那么直接tx对ty取余即可。同理，若ty大于tx，我们可以尝试缩小ty，但是如果此时tx等于sx了话，
我们可以迅速判断出结果，通过计算此时ty和sy的差值是否是tx的倍数，如果tx不等于sx的话，那么直接ty对tx取余即可。
循环退出后检测起始点和目标点是否相等
 */
    public boolean reachingPoints_Best(int sx, int sy, int tx, int ty) {
        while (tx >= sx && ty >= sy) {
            if (tx == ty) break;
            if (tx > ty) {
                if (ty > sy) tx %= ty;
                else return (tx - sx) % ty == 0;
            } else {
                if (tx > sx) ty %= tx;
                else return (ty - sy) % tx == 0;
            }
        }
        return (tx == sx && ty == sy);
    }
    //Recursive ones
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        if (sx == tx && sy == ty) {
            return true;
        } else if (tx == ty || sx > tx || sy > ty) { 
            return false;
        } else if (tx > ty) {
            int subtract = Math.max(1, (tx - sx)/ty);
            return reachingPoints(sx, sy, tx - subtract * ty, ty);                 
        } else {
            int subtract = Math.max(1, (ty - sy)/tx);
            return reachingPoints(sx, sy, tx, ty - subtract * tx);
        }
    }
}