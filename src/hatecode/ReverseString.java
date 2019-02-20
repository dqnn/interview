package hatecode;

import java.util.Stack;

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
    
    //in ="ab(cd)"-->abdc, "ab(cd(ef))"-->abefdc 
    public static String reverseStringWithBrakets(String s) {
        if (s == null || s.length() == 0) return s;
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< s.length();) {
            char ch = s.charAt(i);
            if (ch == '(') {
                int end = i;
                int cnt = 1;
                StringBuilder temp = new StringBuilder();
                while(end < s.length() && cnt > 0) {
                    if (s.charAt(end) == '(') cnt++;
                    else if (s.charAt(end) == ')') cnt--;
                    end++;
                }
                temp.append(reverseStringWithBrakets(s.substring(i+1, end - 1)));
                sb.append(temp.reverse().toString());
                i = end;
            } else {
                sb.append(ch);
                i++;
            }
        }
        return sb.toString();
    }
    
    //this is interview friendly and least space complexity
    public static String reverseStringWithBrakets_Stack(String s) {
        if (s == null || s.length() == 0) return s;
        Stack<String> stack = new Stack<>();
        String res = "";
        for(int i = 0; i< s.length();i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(res);
                res = "";
            } else if (ch == ')'){
               StringBuilder sb = new StringBuilder(stack.pop());
               res = sb.toString() + new StringBuilder(res).reverse().toString();
            } else {
                res += ch;
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
        System.out.println(reverseStringWithBrakets("ab(cd)"));
        System.out.println(reverseStringWithBrakets("ab(cd(ef))"));
        //ab(cd(ef(gh)))->ab(cd(efhg))->ab(cdghfe)->abefhgdc
        System.out.println(reverseStringWithBrakets("ab(cd(ef(gh)))"));
        
        System.out.println(reverseStringWithBrakets_Stack("ab(cd)"));
        System.out.println(reverseStringWithBrakets_Stack("ab(cd(ef))"));
        //ab(cd(ef(gh)))->ab(cd(efhg))->ab(cdghfe)->abefhgdc
        System.out.println(reverseStringWithBrakets_Stack("ab(cd(ef(gh)))"));
    }
}
