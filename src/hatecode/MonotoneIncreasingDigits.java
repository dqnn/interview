package hatecode;
public class MonotoneIncreasingDigits {
/*
738. Monotone Increasing Digits
Given a non-negative integer N, find the largest number that is less than or equal to N with monotone increasing digits.

(Recall that an integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.)

Example 1:
Input: N = 10
Output: 9
*/
    
    //the solution is based on 
    public int monotoneIncreasingDigits(int N) {
        if (N < 0) return -1;
        if (N < 10) return N;
        
        char[] chs = String.valueOf(N).toCharArray();
        int mark = chs.length;
        //332, so mark = 1, chs[1] = 2;
        //this is to find the decrease sequence and mark them they have to self decresed by 1,every digit
        for(int i = chs.length - 1; i> 0; i--) {
            if (chs[i] < chs[i-1]) {
                mark = i - 1;
                chs[i-1] --;
            }
        }
        //we do not change first digit since already decreased, change all rest to 9
        for(int i = mark+1;i<chs.length;i++){
            chs[i] = '9';
        }
        return Integer.valueOf(new String(chs));
    }
}