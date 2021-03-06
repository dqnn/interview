package hatecode._0001_0999;
import java.util.*;
public class _703KthLargestElementInAStream {
/*
703. Kth Largest Element in a Stream
Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Your KthLargest class will have a constructor which accepts an integer k and an integer array nums, which contains initial elements from the stream. For each call to the method KthLargest.add, return the element representing the kth largest element in the stream.

Example:

int k = 3;
int[] arr = [4,5,8,2];
KthLargest kthLargest = new KthLargest(3, arr);
kthLargest.add(3);   // returns 4
kthLargest.add(5);   // returns 5
kthLargest.add(10);  // returns 5
kthLargest.add(9);   // returns 8
kthLargest.add(4);   // returns 8
*/
    //actually we always keeping k + 1 size PQ and always poll
    final PriorityQueue<Integer> pq; 
    final int k; 
    public _703KthLargestElementInAStream(int k, int[] nums) {
        this.k = k; 
        pq = new PriorityQueue<>(k);
        for(int num : nums){
            if(pq.size() < k){
                pq.add(num);
            } else if (pq.peek() < num){
                pq.poll();
                pq.offer(num);
            }
        }
    }
    // very common trick, how to keep k largest number in q
    public int add(int val) {
        if(pq.size() < k){
            pq.offer(val);
        }
        else if(pq.peek() < val){
            pq.poll();
            pq.offer(val);
        }
        return pq.peek();  
    }
}