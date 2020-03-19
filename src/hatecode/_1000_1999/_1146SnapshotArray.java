package hatecode._1000_1999;

import java.util.*;
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

    //thinking process: O(lgn)/O(n), n= times set() called
    
    //
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

    public int get(int index, int snap_id) {
        return A[index].floorEntry(snap_id).getValue();
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