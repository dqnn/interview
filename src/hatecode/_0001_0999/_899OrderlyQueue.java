package hatecode._0001_0999;
import java.util.*;
public class _899OrderlyQueue {
/*
899. Orderly Queue
A string S of lowercase letters is given.  Then, we may make any number of moves.

In each move, we choose one of the first K letters (starting from the left), remove it, and place it at the end of the string.

Return the lexicographically smallest string we could have after any number of moves.

 

Example 1:

Input: S = "cba", K = 1
Output: "acb"


Intuition:
First, this is string rotation.
12345 -> 23451 -> 34512 -> 45123 -> 51234
I use number instead of letters to make it clear.

If K == 1, we can only rotate the whole string.
There are S.length different states and
we return the lexicographically smallest string.

If K > 1, it means we can:

rotate the whole string,
rotate the whole string except the first letter.
012345 -> 023451 -> 034512 -> 045123 -> 051234
We can rotate i+1th big letter to the start (method 1),
then rotate ith big letter to the end (method 2).
2XXX01 -> XXX012

In this way, we can bubble sort the whole string lexicographically.
So just return sorted string.
*/
    public String orderlyQueue(String s, int k) {
        if (k <= 0) return s;
        if (k > 1) {
            char[] chs = s.toCharArray();
            Arrays.sort(chs);
            return new String(chs);
        }
        String res = s;
        for(int i = 0; i< s.length(); i++) {
            String tmp = s.substring(i) + s.substring(0, i);
            if (tmp.compareTo(res) < 0) res = tmp;
        }
        return res;
    }
}