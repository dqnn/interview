package hatecode;
public class _972EqualRationalNumbers {
/*
972. Equal Rational Numbers
Input: S = "0.(52)", T = "0.5(25)"
Output: true
Explanation:
Because "0.(52)" represents 0.52525252..., and "0.5(25)" represents 0.52525252525..... , the strings represent the same number.

*/
    //thinking process: O(1)/O(1)
    
    //0.(12)->12/100 + 12/10^4 + 12/10^6 + 12/10^8 .....
    //0.(12) = 12 * r/(1-r)
    //another solution is to turn this number to 1/3, like this, so we can
    //compare its upper and lower
    public boolean isRationalEqual(String S, String T) {
        Fraction f1 = convert(S);
        Fraction f2 = convert(T);
        return f1.n == f2.n && f1.d == f2.d;
    }

    public Fraction convert(String S) {
        int state = 0; //whole, decimal, repeating
        Fraction ans = new Fraction(0, 1);
        int decimal_size = 0;

        for (String part: S.split("[.()]")) {
            state++;
            if (part.isEmpty()) continue;
            long x = Long.valueOf(part);
            int sz = part.length();
            //digits before .
            if (state == 1) { // whole
                 ans.iadd(new Fraction(x, 1));
            //digits beween . (
            } else if (state == 2) { // decimal
                 ans.iadd(new Fraction(x, (long) Math.pow(10, sz)));
                 decimal_size = sz;
            //digits between ( )
            } else { // repeating
                 long denom = (long) Math.pow(10, decimal_size);
                 denom *= (long) (Math.pow(10, sz) - 1);
                 ans.iadd(new Fraction(x, denom));
            }
        }
        return ans;
    }
}
//
class Fraction {
    long n, d;
    Fraction(long n, long d) {
        long g = gcd(n, d);
        this.n = n / g;
        this.d = d / g;
    }

    public void iadd(Fraction other) {
        long numerator = this.n * other.d + this.d * other.n;
        long denominator = this.d * other.d;
        long g = Fraction.gcd(numerator, denominator);
        this.n = numerator / g;
        this.d = denominator / g;
    }

    static long gcd(long x, long y) {
        return x != 0 ? gcd(y % x, x) : y;
    }
}