package hatecode;

import java.util.concurrent.Semaphore;

public class _1117BuildingH2O {
/*1117. Building H2O
 * There are two kinds of threads, oxygen and hydrogen. Your goal is to group these threads to form water molecules. There is a barrier where each thread has to wait until a complete molecule can be formed. Hydrogen and oxygen threads will be given releaseHydrogen and releaseOxygen methods respectively, which will allow them to pass the barrier. These threads should pass the barrier in groups of three, and they must be able to immediately bond with each other to form a water molecule. You must guarantee that all the threads from one molecule bond before any other threads from the next molecule do.

In other words:

If an oxygen thread arrives at the barrier when no hydrogen threads are present, it has to wait for two hydrogen threads.
If a hydrogen thread arrives at the barrier when no other threads are present, it has to wait for an oxygen thread and another hydrogen thread.
We don’t have to worry about matching the threads up explicitly; that is, the threads do not necessarily know which other threads they are paired up with. The key is just that threads pass the barrier in complete sets; thus, if we examine the sequence of threads that bond and divide them into groups of three, each group should contain one oxygen and two hydrogen threads.

Write synchronization code for oxygen and hydrogen molecules that enforces these constraints.

 

Example 1:

Input: "HOH"
Output: "HHO"
Explanation: "HOH" and "OHH" are also valid answers.
 */
    
    //thinking process:
    
    //the problem is to say: 
    
    Semaphore outputting;
    Semaphore outputHydrogen, outputOxygen;
    public _1117BuildingH2O() {
        outputting = new Semaphore(1);
        outputHydrogen = new Semaphore(0);
        outputOxygen = new Semaphore(0);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        //we only "output" when oxygen says so
        outputHydrogen.acquire();
        releaseHydrogen.run();
        outputOxygen.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        //we only output 1 complete set at a time.
        outputting.acquire();
        
        outputHydrogen.release(2);
		releaseOxygen.run();
		//we wait for 2 hydrogens to finish printing
        outputOxygen.acquire(2);
        
        outputting.release();
    }
}


class H2O {
    
    Semaphore h, o;
    public H2O() {
        h = new Semaphore(2, true);
        o = new Semaphore(0, true);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
		h.acquire();
        releaseHydrogen.run();
        o.release();
        
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire(2);
		releaseOxygen.run();
        h.release(2);
    }
}