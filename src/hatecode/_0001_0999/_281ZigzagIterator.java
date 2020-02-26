package hatecode._0001_0999;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ZigzagIterator
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 281. Zigzag Iterator
 */
public class _281ZigzagIterator {
    /**
     *For example, given two 1d vectors:

     v1 = [1, 2]
     v2 = [3, 4, 5, 6]

     output : 1 3 2 4 5 6

     [1,2,3]
     [4,5,6,7]
     [8,9]
     It should return [1,4,8,2,5,9,3,6,7].
Follow up: What if you are given k 1d vectors? How well can your code be 
extended to such cases?

Clarification for the follow up question:
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. 
If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". 
For example:

Input:
[1,2,3]
[4,5,6,7]
[8,9]

Output: [1,4,8,2,5,9,3,6,7].
     time : O(n)
     space : O(1)

     * @param v1
     * @param v2
     */

    private Iterator<Integer> i, j, temp;


    public _281ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        i = v2.iterator();
        j = v1.iterator();
    }

    public int next() {
        if (j.hasNext()) {
            temp = j;
            j = i;
            i = temp;
        }
        return i.next();
    }

    public boolean hasNext() {
        return i.hasNext() || j.hasNext();
    }

    /**
     * v1 = [1, 2]
     v2 = [3, 4, 5, 6]

     time : O(n)
     space : O(2)
     */

    LinkedList<Iterator> list;

    public void ZigzagIterator2(List<Integer> v1, List<Integer> v2) {
        list = new LinkedList<>();
        if (!v1.isEmpty()) list.add(v1.iterator());
        if (!v2.isEmpty()) list.add(v2.iterator());
    }

    public int next2() {
        //remove the get the head
        Iterator poll = list.remove();
        int result = (Integer) poll.next();
        if (poll.hasNext()) {
            list.add(poll);
        }
        return result;
    }

    public boolean hasNext2() {
        return !list.isEmpty();
    }
}
