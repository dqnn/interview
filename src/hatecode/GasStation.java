package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : GasStation
 * Creator : duqiang
 * Date : Nov, 2017
 * Description : 134. Gas Station
 */
public class GasStation {
    /**
     * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

     You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1).
     You begin the journey with an empty tank at one of the gas stations.

     Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

     非常经典的一道题。可以转换成求最大连续和做，但是有更简单的方法。基于一个数学定理：

     如果一个数组的总和非负，那么一定可以找到一个起始位置，从他开始绕数组一圈，累加和一直都是非负的
     （证明貌似不难，以后有时间再补）


     有了这个定理，判断到底是否存在这样的解非常容易，只需要把全部的油耗情况计算出来看看是否大于等于0即可。

     那么如何求开始位置在哪？

     注意到这样一个现象：

     1. 假如从位置i开始，i+1，i+2...，一路开过来一路油箱都没有空。说明什么？说明从i到i+1，i+2，...肯定是正积累。
     2. 现在突然发现开往位置j时油箱空了。这说明什么？说明从位置i开始没法走完全程(废话)。那么，我们要从位置i+1开始重新尝试吗？不需要！为什么？
     因为前面已经知道，位置i肯定是正积累，那么，如果从位置i+1开始走更加没法走完全程了，因为没有位置i的正积累了。
     同理，也不用从i+2，i+3，...开始尝试。所以我们可以放心地从位置j+1开始尝试。

     https://www.cnblogs.com/boring09/p/4248482.html

     time : O(n)
     space : O(1)

     * @param gas
     * @param cost
     * @return
     */
    //thinking process:
    //given an array with each cost as another array. fiind out index which as starting index we can 
    //travel around the array
    
    //for any two points, if the sum >=0, then we always can find a point which can travel whole circle, 
    //
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas.length == 0 || cost.length == 0 || gas.length != cost.length) return -1;
        int total = 0, sum = 0, start = 0;
        for (int i = 0; i < gas.length; i++) {
            int delta = gas[i] - cost[i];
            // total here is the sum of all gas-cost
            total += delta;
            // if like first station or some station that we cannot go to next one.
            if (sum < 0) {
                // sum restarted, means previous as start cannot make it
                sum = delta;
                start = i;
            } else {
                // so sum also have to add them together, 
                sum += delta;
            }
        }
        // no matter from which index, so if total is smaller than 0 which means we have no way to achieve the goal
        // or else we could always find a starting to go
        return total >= 0 ?  start : -1;
    }
    
    // another solutions
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length; i++) {
            gas[i] -= cost[i];
        }
        int sum = 0;
        int result = 0;
        int n = gas.length;
        //We have to check every node (station) to see if it meets the criteria and we can't stop at n - 1 
        //cause there's possibility that no station is satisfied. So when we come to the n - 1 node, 
        //we have to go another round.
        //Simulate a cycle by an array, such as:
        //0,1,2...n-1,0,1,2....n-2
        //total 2n-1 elements.
        for (int i = 0; i < n * 2 - 1; i++) {
            sum += gas[i % n];
            if (sum < 0) {
                result = i + 1;
                if (result >= n) {
                    return -1;
                }
                sum = 0;
            }
        }
        return result;
    }
}
