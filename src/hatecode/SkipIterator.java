package hatecode;

import java.util.*;

class SkipIterator {
/*
 * Goole interview question, not from LC
 * problem statement:
 * implement SkipIterator class and 4 methods
 * class SkipIterator {
    public SkipIterator(Iterator itr);
    boolean hasNext();
    Integer next();
    void skip(int num);
}
传入的迭代器让我看作是在一个List<Integer>上面的迭代器，
主要说下skip函数，输入参数是个int，表示下一个数字等于num的需要被跳过，
就是当作这个list中下一个num不存在，skip可能被多次调用,skip(5),skip(5)表示后面的两个5都不要了。

 */
    //thinking process:
    //need to implement a new method findNext()
    private Iterator<Integer>     itr;
    private boolean               hasNext;
    private Integer               nextElement;
    private Map<Integer, Integer> map = new HashMap<>();

    public SkipIterator(Iterator<Integer> itr) {
        this.itr = itr;
        findNext();
    }

    public boolean hasNext() {
        return hasNext;
    }

    public Integer next() {
        if (!hasNext)
            return null;
        Integer tmp = nextElement;
        findNext();
        return tmp;
    }

    public void skip(int num) {
        if (hasNext) {
            if (nextElement == num) {
                findNext();
            } else {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }
    }
    //this is the key to find next qualified element
    private void findNext() {
        hasNext = false;
        nextElement = null;
        while (itr.hasNext()) {
            Integer e = itr.next();
            if (map.containsKey(e)) {
                map.put(e, map.get(e) - 1);
                if (map.get(e) == 0)
                    map.remove(e);
            } else {
                hasNext = true;
                nextElement = e;
                return;
            }
        }
    }
}
