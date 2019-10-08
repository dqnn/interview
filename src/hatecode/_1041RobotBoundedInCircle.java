package hatecode;
public class _1041RobotBoundedInCircle {
/*
1041. Robot Bounded In Circle
On an infinite plane, a robot initially stands at (0, 0) and faces north.  The robot can receive one of three instructions:

"G": go straight 1 unit;
"L": turn 90 degrees to the left;
"R": turn 90 degress to the right.
The robot performs the instructions given in order, and repeats them forever.

Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.

Example 1:

Input: "GGLLGG"
Output: true
Explanation: 
The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.
*/
    
    //thinking process:O(n)/O(1)
    //given a dir string, G means direct, L->left, R->right, so 
    //we start in a infinite board, we walk as ins, can we have a circle?
    
    //we start from (i, j)->(x, y), so just need to check whether 
    //x ==0 && y == 0 || i > 0, i > 0 because 
    public boolean isRobotBounded(String ins) {
        int x = 0, y = 0, i = 0;
        //U R D L
        int[][] dir = {{0, 1}, {1, 0}, {0, -1},{-1, 0}};
        for (int j = 0; j < ins.length(); ++j)
            // (i + 1) % 4 means 
            if (ins.charAt(j) == 'R') i = (i + 1) % 4;
            else if (ins.charAt(j) == 'L') i = (i + 3) % 4;
            else x += dir[i][0]; y += dir[i][1];
            
        return x == 0 && y == 0 || i > 0;
    }
}