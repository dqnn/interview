package hatecode._0001_0999;

import java.util.*;
public class KeysAndRooms {
/*
841. Keys and Rooms
There are N rooms and you start in room 0.  Each room has a distinct number in 0, 1, 2, ..., N-1, and each room may have some keys to access the next room. 

Formally, each room i has a list of keys rooms[i], and each key rooms[i][j] is an integer in [0, 1, ..., N-1] where N = rooms.length.  A key rooms[i][j] = v opens the room with number v.

Initially, all the rooms start locked (except for room 0). 

You can walk back and forth between rooms freely.

Return true if and only if you can enter every room.

Example 1:

Input: [[1],[2],[3],[]]
Output: true
*/
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if (rooms == null || rooms.size() < 1) return false;
        
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        visited.add(0);
        while(!q.isEmpty()) {
            int room = q.poll();
            for(int n : rooms.get(room)) {
                if (visited.contains(n)) continue;
                visited.add(n);
                q.offer(n);
            }
            if (visited.size() == rooms.size()) return true;
        }
        return visited.size() == rooms.size();
    }
}