package hatecode;
public class MagicalString {
/*
481. Magical String
A magical string S consists of only '1' and '2' and obeys the following rules:

The string S is magical because concatenating the number of contiguous occurrences of characters '1' and '2' generates the string S itself.

The first few elements of string S is the following: S = "1221121221221121122……"

If we group the consecutive '1's and '2's in S, it will be:

1 22 11 2 1 22 1 22 11 2 11 22 ......

and the occurrences of '1's or '2's in each group are:

1 2	2 1 1 2 1 2 2 1 2 2 ......

You can see that the occurrence sequence above is the S itself.

Given an integer N as input, return the number of '1's in the first N number in the magical string S.

Note: N will not exceed 100,000.

Example 1:
Input: 6
Output: 3
*/
    public static int magicalString(int n) {
        if (n <= 0) return 0;
        if (n <= 3) return 1;
        
        int[] a = new int[n + 1];
        a[0] = 1; a[1] = 2; a[2] = 2;
        int head = 2, tail = 3, num = 1, result = 1;
        
        while (tail < n) {
            for (int i = 0; i < a[head]; i++) {
                a[tail] = num;
                if (num == 1 && tail < n) result++;
                tail++;
            }
            num = num ^ 3;
            head++;
        }
        
        return result;
    }
    
    //Lakoski sequence
    public int magicalString_Recursive(int n) {
        if (n == 0) return 0;
        int ones = 1;
        n -= 2;
        Lakoski lakoski = new Lakoski();
        while (n-- > 0)
            if (lakoski.next() == 1)
                ones++;
        return ones;
    }

    private class Lakoski {
        private int number = 2;
        private boolean again = false;
        private Lakoski source;
        int next() {
            if (again)
                again = false;
            else if (source == null)
                source = new Lakoski();
            else {
                number ^= 3;
                again = source.next() == 2;
            }
            return number;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(magicalString(10));
    }
}