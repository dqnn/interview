package hatecode._2000_2999;

import java.util.*;

public class _2671FrequencyTracker {
    /*
    2671. Frequency Tracker
    Design a data structure that keeps track of the values in it and answers some queries regarding their frequencies.
    
    Implement the FrequencyTracker class.
    
    FrequencyTracker(): Initializes the FrequencyTracker object with an empty array initially.
    void add(int number): Adds number to the data structure.
    void deleteOne(int number): Deletes one occurence of number from the data structure. The data structure may not contain number, and in this case nothing is deleted.
    bool hasFrequency(int frequency): Returns true if there is a number in the data structure that occurs frequency number of times, otherwise, it returns false.
     
    
    Example 1:
    
    Input
    ["FrequencyTracker", "add", "add", "hasFrequency"]
    [[], [3], [3], [2]]
    Output
    [null, null, null, true]
    */
    /*
     * the problem is to say: frequency with integers. plain ones but one good stratedy is to check whether 
     * 
     * we have such element in add and deleteOne
     */
        Map<Integer, Integer> kv;
        Map<Integer, Set<Integer>> fre2k;
        public _2671FrequencyTracker() {
            kv = new HashMap<>();
            fre2k = new HashMap<>();
        }
        
        public void add(int A) {
            if (kv.containsKey(A)) {
                int old = kv.get(A);
                kv.put(A, old + 1);
                fre2k.get(old).remove(A);
                fre2k.computeIfAbsent(old+1, v->new HashSet<>()).add(A);
            } else {
                kv.put(A, 1);
                fre2k.computeIfAbsent(1, v->new HashSet<>()).add(A);
            }
        }
        
        public void deleteOne(int A) {
            if (!kv.containsKey(A)) return;
            
            int old = kv.get(A);
            if (old == 1) {
                kv.remove(A);
                fre2k.get(1).remove(A);
            } else {
                kv.put(A, old -1);
                fre2k.get(old).remove(A);
                fre2k.get(old-1).add(A);
            }
        }
        
        public boolean hasFrequency(int fre) {
            if (!fre2k.containsKey(fre)) return false;
            return fre2k.get(fre).size() > 0;
        }
    }
    
    /**
     * Your FrequencyTracker object will be instantiated and called as such:
     * FrequencyTracker obj = new FrequencyTracker();
     * obj.add(number);
     * obj.deleteOne(number);
     * boolean param_3 = obj.hasFrequency(frequency);
     */