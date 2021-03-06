/*

Link : https://leetcode.com/problems/sliding-window-maximum/

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

Follow up:
Could you solve it in linear time?

Example:
Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7] 

Explanation: 

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 

Constraints:
1 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4
1 <= k <= nums.length

*/



// Solution 1 : Using Deque
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        
        if (nums == null || nums.length == 0)
            return null;
        
        int len = nums.length;
        int ans[] = new int[len - k + 1];
        
        Deque<Integer> deq = new LinkedList<Integer>();
        
        for (int i = 0; i < len; i++) {
            
            if (!deq.isEmpty() && deq.peekFirst() == i - k)
                deq.poll();
            
            while (!deq.isEmpty() && nums[deq.peekLast()] < nums[i])
                deq.removeLast();
            
            deq.offer(i);
            
            if (i + 1 >= k)
                ans[i + 1 - k] = nums[deq.peekFirst()];
        }
        return ans;
    }
}
