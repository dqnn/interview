package leetcode;

/**
 * Project Name : Leetcode Package Name : leetcode File Name :
 * CountNumberswithUniqueDigits Creator : duqiang Date : Aug, 2018 Description :
 * 
 * 
 * 
 * 
 * 357. Count Numbers with Unique Digits Given a non-negative integer n, count
 * all numbers with unique digits, x, where 0 ≤ x < Math.pow(10, n).
 * 
 * Example: Given n = 2, return 91. (The answer should be the total numbers in
 * the range of 0 ≤ x < 100, excluding [11,22,33,44,55,66,77,88,99])
 * 
 * 
 */
public class CountNumberswithUniqueDigits {

    /*
     * f(1) = 10 since 0 -9,
     * 
     * f(2) = 9 * 9 + f(1) = 91
     * 
     * f(3) = 9 * 9 * 8 + f(2) + f(1)
     */
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0)     return 1;

        int res = 10;
        // how many unique digits
        int uniqueDigits = 9;
        // means how many number we can choose as this position
        // fg, three digits
        // first we have 9 available 1-9
        // second we have 0-9 except first so we have 9
        // third we have 8, so
        // we have 9 * 9 * 8
        int availableNumber = 9;

        while (n-- > 1 && availableNumber > 0) {
            uniqueDigits = uniqueDigits * availableNumber;
            res += uniqueDigits;
            availableNumber--;
        }
        return res;
    }

    // Time exceeded
    public int countNumbersWithUniqueDigits2(int n) {
        // edge case
        if (n < 0) {
            return 0;
        }

        int count = 0;
        int end = (int) Math.pow(10, n) - 1;
        for (int i = 11; i <= end; i++) {
            String t = String.valueOf(i);
            int len = t.length();
            int[] visited = new int[10];
            for (int j = 0; j < len; j++) {
                if (visited[t.charAt(j) - '0'] > 0) {
                    count++;
                    break;
                }
                visited[t.charAt(j) - '0']++;
            }
        }
        return end - count + 1;
    }
}
