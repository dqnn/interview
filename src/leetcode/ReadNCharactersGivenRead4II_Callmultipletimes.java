package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ReadNCharactersGivenRead4II_Callmultipletimes
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 158. Read N Characters Given Read4 II - Call multiple times
 */
public class ReadNCharactersGivenRead4II_Callmultipletimes {
    /**
     The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 
if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that 
reads n characters from the file.

Note:
The read function may be called multiple times.

Example 1: 

Given buf = "abc"
read("abc", 1) // returns "a"
read("abc", 2); // returns "bc"
read("abc", 1); // returns ""
Example 2: 

Given buf = "abc"
read("abc", 4) // returns "abc"
read("abc", 1); // returns ""
     case :
     abcd efgh igk 11
     case 1 : n = 8 first time read 4 next count == 4 index == n
     case 2 : n = 7 first time read 4 next count == 3 index == n

     abcd efgh igk 11
     n = 2 count = 4  buf[ab] pointer = 2 temp[abcd]
     n = 3 index = 3  buf[cd] pointer = 0
            temp[efgh] count = 4 buf[cde] pointer = 1


     time : O(n);
     space : O(1);


     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */

    private int count = 0;
    private int pointer = 0;
    private char[] temp = new char[4];

    //  read from buf with max n chars
    // firstly understand read4, this function has an internal pointer to note where we are,
    // so evert time it will return 1: how much it actually read 2. result in buf
    
    // the problem is to say, we want to call read multiple times, and we want n is 
    //how many chars we want to read, but we need to remember last time what we have 
    //read, so we don't want to duplicate chars. 
    
    //so buf should contains chars read from last time with end n ideally, but 
    // read4 cannot read n, buf should contain what it is, and we return how 
    // many we read
    // suppose n % 4 != 0
    //use case 1: read('abc', 1)--> 'a', read('abc', 2)-> 'bc' read3 one time 
    // can return 4 chars but we only first one, next time we has to read another 2
    //'a',so we need temp char[4] to store current read since read4 already move to 
    // next 4 char position, 
    // suppose n % 4 == 0,
    // in this case, the same as last one, we don't need to keep a copy from last 
    //read
    public int read(char[] buf, int n) {
        int index = 0;
        // we can change while(true)
        while (index < n) {
            // how much we can read from buf 
            if (pointer == 0) {
                count = read4(temp);
            }
            if (count == 0) break;
            //we need this while here because not sure pointer or index which 
            //reach to its limit first
            while (index < n && pointer < count) {
                buf[index++] = temp[pointer++];
            }
            //this means we rewind to start of next read4
            if (pointer == count) {
                pointer = 0;
            }
        }
        return index;
    }

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
    
    
    
    int pointer2 = 0;
    char[] temp2 = new char[4];
    int cnt = 0;
    public int read2(char[] buf, int n) {
        if (buf == null || buf.length < 1 || n < 1) {
            return 0;
        }

        int index = 0;
        while(true) {
            if (pointer2 == 0) {
                cnt = read4(temp2);
            }
            if (cnt == 0) break;
            while(index < n && pointer2 < cnt) {
                buf[index++] = temp2[pointer2++];
            }
            if(pointer == cnt) pointer2 = 0;
            if (index == n) {
                return index;
            }
        }
        return index;
    }
}
