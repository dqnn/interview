package hatecode._0001_0999;

import java.util.List;
import java.util.Stack;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : FlattenNestedListIterator
 * Creator : duqiang
 * Date : Sep, 2017
 * Description : 341. Flatten Nested List Iterator
 */
public class _341FlattenNestedListIterator {
    /**
     * Given a nested list of integers, implement an iterator to flatten it.

     Each element is either an integer, or a list -- whose elements may also be integers or other lists.

     Example 1:
     Given the list [[1,1],2,[1,1]],

     By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

     Example 2:
     Given the list [1,[4,[6]]],

     By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].

     time : O(n)
     space : O(n)

     */
    
    /* public interface NestedInteger {
        *
        *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
        *     public boolean isInteger();
        *
        *     // @return the single integer that this NestedInteger holds, if it holds a single integer
        *     // Return null if this NestedInteger holds a nested list
        *     public Integer getInteger();
        *
        *     // @return the nested list that this NestedInteger holds, if it holds a nested list
        *     // Return null if this NestedInteger holds a single integer
        *     public List<NestedInteger> getList();
        * }
*/
    //we have to use Stack, because we need to make sure the elements order, 
    //[1,[1,1],2]->[1,1,1,2], when we get 2nd element out, we need to make sure we can put it back into 
    //queue/stack without changing their original order
    Stack<NestedInteger> stack;

    // so similiar question like this requires to understand the problem correctly, like this 
    // the nestedInteger
    public _341FlattenNestedListIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    //@Override
    public Integer next() {
        return stack.pop().getInteger();
    }

    //@Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            NestedInteger cur = stack.peek();
            if (cur.isInteger()) {
                return true;
            }
            stack.pop();
            for (int i = cur.getList().size() - 1; i >= 0; i--) {
                stack.push(cur.getList().get(i));
            }
        }
        return false;
    }

}
