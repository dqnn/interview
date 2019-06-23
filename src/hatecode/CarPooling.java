package hatecode;

import java.util.*;
public class CarPooling {
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
    //thinking process:
    
    //thinking a stop as a node, we just need to go through all nodes
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