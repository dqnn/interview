package hatecode;

import java.util.*;
public class CutMatrix {
    /*
     * Google interview doc, not form LC 
     * 给一个m x n的矩阵，可以横着切或者竖着切，每横着切一刀的cost为被切矩阵的列数的平方，每竖着切一刀的cost是
     * 被切矩阵的行数的平方。然后给一个desired
     * area，问切到这个area大小最少的cost是多少。好像切好以后两个矩阵都能继续切，差点没做出来，还是弱鸡啊
     * 
     * 输入为矩阵的长和宽和一个int代表desired area
     * 思路： dfs带memorization，一刀切成两半，在其中一半找面积为k的矩形，在另一半找面积为desired area -
     * k的矩形，然后每一刀有两种切法，横着切，竖着切，分别遍历
     */
    
    
    Map<List<Integer>, Integer> memo = new HashMap<>();
    public int minCost(int rows, int cols, int area) {
        // sanity check
        return helper(rows, cols, area);
    }
    private int helper(int rows, int cols, int area) {
        if(area == 0) return 0;
        if(rows == 0) return 10000; // some big integer will mask the result in min() function
        if(cols == 0) return 10000;
        if(rows * cols == area) return 0;
        if(rows * cols < area) return 10000;
        if(memo.containsKey(Arrays.asList(rows, cols, area))) {
            return memo.get(Arrays.asList(rows, cols, area));
        }

        int cost = Integer.MAX_VALUE;
        for(int k = 0; k <= area; k++) {
            // cut row
            int tmp = (int) Math.pow(cols, 2);
            for(int i = 1 ; i <= rows / 2 ; i++) {
                cost = Math.min(cost, tmp + helper(i, cols, k) + helper(rows - i, cols, area - k));
            }
            // cut cols
            tmp = (int) Math.pow(rows, 2);
            for(int j = 1 ; j <= cols / 2; j++) {
                cost = Math.min(cost, tmp + helper(rows, j, k) + helper(rows, cols - j, area - k));
            }
        }
        memo.put(Arrays.asList(rows, cols, area), cost);
        return cost;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
