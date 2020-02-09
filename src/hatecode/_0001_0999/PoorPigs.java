package hatecode._0001_0999;

class PoorPigs {
/*
458. Poor Pigs
There are 1000 buckets, one and only one of them is poisonous, while the rest are filled with water. They all look identical. If a pig drinks the poison it will die within 15 minutes. What is the minimum amount of pigs you need to figure out which bucket is poisonous within one hour?

Answer this question, and write an algorithm for the general case.

 

General case:

If there are n buckets and a pig drinking poison will die within m minutes, how many pigs (x) you need to figure out the poisonous bucket within p minutes? There is exactly one bucket with poison.

 

Note:

A pig can be allowed to drink simultaneously on as many buckets as one would like, and the feeding takes no time.
After a pig has instantly finished drinking buckets, there has to be a cool down time of m minutes. During this time, only observation is allowed and no feedings at all.
Any given bucket can be sampled an infinite number of times (by an unlimited number of pigs).
*/

    //thinking process:

    //the problem is to say: now we have buckets water and only one of them is poisonous, we need to use min pigs to find out which one

    //it is easy to be related with binary search, which is true, but here we have a factor to say we want to find the bucket within
    //minutesToTest minute, so we want to use more than 1 pig at same time, so we use a while to find the this number.

    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int pigs = 0;
        
        while(Math.pow(minutesToTest/minutesToDie + 1, pigs) < buckets) pigs ++;
        
        return pigs;
    }
}