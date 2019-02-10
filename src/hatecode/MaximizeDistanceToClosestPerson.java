package hatecode;
import java.util.*;
public class MaximizeDistanceToClosestPerson {
/*
849. Maximize Distance to Closest Person
In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty. 

There is at least one empty seat, and at least one person sitting.

Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized. 

Return that maximum distance to closest person.

Example 1:

Input: [1,0,0,0,1,0,1]
Output: 2
Explanation: 
If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
If Alex sits in any other open seat, the closest person has distance 1.
Thus, the maximum distance to the closest person is 2.
*/
    //O(n)/O(1)
    //thinking process: given an array with 0 or 1, 1 stands occupied, return the distance which max the distance to closest person
    
    //two pointers, the length of window is distance, so /2 is the answer.
    //if we did not find the window, right++, if we find the window, left = right, and right continue searching for next
    //here need to take care first and last element, special case 
    public int maxDistToClosest(int[] seats) {
        if (seats == null || seats.length < 1) {
            return 0;
        }
        
        int left = -1, dist = 0;
        if (seats[0] == 1) left = 0;
        for(int right = 1; right< seats.length;right++) {
            if (seats[right] == 1) {
                if (left == -1) {
                    dist = Math.max(dist, right);
                } else dist = Math.max(dist, (right - left)/2);
                left = right;
            }
        }
        //process the last element, because if we don't handle last one ,like 1,0,0,0,0, then answer is not correct, and we do not need to /2
        if (seats[seats.length - 1] == 0) dist = Math.max(dist, seats.length - 1 - left);
        return dist;
    }
    
    //here is using two pass, and two arrays, left and right arrays to record 
    //for position i, what's the past looks like, we use N as default value, and if 
    //it is 1 then we mark 0, if not, we use left[i] = left[i-1] + 1, so if there is 1, left[i]
    //will record its distance
    //for right, the same, 
    
    //and when we compare, we only compare seats[i] = 0, then we can know, this position, to left 1
    //the distance, to right, the distance
    public int maxDistToClosest2(int[] seats) {
        int N = seats.length;
        int[] left = new int[N], right = new int[N];
        Arrays.fill(left, N);
        Arrays.fill(right, N);

        for (int i = 0; i < N; ++i) {
            if (seats[i] == 1) left[i] = 0;
            else if (i > 0) left[i] = left[i-1] + 1;
        }

        for (int i = N-1; i >= 0; --i) {
            if (seats[i] == 1) right[i] = 0;
            else if (i < N-1) right[i] = right[i+1] + 1;
        }

        int ans = 0;
        for (int i = 0; i < N; ++i)
            if (seats[i] == 0)
                ans = Math.max(ans, Math.min(left[i], right[i]));
        return ans;
    }
    //this is interesting solution, we count 0 group by group 
    //and then ans will record
    public int maxDistToClosest3(int[] seats) {
        int N = seats.length;
        int zeroCount = 0; //current longest group of empty seats
        int res = 0;

        for (int i = 0; i < N; ++i) {
            if (seats[i] == 1) {
                zeroCount = 0;
            } else {
                zeroCount++;
                res = Math.max(res, (zeroCount + 1) / 2);
            }
        }
        //only need to find 1 case
        for (int i = 0; i < N; ++i)  if (seats[i] == 1) {
            res = Math.max(res, i);
            break;
        }
        //only need to find 1 case
        for (int i = N-1; i >= 0; --i)  if (seats[i] == 1) {
            res = Math.max(res, N - 1 - i);
            break;
        }

        return res;
    }
    
}