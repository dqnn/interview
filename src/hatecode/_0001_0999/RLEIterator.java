package hatecode._0001_0999;
import java.util.*;
class RLEIterator {
/*
900. RLE Iterator
Write an iterator that iterates through a run-length encoded sequence.

The iterator is initialized by RLEIterator(int[] A), where A is a run-length encoding of some sequence.  More specifically, for all even i, A[i] tells us the number of times that the non-negative integer value A[i+1] is repeated in the sequence.

The iterator supports one function: next(int n), which exhausts the next n elements (n >= 1) and returns the last element exhausted in this way.  If there is no element left to exhaust, next returns -1 instead.

For example, we start with A = [3,8,0,9,2,5], which is a run-length encoding of the sequence [8,8,8,5,5].  This is because the sequence can be read as "three eights, zero nines, two fives".

 

Example 1:

Input: ["RLEIterator","next","next","next","next"], [[[3,8,0,9,2,5]],[2],[1],[1],[2]]
Output: [null,8,8,5,-1]
Explanation: 
RLEIterator is initialized with RLEIterator([3,8,0,9,2,5]).
This maps to the sequence [8,8,8,5,5].
RLEIterator.next is then called 4 times:
*/
    int[] a;
    int cur;
    public RLEIterator(int[] A) {
        this.a= A;
        this.cur = 0;
    }
    
    public int next(int n) {
        if (n < 0) return -1;
        int p = cur;
        for(;p < a.length; p += 2) {
            //System.out.println(Arrays.toString(a));
            if (a[p] == 0) continue;
            if (a[p] < n) {
                n = n - a[p];
                a[p] = 0;
            } else {
                a[p] -= n;
                cur = p;
                return a[p + 1];
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] in = {811,903,310,730,899,684,472,100,434,611};
        RLEIterator it = new RLEIterator(in);
        int[] next = {358,345,154,265,73,220,138,4,170,88};
        for(int temp: next) {
            System.out.println(it.next(temp));
        }
    }
}