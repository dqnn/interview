package hatecode;

import java.util.*;
import java.util.concurrent.Semaphore;
public class _1115PrintFooBarAlternately {
/*
1115. Print FooBar Alternately
Suppose you are given the following code:

class FooBar {
  public void foo() {
    for (int i = 0; i < n; i++) {
      print("foo");
    }
  }

  public void bar() {
    for (int i = 0; i < n; i++) {
      print("bar");
    }
  }
}
The same instance of FooBar will be passed to two different threads. Thread A will call foo() while thread B will call bar(). Modify the given program to output "foobar" n times.

 

Example 1:

Input: n = 1
Output: "foobar"
*/

    //thinking process: same as 1114, 
    
    //the problem is to say given two function, foo and bar and an integer n,
    //we would like to print foorbar n =1, foobarfoobar n=2, like this way,
    //just add some code into current class
    
    //so we would like to print the string in correct squence, but they should 
    //print in correct order, recursion as dependency can work here
    
    //
    private int n;

    Semaphore mu1,mu2;
    public _1115PrintFooBarAlternately(int n) {
        this.n = n;
        this.mu1 = new Semaphore(1);
        this.mu2 = new Semaphore(0);
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            mu1.acquire();
        	// printFoo.run() outputs "foo". Do not change or remove this line.
        	printFoo.run();
            mu2.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            mu2.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            mu1.release();
        }
    }
}