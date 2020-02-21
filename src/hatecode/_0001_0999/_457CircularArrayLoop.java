package hatecode._0001_0999;
public class _457CircularArrayLoop {
    /*
     * 457. Circular Array Loop 
     * You are given an array of positive and negative
     * integers. If a number n at an index is positive, then move forward n steps.
     * Conversely, if it's negative (-n), move backward n steps. Assume the first
     * element of the array is forward next to the last element, and the last
     * element is backward next to the first element. Determine if there is a loop
     * in this array. A loop starts and ends at a particular index with more than 1
     * element along the loop. The loop must be "forward" or "backward'.
     * 
     * Example 1: Given the array [2, -1, 1, 2, 2], there is a loop, from index 0 ->
     * 2 -> 3 -> 0.
     * 
     * Example 2: Given the array [-1, 2], there is no loop.
     */
    //thinking process: given a array, the array tail and head could be connected. 
    
    // 
    int len;
    /**
     * Moves the pointer 'i' ahead one iteration.
     */
    private int next(int[] nums, int i) {
        i += nums[i];
        if (i < 0) i += len;
        else if (i > len - 1) i %= len;
        return i;
    }
    
    public boolean circularArrayLoop(int[] nums) {
        // Handle bad input
        if (nums == null || nums.length < 2) return false;
        
        len = nums.length;
        
        /**
         * Check every possible start location.
         * We may start at a short-loop, for instance, but the Array
         * may still contain a valid loop.
         */
        for (int i = 0; i < len; i++) {
            /**
             * We set elements to 0 which are on known non-loop paths.
             * So, if we encounter a 0, we know we're not on a loop path.
             * So, move to the next start location in the list.
             */
            if (nums[i] == 0) continue;
            
            // Stagger our starts, so we don't conclude we've found a loop,
            // as we might otherwise when slow == fast.
            int slow = i, fast = next(nums, slow);
            
            /** 
             * Whether i is positive or negative defines our direction, so if
             * the directions differ, so too will the signs.
             * If the signs differ, we can't be in a 'forward' or a 'backward'
             * loop, so we exit the traverse.
             */
            while (nums[i] * nums[fast] > 0 &&
                    nums[i] * nums[next(nums, fast)] > 0) {
                if (slow == fast) {
                    if (slow == next(nums, slow)) break; // 1-element loop
                    return true;
                }
                slow = next(nums, slow);
                //fast is moving at 2x
                fast = next(nums, next(nums, fast));
            }
            
            /**
             * If we're here, we didn't find a loop, so we know this path
             * doesn't have a loop, so we re-traverse it until we reverse
             * direction or encounter a '0' element.
             * During the re-traverse, we set each element we see to 0.
             */
            //the first number as sign
            slow = i;
            int sgn = nums[i];
            while (sgn * nums[slow] > 0) {
                int tmp = next(nums, slow);
                nums[slow] = 0;
                slow = tmp;
            }
        }
        
        // We've tested the whole array and have not found a loop,
        // therefore there isn't one, so return false.
        return false;
    }
}