package hatecode._0001_0999;

//0-1背包问题
public class Knapsack {
/*
https://blog.csdn.net/likunkun__/article/details/80223003
给定 n 种物品，每种物品有对应的重量weight和价值value，一个容量为 maxWeight 的背包，
问：应该如何选择装入背包的物品，使得装入背包中的物品的总价值最大？

面对每个物品，我们只有选择拿取或者不拿两种选择，不能选择装入某物品的一部分，也不能装入同一物品多次。

使用动态规划思想，很容易想到，我们需要一个空间来储存：从0号物品开始，对于每个重量上限的最优解。
所以我们可以设计一个二维数组存放当前最大价值，如下：（我们假设4件物品，重量和价值数组如下）

 

Weight[]  = { 2 ,3 ,4 ,5 }    

value[]   = { 3 ,4 ,5 ,7 }      N = 4 件物品

最大价值数组为maxvalue[N+1][maxWeight+1]，因为我们要从0开始保存
然后我们开始遍历这个表格，填充他们。

我们考虑：开始拿物品的时候，我们判断，如果当前拿到的物品的重量
（weight[i-1]）<= 当前能取的最大重量(j)，我们就考虑要不要放进这个。

于是我们比较这两个价值：

maxvalue[i-1][j]（不放这个物品的价值）

value[i-1] + maxvalue[i-1][j-weight[i-1]]（这个物品的价值 加上 
当前能放的总重量减去当前物品重量时取前i-1个物品时的对应重量时候的最高价值）
！
i\j 0   1   2   3   4   5   6   7   8   9
0   0   0   0   0   0   0   0   0   0   0
1   0   0   3   3   3   3   3   3   3   3
2   0   0   3   4   4   7   7   7   7   7
3   0   0   3   4   5   7   8   9   9   12
4   0   0   3   4   5   7   8   10  11  12

the table 
 */
    public static int knapsack(int[] weight, int[] value, int maxweight) {

        int n = weight.length;
        // 最大价值数组为maxvalue[N+1][maxWeight+1]，因为我们要从0开始保存
        int[][] maxvalue = new int[n + 1][maxweight + 1];

        // 重量和物品为0时，价值为0
        for (int i = 0; i < maxweight + 1; i++) {
            maxvalue[0][i] = 0;

        }
        for (int i = 0; i < n + 1; i++) {
            maxvalue[i][0] = 0;

        }

        // i：只拿前i件物品（这里的i因为取了0，所以对应到weight和value里面都是i-1号位置）
        // j：假设能取的总重量为j
        // n是物品件数
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= maxweight; j++) {
                // 当前最大价值等于放上一件的最大价值
                maxvalue[i][j] = maxvalue[i - 1][j];
                // 如果当前件的重量小于总重量，可以放进去或者拿出别的东西再放进去
                if (weight[i - 1] <= j) {
                    // 比较（不放这个物品的价值）和
                    // （这个物品的价值 加上 当前能放的总重量减去当前物品重量时取前i-1个物品时的对应重量时候的最高价值）
                    if (maxvalue[i - 1][j - weight[i - 1]] + value[i - 1] > maxvalue[i - 1][j]) {
                        maxvalue[i][j] = maxvalue[i - 1][j - weight[i - 1]] + value[i - 1];
                    }
                }
            }
        }
        return maxvalue[n][maxweight];
    }

    public static void main(String[] args) {
        // TODO 自动生成的方法存根
        int weight[] = {
                2, 3, 4, 5 };
        int value[] = {
                3, 4, 5, 7 };
        int maxweight = 9;
        System.out.println(knapsack(weight, value, maxweight));
    }

}