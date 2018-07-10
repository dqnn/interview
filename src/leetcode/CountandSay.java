package leetcode;

/**
 * Project Name : Leetcode Package Name : leetcode File Name : CountandSay
 * Creator : duqiang Date : Ang, 2018 Description : 38. Count and Say
 */

/*
 * * The count-and-say sequence is the sequence of integers with the first five
 * terms as following:
 * 
 * 
 * 
 * 1. 1
 * 
 * 2. 11
 * 
 * 3. 21
 * 
 * 4. 1211
 * 
 * 5. 111221
 * 
 * 
 * 1 is read off as "one 1" or 11. 11 is read off as "two 1s" or 21. 21 is read
 * off as "one 2, then one 1" or 1211. Given an integer n, generate the nth term
 * of the count-and-say sequence.
 * 
 * Note: Each term of the sequence of integers will be represented as a string.
 * 
 * Example 1:
 * 
 * Input: 1 Output: "1" Example 2:
 * 
 * Input: 4 Output: "1211"
 */



public class CountandSay {
    /**
     1.     1
     2.     11
     3.     21
     4.     1211
     5.     111221

     time : 不知道
     space : O(n)

     * @param n
     * @return
     */
    public String countAndSay(int n) {
        int i = 1;
        String res = "1";
        while (i < n) {
            int count = 0;
            StringBuilder sb = new StringBuilder();
            char c = res.charAt(0);
            for (int j = 0; j <= res.length(); j++) {
                if (j != res.length() && res.charAt(j) == c) {
                    count++;
                } else {
                    sb.append(count);
                    sb.append(c);
                    if (j != res.length()) {
                        count = 1;
                        c = res.charAt(j);
                    }
                }
            }
            res = sb.toString();
            i++;
        }
        return res;
    }

    // so this problem essentially:
    // you need to understand countAndSay2(n) is the readoff to countAndSay2(n -1).
    // from example we can see
    // 3 is 21,
    // so 4 is the "read off" to 3, 1211. 12--> 2, 11-->1, we can split the problem
    // 1 get the previous number digits and then read off again, and return.
    public String countAndSay2(int n) {
        if (n <= 0)
            return "";
        if (n == 1)
            return "1";
        if (n == 2)
            return "11";

        String temp = countAndSay2(n - 1);
        int len = temp.length() - 1;
        StringBuilder sb = new StringBuilder();
        int counter = 1;
        for (int i = 0; i <= len; i++) {
            // last digit only one.
            if (counter == 1 && i == len) {
                sb.append(counter + String.valueOf(temp.charAt(i)));
                break;
            }

            if (temp.charAt(i) == temp.charAt(i + 1)) {
                counter++;
                // reach last element
                if (i == len - 1) {
                    sb.append(counter + String.valueOf(temp.charAt(i)));
                    break;
                }
                continue;
            } else {
                sb.append(counter + String.valueOf(temp.charAt(i)));
                counter = 1;
            }
        }
        return sb.toString();

    }
}
