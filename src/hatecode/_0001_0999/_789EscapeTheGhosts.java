package hatecode._0001_0999;
public class _789EscapeTheGhosts {
/*
789. Escape The Ghosts
You are playing a simplified Pacman game. You start at the point (0, 0), and your destination is (target[0], target[1]). There are several ghosts on the map, the i-th ghost starts at (ghosts[i][0], ghosts[i][1]).

Each turn, you and all ghosts simultaneously *may* move in one of 4 cardinal directions: north, east, west, or south, going from the previous point to a new point 1 unit of distance away.

You escape if and only if you can reach the target before any ghost reaches you (for any given moves the ghosts may take.)  If you reach any square (including the target) at the same time as a ghost, it doesn't count as an escape.

Return True if and only if it is possible to escape.

Example 1:
Input: 
ghosts = [[1, 0], [0, 3]]
target = [0, 1]
Output: true
*/
    //thinking process:
    //given a board with ghosts coordination as array, and target coordination, we start from top left
    //so return if true we can escape which means ghosts could not catch us and we can rearch target first
    
    //this problems first sounds like BSF, we can have two sets in every loop, and we remove the cell which 
    //we and ghosts are in same cell, if we can reach the target then we are winning else lose.
    
    //but that was incorrect because the key critireal is who can reach the target who will win. 
    // so we just need to compete who can reach there by fastest speed so the coordination is key,
    //i was wonerding first whether incepting is better solution but no because that's not critirial of success
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        if (ghosts == null || ghosts.length < 1) return true;
        int distance = taxiCabDistance(new int[]{0, 0}, target);
        for(int[] g : ghosts) {
            if (taxiCabDistance(g, target) <= distance) return false;
        }
        return true;
    }
    
    private int taxiCabDistance(int[] c1, int[] c2) {
        return Math.abs(c1[0] - c2[0]) + Math.abs(c1[1]- c2[1]);
    }
}