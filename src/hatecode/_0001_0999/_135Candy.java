package hatecode._0001_0999;

import java.util.Arrays;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : Candy
 * Creator : professorX
 * Date : Dec, 2017
 * Description : 135. Candy
 */
public class _135Candy {
    /**
     * There are N children standing in a line. Each child is assigned a rating value.

     You are giving candies to these children subjected to the following requirements:

     Each child must have at least one candy.
     Children with a higher rating get more candies than their neighbors.
     What is the minimum candies you must give?

     ratings:     [4, 5, 1, 1, 3, 7]
     candies:     [1, 1, 1, 1, 1, 1]

     ratings:     [4, 5, 1, 1, 3, 7]
     candies:     [1, 2, 1, 1, 2, 3]

     ratings:     [4, 5, 1, 1, 3, 7]
     candies:     [1, 2, 1, 1, 2, 3]

     time : O(n)
     space : O(n)


     * @param ratings
     * @return
     */
    // this problem is trick, for example:
    // 5,4,3,2,1 you cannot only compare 2 or 3 adjacent numbers, because the
    // correct
    // value is decided by far more numbers. so since for neighbours, we need to
    // scan from left, then scan from right.
    // so two scan can make sure the rules are correct executed
    public int candy(int[] r) {
        // edge case
        if (r == null || r.length < 1) {
            return 0;
        }

        int len = r.length;
        if (len < 2)  return 1;


        int[] c = new int[len];
        Arrays.fill(c, 1);

        for (int i = 1; i < len; i++) {
            if (r[i] > r[i - 1]) {
                c[i] = c[i - 1] + 1;
            }
        }

        // this combine two loops,
        // second we scan from right to left
        // so any updates to candy should be c[i] = c[i + 1] + 1
        // third loop is res += max(l2r[i], r2l[i])-->
        for (int i = c.length - 2; i >= 0; i--) {
            if (r[i] > r[i + 1]) {
                // like 5,4,3,2,1
                // 1,1,1,1,1
                // last one element since only have 1 neigbour,
                // so we can only consider from c.length - 2.
                c[i] = Math.max(c[i], c[i + 1] + 1);
            }
        }

        return Arrays.stream(c).sum();
    }

    // this is another solution, bruth-force
    public int candy2(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);
        boolean flag = true;
        int sum = 0;
        while (flag) {
            flag = false;
            for (int i = 0; i < ratings.length; i++) {
                if (i != ratings.length - 1 && ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                    candies[i] = candies[i + 1] + 1;
                    flag = true;
                }
                if (i > 0 && ratings[i] > ratings[i - 1] && candies[i] <= candies[i - 1]) {
                    candies[i] = candies[i - 1] + 1;
                    flag = true;
                }
            }
        }
        for (int candy : candies) {
            sum += candy;
        }
        return sum;
    }
}
