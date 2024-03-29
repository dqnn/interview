package hatecode._0001_0999;

/**
 * Created by professorX on 25/07/2017.
 */
public class _389FindtheDifference {
    /**
     * 389. Find the Difference
     * Given two strings s and t which consist of only lowercase letters.

     String t is generated by random shuffling string s and then add one more letter at a random position.

     Find the letter that was added in t
     Input:
     s = "abcd"
     t = "abcde"

     Output:
     e

     Explanation:
     'e' is the letter that was added.

     异或:  ^ (相同为零，不同为一)

     0 0 0
     0 1 1
     1 0 1
     1 1 0

     4 : 0100
     6 : 0110
         0010
     4 ：0100
         0110 ->6

     time: O(n);
     space: O(1);
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference(String s, String t) {
        char c = t.charAt(t.length() - 1);
        for (int i = 0; i < s.length(); i++) {
            c ^= s.charAt(i);
            c ^= t.charAt(i);
        }
        return c;
    }
    
    // we can use the sum to get the best solutions
    public char findTheDifference2(String s, String t) {
        if (s == null || t == null || s.length() >= t.length()) {
            return '$';
        }
        int sum = t.charAt(t.length() - 1);
        for(int i = 0; i < s.length(); i++) {
            sum += t.charAt(i);
            sum -= s.charAt(i);
        }

        return (char)sum;
    }
    
    public char findTheDifference3(String s, String t) {
        //the reduce method expects two arguments: an identity element, and a lambda expression
        // here just 0 + c ^ d. 
        //String.chars() will open a stream
        return (char) (s + t).chars().reduce(0, (c, d) -> c ^ d);
    }
}
