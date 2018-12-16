package hatecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : NumberofIslandsII
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : 305. Number of Islands II
 */
public class NumberofIslandsII {
    /**
     * tag: union-find
     * A 2d grid map of m rows and n columns is initially filled with water.
     * We may perform an addLand operation which turns the water at position (row, col) into a land.
     * Given a list of positions to operate, count the number of islands after each addLand operation.
     * An island is surrounded by water and is formed by connecting
     * adjacent lands horizontally or vertically.
     * You may assume all four edges of the grid are all surrounded by water.

     Example:

     Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
     Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

     0 0 0
     0 0 0
     0 0 0
     Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

     1 0 0
     0 0 0   Number of islands = 1
     0 0 0
     Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

     1 1 0
     0 0 0   Number of islands = 1
     0 0 0
     Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

     1 1 0
     0 0 1   Number of islands = 2
     0 0 0
     Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

     1 1 0
     0 0 1   Number of islands = 3
     0 1 0
     We return the result as an array: [1, 1, 2, 3]

     time : O(k^2)
     space : O(m * n)
     */
    static int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    
    // thinking process: given a all 0 matrix, we will make some islands in the matrix,
    //with 2D array, we want to know after a set of ops, how many islands are connected
    
    //suppose x,y is the coordination we want to make island, we assume this is one single
    //circle, so we from this point, 4 adjacent ops to mark the nodes is this node's child
    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        if (m <= 0 || n <= 0) return res;

        int count = 0;
        int[] roots = new int[m * n];
        //later it will changed to its computed index, like mapping
        Arrays.fill(roots, -1);
        
        //construct the model how to process the pair,
        for (int[] pair : positions) {
            int position = n * pair[0] + pair[1];
            roots[position] = position;
            count++;

            for (int[] dir : dirs) {
                int x = pair[0] + dir[0];
                int y = pair[1] + dir[1];
                int newPosition = n * x + y;
                if (x < 0 || x >= m || y < 0 || y >= n || roots[newPosition] == -1) {
                    continue;
                }
                //find root of curPos and flat the parent tree,
                //if new root id not equals to position, which means they should, so we 
                //merge this into the correct place.
                if (union(roots, position, newPosition)) {
                    position = newPosition;
                    count--;
                }
            }
            res.add(count);
        }
        return res;
    }

    //find root of i,
    //here i != roots[i] is trick, we have to use i not -1, in LC 323, we have to use -1
    //TODO: find the root cause
    private static int find(int[] roots, int i) {
        while (i != roots[i]) {
            roots[i] = roots[roots[i]]; //path compressions
            i = roots[i];
        }
        return i;
    }
    
    private static boolean union(int[] roots, int x, int y) {
        int i = find(roots, x);
        int j = find(roots, y);
        if (i != j) {
            roots[i] = j;
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        int[][] in = {{0,0},{0,1},{1,2},{2,1}};
        System.out.println(numIslands2(3,3,in));
    }
}
