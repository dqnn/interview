package hatecode._0001_0999;
public class _461HammingDistance {
/*
461. Hamming Distance
The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Given two integers x and y, calculate the Hamming distance.
*/
    public int hammingDistance(int x, int y) {
        int res = 0;
        
        for(int i = 0; i< 32; i++) {
           if (((x >> i) & 1) != ((y >> i) & 1)) res++;
        }
        return res;
    }
}