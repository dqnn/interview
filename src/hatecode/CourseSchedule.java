package hatecode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : CourseSchedule
 * Creator : duqiang
 * Date : Nov, 2017
 * Description : 207. Course Schedule
 */
public class CourseSchedule {

    /**
     * There are a total of n courses you have to take, labeled from 0 to n - 1.

     Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
     which is expressed as a pair: [0,1]

     Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

     For example:

     2, [[1,0]]
     There are a total of 2 courses to take. To take course 1 you should have finished 
     course 0. So it is possible.

     2, [[1,0],[0,1]]
     There are a total of 2 courses to take. To take course 1 you should have finished course 0,
     and to take course 0 you should also have finished course 1. So it is impossible.

     3-  0 - 1
         \   /
           2

     入度 = 0

     0 : 1
     1 : 1
     2 : 1

     queue : 2

     pre : 2

     res = 3


     time : O(V + E)
     space : O(n)

     * @param numCourses
     * @param prerequisites
     * @return
     */

    // BFS
    public boolean canFinish(int num, int[][] prereq) {
        // edge case
        if (num < 1) {
            return false;
        }

        int[] indegree = new int[num];
        // courses number
        int res = num;

        for (int[] pair : prereq) {
            // here the end of each pair degree+, if
            // one node has more "end", which means this node has several depenencies.
            // indegree here
            // means the dependency number
            indegree[pair[0]]++;
        }

        // so we here add any node which has no dependency, which means there is no
        // requirements to
        // start this class
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);// i here means course number, i = pair[0]
            }
        }

        while (!queue.isEmpty()) {
            int pre = queue.poll();
            res--;
            for (int[] pair : prereq) {
                // here means these pairs are good to ignore, [2,[1,0]]-->[2,1]
                if (pair[1] == pre) {// this means the one who has dependency
                    // degree decrease because we have done this operation on 1 [2,[1,0]]-->[2,1]
                    // but there also
                    // [1,5], so 1 still have 1 dependency, here like 1 degree --
                    indegree[pair[0]]--;
                    // this one means course 1 has no dependency, and 1 has passed test
                    if (indegree[pair[0]] == 0) queue.offer(pair[0]);
                }
            }
        }
        return res == 0 ? true : false;

    }
}
