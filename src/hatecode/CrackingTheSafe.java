package hatecode;
import java.util.*;
public class CrackingTheSafe {
    /*
     * 753. Cracking the Safe 
     * There is a box protected by a password. The password
     * is n digits, where each letter can be one of the first k digits 0, 1, ...,
     * k-1.
     * 
     * You can keep inputting the password, the password will automatically be
     * matched against the last n digits entered.
     * 
     * For example, assuming the password is "345", I can open it when I type
     * "012345", but I enter a total of 6 digits.
     * 
     * Please return any string of minimum length that is guaranteed to open the box
     * after the entire string is inputted.
     * 
     * Example 1: Input: n = 1, k = 2 Output: "01" Note: "10" will be accepted too.
     */
    
    //O(k^n)/O(k^n)
    public String crackSafe(int n, int k) {
        int M = (int) Math.pow(k, n-1);
        int[] P = new int[M * k];
        for (int i = 0; i < k; ++i)
            for (int q = 0; q < M; ++q)
                P[i*M + q] = q*k + i;

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < M*k; ++i) {
            int j = i;
            while (P[j] >= 0) {
                ans.append(String.valueOf(j / M));
                int v = P[j];
                P[j] = -1;
                j = v;
            }
        }

        for (int i = 0; i < n-1; ++i)
            ans.append("0");
        return new String(ans);
    }
    
    //typical  DFS O(n* k^n)/O(n* k^n), interview friendly
    //thinking process: 
    
    //k = 2, n =2, so suppose we input "00", then we start from idx =1, and append
    // other results using permutations
     public static String crackSafe2(int n, int k) {
        if (n < 1 || k < 1) return "";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            result.append("0");
        }
        Set<String> visited = new HashSet<>();
        visited.add(result.toString());
        crackSafeFrom(result, n, k, (int) Math.pow(k, n), visited);

        return result.toString();
    }
    //n is total length, k is digits pool
    private static boolean crackSafeFrom(StringBuilder result, int n, int k, 
            int total, Set<String> visited) {
        //the only way to guarantee we can unlock the case is that we tried every
        //combinations
        if (visited.size() == total) {
            return true;
        }
        //n = 2, k =2 
        //here res = "01", then curNode = "1", image we have a fixed window n 
        //we here to use last char as first char
        String curNode = result.substring(result.length() - n + 1);

        //for every possible character, we go through since it can be dup
        //we go through 10, 11, we resue the last character,if visited all possible
        //combinations, then it is good
        for (char c = '0'; c < '0' + k; c++) {
            if (!visited.contains(curNode + c)) {
                result.append(c);
                visited.add(curNode + c);
                //note here n is total digits
                if (crackSafeFrom(result, n, k, total, visited)) {
                    return true;
                }
                result.deleteCharAt(result.length() - 1);
                visited.remove(curNode + c);
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        System.out.println(crackSafe2(2,2));
    }
}