package hatecode._1000_1999;
/*
1184. Distance Between Bus Stops
A bus has n stops numbered from 0 to n - 1 that form a circle. We know the distance between all pairs of neighboring stops where distance[i] is the distance between the stops number i and (i + 1) % n.

The bus goes along both directions i.e. clockwise and counterclockwise.

Return the shortest distance between the given start and destination stops.
*/
public class _1184DistanceBetweenBusStops {
    
    //O(n)/O(1)
    //thinking process: just need to figure out the sub array
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        if (distance == null || distance.length < 1) return -1;
        int sum = 0,  partialSum = 0;
        int s = Math.min(start, destination);
        int e = Math.max(start, destination);
        for(int i = 0; i< distance.length; i++) {
            if (i < e && i >= s) {
                partialSum += distance[i];
            }
            sum += distance[i];
        }
       
        return Math.min(sum - partialSum, partialSum);
    }
}