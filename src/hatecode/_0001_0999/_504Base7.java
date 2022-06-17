package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : Base7
 * Creator : professorX
 * Date : Nov, 2017
 * Description : 504. Base 7
 */
public class _504Base7 {
    /**
     * Given an integer, return its base 7 string representation.

     Example 1:
     Input: 100
     Output: "202"
     Example 2:
     Input: -7
     Output: "-10"

     time : O(n)
     space : O(n)

     */

    public String convertToBase7(int num) {
        if (num == 0) return "0";

        StringBuilder sb = new StringBuilder();
        boolean negative = false;

        if (num < 0) {
            negative = true;
        }
        while (num != 0) {
            sb.append(Math.abs(num % 7));
            num = num / 7;
        }
        if (negative) {
            sb.append("-");
        }
        return sb.reverse().toString();
    }

    public String convertToBase72(int num) {
        if (num < 0) {
            return "-" + convertToBase72(-num);
        }
        if (num < 7) {
            return num + "";
        }
        return convertToBase72(num / 7) + num % 7;
    }
    
    public String convertToBase73(int num) {
        boolean flag = num < 0 ? true :false;
        num = Math.abs(num);
        StringBuilder sb = new StringBuilder();
        while(num / 7 != 0) {
            int remainer = num % 7; 
            sb.append(remainer);
            num  = num / 7;
        }
        sb.append(num);
        String res = sb.reverse().toString();
        return flag ? "-" + res : res;
    }
}
