package leetcode;

import java.util.*;

public class FrogJump {
/*
403. Frog Jump
A frog is crossing a river. The river is divided into x units and at each unit 
there may or may not exist a stone. The frog can jump on a stone, but it must not 
jump into the water.

Given a list of stones' positions (in units) in sorted ascending order, determine
 if the frog is able to cross the river by landing on the last stone. Initially, 
 the frog is on the first stone and assume the first jump must be 1 unit.

If the frog's last jump was k units, then its next jump must be either k - 1, k, 
or k + 1 units. Note that the frog can only jump in the forward direction.

Note:

The number of stones is ≥ 2 and is < 1,100.
Each stone's position will be a non-negative integer < 231.
The first stone's position is always 0.
Example 1:

[0,1,3,5,6,8,12,17]

There are a total of 8 stones.
The first stone at the 0th unit, second stone at the 1st unit,
third stone at the 3rd unit, and so on...
The last stone at the 17th unit.

Return true. 
 */
    public class Node{
        int index;
        int preJump;
        public Node(int index, int preJump) {
            this.index = index;
            this.preJump = preJump;
        }
        //for debug purpose
        public String toString(){
            return index +"-" + preJump;
        }
    }
    public boolean canCross(int[] s) {
        if (s == null || s.length < 2 || s[1] > 1) {
            return false;
        }
        Queue<Node> q = new PriorityQueue<>((a,b)->(b.index - a.index));
        Set<String> visited = new HashSet<>();
        Node first = new Node(1,1);
        visited.add(first.index +"#" + first.preJump);
        q.offer(first);
        while(!q.isEmpty()){
            Node node = q.poll();
            int i = node.index;
            int jump = node.preJump;
            int[] dis = {s[i] + jump-1, s[i] + jump, s[i] + jump + 1};
            for(int d : dis) {
                if (d == s[s.length -1]) return true;
            }
            int j =i + 1;
            while(j < s.length && s[j] <= dis[2]) j++;
            for(int k = i+1; k < j;k++) {
                if (dis[0] == s[k]) {
                    Node node1 = new Node(k, jump -1);
                    if (!visited.contains(k +"#" + (jump -1))) {
                        visited.add(k +"#" + (jump -1));
                        q.offer(node1);
                    }
                    
                } else if (dis[1] == s[k]) {
                    Node node1 = new Node(k, jump);
                    if (!visited.contains(k +"#" + jump)) {
                        visited.add(k +"#" + jump);
                        q.offer(node1);
                    }
                } else if (dis[2] == s[k]) {
                    Node node1 = new Node(k, jump +1);
                    if (!visited.contains(k +"#" + (jump +1))) {
                        visited.add(k +"#" + (jump+1));
                        q.offer(node1);
                    }
                }
            }
        }
        return false;
    }
    //another good solution, so we setup a map s[1]=[3,4,5], this means on s[1]
    //we can jump 3, 4 or 5 moves, so as we visit the array, we have several options
    //and keep updating the map, as we scan stone, we just need to check current
    //for current stone, whether i reached the last one or how many options have, and update
    //each of them, if we cannot find in the map which means fall into water
    //O(n)/O(n)
    public boolean canCross2(int[] stones) {
        if (stones.length == 0) {
            return true;
        }
        //[0,1,3,5,6,8,12,17] for examples, stores each value, its next possible moves
        //{17=[], 0=[1], 1=[1, 2], 3=[1, 2, 3], 5=[1, 2, 3], 
        //6=[1, 2, 3, 4], 8=[1, 2, 3, 4], 12=[3, 4, 5]}
        HashMap<Integer, Set<Integer>> map = new HashMap<>(stones.length);
        map.computeIfAbsent(0, v->new HashSet<>()).add(1);
        Arrays.stream(stones).forEach(e->map.computeIfAbsent(e, v->new HashSet<>()));
        
        for (int stone : stones) {
            for (int step : map.get(stone)) {
                int reach = step + stone;
                if (reach == stones[stones.length - 1]) {
                    return true;
                }
                if (map.containsKey(reach)) {
                    map.get(reach).add(step);
                    map.get(reach).add(step + 1);
                    if (step > 1) map.get(reach).add(step - 1);
                }
            }
        }
        return false;
    } 
}