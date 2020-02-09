package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ReverseStringII
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : TODO
 */
public class ReverseStringII {
    /**
     * 541. Reverse String II
Given a string and an integer k, you need to reverse the first k characters for 
every 2k characters counting from the start of the string. If there are less 
than k characters left,reverse all of them. If there are less than 2k but 
greater than or equal to k characters, then reverse the first k characters 
and left the other as original.


     time : O(n);
     space : O(n);
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {
        char[] arr = s.toCharArray();
        int i = 0;
        while (i < s.length()) {
            // swap i and distance k k, k - 1, actually we use how 
            int j = Math.min(i + k - 1, s.length() - 1);
            reverse(arr, i, j);
            i += 2 * k;
        }
        return String.valueOf(arr);
    }
    public void reverse(char[] arr, int i, int j) {
        while (i < j) {
            char temp = arr[i];
            arr[i++] = arr[j];
            arr[j--] = temp;
        }
    }
    //same but interview friendly, the key is the question
    // for every 2k string, so we will need to implement 
    // 3 use cases
    // 1 2k+ length string
    // 2 [k, 2k)
    //3 (0, k), for peoridical k, just need to 
    public String reverseStr2(String s, int k) {
        if (s == null || s.length() < 1) {
            return s;
        } 
        int left = 0;
        char[] chs = s.toCharArray();
        while(left < s.length()) {
            int end = Math.min(left + k, s.length()) - 1;
            int start = left;
            while(start < end) {
                char temp = chs[start];
                chs[start++] = chs[end];
                chs[end--] = temp;
            }
            left += 2* k;
        }
        
        return String.valueOf(chs);
    }
}
