package hatecode._0001_0999;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MovingAveragefromDataStream
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : 346. Moving Average from Data Stream
 */
public class _346MovingAveragefromDataStream {
    /**
     * Given a stream of integers and a window size, calculate the moving average of all integers '
     * in the sliding window.

For example,
     * MovingAverage m = new MovingAverage(3);
     m.next(1) = 1
     m.next(10) = (1 + 10) / 2
     m.next(3) = (1 + 10 + 3) / 3
     m.next(5) = (10 + 3 + 5) / 3

     time : O(n)
     space : O(n)

     */

    private Queue<Integer> queue;
    private double sum = 0;
    private int size;

    public _346MovingAveragefromDataStream(int size) {
        queue = new LinkedList<>();
        this.size = size;
    }
    // next here means if we add val in to our consideratoon, how can we 
    // calc the avg but keeping size the same
    public double next(int val) {
        if (queue.size() == size) {
            sum -= queue.remove();
        }
        sum += val;
        queue.offer(val);
        return sum / queue.size();
    }
}
