package hatecode;
public class _1093StatisticsFromALargeSample {
/*
1093. Statistics from a Large Sample
We sampled integers between 0 and 255, and stored the results in an array count:  count[k] is the number of integers we sampled equal to k.

Return the minimum, maximum, mean, median, and mode of the sample respectively, as an array of floating point numbers.  The mode is guaranteed to be unique.

(Recall that the median of a sample is:

The middle element, if the elements of the sample were sorted and the number of elements is odd;
The average of the middle two elements, if the elements of the sample were sorted and the number of elements is even.)
 

Example 1:

Input: count = [0,1,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
Output: [1.00000,3.00000,2.37500,2.50000,3.00000]
*/
    //thinking process:
    
    //mode means most frquent number
    public double[] sampleStats(int[] count) {
        int total = 0, mode = 0;
        double median = 0, min = -1, max = 0, avg = 0, sum = 0;
        for (int i = 0; i < 256; ++i) {
            if (count[i] > 0) {
                total += count[i];
                if (min < 0) min = i;
                max = i;
                sum += i * count[i];
                if (count[i] > count[mode]) mode = i;
            }
        }
        avg = sum / total;
        if (total == 1) median = sum; // single element.
        int m1 = (total + 1) / 2, m2 = total / 2 + 1; // m1-th and m2-th items are medians.
        for (int i = 0, cnt = 0; total > 1 && i < 256; ++i) { // more than 1 elements.
            if (cnt < m1 && cnt + count[i] >= m1) // find m1-th item.
                median += i / 2.0d; // add its half.
            if (cnt < m2 && cnt + count[i] >= m2) // find m2-th item.
                median += i / 2.0d; // add its half.
            cnt += count[i];
        }
        return new double[]{min, max, avg, median, mode};
    }
}