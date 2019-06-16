package hatecode;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;
class SmallestRange {
    /*
     * 632. Smallest Range You have k lists of sorted integers in ascending order.
     * Find the smallest range that includes at least one number from each of the k
     * lists.
     * 
     * We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c
     * if b-a == d-c.
     * 
     * Example 1: Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]] Output: [20,24]
     * Explanation: List 1: [4, 10, 15, 24,26], 24 is in range [20,24]. List 2: [0,
     * 9, 12, 20], 20 is in range [20,24]. List 3: [5, 18, 22, 30], 22 is in range
     * [20,24].
     */
    //thinking process: given K sorted array, find smallest range which satisfy: 1: range 
    //is smallest, 2: the range at least contain one element from K lists
    
    //so the range is smallest means it has fewest number,also means the diff between last and 
    //first element is smallest
    // so suppose we get each element from the list, and we compare smallest to largest, then 
    //we can have 1 range could satisfy 2, for 1 we need a loop to make sure we always 
    //sort the list easily
    
    //the process is exactly the same as merge sorted K list. 
    
    //the heap help us to maintain each element from one list, if one list is done, then we 
    //should end the loop because we need to satisfy 2. 
    
    //the easier understanding solution is to have min and max heap, min always pop the element
    //max always offer elements in while it always keep max element no matter how many added into 
    //min heap, we use max element to compare first element in min heap, so we can know for 
    //each range, it is bigger or smaller
    public int[] smallestRange(List<List<Integer>> lists) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b)->(a.val - b.val));
        //max indicate the max value in current heap
        int max = Integer.MIN_VALUE;
        //add each row head into the min heap
        for (int row = 0; row < lists.size(); row++) {
            int val = lists.get(row).get(0);
            Node node = new Node(row, 0, val);
            pq.offer(node);
            max = Math.max(max, val);
        }
        int range = Integer.MAX_VALUE;
        //output
        int start = -1, end = -1;
        //this is like sliding window, the size is list size,which means each sorted row
        //would only have 1 element in the heap, we always choose min one and comarpe the 
        //max in this heap, and we update the range, and we will add rest of the array into 
        //this heap until we get all possible ranges
        
        //for that column, we always choose the range
        while (pq.size() == lists.size()) {
            Node curr = pq.poll();
            //if new range smaller than previous one, update it
            if (max - curr.val < range) {
                range = max - curr.val;
                start = curr.val;
                end = max;
            }
            //if the list has more elements after this index, then add to queue
            if (curr.idx + 1 < lists.get(curr.row).size()) {
                curr.idx = curr.idx + 1;
                curr.val = lists.get(curr.row).get(curr.idx);
                pq.offer(curr);
                //
                if (curr.val > max) {
                    max = curr.val;
                }
            }
        }
        return new int[] {start, end};
    }

    class Node {
        int val;
        int idx;
        int row;
        public Node(int row, int idx, int val) {
            this.val = val;
            this.idx = idx;
            this.row = row;
        }
    }
    //sliding window
    public static int[] smallestRange2(List<List<Integer>> nums) {
        List<int[]> list = IntStream.range(0, nums.size())
                .mapToObj( i -> nums.get(i).stream().map(x -> new int[]{x, i}))
                .flatMap(y -> y)
                .sorted(Comparator.comparingInt(p -> p[0])).collect(toList());
        int[] counts = new int[nums.size()];
        BitSet set = new BitSet(nums.size());
        int start = -1;
        int[] res = new int[2];
        for(int i = 0; i < list.size(); i++) {
            int[] p = list.get(i);
            set.set(p[1]);
            counts[p[1]] += 1;
            if(start == -1) { start = 0; }
            while(start < i && counts[list.get(start)[1]] > 1) {
                counts[list.get(start)[1]]--;
                start++;
            }
            if(set.cardinality() == nums.size()) {
                if( (res[0] == 0 && res[1] == 0) || (list.get(i)[0] - list.get(start)[0]) < res[1] - res[0]) {
                    res[0] = list.get(start)[0];
                    res[1] = list.get(i)[0];
                }
            }
        }
        return res;
    }
    //use max heap and min heap
    public int[] smallestRange3(List<List<Integer>> nums) {
        //min heap
        PriorityQueue<int[]> min=new PriorityQueue<>(1,new Comparator<int[]>(){
            public int compare(int[] n1,int[] n2){
                return nums.get(n1[0]).get(n1[1])-nums.get(n2[0]).get(n2[1]);
            }              
        });
        //max heap
        PriorityQueue<int[]> max=new PriorityQueue<>(1,new Comparator<int[]>(){
            public int compare(int[] n1,int[] n2){
                return nums.get(n2[0]).get(n2[1])-nums.get(n1[0]).get(n1[1]);
            }
        });
        //first we add first element in each list in nums
        for(int i=0;i<nums.size();i++){
              int[] tmp=new int[]{i,0};
              min.offer(tmp);
              max.offer(tmp);
        }
        //result
        int[] res=new int[]{Integer.MIN_VALUE,Integer.MAX_VALUE};
        
        while(min.size()==nums.size()){
            int[] m1=max.peek();
            int[] m2=min.poll();
            if((long)nums.get(m1[0]).get(m1[1])-(long)nums.get(m2[0]).get(m2[1]) 
                    < (long)res[1]-(long)res[0]){
                res[0]=nums.get(m2[0]).get(m2[1]);
                res[1]=nums.get(m1[0]).get(m1[1]);
            }
            
            if(m2[1]+1<nums.get(m2[0]).size()){
                int[] m3=new int[]{m2[0],m2[1]+1};
                min.offer(m3);
                max.offer(m3);
                max.remove(m2);
            }
        }
        return res;
  }
}