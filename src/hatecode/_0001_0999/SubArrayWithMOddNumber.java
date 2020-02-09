package hatecode._0001_0999;

// Java program to count the 
// number of subarrays with 
// m odd numbers 
import java.util.*;

 public class SubArrayWithMOddNumber {
/*
Given an array of n elements and an integer m, we need to write a program to find the number of 
contiguous subarrays in the array which contains exactly m odd numbers.

Input : arr = {2, 5, 6, 9},  m = 2 
Output : 2
Explanation: subarrays are [2, 5, 6, 9] 
and [5, 6, 9]

Input : arr = {2, 2, 5, 6, 9, 2, 11},  m = 2
Output : 8
Explanation: subarrays are [2, 2, 5, 6, 9], 
[2, 5, 6, 9], [5, 6, 9], [2, 2, 5, 6, 9, 2], 
[2, 5, 6, 9, 2], [5, 6, 9, 2], [6, 9, 2, 11] 
and [9, 2, 11]
 */
    // fucntion that returns the count of
    // subarrays with m odd numbers
    public static int countSubarrays(int a[], int n, int m) {
        int count = 0;
        //here stores the how many numbers before encounter an odd
        //example here [2, 2, 5, 6, 9, 2, 11], prefix = [3, 2, 2, 0, 0, 0, 0]
        //which means first odd number it has 3 elements, 2,2,5, include itself
        // prefix[1] = 2 means second odd between 1 and 2 there are 2 numbers include 9, 
        
        //so when we get the answer how many subarrays, then we can know how many distinct 
        //number 
        int prefix[] = new int[n];
        int odd = 0;

        // traverse in the array
        for (int i = 0; i < n; i++) {
            prefix[odd]++;

            // if array element is odd
            if ((a[i] & 1) == 1)
                odd++;

            // when number of odd
            // elements >= M
            if (odd >= m)
                count += prefix[odd - m];
        }
        System.out.println(Arrays.toString(prefix));
        return count;
    }

    // Driver code
    public static void main(String[] args) {
        int a[] = {
                2, 2, 5, 6, 9, 2, 11 };
        int n = a.length;
        int m = 2;
        
        System.out.println(countSubarrays(a, n, m));
    }
}
