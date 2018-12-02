package leetcode;
import java.util.*;
public class BusRoutes {
/*
815. Bus Routes
We have a list of bus routes. Each routes[i] is a bus route that the i-th bus 
repeats forever. For example if routes[0] = [1, 5, 7], this means that the first 
bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.

We start at bus stop S (initially not on a bus), and we want to go to bus stop T. 
Travelling by buses only, what is the least number of buses we must take to reach 
our destination? Return -1 if it is not possible.

Example:
Input: 
routes = [[1, 2, 7], [3, 6, 7]]
S = 1
T = 6
Output: 2
 */
    static class Node {
        int stop;
        int line;
        int bus;
        public Node(int stop, int line, int bus) {
            this.stop = stop;
            this.line = line;
            this.bus = bus;
        }
        public int hashCode() {
            return stop * 31;
        }
        public boolean equals(Node o) {
            return o!=null && o.hashCode() == hashCode() && 
                o.stop == stop;
        }
        public String toString() {
            return stop +"-" + line + "-" + bus;
        }
        
    }
    
    //TLE, 
    public static int numBusesToDestination(int[][] r, int S, int T) {
        if (r == null || r.length < 1 || r[0].length < 1) {
            return -1;
        }
        Map<Integer, List<Node>> map = new HashMap<>();
        int line = 0;
        for(int[] stops : r){
            map.computeIfAbsent(stops[stops.length -1], v->new ArrayList<>()).add(new Node(stops[0], line, 0));
            for(int i = 0; i< stops.length -1;i++) {
                map.computeIfAbsent(stops[i], v->new ArrayList<>()).add(new Node(stops[i+1], line,0));
            }
            line++;
        }
        Queue<Node> q =new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.offer(new Node(S, -1, 0));
        while(!q.isEmpty()) {
            Node node = q.poll();
            int stop = node.stop;
            if (stop == T) return node.bus;
            if (visited.contains(stop)) continue;
            visited.add(stop);
            List<Node> nextStops = map.get(stop);
            for(int i =0; i< nextStops.size();i++) {
                Node next = nextStops.get(i);
                q.offer(new Node(next.stop, next.line, next.line == node.line ? node.bus : node.bus + 1));
            }
        }
        return -1;
    }
    //this is faster, the reason why it faster it is because we use stop->{route1,route2..}
    //since we want least number,so we do not care how many stops in one route, so 
    //in queue while loop, we add all bus stop, so next time visit, they should not be in sameline 
    //since we add all same line stop in visited that's why it is faster
    public int numBusesToDestination2(int[][] r, int S, int T) {
        if (r == null || r.length < 1 || r[0].length < 1) {
            return -1;
        }
        //stop-<busline1, busline2>
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int i = 0; i< r.length;i++){
            for(int j = 0; j< r[i].length;j++) {
                map.computeIfAbsent(r[i][j], v->new HashSet<>()).add(i);
            }
        }
        //System.out.println(map);
        Queue<Node> q =new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.offer(new Node(S, -1, 0));
        while(!q.isEmpty()) {
            Node node = q.poll();
            int stop = node.stop;
            if (stop == T) return node.bus;
            for(int nextRoute : map.get(stop)) {
                for(int nextStop : r[nextRoute]) {
                    if (visited.contains(nextStop)) continue;
                    visited.add(nextStop);
                    q.offer(new Node(nextStop, 0, node.bus + 1));
            }
        }
    }
    return -1;
}
    
    public static void main(String[] args) {
        int[][] in = {{7,12},{4,5,15},{6},{15,19},{9,12,13}};
        System.out.println(numBusesToDestination(in, 15,12));
    }
}