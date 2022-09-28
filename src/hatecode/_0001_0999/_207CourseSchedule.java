package hatecode._0001_0999;

import java.util.*;
import java.util.stream.*;

/**
 * Description : 207. Course Schedule
 */
public class _207CourseSchedule {

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

    // interview friendly. 
    
    
    
    //So the question is to ask given how many classes, and their dependency, to 
    //test whether we can pass all courses
    /*
     * numCourses = 3, prerequisites = [[1,0],[2,1]]
     * we have 2 courses, 0, 1 and 2, you need to take 0 to get 1, and to get 2 you 
     * need to take 1, so return true
     *  
     *  this problem is like alien dictionary to reduce indegree every time
     *  we need to find a path which start from 0 indegree to last element
     */
    //we are doing a little different compared to alien dictionary, for example, we here the indegree is 
    //adding on the parent, not on the child itself, u mean dependency itself.
    public boolean canFinish_Improved(int courses, int[][] deps) {
        if(courses <= 1) return true;

        int[] indegree = new int[courses];
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int[] dep :deps) {
            indegree[dep[0]]++;
            map.computeIfAbsent(dep[1], v->new HashSet<>()).add(dep[0]);
        }
        Queue<Integer> q = new LinkedList<>();
        IntStream.range(0, courses).filter(e->indegree[e] == 0).forEach(e->q.offer(e));
        int res = courses;
        while(!q.isEmpty()) {
            res--;
            int course = q.poll();
            //here we cannot break, because the map we use here only collects the courses which has 
            //parents, some isolated nodes may not need pre-courses, which means they can finish immediately
            //so just let the loop go
            if(!map.containsKey(course)) continue;
            for(int parent : map.get(course)) {
                indegree[parent]--;
                if(indegree[parent] == 0) q.offer(parent);
            }
        }
        return res == 0? true: false;
    }
    
    //for reference
    public boolean canFinish(int courses, int[][] dependencies) {
        // edge case
        if (courses < 1) return false;

        int[] indegree = new int[courses];
        // courses number
        int res = courses;

        for (int[] dep : dependencies) {
            // here the end of each pair degree+, if
            // one node has more "end", which means this node has several depenencies.
            // indegree here
            // means the dependency number
            indegree[dep[0]]++;
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
            for (int[] pair : dependencies) {
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
