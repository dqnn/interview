package hatecode._0001_0999;
public class PushDominoes {
/*
838. Push Dominoes
There are N dominoes in a line, and we place each domino vertically upright.

In the beginning, we simultaneously push some of the dominoes either to the left or to the right.
After each second, each domino that is falling to the left pushes the adjacent domino on the left.

Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.

When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.

For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.

Given a string "S" representing the initial state. S[i] = 'L', if the i-th domino has been pushed to the left; S[i] = 'R', if the i-th domino has been pushed to the right; S[i] = '.', if the i-th domino has not been pushed.

Return a string representing the final state. 

Example 1:

Input: ".L.R...LR..L.."
Output: "LL.RR.LLRRLL.."
*/
    //O(n)/O(1)
    public String pushDominoes(String d) {
/*
to solve 4 situations:
'R......R' => 'RRRRRRRR'
'R......L' => 'RRRRLLLL' or 'RRRR.LLLL'
'L......R' => 'L......R'
'L......L' => 'LLLLLLLL'

And d = 'L' + d + 'R' won't change the result.
*/
        d = "L" + d + "R";
        StringBuilder sb = new StringBuilder(); 
        
        for(int i = 0, j=1; j <d.length(); j++) {
            //note j point to char != '.'
            if (d.charAt(j) == '.') continue;
            //j -i - 1 means except i and j, how many letters are there
            int mid = j - i - 1;
            //keep same count of letters
            if (i > 0) sb.append(d.charAt(i));
            
            if (d.charAt(i) == d.charAt(j)) {
                for(int k =0; k< mid; k++) sb.append(d.charAt(i));
            //this is special case, chars between L and R they must be all .
            } else if (d.charAt(i) == 'L' && d.charAt(j) == 'R') {
                //or  for(int k =0; k< mid; k++) sb.append('.'); will be accepted
                for(int k =0; k< mid; k++) sb.append(d.charAt(i + k + 1));
            } else {
                for(int k =0; k < mid/2; k++) sb.append('R');
                if (mid % 2 == 1) sb.append('.');
                for(int k =0; k < mid/2; k++) sb.append('L');
            }
            i = j;
        }
        return sb.toString();
    }
    
    public String pushDominoes_TWOP(String S) {
        char[] A = ("L" + S + "R").toCharArray();
        int N = A.length;        
        // sliding window [start, end] in format of [L|R....L|R]
        for (int start = 0, end = 0; end < N; end++) {
            char curCh = A[end];
            if (curCh != '.') {
                convert(A, start, end); // convert current window to res
                start = end;            // adjust start
            }
        }
        return String.valueOf(A).substring(1, N - 1);
    } 
    
    private void convert(char[] A, int start, int end) {
        int N = end - start + 1;
        char sCh = A[start], eCh = A[end];
        if (sCh == eCh) {
            for (int i = 0; i < N; i++) A[start + i] = sCh;
        } else if (sCh == 'R' && eCh == 'L') {
            for (int i = 0; i < N; i++) A[start + i] = (i < N - 1 - i) ? 'R' : (i > N - 1 - i) ? 'L' : '.';
        } // else if (sCh == 'L' && eCh == 'R') no need to change content
    }
}