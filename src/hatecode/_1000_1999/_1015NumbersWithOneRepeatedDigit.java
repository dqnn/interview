package hatecode._1000_1999;
import java.util.*;
public class _1015NumbersWithOneRepeatedDigit {
/*
 * 1015. Numbers With 1 Repeated Digit
Given a positive integer N, return the number of positive integers less than or equal to N that have at least 1 repeated digit.

Example 1:

Input: 20
Output: 1
    
Count res the Number Without Repeated Digit
Then the number with repeated digits = N - res

Similar as
788. Rotated Digits
902. Numbers At Most N Given Digit Set

##Explanation:

Transform N + 1 to arrayList
Count the number with digits < n
Count the number with same prefix
Time Complexity:
the number of permutations A(m,n) is O(1)
We count digit by digit, so it's O(logN)

*/
    //
    public static int numDupDigitsAtMostN(int N) {
        // Transform N + 1 to arrayList
        List<Integer> list = new ArrayList<Integer>();
        //if N = 1000, then list=[1,0,0,1]
        for (int x = N + 1; x > 0; x /= 10) list.add(0, x % 10);

        // Count the number with digits < N
        int res = 0, n = list.size();
        for (int i = 1; i < n; ++i) 
            res += 9 * A(9, i - 1);

        // Count the number with same prefix
        HashSet<Integer> seen = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            for (int j = i > 0 ? 0 : 1; j < list.get(i); ++j)
                if (!seen.contains(j)) res += A(9 - i, n - i - 1);

            if (seen.contains(list.get(i))) break;
            seen.add(list.get(i));
        }
        return N - res;
    }


    public static int A(int m, int n) {
        return n == 0 ? 1 : A(m, n - 1) * (m - n + 1);
    }
    
    public  static void main(String[] args) {
        System.out.println(numDupDigitsAtMostN(2500));
    }
}