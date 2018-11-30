package leetcode;

import java.util.Iterator;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : Flatten2DVector
 * Creator : duqiang
 * Date : Nov, 2017
 * Description : 251. Flatten 2D Vector
 */
public class Flatten2DVector {
    /**
     * Given 2d vector =

     [
     [1,2],
     [3],
     [4,5,6]
     ]
     By calling next repeatedly until hasNext returns false,
     the order of elements returned by next should be: [1,2,3,4,5,6].
     
     so it requires to implement two functions
     E next()
     boolean hasNext()

     time : O(n)
     space : O(1)

     */

    Iterator<List<Integer>> itrs;
    Iterator<Integer> row;
    public Flatten2DVector(List<List<Integer>> vec2d) {
        if(vec2d == null || vec2d.size() == 0) return;
        itrs = vec2d.iterator();
        row = itrs.next().iterator();
        getNextRow();
    }
    private void getNextRow() {
        while(!row.hasNext() && itrs.hasNext()) {
            row = itrs.next().iterator();
        }
    }
    //@Override
    public Integer next() {
        int next = row.next();
        getNextRow();
        return next;
    }

    //@Override
    public boolean hasNext() {
        return row != null && row.hasNext();
    }
}
