package hatecode._0001_0999;
import java.util.*;
public class _969PancakeSorting {
    /*969. Pancake Sorting
     * Given an array A, we can perform a pancake flip: We choose some positive
     * integer k <= A.length, then reverse the order of the first k elements of A.
     * We want to perform zero or more pancake flips (doing them one after another
     * in succession) to sort the array A.
     * 
     * Return the k-values corresponding to a sequence of pancake flips that sort A.
     * Any valid answer that sorts the array within 10 * A.length flips will be
     * judged as correct.
     */
/*
 

Example 1:

Input: [3,2,4,1]
Output: [4,2,4,3]
Explanation: 
We perform 4 pancake flips, with k values 4, 2, 4, and 3.
Starting state: A = [3, 2, 4, 1]
After 1st flip (k=4): A = [1, 4, 2, 3]
After 2nd flip (k=2): A = [4, 1, 2, 3]
After 3rd flip (k=4): A = [3, 2, 1, 4]
After 4th flip (k=3): A = [1, 2, 3, 4], which is sorted. 
 */
    //thinking process, O(n^2)/O(n) n = A.length
    //the problem is to say if we have pancake sort,
    //change position from back to front, so return an array which each integer
    //in the array is the length of each pancake sort, after we done, 
    //the input is sorted
    //first max number, put first, then flip, then find second 
    //largest and so on
    
    //the key is how to use recursive to solve the problem, how to figure out 
    //the way to solve
    public List<Integer> pancakeSort(int[] A) {
            List<Integer> res = new ArrayList<>();
            if (A == null || A.length < 1) return res;
            helper(A, res, A.length);
            return res;
        }
        //sort len integers function
        private void helper(int[] A, List<Integer> list, int len) {
            if (len == 0) return;
            int maxIdx = len - 1;
            for(int i = 0; i<len; i++) {
                if (A[maxIdx] < A[i]) {
                    maxIdx = i;
                }
            }
            //exclude last one and sort len - 1 integers
            if (maxIdx == len - 1)  {
                helper(A, list, len -1);
            } else if (maxIdx == 0) {
                list.add(len);
                flip(A, len);
                helper(A, list, len -1);
            } else {
                list.add(maxIdx + 1);
                flip(A, maxIdx + 1);
                helper(A, list, len);
            }
        }
        //flip A from 0 to len-1, pancake sort
        private void flip(int[] A, int len) {
            for(int i = 0; i< len /2; i++) {
                int temp = A[i];
                A[i]= A[len - 1 - i];
                A[len- 1 -i] = temp;
            }
        }
        //O(n^2)/O(n)
        //this is leveraging the elements are 1---n, 
        //so for each element in A[i] != x which means correct max
        //number is not correct position, so we sort each once a time
        public List<Integer> pancakeSort_Best(int[] A) {
            List<Integer> res = new ArrayList<>();
            for (int x = A.length, i; x > 0; --x) {
                for (i = 0; A[i] != x; ++i);
                flip(A, i + 1);
                res.add(i + 1);
                flip(A, x);
                res.add(x);
            }
            return res;
        }
}

