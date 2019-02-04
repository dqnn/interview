package hatecode;
import java.util.*;
public class CarFleet {
/*
853. Car Fleet
N cars are going to the same destination along a one lane road.  The destination is target miles away.

Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
Output: 3
Explanation:
The cars starting at 10 and 8 become a fleet, meeting each other at 12.
The car starting at 0 doesn't catch up to any other car, so it is a fleet by itself.
The cars starting at 5 and 3 become a fleet, meeting each other at 6.
Note that no other cars meet these fleets before the destination, so the answer is 3.

*/
    
    //thinking process: 
    //given a destination as int target, position is car position and its corresponding speed, car will meet to form the fleet
    //but they cannot pass ahead of the leading car, so speed is decided by leading car, how many car fleets?
    
    //we should recongize if two cars one by one, so time to target is the same, they will be one fleet
    //according to this rule, we can sort the car by position and time, do the process
    
    //this is sort by position desc, int[]->[0]= index, [1]= position, each time, we add to queue and sort
    //we scan from nearest car, caculate the time and comapre to next car, if they are the same, then good, if not, then count++;
    public int carFleet3(int target, int[] position, int[] speed) {
        //sort the int[] by position desc
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return b[1] - a[1]; 
        });
        for (int i = 0; i < position.length; i++)  pq.offer(new int[]{ i, position[i] });
        double time = 0;
        int count = 0;
        while (!pq.isEmpty()) {
            int[] next = pq.poll();
            int index = next[0];
            int pos = next[1];
            int spd = speed[index];
            double needTime = (double)(target - pos)/spd;
            if (needTime > time) {
                count++;
                time = needTime;
            }
        }
        return count;
    }

    //this is mot simple solution, it use treeMap to sort the key while using -pos[i], which is brilliant 
    //the key is same as above
    public int carFleet(int target, int[] pos, int[] speed) {
        TreeMap<Integer, Double> m = new TreeMap<>();
        for (int i = 0; i < pos.length; ++i) m.put(-pos[i], (double)(target - pos[i]) / speed[i]);
        int res = 0; double cur = 0;
        for (double time : m.values()) {
            if (time > cur) {
                cur = time;
                res++;
            }
        }
        return res;
    }

    // text book solution, the same as previous, but it use cars[t-1] = cars[t] to
    // replace to save the time, not good ways
    public int carFleet2(int target, int[] position, int[] speed) {
        int N = position.length;
        Car[] cars = new Car[N];
        for (int i = 0; i < N; ++i)
            cars[i] = new Car(position[i], (double) (target - position[i]) / speed[i]);
        Arrays.sort(cars, (a, b) -> Integer.compare(a.position, b.position));

        int ans = 0, t = N;
        while (--t > 0) {
         // if cars[t] arrives sooner, it can't be caught
            if (cars[t].time < cars[t - 1].time) ans++; 
            else cars[t - 1] = cars[t]; // else, cars[t-1] arrives at same time as cars[t]
        }

        return ans + (t == 0 ? 1 : 0); // lone car is fleet (if it exists)
    }

    class Car {
        int    position;
        double time;

        Car(int p, double t) {
            position = p;
            time = t;
        }
    }
}