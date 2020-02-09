package hatecode._0001_0999;

import java.util.Iterator;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : PeekingIterator
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 284. Peeking Iterator
 * TODO: 
 * generic how to implement? 
 */
public class PeekingIterator {
    /**
     Given an Iterator class interface with methods: next() and hasNext(), design and implement 
     a PeekingIterator that support the peek() operation -- 
     it essentially peek() at the element that will be returned by the next call to next().

Example:

Assume that the iterator is initialized to the beginning of the list: [1,2,3].

Call next() gets you 1, the first element in the list.
Now you call peek() and it returns 2, the next element. Calling next() after that still return 2. 
You call next() the final time and it returns 3, the last element. 
Calling hasNext() after that should return false.
Follow up: How would you extend your design to be generic and work with all types, not just integer?

     * @param iterator
     */

    private Iterator<Integer> iterator;
    private Integer next = null;

    public PeekingIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
        if (iterator.hasNext()) {
            next = iterator.next();
        }
    }

 // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return next;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    public Integer next() {
        Integer res = next;
        // we cannot use iterator.next() since it will throw exception if no more elements, they need to use together
        next = iterator.hasNext() ? iterator.next() : null; 
        return res;
    }

    
    // we are one step slow than original iterator bcause we want this Iterator peek works as we want. but still 
    // keeping next and hasNext interface. 
    public boolean hasNext() {
        return next != null;
    }
}
