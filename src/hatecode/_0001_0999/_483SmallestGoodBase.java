package hatecode._0001_0999;
public class _483SmallestGoodBase {
/*
483. Smallest Good Base
For an integer n, we call k>=2 a good base of n, if all digits of n base k are 1.

Now given a string representing n, you should return the smallest good base of n in string format.

Example 1:

Input: "13"
Output: "3"
Explanation: 13 base 3 is 111.
 

Example 2:

Input: "4681"
Output: "8"
Explanation: 4681 base 8 is 11111.
*/
    /*
     * n = 1 + k + k^2 + k^3 + ... + k^(m-1)
     * 
     * 这是一个等比数列，中学数学的内容吧，利用求和公式可以表示为 n = (k^m - 1) / (k -
     * 1)。我们的目标是求最小的k，那么仔细观察这个式子，在n恒定的情况，k越小则m却大，那么就是说上面的等式越长越好。下面我们来分析m的取值范围，
     * 题目中给了n的范围，是[3,
     * 10^18]。那么由于k至少为2，n至少为3，那么肯定至少有两项，则m>=2。那么m的上限该如何求？其实也不难，想要m最大，那么k就要最小，k最小是2，
     * 那么m最大只能为log2(n +
     * 1)，数字n用二进制表示的时候可拆分出的项最多。但这道题要求变换后的数各位都是1，那么我们看题目中最后一个例子，可以发现，当k=n-1时，一定能变成11，
     * 所以实在找不到更小的情况下就返回n-1。
     * 
     * 下面我们来确定k的范围，由于k至少为2，那么我们可以根据下面这个不等式来求k的上限：
     * 
     * n = 1 + k + k^2 + k^3 + ... + k^(m-1) > k^(m-1)
     * 
     * 解出k < n^(1 / (m-1))，其实我们也可以可以通过n < k^m - 1
     * 来求出k的准确的下限，但由于是二分查找法，下限直接使用2也没啥问题。分析到这里，那么解法应该已经跃然纸上了，我们遍历所有可能的m值，
     * 然后利用二分查找法来确定k的值，对每一个k值，我们通过联合m值算出总和sum，然后跟n来对比即可
     */
    public String smallestGoodBase(String n) {
        long num = Long.valueOf(n);

        for (int m = (int) (Math.log(num + 1) / Math.log(2)); m > 2; m--) {
            long l = (long) (Math.pow(num + 1, 1.0 / m));
            long r = (long) (Math.pow(num, 1.0 / (m - 1)));

            while (l <= r) {
                long k = l + ((r - l) >> 1);
                long f = 0L;
                for (int i = 0; i < m; i++, f = f * k + 1);

                if (num == f) {
                    return String.valueOf(k);
                } else if (num < f) {
                    r = k - 1;
                } else {
                    l = k + 1;
                }
            }
        }
        return String.valueOf(num - 1);
    }

}