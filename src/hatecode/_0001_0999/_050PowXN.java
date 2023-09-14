package hatecode._0001_0999;


public class _050PowXN {
    /**
     * 50. Pow(x, n)
     * Implement pow(x, n).


     eg. 2^2 = 2^1 * 2^1 = (2^0 * 2^0 * 2) * (2^0 * 2^0 * 2) = (1 * 1 * 2) * (1 * 1 * 2) = 4

     eg. 2^3 = 2^1 * 2^1 * 2 = (2^0 * 2^0 * 2) * (2^0 * 2^0 * 2) * 2 = (1 * 1 * 2) * (1 * 1 * 2) * 2 = 8

     time : O(logn);
     space : O(n)/O(1)

     * @param x
     * @param n
     * @return
     */
    public static double myPow1(double x, int n) {
        if (n > 0) {
            return pow(x, n);
        } else {
            return  1.0 / pow(x, n);
        }
    }
    
    public static double pow (double x, int n) {
        if (n == 0) {
            return 1;
        }
        double y = pow(x, n / 2);
        if (n % 2 == 0) {
            return y * y;
        } else {
            return y * y * x;
        }
    }
    /*
     * interview friendly : O(lgn)/O(1)
     * 
     * the problem is to say: given double x and n, return x^n, note n might be negative numbers
     * 
     * so first we make sure n is positive, then we can focus on process n
     * 
     * obviously we would like to use logn way, suppose n = 3, then res =x, then next is 1 
     */
    public double myPow(double x, int n) {
        if (n  == 0) return 1.0;
    
        //in case n = Integer.MIN_VALUE;
        long m = n;
        if(m < 0) {
            x = 1/x;
            m = -m;
        }
        
        
        double res = 1.0;
        
        while(m > 0) {
            if (m % 2 == 1) {
                res *= x;
            }
            
            x = x* x;
            m = m/2;
        }
        
        return res;
    }
}
