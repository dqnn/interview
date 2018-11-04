package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

class NumberOfRecentCalls {
/*
933. Number of Recent Calls
Write a class RecentCounter to count recent requests.

It has only one method: ping(int t), where t represents some time 
in milliseconds.

Return the number of pings that have been made from 3000 milliseconds
 ago until now.

Any ping with time in [t - 3000, t] will count, including the current ping.

It is guaranteed that every call to ping uses a strictly larger value of t than before.

 

Example 1:

Input: inputs = ["RecentCounter","ping","ping","ping","ping"], inputs = [[],[1],[100],[3001],[3002]]
Output: [null,1,2,3,3]
 
Note:

Each test case will have at most 10000 calls to ping.
Each test case will call ping with strictly increasing values of t.
Each call to ping will have 1 <= t <= 10^9.
 
 */
    //TLE solution
    private TreeMap<Integer, Integer> map;
    public void RecentCounter2() {
        this.map = new TreeMap<>();
    }
    
    public int ping2(int t) {
        map.put(t, 1);
        return map.subMap(t - 3000, true, t, true).size();
    }
    
    //another TLE solution
    private List<Integer> list;
    public NumberOfRecentCalls() {
        this.list = new ArrayList<>();
    }
    public int ping3(int t) {
        list.add(t);
        int index = list.size() - 2;
        int res = 1;
        while(index >= 0) {
            if (list.get(index) >= (t - 3000)) {
                res++;
            }
            index--;
        }
        return res;
    }
    
    //accepted solution
    public int ping(int t) {
        list.add(t);
        int low = 0, high = list.size() - 1;
        int target =  t - 3000, mid = 0;
        while (low <= high) {
            mid  = low + (high - low) / 2;
            if (list.get(mid) == target) {
                return list.size() - 1 - mid + 1;
            } else if(list.get(mid) > target) {
                high = mid -1;
            } else {
                low = mid + 1;
            }
        }
        //here handle high + 1 == low and on one = target,
        //if only 0 element,then high will be -1, so add check
        if (high >=0 && list.get(high) >= target) {
            mid = high;
        } else {
            mid = low;
        }
        
        return list.size() - 1 - mid + 1;
    }
}
