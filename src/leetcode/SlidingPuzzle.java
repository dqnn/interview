package leetcode;

import java.util.HashMap;
import java.util.Map;

public class SlidingPuzzle {
/*
 773. Sliding Puzzle
On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, 
and an empty square represented by 0.

A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given a puzzle board, return the least number of moves required so that the state of 
the board is solved. If it is impossible for the state of the board to be solved, return -1.

Examples:

Input: board = [[1,2,3],[4,0,5]]
Output: 1
Explanation: Swap the 0 and the 5 in one move.
Input: board = [[1,2,3],[5,4,0]]
Output: -1
Explanation: No number of moves will make the board solved.
 */
    
    //this is DFS solution
    //O(mn *(mn)!) O(mn * mn!)
    public static int slidingPuzzle(int[][] b) {
        if (b == null || b.length < 1 || b[0].length < 1) {
            return -1;
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("123450", Integer.MAX_VALUE);
        for(int i = 0; i < b.length; i++) {
            for(int j = 0; j < b[0].length;j++) {
                if (b[i][j] == 0) {
                    dfs(b, map,i, j, 0);
                    break;
                }
                
            }
        }
        System.out.println(map);
        return map.get("123450") == Integer.MAX_VALUE ? -1 : map.get("123450");
    }
    private static String dump(int[][] b) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < b.length; i++) {
            for(int j = 0; j < b[0].length;j++) {
                sb.append(b[i][j]);
            }
        }
        return sb.toString();
    }
    private static void dfs(int[][] b, Map<String, Integer> map, int i, int j, int count) {
        if ( i< 0 || j <0 || i >= b.length || j >= b[0].length || map.get("123450") < count) {
            return;
        }
        String curStr = dump(b);
        
        //here  is the key: if we encounter a smaller path, we should continue. 
        //we only return when current move is bigger than map.get("123450"
        if (map.containsKey(curStr) && (count >= map.get(curStr))) {
            return;
        }
        map.put(curStr, count);
        
        //swap next row
        //System.out.println(curStr);
        int[][] dirs ={{1,0},{-1,0},{0,1},{0,-1}};
        for(int[] dir : dirs) {
            int ni = i + dir[0], nj = j + dir[1];
            if (ni >=0 && ni < b.length && nj >=0 && nj < b[0].length) {
                swap(b, i, j, ni, nj);
                dfs(b, map, ni, nj, count+1);
                swap(b, ni, nj, i, j);
            }
        }
    }
    
    private static void swap(int[][] b, int i, int j, int x, int y) {
        int temp = b[i][j];
        b[i][j] = b[x][y];
        b[x][y] = temp;
    }
    public static void main(String[] args) {
        int[][] input = {{4,1,2},{5,0,3}};
        System.out.println(slidingPuzzle(input));
    }
    
    //BFS solution
    
    
}