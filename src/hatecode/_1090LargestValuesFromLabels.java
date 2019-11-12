package hatecode;

import java.util.*;
public class _1090LargestValuesFromLabels {
/*
1090. Largest Values From Labels
We have a set of items: the i-th item has value values[i] and label labels[i].

Then, we choose a subset S of these items, such that:

|S| <= num_wanted
For every label L, the number of items in S with label L is <= use_limit.
Return the largest possible sum of the subset S.

 

Example 1:

Input: values = [5,4,3,2,1], labels = [1,1,2,2,3], num_wanted = 3, use_limit = 1
Output: 9
*/
    public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        // create a hashmap for maintaining the use_limit of each label group
        Arrays.stream(labels).forEach(i->map.put(i, 0));

        int size = values.length;
        int[][] val_lab = new int[size][2];

        // creating a 2D array which has values and labels corresponding to the values
        for (int i = 0; i < size; i++) {
            val_lab[i][0] = values[i];
            val_lab[i][1] = labels[i];
        }

        // sorting the array in descending order based on the values from column 0
        Arrays.sort(val_lab, (a, b)->(b[0] - a[0]));
        int sum = 0;

        for (int i = 0; i < size; i++) {
            int val = val_lab[i][0];
            int lab = val_lab[i][1];
        // if label usage less than use_limit and subset size is less than num_wanted, include array item in the subset
            if (num_wanted > 0 && map.get(lab) < use_limit) {
                sum += val;
                map.put(lab, map.get(lab) + 1);
                num_wanted--;
            }
        }
        return sum;
    }
}