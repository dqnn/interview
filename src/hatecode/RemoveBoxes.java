package hatecode;

/*
 * 546. Remove Boxes
 * Given several boxes with different colors represented by different positive numbers. 
You may experience several rounds to remove boxes until there is no box left. Each time you can choose some 
continuous boxes with the same color
 (composed of k boxes, k >= 1), 
remove them and get k*k points.
Find the maximum points you can get.

Example 1:
Input:

[1, 3, 2, 2, 2, 3, 4, 3, 1]
Output:
23
Explanation:
[1, 3, 2, 2, 2, 3, 4, 3, 1] 
----> [1, 3, 3, 4, 3, 1] (3*3=9 points) 
----> [1, 3, 3, 3, 1] (1*1=1 points) 
----> [1, 1] (3*3=9 points) 
----> [] (2*2=4 points)
Note: The number of boxes n would not exceed 100.
 */

/*
 * 刚开始看这道题的时候，感觉跟之前那道Zuma Game很像，于是就写了一个暴力破解的方法，结果TLE了。
 * 无奈之下只好上网搜大神们的解法，又看了fun4LeetCode大神写的帖子，
 * 之前那道Reverse Pairs就是参考的fun4LeetCode大神的帖子，
 * 惊为天人，这次又是这般精彩，大神请收下我的膝盖。
 * 那么下面的解法就大部分参考fun4LeetCode大神的帖子来讲解吧。在之前帖子Reverse Pairs的讲解中，
 * 大神归纳了两种重现模式，我们这里也试着看能不能套用上。对于这种看来看去都没思路的题来说，
 * 抽象建模的能力就非常的重要了。对于题目中的具体场景啊，具体代表的东西我们都可忽略不看，
 * 这样能帮助我们接近问题的本质，这道题的本质就是一个数组，我们每次消去一个或多个数字，
 * 并获得相应的分数，让我们求最高能获得的分数。而之前那道Zuma Game也是给了一个数组，
 * 让我们往某个位置加数字，使得相同的数字至少有3个才能消除，二者是不是很像呢，
 * 但是其实解法却差别很大。那道题之所以暴力破解没有问题是因为数组的长度和给定的数字个数都有限制，
 * 而且都是相对较小的数，那么即便遍历所有情况也不会有太大的计算量。而这道题就不一样了，
 * 既然不能暴力破解，那么对于这种玩数组和子数组的题，刷题老司机们都会优先考虑用DP来做吧。
 * 既然要玩子数组，肯定要限定子数组的范围，那么至少应该是个二维的dp数组，
 * 其中dp[i][j]表示在子数组[i, j]范围内所能得到的最高的分数，
 * 那么最后我们返回dp[0][n-1]就是要求的结果。

那么对于dp[i][j]我们想，如果我们移除boxes[i]这个数字，那么总得分应该是1*1 + dp[i+1][j]，
但是通过分析题目中的例子，能够获得高积分的trick是，移除某个或某几个数字后，
如果能使得原本不连续的相同数字变的连续是坠好的，因为同时移除的数字越多，那么所得的积分就越高。
那么假如在[i, j]中间有个位置m，使得boxes[i]和boxes[m]相等，
那么我们就不应该只是移除boxes[i]这个数字，而是还应该考虑直接移除[i+1, m-1]区间上的数，
使得boxes[i]和boxes[m]直接相邻，那么我们获得的积分就是dp[i+1][m-1]，那么我们剩余了什么，
boxes[i]和boxes[m, j]区间的数，此时我们无法处理子数组[m, j]，
因为我们有些信息没有包括在我们的dp数组中，此类的题目归纳为不自己包含的子问题，其解法依赖于
一些子问题以外的信息。这类问题通常没有定义好的重现关系，所以不太容易递归求解。为了解决这类问题，
我们需要修改问题的定义，使得其包含一些外部信息，从而变成自包含子问题。

那么对于这道题来说，无法处理boxes[m, j]区间是因为其缺少了关键信息，
我们不知道boxes[m]左边相同数字的个数k，只有知道了这个信息，那么m的位置才有意义，
所以我们的dp数组应该是一个三维数组dp[i][j][k]，表示区间[i, j]中能获得的最大积分，
当boxes[i]左边有k个数字跟其相等，那么我们的目标就是要求dp[0][n-1][0]了，
而且我们也能推出dp[i][i][k] = (1+k) * (1+k)这个等式。那么我们来推导重现关系，
对于dp[i][j][k]，如果我们移除boxes[i]，那么我们得到(1+k)*(1+k) + dp[i+1][j][0]。
对于上面提到的那种情况，当某个位置m，有boxes[i] == boxes[m]时，
我们也应该考虑先移除[i+1,m-1]这部分dp[i+1][m-1][0]，然后再处理剩下的部分，
得到积分dp[m][j][k+1]，这里k加1点原因是，移除了中间的部分后，原本和boxes[m]不相邻的
boxes[i]现在相邻了，又因为二者值相同，所以k应该加1，
因为k的定义就是左边相等的数字的个数。讲到这里，那么DP方法最难的递推公式也就得到了，
那么代码就不难写了，需要注意的是，这里的C++的写法不能用vector来表示三维数组，好像是内存限制超出，
只能用C语言的写法，由于C语言数组的定义需要初始化大小，
而题目中说了数组长度不会超100，所以我们就用100来初始化，参见代码如下：

Use dp[l][r][k] to denote the max score of subarray box[l] ~ box[r] 
with k boxes after box[l] that have the same color as box[l]

box[l-k], box[l-k+1] ...box[l], box[l+1], …, box[r]

e.g. “AAAACDABACABCDH”

dp[6][10][4] is the max score of [ABACA], prefix is [AAAA]
dp[6][10][3] is the max score of [ABACA], prefix by [AAA]

base case: l > r, empty array, return 0.
Transition:
dp[l][r][k] = max(dp[l+1][r][0] + (k + 1)*(k + 1),  # case 1
                  dp[l+1][i-1][0] + dp[i+1][r][k+1])  # case 2
# "ABACA|AAAA" 
# case 1: dp("ABAC") + score("AAAAA") drop j and the tail.
# case 2: box[i] == box[l], l <= i < r, try all break points
# max({dp("A|AAAAA") + dp("BAC")}, {dp("ABA|AAAAA") + dp("C")})
Time complexity: O(n^4)

Space complexity: O(n^3)
 */
public class RemoveBoxes {

    public int removeBoxes(int[] b) {
        //edge case
        if (b == null || b.length < 1) {
            return 0;
        }
        int len = b.length;
        int[][][] dp = new int[len][len][len];
        return helper(dp, b, 0, len - 1, 0);
    }
    
    public int helper(int[][][] dp, int[] nums, int left, int right, int k) {
        if (left > right) return 0;
        if (dp[left][right][k] > 0) {
            return dp[left][right][k];
        }
        //we use k to represent left
        int res = (1 + k) * (1 + k) + helper(dp, nums, left + 1, right, 0);
        // this is backtracking templates
        for (int m = left + 1; m <= right; ++m) {
            if (nums[m] == nums[left]) {
                res = Math.max(res, helper(dp, nums, left + 1, m - 1, 0) 
                        + helper(dp, nums, m, right, k + 1));
            }
        }
        return dp[left][right][k] = res;
    }
}