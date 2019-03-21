package hatecode;
public class SwapAdjacentInLRString {
/*
777. Swap Adjacent in LR String
In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", 
a move consists of either replacing one occurrence of "XL" with "LX", or 
replacing one occurrence of "RX" with "XR". Given the starting string start and 
the ending string end, return True if and only if there exists a sequence of moves 
to transform one string to the other.

Example:

Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
Output: True
Explanation:
We can transform start to end following these steps:
RXXLRXRXL ->
XRXLRXRXL ->
XRLXRXRXL ->
XRLXXRRXL ->
XRLXXRRLX
*/
    //thinking process: the pattern is L cannot go beyond end's L, 
    //
    public boolean canTransform2(String start, String end) {
        //thinking L face left, R face right, X as empty, our op does not 
        //change the L R count and order, 
        if (!start.replace("X", "").equals(end.replace("X", "")))
            return false;

        int t = 0;
        //L cannot go beyond end, L can only go left
        for (int i = 0; i < start.length(); ++i)
            if (start.charAt(i) == 'L') {
                while (end.charAt(t) != 'L') t++;
                if (i < t++) return false;
            }

        t = 0;
        //R can only go back, if we found R
        for (int i = 0; i < start.length(); ++i)
            if (start.charAt(i) == 'R') {
                while (end.charAt(t) != 'R') t++;
                if (i > t++) return false;
            }

        return true;
    }
    //Two Pointers, O(n)/O(n)
    public boolean canTransform(String start, String end) {
     // Add this one to make sure their length is same.
        if (start.length() != end.length()) return false; 
        if (!start.replace("X", "").equals(end.replace("X", ""))) return false;
        int p1 = 0, p2 = 0;
        char[] ch1 = start.toCharArray();
        char[] ch2 = end.toCharArray();
        while (p1 < start.length()) {
            while (p1 < ch1.length && ch1[p1] == 'X')
                p1++;
            while (p2 < ch2.length && ch2[p2] == 'X')
                p2++;
            if (p1 == ch1.length || p2 == ch2.length)
                return true; // if one reach the end, the other one must reach the end too, since we have
                             // already check their order first.
            if (ch1[p1] == 'R' && p1 > p2)
                return false;
            if (ch1[p1] == 'L' && p1 < p2)
                return false;
            p1++;
            p2++;
        }
        return true;
    }
}