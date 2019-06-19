package hatecode;
import java.util.*;
public class FindTheClosestPalindrome {
/*
564. Find the Closest Palindrome
Given an integer n, find the closest integer (not including itself), which is a palindrome.

The 'closest' is defined as absolute difference minimized between two integers.

Example 1:
Input: "123"
Output: "121"
*/

    //shorter solution, 
    //
    public String nearestPalindromic_Interview(String n) {
        int order = (int) Math.pow(10, n.length() / 2);
        Long ans = Long.valueOf(new String(n));
        Long noChange = mirror(ans);
        Long larger = mirror((ans / order) * order + order + 1);
        Long smaller = mirror((ans / order) * order - 1);
        if (noChange > ans) {
            larger = (long) Math.min(noChange, larger);
        } else if (noChange < ans) {
            smaller = (long) Math.max(noChange, smaller);
        }
        return String.valueOf(ans - smaller <= larger - ans ? smaller : larger);
    }

    Long mirror(Long ans) {
        char[] a = String.valueOf(ans).toCharArray();
        int i = 0;
        int j = a.length - 1;
        while (i < j) {
            a[j--] = a[i++];
        }
        return Long.valueOf(new String(a));
    }
    
    
    
    public String nearestPalindromic(String n) {
        Long number = Long.parseLong(n);
        Long big = findHigherPalindrome(number + 1);
        Long small = findLowerPalindrome(number - 1);
        return Math.abs(number - small) > Math.abs(big - number) ? String.valueOf(big) : String.valueOf(small);
    }
    
    public Long findHigherPalindrome(Long limit) {
        String n = Long.toString(limit);
        char[] s = n.toCharArray(); // limit
        int m = s.length;
        char[] t = Arrays.copyOf(s, m); // target
        //copy first half to next, so it is palidrom
        for (int i = 0; i < m / 2; i++) t[m - 1 - i] = t[i];

        
        for (int i = 0; i < m; i++) {
            //already find it, higher band
            if (s[i] < t[i]) return Long.parseLong(String.valueOf(t)); 
            else if (s[i] > t[i]) { 
                //for first half, we are trying to increase 1,
                for (int j = (m - 1) / 2; j >= 0; j--) {
                    if (++t[j] > '9') t[j] = '0';
                    else break;
                }
                // make it palindrome again
                for (int k = 0; k < m / 2; k++) t[m - 1 - k] = t[k];
                return Long.parseLong(String.valueOf(t)); 
            }
        }
        return Long.parseLong(String.valueOf(t));    
    }
    
    public Long findLowerPalindrome(Long limit) {
        String n = Long.toString(limit);
        char[] s = n.toCharArray();
        int m = s.length;
        char[] t = Arrays.copyOf(s, m);
        for (int i = 0; i < m / 2; i++) t[m - 1 - i] = t[i];
        
        for (int i = 0; i < m; i++) {
            if (s[i] > t[i])  return Long.parseLong(String.valueOf(t)); 
            else if (s[i] < t[i]) {
                for (int j = (m - 1) / 2; j >= 0; j--) {
                    if (--t[j] < '0')  t[j] = '9';
                    else break;
                }
                if (t[0] == '0') {
                    char[] a = new char[m - 1];
                    Arrays.fill(a, '9');
                    return Long.parseLong(String.valueOf(a)); 
                }
                // make it palindrome again
                for (int k = 0; k < m / 2; k++) t[m - 1 - k] = t[k];
                return Long.parseLong(String.valueOf(t)); 
            }
        }
         return Long.parseLong(String.valueOf(t));  
    }
    
    
    //we copy first half and mirror to right
    public String mirroring(String s) {
        String x = s.substring(0, (s.length()) / 2);
        return x + (s.length() % 2 == 1 ? s.charAt(s.length() / 2) : "") + new StringBuilder(x).reverse().toString();
    }
    
    //this is solution copy from LC, just for reference, pure math
    public String nearestPalindromic_reference(String n) {
        if (n.equals("1")) return "0";

        String a = mirroring(n);
        long diff1 = Long.MAX_VALUE;
        diff1 = Math.abs(Long.parseLong(n) - Long.parseLong(a));
        //we want it is palidrom but not n itself,so it is max
        if (diff1 == 0)  diff1 = Long.MAX_VALUE;

        StringBuilder s = new StringBuilder(n);
        int i = (s.length() - 1) / 2;
        //replace char At i, SB only have one replace method
        while (i >= 0 && s.charAt(i) == '0') {
            s.replace(i, i + 1, "9");
            i--;
        }
        if (i == 0 && s.charAt(i) == '1') {
            s.delete(0, 1);
            int mid = (s.length() - 1) / 2;
            s.replace(mid, mid + 1, "9");
        } else s.replace(i, i + 1, "" + (char)(s.charAt(i) - 1));
        
        
        String b = mirroring(s.toString());
        long diff2 = Math.abs(Long.parseLong(n) - Long.parseLong(b));


        s = new StringBuilder(n);
        i = (s.length() - 1) / 2;
        while (i >= 0 && s.charAt(i) == '9') {
            s.replace(i, i + 1, "0");
            i--;
        }
        
        if (i < 0) s.insert(0, "1");
        else s.replace(i, i + 1, "" + (char)(s.charAt(i) + 1));
        
        String c = mirroring(s.toString());
        long diff3 = Math.abs(Long.parseLong(n) - Long.parseLong(c));

        if (diff2 <= diff1 && diff2 <= diff3) return b;
        if (diff1 <= diff3 && diff1 <= diff2) return a;
        else return c;
    }
}