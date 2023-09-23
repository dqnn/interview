package hatecode._1000_1999;

import java.util.*;
public class _1094CarPooling {
    /*
     * 1094. Car Pooling You are driving a vehicle that has capacity empty seats
     * initially available for passengers. The vehicle only drives east (ie. it
     * cannot turn around and drive west.)
     * 
     * Given a list of trips, trip[i] = [num_passengers, start_location,
     * end_location] contains information about the i-th trip: the number of
     * passengers that must be picked up, and the locations to pick them up and drop
     * them off. The locations are given as the number of kilometers due east from
     * your vehicle's initial location.
     * 
     * Return true if and only if it is possible to pick up and drop off all
     * passengers for all the given trips.
     */
    /*
     * thinking process: O(n)/O(1)
     * 
     * the problem is to say: given 2D array,  cap means car capacity, driver is excluded 
     * a[0]: passenage count, a[1] start stop, a[2] off stop
     * return whether the car can go through call trips with all passenages 
     * [[2,1,5],[3,3,7]] 4 --- > true
     * 
     * 
     * 
     * we use treemap to store for each stop, how many passengaes on the car, when start stop, we put passenger delta to value of the map
     * 
     * then laster we can go through the stop one by one 
     */
    public boolean carPooling(int[][] trips, int capacity) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int[] t : trips) {
            map.put(t[1], map.getOrDefault(t[1], 0) + t[0]);
            map.put(t[2], map.getOrDefault(t[2], 0) - t[0]);
        }
        int count = 0;
        for(int s: map.keySet()) {
            count += map.get(s);
            //System.out.println(count);
            if(count > capacity) return false;
        }
        return true;
    }
}