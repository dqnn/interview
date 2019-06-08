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
    
    //so this problem is to say we want the min the max distance between two adjacent gas stations with 
    // new K stations, so thinking about the TextJustification, how can we model the problem
    //into data structure
    
    //we the max distance between two adjacent nodes, so we want to minimized the distance
    // the idea is to we use every interval (st[i+1] - st[i])/len, len is whole length, so 
    //we proportionally add K * (st[i+1] - st[i])/len into this interval to shorter the distance between
    //adjacent nodes.
    
    //after first round, we need to do some small modification, for example if we have more stations need to 
    //use, we repeat same thing, that's why i say it is same like TextJustification
    
    //TODO: proof:
    
    //
    
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
            //unit: 
            double unit = ((double)(s[i + 1] - s[i]) / len);
            int num = (int) (K * unit);
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
    //binary search, this is log(nlgM)/O(1), M = st[n-1] - st[0]
    //--> already sorted, log(lgn)/O(1)
    
    //thinking process: 
    //so first we need to make sure all stations sorted, because input may not sorted. 
    //thinking about from this perspective, the first status and last status, 
    //first we have N stations and sorted on a line, last we have put K more stations into among N stations 
    //the max distance between adjacent ones are minimal, so each adjacent must placed same equal distance 
    //to others, if not, we can make it shorter!!!!
    
    //so starting from 0 to max distance between nodes, which is len = st[N - 1] - st[0]
    //we want to find the min of max distance between any 2 adjacent nodes, so 
    // 
    
    //count: the number of gas station we need to make it possible.
    //l, r:  the distance between the first and the last station
    public double minmaxGasDist2(int[] st, int K) {
        Arrays.sort(st);
        int count, N = st.length;
        double l = 0, r = st[N - 1] - st[0];
        // it means the answer within 10^-6 of the true value and it will be accepted.
        while (l + 1e-6 < r) {
            double mid = l + (r - l) / 2;
            count = 0;
            for (int i = 0; i < N - 1; i++)
                count += Math.ceil((st[i + 1] - st[i]) / mid) - 1;
            //if count > K, means mid is too small to realize using only K more stations.
            if (count > K) l = mid;
            //count <= K, it means mid is possible and we can continue to find a bigger one.
            else r = mid;
        }
        return r;
    }
    /* this is only for interviewing brute force solution
     * Intuition
     * 
     * Let dp[n][k] be the answer for adding k more gas stations to the first n
     * intervals of stations. We can develop a recurrence expressing dp[n][k] in
     * terms of dp[x][y] with smaller (x, y).
     * 
     * Algorithm
     * 
     * Say the ith interval is deltas[i] = stations[i+1] - stations[i]. We want to
     * find dp[n+1][k] as a recursion. We can put x gas stations in the n+1th
     * interval for a best distance of deltas[n+1] / (x+1), then the rest of the
     * intervals can be solved with an answer of dp[n][k-x]. The answer is the
     * minimum of these over all x.
     */
    //O(nK^2)/O(NK)
    public double minmaxGasDist_DP(int[] stations, int K) {
        int N = stations.length;
        double[] deltas = new double[N-1];
        for (int i = 0; i < N-1; ++i) deltas[i] = stations[i+1] - stations[i];

        double[][] dp = new double[N-1][K+1];
        //dp[i][j] = answer for deltas[:i+1] when adding j gas stations
        for (int i = 0; i <= K; ++i)
            dp[0][i] = deltas[0] / (i+1);

        for (int p = 1; p < N-1; ++p)
            for (int k = 0; k <= K; ++k) {
                double bns = 999999999;
                for (int x = 0; x <= k; ++x)
                    bns = Math.min(bns, Math.max(deltas[p] / (x+1), dp[p-1][k-x]));
                dp[p][k] = bns;
            }

        return dp[N-2][K];
    }
}