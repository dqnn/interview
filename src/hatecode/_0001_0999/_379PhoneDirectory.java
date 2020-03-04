package hatecode._0001_0999;
import java.util.*;
public class _379PhoneDirectory {
/*
379. Design Phone Directory
Design a Phone Directory which supports the following operations:

get: Provide a number which is not assigned to anyone.
check: Check if a number is available or not.
release: Recycle or release a number.
*/

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    private Queue<Integer> q;
    private Set<Integer> set;
    int max;
    public _379PhoneDirectory(int maxNumbers) {
        this.q = new LinkedList<>();
        this.set = new HashSet<>();
        this.max = maxNumbers;
        for(int i = 0; i<maxNumbers; i++) {
            q.offer(i);
            set.add(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (q.size() == 0) return -1; 
        int res = q.poll();
        set.remove(res);
        return res;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        if (set.contains(number)) return true;
        else return false;
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if (set.contains(number) || set.size() == max) return;
        set.add(number);
        q.offer(number);
    }
}


//this is more on the space friendly solution but time complexity on get will be higher
 class PhoneDirectoryBitSetSolution {

    BitSet bitset;
    int max; // max limit allowed
    int smallestFreeIndex; // current smallest index of the free bit

    public PhoneDirectoryBitSetSolution(int maxNumbers) {
        this.bitset = new BitSet(maxNumbers);
        this.max = maxNumbers;
    }

    public int get() {
        // handle bitset fully allocated
        if(smallestFreeIndex == max) {
            return -1;
        }
        int num = smallestFreeIndex;
        bitset.set(smallestFreeIndex);
        //Only scan for the next free bit starting from smallestFreeIndex, 
        //from the previously known smallest free index
        smallestFreeIndex = bitset.nextClearBit(smallestFreeIndex);
        return num;
    }

    public boolean check(int number) {
        return bitset.get(number) == false;
    }

    public void release(int number) {
        //handle release of unallocated ones
        if(bitset.get(number) == false)
            return;
        bitset.clear(number);
        if(number < smallestFreeIndex) {
            smallestFreeIndex = number;
        }
    }
}


