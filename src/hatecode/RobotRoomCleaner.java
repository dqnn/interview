package hatecode;

import java.util.*;

class RobotRoomCleaner {
    /*
     * 489. Robot Room Cleaner Given a robot cleaner in a room modeled as a grid.
     * 
     * Each cell in the grid can be empty or blocked.
     * 
     * The robot cleaner with 4 given APIs can move forward, turn left or turn
     * right. Each turn it made is 90 degrees.
     * 
     * When it tries to move into a blocked cell, its bumper sensor detects the
     * obstacle and it stays on the current cell.
     * 
     * Design an algorithm to clean the entire room using only the 4 given APIs
     * shown below.
     */
    interface Robot {
        public boolean move();

        public void clean();

        public void turnLeft();

        public void turnRight();
    }

    public void cleanRoom2(Robot robot) {
        // A number can be added to each visited cell
        // use string to identify the class
        backtrack(robot, new HashSet<>(), 0, 0, 0);
    }

    public void backtrack(Robot robot, Set<String> set, int i, int j, int cur_dir) {
        String tmp = i + "->" + j;
        if (set.contains(tmp)) {
            return;
        }

        robot.clean();
        set.add(tmp);

        for (int n = 0; n < 4; n++) {
            // the robot can to four directions, we use right turn
            if (robot.move()) {
                // can go directly. Find the (x, y) for the next cell based on current direction
                int x = i, y = j;
             // 0: up, 90: right, 180: down, 270: left
                switch (cur_dir) {
                case 0:
                    // go up, reduce i
                    x = i - 1;
                    break;
                case 90:
                    // go right
                    y = j + 1;
                    break;
                case 180:
                    // go down
                    x = i + 1;
                    break;
                case 270:
                    // go left
                    y = j - 1;
                    break;
                default:
                    break;
                }

                backtrack(robot, set, x, y, cur_dir);
                // go back to the starting position
                robot.turnLeft();
                robot.turnLeft();
                robot.move();
                robot.turnRight();
                robot.turnRight();

            }
            // turn to next direction
            robot.turnRight();
            cur_dir += 90;
            cur_dir %= 360;
        }

    }

    //interview friendly:
    //thinking process:
    //so provide 4 APIs for the robot, try to clean the room which is represented as map
    //why we need for(d=0; d<4;d++) this and have a curDirection as recursive parameter because 
    //1. when we enter a new cell, we need to which direction we are, if we do not know, then we do not know
    //how to call API, turn left or turn right, how can we avoid visited ones? 
    //2. the dirs have dedicated order thats why after if, we always turn right, clockwise to scan the 4 nighbours
    //O(4^(n-m))/O(n-m), n is the cells=r*c, m is obstacles,
    final int[][] dirs = new int[][] {
        {-1, 0}, //turn up
        {0, 1},//right
        {1, 0},//down
        {0, -1}};//left

    private void helper(Robot robot, Set<String> visited, int curDirection, int i, int j) {
        visited.add(i + "->" + j);
        robot.clean();
        for (int d = 0; d < 4; ++d) {
            int nextDir = (curDirection + d) % 4;
            int[] next = dirs[nextDir];
            int ni = i + next[0];
            int nj = j + next[1];
            //so if we have not visited (ni, nj) then we move to this cell, 
            //first visit, then we should back to previous cell, since we would like to try other directions
            //first 2 turn left will reverse our direction, move() will move back to original position, 
            //then 2 turn left will make our direction the same when we would like go into (ni, nj),
            //we last we turn right, so we can proceed
            
            //robot.move() means we successful move to this position,so we need first detect and take actions
            //
            if (!visited.contains(ni + "->" + nj) && robot.move()) {
                helper(robot, visited, nextDir, ni, nj);
                robot.turnLeft();
                robot.turnLeft();
                robot.move();
                robot.turnLeft();
                robot.turnLeft();
            }
            robot.turnRight();
        }
    }

    public void cleanRoom(Robot robot) {
        Set<String> visited = new HashSet<>();
        //first we think we turn up
        helper(robot, visited, 0, 0, 0);
    }
}