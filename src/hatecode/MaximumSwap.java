package hatecode;
public class MaximumSwap {
/*
670. Maximum Swap
Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.

Example 1:
Input: 2736
Output: 7236
*/
    //interview friendly and a little tricky solution,
    //O(n)/O(1)
    
    //so only 1 swap, so we need to find the sigficant digit, and to see whether it is possible or not
    //
    public int maximumSwap(int n) {
        char[] chs = Integer.toString(n).toCharArray();
        
        int[] buckets = new int[10];
        for(int i = 0; i <chs.length; i++) {
            buckets[chs[i] - '0'] = i;
        }
        
        for(int i = 0; i< chs.length; i++) {
            for(int k = 9; k > chs[i] - '0'; k--) {
                if (buckets[k] > i) {
                    char tmp = chs[i];
                    chs[i] = chs[buckets[k]];
                    chs[buckets[k]] = tmp;
                    return Integer.valueOf(new String(chs));
                }
            }
        }
        return n;
    }
}