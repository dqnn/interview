package leetcode;

class DIStringMatch {
/*
 * 942 DI String Match
Given a string S that only contains "I" (increase) or "D" (decrease), let N = S.length.

Return any permutation A of [0, 1, ..., N] such that for all i = 0, ..., N-1:

If S[i] == "I", then A[i] < A[i+1]
If S[i] == "D", then A[i] > A[i+1]
 

Example 1:

Input: "IDID"
Output: [0,4,1,3,2]
Example 2:

Input: "III"
Output: [0,1,2,3]
Example 3:

Input: "DDI"
Output: [3,2,0,1]
 

Note:

1 <= S.length <= 10000
S only contains characters "I" or "D".

 */
    //thinking process:
    
    //the problem is to from String->int array, to say if "D" then res[i] > res[i+1] else 
    // "I" res[i] < res[i+1}
    
    //so since the number are fixed from 0 to N, so we need to figure out a way how 
    //to management the relationship between adjacent numbers, IDID->0,4,1,3,2 from this example
    //we can see first I must be 0 and first D must be N, each will decrease no matter res[i] or not
    
    //
    public int[] diStringMatch(String S) {
        if (S == null || S.length() < 1) {
            return null;
        }
        int N = S.length();
        int[] res = new int[N + 1];
        int curI = 0, curD = N;
        //IDID->0, 4, 1, 3,
        for(int i = 0; i < N; i++) {
            if ('I' == S.charAt(i)) {
                res[i] = curI;
                curI++;
            } else if ('D' == S.charAt(i)) {
                res[i] = curD;
                curD--;
            }
        }
        if (S.charAt(N - 1) == 'I') {
           res[N] = curI; 
        } else {
           res[N] = curD;
        }
        return res;
    }
}