package hatecode._1000_1999;

public class _1228MissingNumberInArithmeticProgression {
/*
 * 1228 - Missing Number In Arithmetic Progression
 * Given an array that represents elements of arithmetic progression in order. 
 * One element is missing in the progression, 
 * find the missing number.
 * 
 * examples：
 * Input: arr[]  = {2, 4, 8, 10, 12, 14}
        Output: 6
        
  Input: arr[]  = {1, 6, 11, 16, 21, 31};
        Output: 26
 */
    //thinking process: O(n)/O(1)
    
    //get its sub first, then try to figure out the one which is missing
    public static int missingNumber(int[] A) {
        if (A == null || A.length < 1) return -1;
        
        int n = A.length;
        int first = A[0], last = A[n - 1];
        int sub = (last - first)/ n;
        
        for(int i = 0; i<n - 1; i++) {
            int cur = A[i], next = A[i+1];
            if (cur + sub != next) return cur + sub;
        }
        return -1;
    }
    
    public static void main(String[] args) {
        System.out.println(missingNumber(new int[] {1,2,3,4,6}));
    }
}

