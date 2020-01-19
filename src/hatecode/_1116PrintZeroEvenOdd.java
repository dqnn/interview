package hatecode;

import java.util.concurrent.*;
import java.util.function.IntConsumer;
public class _1116PrintZeroEvenOdd {
/*
1116. Print Zero Even Odd
Suppose you are given the following code:


The same instance of ZeroEvenOdd will be passed to three different threads:

Thread A will call zero() which should only output 0's.
Thread B will call even() which should only output even numbers.
Thread C will call odd() which should only output odd numbers.
Each of the threads is given a printNumber method to output an integer. Modify the given program to output the series 010203040506... where the length of the series must be 2n.

 

Example 1:

Input: n = 2
Output: "0102"
*/
    // thinking process: 
    //the problem is to say: given 3 threads with 3 functions and an integer n, 
    //zero function will print 0, even will print even number and 
    //odd will print odd number, we would like to print such form like
    //"0102030405" n = 5 string,
    
    //so the key is how to control the print flow, we need some control signal to 
    //allow each function can be executed
    
    //even or odd will only be executed n / 2 time but 0 will be executed n times
    
    //thinking 3 threads already exists，now we want them to print each function below, 
    //so we just need to control when can print with signal inside these 3 functions
    //zero has 1 initially because it will print first, others will be blocked. 
    //same logic with 1115
    
    int n;
    
    Semaphore semaphoreEven, semaphoreOdd, semaphoreZero;
    
    public _1116PrintZeroEvenOdd(int n) {
        this.n = n;
        
        semaphoreZero = new Semaphore(1);
        semaphoreEven = new Semaphore(0);
        semaphoreOdd = new Semaphore(0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        int numTimes = this.n;
        boolean printOdd = true;
        
        for(int i = 0; i < numTimes; i++){
            semaphoreZero.acquire();
            printNumber.accept(0);

            //print the next number
            if(printOdd)
                semaphoreOdd.release();
            else
                semaphoreEven.release();
            
            printOdd = !printOdd;   //flip it!
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        int numTimes = this.n / 2;
        
        int nextEvenNum = 2;
        for(int i = 0; i < numTimes; i++){
            semaphoreEven.acquire();
            
            printNumber.accept(nextEvenNum);
            nextEvenNum += 2;
            
            semaphoreZero.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        int numTimes = (int)Math.ceil((double)this.n / 2);
        
        int nextOdd = 1;
        for(int i = 0; i < numTimes; i++){
            semaphoreOdd.acquire();
            printNumber.accept(nextOdd);
            nextOdd += 2;
            
            semaphoreZero.release();
        }
    }
}