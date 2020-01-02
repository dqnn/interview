package hatecode;

import java.util.*;
import java.util.concurrent.Semaphore;
public class _1114PrintInOrder {
/*
1114. Print in Order
Suppose we have a class:

public class Foo {
  public void first() { print("first"); }
  public void second() { print("second"); }
  public void third() { print("third"); }
}
The same instance of Foo will be passed to three different threads. Thread A will call first(), thread B will call second(), and thread C will call third(). Design a mechanism and modify the program to ensure that second() is executed after first(), and third() is executed after second().

 

Example 1:

Input: [1,2,3]
Output: "firstsecondthird"

Example 2:

Input: [1,3,2]
Output: "firstsecondthird"
*/
    //just use semaphore or lock to release or acquire the lock，
    //the key is output is always the same no matter the sequence of 1,2, 3.  see example 2. 
    //so how can i make sure the output order is guaranteed?
    //one way is to have dependency, third->second->first, so no matter which sequence, it alwyas
    //will seek dependency ready, recursive way
    
    //just think we have 3 boxes in a line, we want to open as above sequence, 
    //so if we random choose 1, if it is 3, then we do not print, we want to see its dependency,
    //to look for 2, so random open 2nd box, if it is 2 then good, when we print, we found we need to 
    //print 1, as this recursion goes, we can find the last one.
    
    //so  --1--  --2--  --3-- how can we insert dependency?
    //
    Semaphore run2, run3;
    //Semaphore constructor take one integer to count down, if it is 0 then 
    //you have to wait when call acquire, when release it will increase 1
    
    //so we initialize 0 means all have to wait
    public _1114PrintInOrder() {
        run2 = new Semaphore(0);
        run3 = new Semaphore(0);
    }
    //we allow run2 to run to print second
    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        run2.release();
    }
    //first we have to let first to print, and release run3
    public void second(Runnable printSecond) throws InterruptedException {
        run2.acquire();
        printSecond.run();
        run3.release();
    }
    //here is the last
    public void third(Runnable printThird) throws InterruptedException {
        run3.acquire(); 
        printThird.run();
    }
}