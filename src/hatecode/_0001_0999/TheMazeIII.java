package hatecode._0001_0999;

import java.util.*;
public class TheMazeIII {
/*
499. The Maze III
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up (u), down (d), left (l) or right (r), but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it rolls on to the hole.

Given the ball position, the hole position and the maze, find out how the ball could drop into the hole by moving the shortest distance. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the hole (included). Output the moving directions by using 'u', 'd', 'l' and 'r'. Since there could be several different shortest ways, you should output the lexicographically smallest way. If the ball cannot reach the hole, output "impossible".

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The ball and the hole coordinates are represented by row and column indexes.

 

Example 1:

Input 1: a maze represented by a 2D array

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

Input 2: ball coordinate (rowBall, colBall) = (4, 3)
Input 3: hole coordinate (rowHole, colHole) = (0, 1)

Output: "lul"
*/
    // interview friendly, 
    //summary Maze 1, II and III
    //I is to check whether we can rolling from start to end
    //II is to find the shortest path from start to end
    //III is to print the shortest path from start to end, the same as II
    
    //so about the shortest path in 2D matrix, we can always use BFS, if each step
    //is the same, we just use step++ in while loop, and if reached the destination, 
    //just return, if each step is different, like here, rolling style then we can 
    //use PriorityQueue to prioritize the smallest length first. but we need to remember 
    //how we note the path we choose
    public String findShortestWay(int[][] maze, int[] start, int[] hole) {
        int m=maze.length, n=maze[0].length;
        Point[][] points = new Point[m][n]; // record length
        for (int i=0;i<m*n;i++) points[i/n][i%n]=new Point(i/n, i%n);
       
        int[][] dir=new int[][] {{-1,0},{0,1},{1,0},{0,-1}};
        String[] ds=new String[] {"u","r","d","l"};
        
        //sort asc
        PriorityQueue<Point> list=new PriorityQueue<>((o1,o2)->o1.l-o2.l); // using priority queue
        list.offer(new Point(start[0], start[1], 0, ""));
        while (!list.isEmpty()) {
            Point p=list.poll();
            if (points[p.x][p.y].compareTo(p)<=0) continue; // if we have already found a route shorter
            points[p.x][p.y]=p;
            for (int i=0;i<4;i++) {
                int xx=p.x, yy=p.y, l=p.l;
                while (xx>=0 && xx<m && yy>=0 && yy<n && maze[xx][yy]==0 && (xx!=hole[0] || yy!=hole[1])) {
                    xx+=dir[i][0];
                    yy+=dir[i][1];
                    l++;
                }
                if (xx!=hole[0] || yy!=hole[1]) { // check the hole
                    xx-=dir[i][0];
                    yy-=dir[i][1];
                    l--;
                }
                list.offer(new Point(xx, yy, l, p.s + ds[i]));
            }
        }
        return points[hole[0]][hole[1]].l==Integer.MAX_VALUE?"impossible":points[hole[0]][hole[1]].s;
    }
    
    
    class Point {
        int x,y,l;
        String s;
        public Point(int _x, int _y) {x=_x;y=_y;l=Integer.MAX_VALUE;s="";}
        public Point(int _x, int _y, int _l,String _s) {x=_x;y=_y;l=_l;s=_s;}
        public int compareTo(Point p) {return l==p.l?s.compareTo(p.s):l-p.l;}
    }
}