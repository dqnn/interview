package hatecode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class MinimizeMaxDistanceToGasStation {
    /*
774.
Minimize Max Distance to Gas Station
On a horizontal number line, we have gas stations at positions 
stations[0], stations[1], ..., stations[N-1], where N = stations.length.

Now, we add K more gas stations so that D, the maximum distance between 
adjacent gas stations, is minimized.

Return the smallest possible value of D.

Example:

Input: stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], K = 9
Output: 0.500000

Note:

stations.length will be an integer in range [10, 2000].
stations[i] will be an integer in range [0, 10^8].
K will be an integer in range [1, 10^6].
Answers within 10^-6 of the true value will be accepted as correct.

     */
    class Interval {
        int start;
        int end;
        int numberInsertion;
        double distance() {
            // the distance we use numberInsertion + 1 since 
            return 1.0 * ( (end - start) / (double)(numberInsertion + 1));
        }
        Interval(int start, int end, int numInsertions) {
            this.start = start;
            this.end = end;
            this.numberInsertion = numInsertions;
        }
    }
    
//thinking process: interview friendly
    
    //the problem is to say we have stations in S, each number represents the gas station 
    //coordination on x axis, so if we want to add K stations in this set, and we want minimized
    //distance between two adjacent gas stations, return the distance. 
    
    //so this problem is to say we want the max distance between two adjacent gas stations with 
    // new K stations, so thinking about the TextJustification, how can we model the problem
    //into data structure
    
    //we the max distance between two adjacent nodes, so we want to minimized the distance
    // so if we can put K into these slots to make every distance is the same that will be 
    // the answer if not we will get a longer distance
    
    //so if we cannot, it does not matter where we put the rest nodes, so we can use a 
    //PriorityQUeue to store all nodes distance. last we just return the longest one
    public double minmaxGasDist(int[] s, int K) {
        if (s == null || s.length < 1) return 0.0;
        Arrays.sort(s);
        double len = s[s.length - 1] - s[0];
        // we need to use comparator instead of Lambda because distance is a function
        PriorityQueue<Interval> q = new PriorityQueue<>(new Comparator<Interval>(){
            public int compare(Interval a, Interval b) {
                double diff = a.distance() - b.distance();
                if (diff > 0) {
                    return -1;
                } else if (diff < 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        int remaining = K;
        for(int i = 0; i < s.length - 1; i++) {
            int num = (int) (K * ((double)(s[i + 1] - s[i]) / len));
            q.add(new Interval(s[i], s[i + 1], num));
            remaining -= num;
        }
        while (remaining > 0) {
            Interval interval = q.poll();
            interval.numberInsertion ++;
            q.add(interval);
            remaining --;
        }
        return q.poll().distance();
    }
    //binary search, this is log(n) 
    public double minmaxGasDist2(int[] st, int K) {
        int count, N = st.length;
        double left = 0, right = st[N - 1] - st[0], mid;

        while (left +1e-6 < right) {
            mid = (left + right) / 2;
            count = 0;
            for (int i = 0; i < N - 1; ++i)
                count += Math.ceil((st[i + 1] - st[i]) / mid) - 1;
            if (count > K) left = mid;
            else right = mid;
        }
        return right;
    }
}