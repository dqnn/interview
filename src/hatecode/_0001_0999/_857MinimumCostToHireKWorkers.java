package hatecode._0001_0999;
import java.util.*;
public class _857MinimumCostToHireKWorkers {
    /*
     * 857. Minimum Cost to Hire K Workers 
     * There are N workers. The i-th worker has
     * a quality[i] and a minimum wage expectation wage[i].
     * 
     * Now we want to hire exactly K workers to form a paid group. When hiring a
     * group of K workers, we must pay them according to the following rules:
     * 
     * Every worker in the paid group should be paid in the ratio of their quality
     * compared to other workers in the paid group. Every worker in the paid group
     * must be paid at least their minimum wage expectation. Return the least amount
     * of money needed to form a paid group satisfying the above conditions.
     * Example 1:
     * 
     * Input: quality = [10,20,5], wage = [70,50,30], K = 2 Output: 105.00000
     * Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
     * 
     */
    // note: pay/quality >= wage/quality, so we choose the greatest wage/quality in the 
    //K workers
    
    //interview friendly: 
    //this question is to ask for minimal cost to hire K workers, the key point for the problem 
    //is to look for minimal cost, no matter how many products we produced, like minimal cost for unit time
    //so we just compare each hour products for K workers, we can move more products to more efficient worker
    //so first we sort all workers by their efficiency(wage/product), the minimal is best, so sort asc
    //second we setup a PQ, when we go through the workers array, note workers array already sorted
    //we add each quality into pq and accumulate the quality
    //if our workers more than k, we kicked out the the worker who has most quality, 
    //this is to assign this part products to other workers
    public static double mincostToHireWorkers(int[] q, int[] w, int K) {
        double[][] workers = new double[q.length][2];
        //finish 1 product's wage
        for (int i = 0; i < q.length; ++i)
            workers[i] = new double[]{(double)(w[i]) / q[i], (double)q[i]};
        Arrays.sort(workers, (a, b) -> Double.compare(a[0], b[0]));
        System.out.println("workers-" + Arrays.toString(workers[1]));
        double res = Double.MAX_VALUE, qsum = 0;
        //min heap, and will always keep K workers in pool and pick smallest quality
        //
        PriorityQueue<Double> pq = new PriorityQueue<>((a, b)->Double.compare(b, a));
        //the total wag= (q1+q2+q3+...qn) * minRatio, we only hire K, so we kick the biggest
        //quality in this group
        for (double[] worker: workers) {
            System.out.println(res + "---" + pq);
            //quality sum, you can think about products
            qsum += worker[1];
            //how much quality we need, this sort asc
            pq.add(worker[1]);
            //maybe we need cheapest guy more time to pay 
            if (pq.size() > K) qsum -= pq.poll();
            // this is 
            if (pq.size() == K) res = Math.min(res, qsum * worker[0]);
        }
        return res;
    }
    //TLE Greedy solutions
    public double mincostToHireWorkers2(int[] quality, int[] wage, int K) {
        int N = quality.length;
        double ans = 1e9;

        for (int captain = 0; captain < N; ++captain) {
            // Must pay at least wage[captain] / quality[captain] per qual
            double factor = (double) wage[captain] / quality[captain];
            double prices[] = new double[N];
            int t = 0;
            for (int worker = 0; worker < N; ++worker) {
                double price = factor * quality[worker];
                if (price < wage[worker]) continue;
                prices[t++] = price;
            }

            if (t < K) continue;
            Arrays.sort(prices, 0, t);
            double cand = 0;
            for (int i = 0; i < K; ++i)
                cand += prices[i];
            ans = Math.min(ans, cand);
        }

        return ans;
    }
    
    public static void main(String[] args) {
        int[] quality = {10,20,5}, wage = {70,50,30};
        System.out.println(mincostToHireWorkers(quality, wage,2));
        
    }
}