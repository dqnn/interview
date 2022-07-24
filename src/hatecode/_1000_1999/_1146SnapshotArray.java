package hatecode._1000_1999;

import java.util.*;
import java.util.stream.*;
public class _1146SnapshotArray {
/*
1146. Snapshot Array
Implement a SnapshotArray that supports the following interface:

SnapshotArray(int length) initializes an array-like data structure with the given length.  Initially, each element equals 0.
void set(index, val) sets the element at the given index to be equal to val.
int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 

Example 1:

Input: ["SnapshotArray","set","snap","set","get"]
[[3],[0,5],[],[0,6],[0,0]]
Output: [null,null,0,null,5]
Explanation: 
SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
snapshotArr.set(0,5);  // Set array[0] = 5
snapshotArr.snap();  // Take a snapshot, return snap_id = 0
snapshotArr.set(0,6);
snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5
*/

    //thinking process: O(lgn)/O(n + length), n= times set() called
    
    //for each element we keep one version, this is easy for debugging
    TreeMap<Integer, Integer>[] A;
    int snap_id = 0;
    public _1146SnapshotArray(int length) {
        A = new TreeMap[length];
        for (int i = 0; i < length; i++) {
            A[i] = new TreeMap<Integer, Integer>();
            A[i].put(0, 0);//as default value
        }
    }

    public void set(int index, int val) {
        A[index].put(snap_id, val);
    }

    public int snap() {
        return snap_id++;
    }
    //return <= snap_id max key, value, the lookup is binary search, this is 
    //optimized than BF
    public int get(int index, int snap_id) {
        return A[index].floorEntry(snap_id).getValue();
    }
    
    //better in interview, since it is more straightward compared to TreeMap[]
   class SnapshotArray_TreeMapInMap {

        Map<Integer, TreeMap<Integer, Integer>> map;
        int snap_id=0;
        int[] A;
        public SnapshotArray_TreeMapInMap(int len) {
            A = new int[len];
            map = new HashMap<>();
            //we need this because if floorEntry will return null if there is no version at all
            /*
             * ["SnapshotArray","snap", "get",  "get",  "set",  "get",  "set",  "get",  "set"]
                    [[2],       [],     [1,0],  [0,0],  [1,8],  [1,0],  [0,20], [0,0],  [0,7]]
             */
            IntStream.range(0, len).forEach(i->map.computeIfAbsent(i, v->new TreeMap<>()).put(0, 0));
            //lastHash = A.hashCode();
        }
        
        public void set(int index, int val) {
            map.computeIfAbsent(index, v->new TreeMap<>()).put(snap_id, val);
        }
        
        public int snap() {
            return snap_id++;
        }
        
        public int get(int index, int snap_id) {
            //System.out.println(index + "--" + snap_id);
           // if (!map.containsKey(index) || map.get(index).size() == 0) return A[index];
            
            //System.out.println(map);
            return map.get(index).floorEntry(snap_id).getValue();
        }
    }
    
    //brute force solution
class SnapshotArray_BF {
    
    Map<String, Integer> map;
    int n;
    int curV;
    public SnapshotArray_BF(int length) {
        map = new HashMap<>();
        n = length;
        curV = 0;
    }
    
    public void set(int index, int val) {
        String key = curV + "_" + index;
        map.put(key, val);
    }
    
    public int snap() {
        return curV++;
    }
    
    public int get(int index, int snap_id) {
        //System.out.println(map);
        for(int i = snap_id; i>=0;i--) {
            String key = i + "_" + index;
            if(map.containsKey(key)) return map.get(key);
        }
        
        return 0;
    }
}
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */