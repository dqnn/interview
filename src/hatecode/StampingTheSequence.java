package hatecode;
import java.util.*;
class StampingTheSequence {
/*
936. Stamping The Sequence
For example, if the sequence is "ababc", and the stamp is "abc", then we could return the answer [0, 2], corresponding to the moves "?????" -> "abc??" -> "ababc".

Also, if the sequence is possible to stamp, it is guaranteed it is possible to stamp within 10 * target.length moves.  Any answers specifying more than this number of moves will not be accepted.

 

Example 1:

Input: stamp = "abc", target = "ababc"
Output: [0,2]
([1,0,2] would also be accepted as an answer, as well as some other answers.)

 */
    //thinking process: 
    //given a target string "ababc" and stamp string "abc", so suppose there is same length string as target, called it pre-target = "****", each time we can copy "abc" to overwrite the pre-target, so what's min number of ops to pre-target = target,return list of index which are needed for the min ops
    
    //TODO brute force solution is ....
    //TODO: DFS:
    //optimal one： it is not easy to think through how we can from "*****"-> "ababc" by "abc", but if we can try different pos and overrite smaller part of target which is not equals to target, this is the direction
    
    //we thinking back: the minimal step 
    
    //O(N(N-M))/O(N), N is target len, M is stamp len
    public int[] movesToStamp(String stamp, String target) {
        char[] aim = new char[target.length()];
        Arrays.fill(aim, '*'); 
        char[] curr = target.toCharArray();
        List<Integer> retList = new ArrayList<>();
        while (!Arrays.equals(aim, curr)) {
            int position = replace(curr, stamp);
            if (position < 0) return new int[0]; // impossible to traverse into "***...***"
            retList.add(position);
        }
        
        // Reverse the result and return 
        Collections.reverse(retList);
        return retList.stream().mapToInt(Integer::intValue).toArray();
    }
    //for each 3 chars in target, if we can find any character matches to target, suppose i in target starts i, 
    //then we change [i, i+3] to * 
    private int replace(char[] target, String stamp) {
        for (int i = 0; i <= target.length - stamp.length(); i++) {
            int k = i, j = 0;
            boolean match = false; 
            // check if target[k:stamp.length()] equals to stamp
            // note: '*' means that position can be matched to any character
            while (k < target.length && j < stamp.length() && (target[k] == '*' || target[k] == stamp.charAt(j))) {
                // to escape the situation of target[k:stamp.length()] = "***...**"
                //System.out.println(Arrays.toString(target) + "---" + stamp.charAt(j));
                if (target[k] != '*') match = true;
                j++;
                k++;
            }
            //System.out.println(i + "---" + j);
            if (j == stamp.length() && match) {
                for (k = i; k < i + stamp.length(); k++)
                    target[k] = '*';
                return i;
            }
        }
        // cannot find any valid position to traverse
        return -1;
    }
}