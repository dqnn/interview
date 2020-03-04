package hatecode._0001_0999;
public class _657RobotReturnToOrigin {
/*
657. Robot Return to Origin
There is a robot starting at position (0, 0), the origin, on a 2D plane. Given a sequence of its moves, judge if this robot ends up at (0, 0) after it completes its moves.

The move sequence is represented by a string, and the character moves[i] represents its ith move. Valid moves are R (right), L (left), U (up), and D (down). If the robot returns to the origin after it finishes all of its moves, return true. Otherwise, return false.

Note: The way that the robot is "facing" is irrelevant. "R" will always make the robot move to the right once, "L" will always make it move left, etc. Also, assume that the magnitude of the robot's movement is the same for each move.

Example 1:

Input: "UD"
Output: true 
*/
    public boolean judgeCircle(String moves) {
        if (moves == null || moves.length() < 1) return true;
        int[] p = new int[2];
        for(char c : moves.toCharArray()) {
            if (c == 'U') p[1] += 1;
            else if (c == 'D')  p[1] -=1;
            else if (c == 'L')  p[0] -=1;
            else  p[0] +=1;
        }
        
        return p[0] == 0 && p[1] ==0;
    }
}