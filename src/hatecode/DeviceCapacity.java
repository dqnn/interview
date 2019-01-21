package hatecode;

import java.util.*;

public class DeviceCapacity {
  /*
  有两个List of apps， ⾥里里⾯面存着每⼀一个app的index 和 它所需的memory。 从
foregroundList<Integer>（[1, 200], [2, 300]） 和⼀一个
boregroundList<Integer> ([1, 400], [2, 500]) 中各取⼀一个组成⼀一对，使得它们
memory加起来的和最接近但不不超过capacity。 需要注意的是多个app的
memory有可能是相同。
例例如： foregroundList<Integer> =[[1, 2000]], boregroundList<Integer> =
[[1, 8000], [2, 8000]], capacity = 10000. 需要返回 [[1,1], [1,2]]。
   */
    public static List<List<Integer>> getCombinations(List<List<Integer>> front, List<List<Integer>> back,
            int capacity) {
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        Comparator<List<Integer>> comparator = (o1, o2) -> {
            int val1 = o1.get(1);
            int val2 = o2.get(1);
            return (val1 - val2) / Math.abs(val1 - val2);
        };
        //this is using Comparator
        //Collections.sort(front, comparator);
        //Collections.sort(back, comparator);
        
        //this is using lambda
        Collections.sort(front, (a,b)->(a.get(1) - b.get(1)));
        Collections.sort(back, (a,b)->(a.get(1) - b.get(1)));
        // for(List<Integer> point: front) {
        // System.out.println(point.get(0)+":"+point.get(1));
        // }
        //
        // System.out.println("-------------------");
        //
        // for(List<Integer> point: back) {
        // System.out.println(point.get(0)+":"+point.get(1));
        // }
        int aIndex = 0;
        int bIndex = back.size() - 1;
        int maxVal = Integer.MIN_VALUE;
        while (aIndex < front.size()) {
            while (bIndex >= 0) {
                List<Integer> pointF = front.get(aIndex);
                List<Integer> pointB = back.get(bIndex);
                int valF = pointF.get(1);
                int valB = pointB.get(1);
                int sum = valF + valB;
                if (sum > capacity) {
                    --bIndex;
                } else {
                    if (sum > maxVal) {
                        maxVal = sum;
                        ret.clear();
                        ret.add(Arrays.asList(pointF.get(0), pointB.get(0)));
                    } else if (sum == maxVal) {
                        ret.add(Arrays.asList(pointF.get(0), pointB.get(0)));
                    }
                    break;
                }
            }
            ++aIndex;
        }
        return ret;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        List<List<Integer>> front = new LinkedList<List<Integer>>(Arrays.asList(
                Arrays.asList(3, 700),
                Arrays.asList(2, 500), 
                Arrays.asList(4, 900), 
                Arrays.asList(1, 300)));
        List<List<Integer>> back = new LinkedList<List<Integer>>(Arrays.asList(
                Arrays.asList(2, 300),
                Arrays.asList(4, 500), 
                Arrays.asList(3, 400), 
                Arrays.asList(1, 250)));
        // List<List<Integer>> ret = getCombinations(front, back, 1180);
        List<List<Integer>> ret = getCombinations(front, back, 900);
        System.out.println("-------------------");
        for (List<Integer> point : ret) {
            System.out.println(point.get(0) + ":" + point.get(1));
        }
    }
}