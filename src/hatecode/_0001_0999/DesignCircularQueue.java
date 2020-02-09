package hatecode._0001_0999;
public class DesignCircularQueue {
/*
622. Design Circular Queue
Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".

One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue. But using the circular queue, we can use the space to store new values.

Your implementation should support following operations:

MyCircularQueue(k): Constructor, set the size of the queue to be k.
Front: Get the front item from the queue. If the queue is empty, return -1.
Rear: Get the last item from the queue. If the queue is empty, return -1.
enQueue(value): Insert an element into the circular queue. Return true if the operation is successful.
deQueue(): Delete an element from the circular queue. Return true if the operation is successful.
isEmpty(): Checks whether the circular queue is empty or not.
isFull(): Checks whether the circular queue is full or not.
 

Example:

MyCircularQueue circularQueue = new MyCircularQueue(3); // set the size to be 3
circularQueue.enQueue(1);  // return true
circularQueue.enQueue(2);  // return true
circularQueue.enQueue(3);  // return true
circularQueue.enQueue(4);  // return false, the queue is full
circularQueue.Rear();  // return 3
circularQueue.isFull();  // return true
circularQueue.deQueue();  // return true
circularQueue.enQueue(4);  // return true
circularQueue.Rear();  // return 4
*/

    //interview friendly
    //thinking process: the problem is want us to implement the ds to have O(1)
    //so i come up with an array, head, tail pointer and size, 
    //first idea is to use head and tail to guard the array, but then problem came as we 
    //endqueue and dequeue in random, so tail maybe before front, and we have no way to tell
    //how many elements are in array, so we use size to indicate this. most tricky here is 
    //1. tail begin with -1. most for indicate current tail is last elment
    //2 we always use size to indicate isFull(). isEmpty(). also we should use these functions
    //as many as possible
    int[] a;
    int head, tail,size;
    public DesignCircularQueue(int k) {
        this.a = new int[k];
        this.head = 0;
        //here is the key, we tail will always point to last element in array.
        //-1 means for adapting the 0
        this.tail = -1;
        //this is the key to indicate how many elements in current array
        this.size = 0;
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    //[1,2,3]
    public boolean enQueue(int value) {
        if (isFull()) return false;
        tail = (tail + 1) % a.length;
        a[tail] = value;
        size++;
        return true;
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty()) return false;
        head = (head + 1) % a.length;
        size--;
        return true;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        if (!isEmpty()) return a[head];
        else return -1;
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        if (!isEmpty()) return a[tail];
        else return -1;
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        if (size == 0) return true;
        else return false;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        if (a.length == size) return true;
        else return false;
    }
    
    //another implementation of circular queue, we use write as index to indicate
    //last's elment's next
    
    //the key is to (write - count + K) % K means front
    //(write - 1 + K) % K means tail
    //third key is write starts 0, and always point to last element's next
    
    //we can think this is a stack, write point to stack bottom, which is not filled yet. 
    //count means how many elements in stack. 
    //so if elements being ejected, we will count--. 
    // if there no dequeue, and not full, count = write
    //if not full, and it has dequeue, then write - count means how many elements are being ejected
    //
    class MyCircularQueue {
        private int[] A;
        private int count;  //how many elements are in queue
        private int write; //this actually means tail, next slot for inserting

        /** Initialize your data structure here. Set the size of the queue to be k. */
        public MyCircularQueue(int k) {
            A = new int[k];
            count = 0;
            write = 0;
        }
        
        /** Insert an element into the circular queue. Return true if the operation is successful. */
        public boolean enQueue(int value) {
            if (isFull()) return false;

            A[write] = value;
            count++;
            write = (write + 1) % A.length;
            return true;
        }
        
        /** Delete an element from the circular queue. Return true if the operation is successful. */
        public boolean deQueue() {
            if (isEmpty()) return false;

            count--;
            return true;
        }
        
        /** Get the front item from the queue. */
        public int Front() {
            return count == 0 ? -1 : A[(write - count + A.length) % A.length];
        }
        
        /** Get the last item from the queue. */
        public int Rear() {
            return count == 0 ? -1 : A[(write - 1 + A.length) % A.length];
        }
        
        /** Checks whether the circular queue is empty or not. */
        public boolean isEmpty() {
            return count == 0;
        }
        
        /** Checks whether the circular queue is full or not. */
        public boolean isFull() {
            return count == A.length;
        }
    }
}