package hatecode._0001_0999;
import java.util.*;
public class MaximumFrequencyStack {
    /*895. Maximum Frequency Stack
     * Implement FreqStack, a class which simulates the operation of a stack-like
     * data structure.
     * 
     * FreqStack has two functions:
     * 
     * push(int x), which pushes an integer x onto the stack. pop(), which removes
     * and returns the most frequent element in the stack. If there is a tie for
     * most frequent element, the element closest to the top of the stack is removed
     * and returned.
     * 
     */
    HashMap<Integer, Integer> key2Fre = new HashMap<>();
    HashMap<Integer, Stack<Integer>> fre2Stack = new HashMap<>();
    int maxFreq = 0;

    public void push(int x) {
        int f = key2Fre.getOrDefault(x, 0) + 1;
        key2Fre.put(x, f);
        maxFreq = Math.max(maxFreq, f);
        fre2Stack.computeIfAbsent(f, v->new Stack<>()).push(x);
    }

    public int pop() {
        int x = fre2Stack.get(maxFreq).pop();
        key2Fre.put(x, maxFreq - 1);
        //here is the key, the reason why we can maxfreq because this is max fre stack, not LFU, 
        //we actually have a lot dup value in these stacks
        if (fre2Stack.get(maxFreq).size() == 0) maxFreq--;
        return x;
    }
    
    public static void main(String[] args) {
        MaximumFrequencyStack s = new MaximumFrequencyStack();
        s.push(4);
        s.push(0);
        s.push(9);
        s.push(3);
        s.push(4);
        s.push(2);
        System.out.print(s.pop());
        s.push(6);
        System.out.print(s.pop());
        s.push(1);
        System.out.print(s.pop());
        s.push(1);
        System.out.print(s.pop());
        s.push(4);
        System.out.print(s.pop());
        System.out.print(s.pop());
        System.out.print(s.pop());
        System.out.print(s.pop());
        System.out.print(s.pop());
        System.out.print(s.pop());
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 */