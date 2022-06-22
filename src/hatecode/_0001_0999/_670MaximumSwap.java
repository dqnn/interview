package hatecode._0001_0999;
public class _670MaximumSwap {
/*
670. Maximum Swap
Given a non-negative integer, you could swap two digits 
at most once to get the maximum valued number. 
Return the maximum valued number you could get.

Example 1:
Input: 2736
Output: 7236
*/
    //interview friendly and a little tricky solution,
    //O(n)/O(1)
    
    //so only 1 swap, so we need to find the significant digit, and to see whether 
    //it is possible or not
    //so we use bucket to record last index of digit chs[i]
    //when we scan from left to right, inner loop is from 9 to j > chs[i]-'0' because we do not need to 
    //check the digit which are smaller, since left is more significant, so find once and return
    public int maximumSwap(int n) {
        char[] chs = Integer.toString(n).toCharArray();
        
        int[] buckets = new int[10];
        for(int i = 0; i <chs.length; i++) {
            buckets[chs[i] - '0'] = i;
        }
        //the loop is to say, scan chs from left to right, 
        //left is significant, for position i, we are able to find a bigger digit from 9 to 0
        //
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