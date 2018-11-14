package leetcode;

/**
 * Created by duqiang on 28/07/2017.
 */
public class ReverseString {
    /**
     * 344. Reverse String
     * Write a function that takes a string as input and returns the string reversed.

     Example:
     Given s = "hello", return "olleh".

     time : O(n);
     space : O(n);
     * @param s
     * @return
     */
    public static String reverseString(String s) {
        if (s == null || s.length() == 0) return s;
        int left = 0;
        int right = s.length() - 1;
        char[] str = s.toCharArray();
        //we use left < right
        while (left < right) {
            char temp = str[left];
            str[left++] = str[right];
            str[right--] = temp;
        }
        return new String(str);
    }
}
