package hatecode._0001_0999;

/**
 * Date : Sep, 2018
 * Description : 443. String Compression
 */
public class _443StringCompression {
    /**
     * Given an array of characters, compress it in-place.

     The length after compression must always be smaller than or equal 
     to the original array.

     Every element of the array should be a character (not int) of length 1.

     After you are done modifying the input array in-place, return the 
     new length of the array.


     Follow up:
     Could you solve it using only O(1) extra space?


     Example 1:
     Input:
     ["a","a","b","b","c","c","c"]

     Output:
     Return 6, and the first 6 
     characters of the input array should be: 
     ["a","2","b","2","c","3"]

     Explanation:
     "aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".
     Example 2:
     Input:
     ["a"]

     Output:
     Return 1, and the first 1 characters of the input 
     array should be: ["a"]

     Explanation:
     Nothing is replaced.
     Example 3:
     Input:
     ["a","b","b","b","b","b","b","b","b","b","b","b","b"]

     Output:
     Return 4, and the first 4 characters of the input array 
     should be: ["a","b","1","2"].

     Explanation:
     Since the character "a" does not repeat, it is not compressed. 
     "bbbbbbbbbbbb" is replaced by "b12".
     Notice each digit has it's own entry in the array.

     time : O(n)
     space : O(1)

     * @param A
     * @return
     */
    //thinking process:
    //the problem is to compress chars in array which char is 
    //duplicate, return the length of optimal compressed strings
    
    //we use a two while to locate where we can stop, note, this way, 
    //index will point to next char

    /*
     * we use two pointers, 
     * 
     * l point to position for next to replace the character, 
     * r point to position for next character which is different compared to previous one
     */
    public int compress(char[] A) {
        int l = 0, r = 0;
        while (r < A.length) {
            char cur = A[r];
            int count = 0;
            while (r < A.length && A[r] == cur) {
                r++;
                count++;
            }
            A[l++] = cur;
            //handle not 1 case, this is edge case
            if (count > 1) {
                for (char c : String.valueOf(count).toCharArray()) {
                    A[l++] = c;
                }
            }
        }
        return l;
    }
}
