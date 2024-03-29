package hatecode._0001_0999;
import java.util.*;
import java.util.stream.Collectors;
public class _658FindKClosestElements {
/*
 * 658. Find K Closest Elements
Given a sorted array, two integers k and x, find the k closest elements to x 
in the array. The result should also be sorted in ascending order. 
If there is a tie, the smaller elements are always preferred.

Example 1:
Input: [1,2,3,4,5], k=4, x=3
Output: [1,2,3,4]
Example 2:
Input: [1,2,3,4,5], k=4, x=-1
Output: [1,2,3,4]

 */
    class Node {
        int diff;
        int val;
        public Node(int diff, int val) {
            this.diff = diff;
            this.val = val;
        }
    }
    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        if (arr == null || k < 0 || arr.length < k) return res;
        Queue<Node> q = new PriorityQueue<>(
            (a, b)->(a.diff == b.diff ? a.val - b.val : a.diff - b.diff));
        q.offer(new Node(Math.abs(arr[0] - x), arr[0]));
        for(int i = 1; i< arr.length; i++) {
            q.offer(new Node(Math.abs(arr[i] - x), arr[i]));
        }
        for(int i =0; i< k;i++ ) {
            res.add(q.poll().val);
        }
        Collections.sort(res);
        return res;
     }
    //O(nlgn), O(k), generating sublist
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
     List<Integer> temp = Arrays.stream(arr).boxed().collect(Collectors.toList());
        Collections.sort(temp, (a,b) -> a == b ? a - b : Math.abs(a-x) - Math.abs(b-x));
     temp = temp.subList(0, k);
     Collections.sort(temp);
     return temp;
}
    
    //BS V2, O(lg(n-k) + k) Space: O(1), simplest one
    /*
   facts:
     1. the result is a subarray in the list, and x should be in the middle of the subarray ideally 
     2. m is the subarray start point, so we want to find its location, and it should be balanced with x 

     if not, we can find a balanced subarray which is more fit to the problem

     */
    //best solution, but need to understand: suppose array [1,2,3,4,5], k=4, x=3, each so we
    //want to know for ai, aj, which is more close to 3, if we minus 3 for every element in above 
    // array, [-2,-1,0,1,2], 
    
    /* 
       x - A[m]  A[m+k] - x
       --------------x--A[m]-------A[m+k]-----------
       ---------A[m]-----x-------A[m+k]-----------

      ---------A[m]-------A[m+k]---x-----------

      closest == distance == x- A[m] 
      when x- A[m] = A[m+k] - x then [m, m+k] is the subarry we need, but 
      since we would like smaller index, so we need to find most left elements

    */
    public List<Integer> findClosestElements_BS(int[] A, int k, int x) {
        if(A == null || A.length < 1) return new ArrayList<>();
        
        if(A.length == k) return Arrays.stream(A).boxed().collect(Collectors.toList());
        
        int l = 0, r = A.length - k;
        
        while(l < r) {
            int m = l + (r-l)/2;
            
            if(x - A[m] <= A[m + k] - x) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        
      
        int[] res = Arrays.copyOfRange(A, l, l+k);
        return Arrays.stream(res).boxed().collect(Collectors.toList());
    }


    //BS version 1, O(lgn + k), O(k) is for shrinking the index range to k elements.
    // Space:O(k) generating the list
    public List<Integer> findClosestElements4(List<Integer> arr, int k, int x) {
        int n = arr.size();
        if (x <= arr.get(0)) {
            return arr.subList(0, k);
        } else if (arr.get(n - 1) <= x) {
            return arr.subList(n - k, n);
        } else {
            //bianry API return 
            int index = Collections.binarySearch(arr, x);
            if (index < 0)
                index = -index - 1;
            int low = Math.max(0, index - k - 1), 
                high = Math.min(arr.size() - 1, index + k - 1);

            while (high - low > k - 1) {
                if (low < 0 || (x - arr.get(low)) <= (arr.get(high) - x))
                    high--;
                else if (high > arr.size() - 1 || (x - arr.get(low)) > (arr.get(high) - x))
                    low++;
                else
                    System.out.println("unhandled case: " + low + " " + high);
            }
            return arr.subList(low, high + 1);
        }
    }
}