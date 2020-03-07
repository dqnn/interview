package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : StringtoInteger
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 8. String to Integer (atoi)
 */
public class _008StringtoInteger {
    /**
     * time : O(n)
     * space : O(1)
     * @param str
     * @return
     */
    // this problem is to translate from string to int
    //1 is to detect + -
    //2 is to use res * 10 + char - '0'
    //result is Long
    //if some char is not digit, then we stop there
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) return 0;
        str = str.trim();
        char firstChar = str.charAt(0);
        int sign = 1;
        int start = 0;
        long res = 0;
        if (firstChar == '+') {
            sign = 1;
            start++;
        } else if (firstChar == '-') {
            sign = -1;
            start++;
        }
        for (int i = start; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return (int) res * sign;
            }
            //this is the computations
            res = res * 10 + str.charAt(i) - '0';
            if (sign == 1 && res > Integer.MAX_VALUE) return  Integer.MAX_VALUE;
            if (sign == -1 && res > Integer.MAX_VALUE) return Integer.MIN_VALUE;
        }
        return (int) res * sign;
    }
}
