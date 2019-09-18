package hatecode;

import java.util.*;
public class _1010PairsOfSongsWithTotalDurationsDivisibleBy60 {
/*
1010. Pairs of Songs With Total Durations Divisible by 60

In a list of songs, the i-th song has a duration of time[i] 
seconds. 

Return the number of pairs of songs for which their total 
duration in seconds is divisible by 60.  Formally, we want 
the number of indices i < j with (time[i] + time[j]) % 60 == 0.


Example 1:

Input: [30,20,150,100,40]
Output: 3
Explanation: Three pairs have a total duration divisible by 60:
(time[0] = 30, time[2] = 150): total duration 180
(time[1] = 20, time[3] = 100): total duration 120
(time[1] = 20, time[4] = 40): total duration 60
*/

    //thinking process: O(n)/O(n)
    //given an integer array time, each integer means a song time,
    //return the pairs of songs which their time during is divided by 60
    public int numPairsDivisibleBy60(int[] time) {
        if (time == null || time.length < 1) return 0;
        
        int res = 0;
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int temp : time) {
            int left = temp % 60;
            if (map.containsKey(60 - left)) {
                res += map.get(60 - left).size();
            } else if (left == 0 && map.containsKey(0)) {
                res += map.get(0).size();
            }
            map.computeIfAbsent(left, v->new ArrayList<>()).add(temp);
        }
        
        return res;
    }
    public int numPairsDivisibleBy60_TLE(int[] time) {
        if (time == null || time.length < 1) return 0;
        
        int res = 0;
        int n = time.length;
        for(int i = 0; i< n;i++) {
            for(int j = i+1; j < n;j++) {
                if ((time[i] + time[j]) % 60 == 0) res++;
            }
        }
        return res;
    }
}