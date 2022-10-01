package hatecode._0001_0999;

import java.util.Stack;

/**
 * 
 */
public class _067AddBinary {
    /**
     * 67. Add Binary
     * Given two binary strings, return their sum 
     * (also a binary string).

     For example,
     a = "11"
     b = "1"
     Return "100".

     time : O(n);
     space : O(n);
     * @param a
     * @param b
     * @return
     */
    public static String addBinary(String a, String b) {
        if (a == null || b == null) return b;
        StringBuilder sb = new StringBuilder();
        int i = a.length()-1, j =b.length()-1;
        
        int cur = 0;
        while(i >=0 || j >=0) {
            if (i >=0)  cur += a.charAt(i) -'0';
            if (j >=0)  cur += b.charAt(j) - '0';
            sb.insert(0, cur % 2); // no need to reverse
            cur = cur/2;
            i--;
            j--;
        }
        
        if (cur != 0) {
            sb.insert(0, cur);
        }
        
        return sb.toString();
    }
    //this is to use stack to avoid reverse(), 
    public String addBinary2(String a, String b) {
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, sum = 0;
        while(i >= 0 || j >= 0) {
            if(i >= 0) sum += a.charAt(i--) - '0';
            if(j >= 0) sum += b.charAt(j--) - '0';
            sum = sum / 2;
            stack.push(sum % 2);
        }
        if(sum != 0)
            sb.append(sum);
        while(!stack.isEmpty())
            sb.append(stack.pop());
        return sb.toString();
    }
}
