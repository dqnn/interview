package hatecode._0001_0999;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Description : 210. Course Schedule II
 */
public class _210CourseScheduleII {
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
    /*
     * thinking process: O(V+E)/O(n)
     * 
     * the problem is to say: give n courses and its path as array[1,0], return a 
     * path which you can finish all courses
     * 
     * 
     */
    public int[] findOrder(int n, int[][] A) {
        // edge case
        if (n < 0 || A == null) {
            return null;
        }

        int[] res = new int[n];
        int[] indegree = new int[n];
        int k = 0; // array index move pointer
        for (int[] pair : A) {
            indegree[pair[0]] += 1;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                //all courses are independently, so we add them all
                res[k++] = i;
            }
        }

        while (!queue.isEmpty()) {
            int pre = queue.poll();
            for (int[] pair : A) {
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
        return k == n ? res : new int[0];
    }
}
