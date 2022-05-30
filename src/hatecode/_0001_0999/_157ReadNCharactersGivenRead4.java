package hatecode._0001_0999;

import org.junit.Test;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ReadNCharactersGivenRead4
 * Date : Sep, 2018
 * Description : 157. Read N Characters Given Read4
 * Tags: templates for two dimension of variables, and index++ as pointer in 
 *array
 */
public class _157ReadNCharactersGivenRead4 {
    /**
The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, 
it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) 
that reads n characters from the file.

Example 1:

Input: buf = "abc", n = 4
Output: "abc"
Explanation: The actual number of characters read is 3, which is "abc".
Example 2:

Input: buf = "abcde", n = 5 
Output: "abcde"
Note:
The read function will only be called once for each test case.

     case :
     abcd efgh igk 11
     case 1 : n = 8 first time read 4 next count == 4 index == n
     case 2 : n = 7 first time read 4 next count == 3 index == n

     case :
     abc 3
     case 1 : n = 4 count = 3 count < 4

     time : O(n);
     space : O(1);


     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    
    //interview friendly.
    public int read(char[] buf, int n) {
        // storage to store the char from  read4
        char[] temp = new char[4];
        int index = 0;
        while (true) {
            int count = read4(temp);
            //how much we can copy from temp to buf, 
            //since index++ always, so index already means how many chars copied
            count = Math.min(count, n - index);
            //copy from temp to buf, 
            for (int i = 0; i < count; i++) {
                buf[index++] = temp[i];
            }
            // means either full or there is nore more data to read so we just return
            if (index == n || count < 4) return index;
        }
    }
    
    //self writing,
    public int read_self(char[] buf, int n) {
        int idx = 0;
        char[] temp = new char[4];
        int cur = read4(temp);
        while (cur > 0 && idx + cur <= n) {
            for(int i = 0; i<cur; i++){
                buf[idx++] = temp[i];
            }
            cur = read4(temp);
        }
        
        if( cur == 0) return idx;
        
        int left = Math.min(cur, n - idx);
        
        if (left > 0) {
            for(int i = 0; i < left; i++) {
                buf[idx++] = temp[i];
            }
        }
        return idx;
        
    }

    /**
     * abcdefghijk
     * char[] temp = new char[4]; temp : ijk 3
     *
     * @param temp
     * @return
     */

    //辅助函数，正常不是这么写
    public int read4(char[] temp) {
        char[] res = new char[10];
        char[] ret = new char[4];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            if (index < 4){
                ret[index++] = temp[i];
            }
        }
        return index;
    }
    
    // this way is more easier to understand
    public int read2(char[] buf, int n) {
        if (buf == null || buf.length < 1 || n < 1) {
            return 0;
        }
        
        char[] temp = new char[4];
        int index = 0;
        int pointer = 0, cnt = 0;
        while(index < n) {
            if (pointer == 0) {
                cnt = read4(temp);
            }
            while(index < n && pointer < cnt) {
                buf[index++] = temp[pointer++];
            }
            if (cnt < 4) {
                break;
            }
            if (pointer == cnt) {
                pointer = 0;
            }
        }
        return index;
    }
//        File 
// n    (x,4]   (5, +x)
//we totally have 4 use cases, n range and file size range, the boundary is 4
    
   @Test
   public void testRead4NCharsNlessthan4AndBufHasLessThan4Chars() {
       
   }
   @Test
   public void testRead4NCharsNlessThan4AndBufHasMoreThan4Chars() {
       
   }
   
   @Test
   public void testRead4NCharsNMoreThan4AndBufHasLessThan4Chars() {
       
   }
   @Test
   public void testRead4NCharsNMoreThan4AndBufHasMoreThan4Chars() {
       
   }
   
}
