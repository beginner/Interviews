package dp;

import java.util.Arrays;

/**
 * Problem no. 198
 * https://leetcode.com/problems/house-robber/
 * Level : Medium
 */

public class HouseRobber_198 {

    public int rob(int[] nums) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);

        return rob(nums, 0, memo);
    }

    private int rob(int[] nums, int i, int[] memo) {
        if (i >= nums.length) return 0;
        if (memo[i] > -1) return memo[i];

        int usingCurrent = nums[i] + rob(nums, i + 2, memo);
        int deferringCurrent = rob(nums, i + 1, memo);
        memo[i] =  Math.max(usingCurrent, deferringCurrent);
        return memo[i];
    }

}
