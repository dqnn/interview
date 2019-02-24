package hatecode;
public class NumberComplement {
/*
476. Number Complement
Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.
Input: 5
Output: 2
*/
    public int findComplement(int num) {
         // this is to get highest bit position and change all to 1 after it,
         //like 3-011, then it will be 100->1000->0111
         int mask = (Integer.highestOneBit(num) << 1) - 1;
         num = ~ num;
         return num & mask;
    }
}