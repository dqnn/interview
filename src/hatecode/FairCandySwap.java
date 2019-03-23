package hatecode;
import java.util.*;
public class FairCandySwap {
/*
888. Fair Candy Swap
Alice and Bob have candy bars of different sizes: A[i] is the size of the i-th bar of candy that Alice has, and B[j] is the size of the j-th bar of candy that Bob has.

Since they are friends, they would like to exchange one candy bar each so that after the exchange, they both have the same total amount of candy.  (The total amount of candy a person has is the sum of the sizes of candy bars they have.)

Return an integer array ans where ans[0] is the size of the candy bar that Alice must exchange, and ans[1] is the size of the candy bar that Bob must exchange.

If there are multiple answers, you may return any one of them.  It is guaranteed an answer exists.

 

Example 1:

Input: A = [1,1], B = [2,2]
Output: [1,2]

*/
  
    //O(mn) brutal force.
    public int[] fairCandySwap_BrutalForce(int[] A, int[] B) {
        
        if (A == null || B == null) return null;
        
        int[] res = new int[2];
        int aSum = Arrays.stream(A).sum(), bSum = Arrays.stream(B).sum();
        for(int i = 0; i<A.length; i++) {
            for(int j = 0; j <B.length; j++) {
                if (aSum - A[i] + B[j] == bSum - B[j] +A[i]) {
                    res[0] = A[i];
                    res[1] = B[j];
                    return res;
                }
            }
        }
        return null;
    }
    //interview friendly, 
    //so diff = (sum(A) - sum(b))/2 means the diff, so if A want to be equals to B, so it needs to find two paris which their diff is  diff
    public int[] fairCandySwap(int[] A, int[] B) {
        
        if (A == null || B == null) return null;
        
       
        int aSum = Arrays.stream(A).sum(), bSum = Arrays.stream(B).sum();
        int diff = (aSum - bSum)/2;
        Set<Integer> set = new HashSet<>();
        Arrays.stream(A).forEach(e->set.add(e));
        for(int b : B) {
            if (set.contains(b + diff)) {
                return new int[]{b+diff, b}; 
            }
        }
        return null;
    }
}