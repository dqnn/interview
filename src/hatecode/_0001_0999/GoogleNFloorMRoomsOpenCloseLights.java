package hatecode._0001_0999;

public class GoogleNFloorMRoomsOpenCloseLights {
    /*
     * from google interview doc, not from LC LintCode 826 
     * A n*m matrix represents
     * an array of computers, and give a list which represents the coordinate of the
     * broken computer. Now we start with (0,0) and repair the computer. There are
     * some request:
     * 
     * You have to fix all the broken computers in the current line to get to the
     * next line. If you are going to the next line, the mechanic must first return
     * to the far left or right of the line. Find the minimum access distance.
     * Notice
     * 
     * The size of the given matrix is n x m, n <= 200, m <= 200. num is the number
     * of broken computer, num <= 1000. After fixing the last computer, you need to
     * return to the far left or right of the last line. Example Given n = 3, m =
     * 10, List = [[0,0],[0,9],[1,7]], return 15.
     * 
     * Explanation:
     * 
     * Starting from (0,0), fix 0, then go to (0,9) to fix 1 and go from (0,9) to
     * next line (1,9), then go to (1,7) to fix 3, then go back to (1,9) and go to
     * (2,9). Given n = 3, m = 10, List = [[0,3],[1,7],[1,2]], return 17.
     * 
     * Explanation:
     * 
     * Starting from (0,0), go to (0,3) and fix 0, then go back to (0,0) to next
     * line (1,0), and go to (1,2) to fix 2, then go to (1,7) to fix 1, then go to
     * (1,9), and end at (2,9).
     * 
     * How to Solve: 如果不上楼，只下楼，用dp逐行扫描 dp[i][0]代表第i层从左边下楼的最小cost
     * dp[i][1]第i层从右边下楼的最小cost， dp[i][0] = dp[i - 1][1] + # of rooms in level i - 1
     * / dp[i - 1][0] + 2 * farthest room with light on dp[i][1]同理 2. 如果可以上下楼
     * （按照楼主描述，应该是不考虑上下楼）
     * 
     * short problem description:
     * 一个n * m矩阵代表一个电脑的阵列，给你一个list< Point >代表坏掉的电脑坐标。现在我们从(0,0)出发修电脑，要求：
1.必须修完当前行所有坏掉的电脑才能走向下一行。
2.如果要走向下一行，修理工必须先返回到这一行的最左端或者最右端。
求最短的访问距离。
     * 
     */
    
    public int max(int a, int b) {
        if(a > b) return a;
        return b;
    }
    public int min(int a, int b) {
        if (a < b) return a;
        return b;
    }
  //dp[i][0] means most left the min distance, dp[i][1] means most right
    public int maintenance(int n, int m, Point[] badcomputers) {
        int [][] dp = new int [201][2];
        int [][] matrix = new int [201][201];
        for(int i = 0; i < badcomputers.length; i++) {
            matrix[badcomputers[i].x][badcomputers[i].y] = 1;
        }
        for(int i = 0; i < n; i++) {
            int most_right = -1;
            int most_left = -1;
            for(int j = 0; j < m; j++) {
                if(matrix[i][j] != 0) {
                    most_right = max(most_right, j);
                    most_left = max(most_left, m - 1 - j);
                }
            }
            if(i == 0) {
                if(most_right == -1) {
                    dp[i][0] = 0;
                    dp[i][1] = m - 1;
                } else {
                    dp[i][0] = 2 * most_right;
                    dp[i][1] = m - 1;
                }
                continue;
            }
            if(most_right == -1) {
                dp[i][0] = dp[i - 1][0] + 1;
                dp[i][1] = dp[i - 1][1] + 1;
            } else {
                dp[i][0] = min(dp[i - 1][0] + 2 * most_right, dp[i - 1][1] + m - 1) + 1;
                dp[i][1] = min(dp[i - 1][1] + 2 * most_left, dp[i - 1][0] + m - 1) + 1;
            }
        }
        return min(dp[n - 1][0], dp[n - 1][1]);
    }
    
    public int maintenance_reference(int n, int m, Point[] badcomputers) {
        // Write your code here
        int[][] dp = new int[n][2];
        int[][] bound = new int[n][2];
        for (int i = 0; i < n; i++){
            bound[i][0] = Integer.MAX_VALUE;
            bound[i][1] = Integer.MIN_VALUE;
        }
        for (Point p : badcomputers){
            bound[p.x][0] = Math.min(bound[p.x][0], p.y);
            bound[p.x][1] = Math.max(bound[p.x][1], p.y);
        }
        for (int i = 0; i < n; i++){
            int left = bound[i][0], right = bound[i][1];
            if (i == 0){
                dp[0][0] = right == Integer.MIN_VALUE ? 0 : 2 * right;
                dp[0][1] = m - 1;
            } else {
                if (right == Integer.MIN_VALUE){
                    dp[i][0] = dp[i - 1][0] + 1;
                    dp[i][1] = dp[i - 1][1] + 1;
                } else {
                    dp[i][0] = Math.min(dp[i - 1][0] + 2 * right, dp[i - 1][1] + m - 1) + 1;
                    dp[i][1] = Math.min(dp[i - 1][1] + 2 * (m - 1 - left), dp[i - 1][0] + m - 1) + 1;
                }
            }
        }
        return Math.min(dp[n - 1][1], dp[n - 1][0]);
    }
}
