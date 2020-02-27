package hatecode._0001_0999;
import java.util.*;
public class _683KEmptySlots {
/*
683. K Empty Slots
There is a garden with N slots. In each slot, there is a flower. 
The N flowers will bloom one by one in N days. In each day, there will be exactly 
one flower blooming and it will be in the status of blooming since then.

Given an array flowers consists of number from 1 to N. Each number in the array 
represents the place where the flower will open in that day.

For example, flowers[i] = x means that the unique flower that blooms at day 
i will be at position x, where i and x will be in the range from 1 to N.

Also given an integer k, you need to output in which day there exists two 
flowers in the status of blooming, and also the number of flowers between them is 
k and these flowers are not blooming.

If there isn't such day, output -1.

Example 1:
Input: 
flowers: [1,3,2]
k: 1
Output: 2
*/
    //thinking process: given array flowers, flower[i] = x means flower will boom on
    //day i on position x, so find two flowers which their distance is K and 
    // two are booming, flowers bwteen them are not
    // 1 2 3 4 5
/*
use an array days[] to record each position's flower's blooming day. 
That means days[i] is the blooming day of the flower in position i+1. 
We just need to find a subarray days[left, left+1,..., left+k-1, right] 
which satisfies: for any i = left+1,..., left+k-1, we can have days[left] < days[i] 
&& days[right] < days[i]. Then, the result is max(days[left], days[right]).
 */
    //O(n)/O(n)
    public int kEmptySlots(int[] flowers, int k) {
        int[] days = new int[flowers.length];
        for (int i = 0; i < days.length; i++) {
            days[flowers[i] - 1] = i + 1;
        }
        int left = 0;
        int right = k + 1;
        int res = Integer.MAX_VALUE;
        //two pointers, i is from 0->right, if flower boom time bigger than left and right, 
        //then
        for (int i = 0; right < days.length; i++) {
            // current days[i] is valid, continue scanning, means at i postion the flower 
            //boom late than right and left
            if (days[i] > days[left] && days[i] > days[right]) {
                continue;
            }
           // reach boundary of sliding window, since previous number are all valid, 
            //record result  
            if (i == right) {
                res = Math.min(res, Math.max(days[left],days[right]));
            }
            // not valid, move the sliding window to next possible location
            left = i;
            right = left + k + 1;
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    //bucket to store min and max, still not understnd how to calc the bucket numbers and index
    public static int kEmptySlots3(int[] flowers, int k) {
        int n = flowers.length;
        if (n == 0 || k >= n) return -1;
        ++k;
        int bs = (n + k - 1) / k;
        int[] lows = new int[bs];
        int[] highs = new int[bs];
        Arrays.fill(lows, Integer.MAX_VALUE);
        Arrays.fill(highs, Integer.MIN_VALUE);
       for (int i = 0; i < n; ++i) {
           int x = flowers[i];
           int p = x / k;
           if (x < lows[p]) {
               lows[p] = x;
               if (p > 0 && highs[p - 1] == x - k) return i + 1;
           } 
           if (x > highs[p]) {
               highs[p] = x;
               if (p < bs - 1 && lows[p + 1] == x + k) return i + 1;
           }            
       }
       
       return -1;
    }
    //O(n)/O(n), this introduce minQueue data structure
    /*
     * Algorithm
     * 
     * Let days[x] = i be the time that the flower at position x blooms. For each
     * window of k days, let's query the minimum of this window in (amortized)
     * constant time using a MinQueue, a data structure built just for this task. If
     * this minimum is larger than it's two neighbors, then we know this is a place
     * where "k empty slots" occurs, and we record this candidate answer.
     * 
     * To operate a MinQueue, the key invariant is that mins will be an increasing
     * list of candidate answers to the query MinQueue.min.
     * 
     * For example, if our queue is [1, 3, 6, 2, 4, 8], then mins will be [1, 2, 4,
     * 8]. As we MinQueue.popleft, mins will become [2, 4, 8], then after 3 more
     * popleft's will become [4, 8], then after 1 more popleft will become [8].
     * 
     * As we MinQueue.append, we should maintain this invariant. We do it by popping
     * any elements larger than the one we are inserting. For example, if we
     * appended 5 to [1, 3, 6, 2, 4, 8], then mins which was [1, 2, 4, 8] becomes
     * [1, 2, 4, 5].
     * 
     * Note that we used a simpler variant of MinQueue that requires every inserted
     * element to be unique to ensure correctness. Also, the operations are
     * amortized constant time because every element will be inserted and removed
     * exactly once from each queue.
     */
    class Solution {
        public int kEmptySlots(int[] flowers, int k) {
            int[] days = new int[flowers.length];
            for (int i = 0; i < flowers.length; i++) {
                days[flowers[i] - 1] = i + 1;
            }

            MinQueue<Integer> window = new MinQueue();
            int ans = days.length;

            for (int i = 0; i < days.length; i++) {
                int day = days[i];
                window.addLast(day);
                if (k <= i && i < days.length - 1) {
                    window.pollFirst();
                    if (k == 0 || days[i-k] < window.min() && days[i+1] < window.min()) {
                        ans = Math.min(ans, Math.max(days[i-k], days[i+1]));
                    }
                }
            }

            return ans < days.length ? ans : -1;
        }
    }

    class MinQueue<E extends Comparable<E>> extends ArrayDeque<E> {
        Deque<E> mins;

        public MinQueue() {
            mins = new ArrayDeque<E>();
        }

        @Override
        public void addLast(E x) {
            super.addLast(x);
            while (mins.peekLast() != null &&
                    x.compareTo(mins.peekLast()) < 0) {
                mins.pollLast();
            }
            mins.addLast(x);
        }

        @Override
        public E pollFirst() {
            E x = super.pollFirst();
            if (x == mins.peekFirst()) mins.pollFirst();
            return x;
        }

        public E min() {
            return mins.peekFirst();
        }
    }
    
    
    
    //O(n^2)//O(n)
    public int kEmptySlots2(int[] f, int k) {
        if (f == null || f.length < 1 || k < 0) return -1;
        //to memo which position folowers are booming
        int[] status = new int[f.length + 1];
        Map<Integer, Integer> map = new HashMap<>();
        //{1=0, look for -1,3}, {2=1, look for 0 or 4}, {3}
        for(int i = 0; i< f.length; i++) {
          //found boundary pair
            if (map.containsKey(f[i] - k - 1) || map.containsKey(f[i] + k + 1)) {
                
                //  1 2 3 4 5
                int start = 0, end = 0;
                if (map.containsKey(f[i] - k - 1)) {
                    start = f[i] - k;
                    end = f[i] - 1;
                } else {
                    start = f[i] + 1;
                    end = f[i] + k;
                }
                int p = start;
                for(; p<= end;p++) {
                    if (status[p] == 1) break;
                }
                if (p == (end + 1)) return i + 1;
            } else {
                map.put(f[i], i);
            }
            status[f[i]] = 1;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] in = {1,2,3,4};
        System.out.println(kEmptySlots3(in, 1));
    }
}