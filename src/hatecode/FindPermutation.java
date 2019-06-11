package hatecode;
import java.util.*;
public class FindPermutation {
/*
484. Find Permutation
By now, you are given a secret signature consisting of character 'D' and 'I'. 'D' represents a decreasing relationship between two numbers, 'I' represents an increasing relationship between two numbers. And our secret signature was constructed by a special integer array, which contains uniquely all the different number from 1 to n (n is the length of the secret signature plus 1). For example, the secret signature "DI" can be constructed by array [2,1,3] or [3,1,2], but won't be constructed by array [3,2,4] or [2,1,3,4], which are both illegal constructing special string that can't represent the "DI" secret signature.

On the other hand, now your job is to find the lexicographically smallest permutation of [1, 2, ... n] could refer to the given secret signature in the input.

Example 1:
Input: "I"
Output: [1,2]

Input: "DI"
Output: [2,1,3]
*/
    //[2,1,3]
    //up =[1, 1, 2],down =[1,2, 2]
    // a little bit tricky
    public int[] findPermutation(String s) {
        if (s == null || s.length() < 1) return new int[0];
        
        int n = s.length();
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i<=n;i++) {
            if (i == n || s.charAt(i) == 'I') {
                for(int j = i+1, temp = res.size(); j>temp;j--) {
                    res.add(j);
                }
            }
        }
        return res.stream().mapToInt(x->x).toArray();
    }
    //interview friendly solution,  O(n)/O(n)
    //so given a string like "IID", we need to output a array,which matches the string pattern
    
/*
 *      IIIIIIII    IDDIDDDI
 *      123456789   143287659
 *  first is the templates, if we change any I ->D, we just reverse the adjacent
 *  
 */
    public int[] findPermutation_Stack(String s) {
     // we append an "I" at the end for the last digit
        s = s + "I"; 
        int[] res = new int[s.length()];
        Stack <Integer> stack = new Stack<>();
        int cnt = 0; // indicating the next index in res to fill
        for (int i = 1; i <= s.length(); i++) {
            if (s.charAt(i - 1) == 'I') {
                res[cnt++] = i;
                while (!stack.empty()) res[cnt++] = stack.pop();
            } else {
                stack.push(i);
            }
        }
        return res;
    }
    
    //most naive solution, but working and easy to write
    //IDDIDDDI, first res=1,2,3,4,5,6,7,8,9
    //then we search for D, first s=1,e=3, so swap(2,4)
    public static int[] findPermutation_naive(String s) {
        int n = s.length();
        int[] res = new int[n + 1];
        for(int i = 0 ; i < res.length ; i++) {
            res[i] = i + 1;
        }
        //IDDIDDDI
        for(int i = 0 ; i < n ; i++) {
            if(s.charAt(i) == 'D') {
                int j = i;
                while(j < n && s.charAt(j) == 'D') j++;
                //note j is not D, but I, that's why i = j - 1
                reverse(res, i, j);
                i = j - 1;
            }
        }
        return res;
    }
    private static void reverse(int[] arr, int s, int e) {
        int l = s, r = e;
        while(l < r) {
            swap(arr, l++, r--);
        }
    }
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findPermutation_naive("IDDIDDDI")));
    }

}