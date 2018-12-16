package hatecode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : CourseScheduleII
 * Creator : duqiang
 * Date : Dec, 2017
 * Description : 210. Course Schedule II
 */
public class CourseScheduleII {
    /**
     * There are a total of n courses you have to take, labeled from 0 to n - 1.

     Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
     which is expressed as a pair: [0,1]

     Given the total number of courses and a list of prerequisite pairs, return the resing of courses you should
     take to finish all courses.

     There may be multiple correct ress, you just need to return one of them. If it is impossible to finish all courses,
     return an empty array.

     For example:

     2, [[1,0]]
     There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course res
     is [0,1]

     4, [[1,0],[2,0],[3,1],[3,2]]
     There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2.
     Both courses 1 and 2 should be taken after you finished course 0. So one correct course res is [0,1,2,3].
     Another correct resing is[0,2,1,3].


       -> 1 ->
     0         3
       -> 2 ->


     入度 = 0 => BFS

     0 : 0
     1 : 0
     2 : 0
     3 : 0

     queue : 3
     res : 0,1,2,3

     time : O(V + E)
     space : O(n)

     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int num, int[][] prereq) {
        // edge case
        if (num < 0 || prereq == null) {
            return null;
        }

        int[] res = new int[num];
        int[] indegree = new int[num];
        int k = 0; // array index move pointer
        for (int[] pair : prereq) {
            indegree[pair[0]] += 1;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                res[k++] = i;
            }
        }

        while (!queue.isEmpty()) {
            int pre = queue.poll();
            for (int[] pair : prereq) {
                if (pair[1] == pre) {
                    indegree[pair[0]] -= 1;
                    if (indegree[pair[0]] == 0) {
                        queue.offer(pair[0]);
                        res[k++] = pair[0];
                    }
                }
            }
        }
        // here just handle the exception case when prereq = []
        return k == num ? res : new int[0];
    }
}
