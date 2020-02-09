package hatecode._0001_0999;

import java.util.*;
public class FillMatrix {
/*
 * from google interview doc, not from LC
 * problem statement

一个矩阵，里面有空字符和abc等character，用最近的字符填满矩阵。
A      ‘’       ‘’        ‘’        ‘’
‘’       ‘’       ‘’        ‘’        ‘’
‘’       ‘’       ‘’        B       ‘’
空字符填上最近字符，有tie的话随便选一个
follow up：char比空字符多
 */
    //if char more than empty cell, then we need to start from char, since this is faster
    public void fillMatrix(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        Queue<Node> pq = new PriorityQueue<>((a, b) -> a.dis - b.dis);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] != ' ') {
                    pq.offer(new Node(i, j, matrix[i][j], 0));
                }
            }
        }
        int[][] dirs = new int[][] {{0, 1},{0, -1},{1, 0},{-1, 0}};
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (matrix[curr.i][curr.j] == ' ') {
                matrix[curr.i][curr.j] = curr.c;
            }
            // generate
            for (int[] dir : dirs) {
                int x = curr.i + dir[0];
                int y = curr.j + dir[1];
                if (isValid(matrix, x, y) && matrix[x][y] == ' ') {
                    pq.offer(new Node(x, y, curr.c, curr.dis + 1));
                }
            }
        }
    }

    private boolean isValid(char[][] m, int i, int j) {
        return i >= 0 && i < m.length && j >= 0 && j < m[0].length;
    }

    private static class Node {
        int  i;
        int  j;
        char c;
        int  dis;

        public Node(int i, int j, char c, int dis) {
            this.i = i;
            this.j = j;
            this.c = c;
            this.dis = dis;
        }
    }
}