package com.dspatched.tasks;

import java.util.HashMap;
import java.util.Map;

public class PrefixSumSolver {

    public static void main(String[] args) {
        PrefixSumSolver solver = new PrefixSumSolver();
        System.out.println(solver.findMaxLength(new int[]{0,1,0,1,0,1,1,0}));
        System.out.println(solver.subarraySum(new int[]{1,1,6,2,9,-1,2,0,8}, 8));
    }

    public int findMaxLength(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return 0;
        }
        if (len == 2 && nums[0] != nums[1]) {
            return 2;
        }
        int[] sums = new int[len];
        Map<Integer, Integer> history = new HashMap<>();
        int longestSum = 0;
        int curSum = 0;
        for (int i = 0; i < len; i++) {
            int add = nums[i] == 1 ? 1 : -1;
            sums[i] = (i == 0) ? add : sums[i-1] + add;
            Integer lastMeetIndex = history.get(sums[i]);
            if (lastMeetIndex == null) {
                history.put(sums[i], i);
                if (sums[i] == 0) {
                    curSum = i + 1;
                    longestSum = Math.max(longestSum, curSum);
                }
            } else {
                if (sums[i] == 0) {
                    curSum = i + 1;
                    longestSum = Math.max(longestSum, curSum);
                } else {
                    curSum = i - lastMeetIndex;
                    longestSum = Math.max(longestSum, curSum);
                }
            }
        }
        return longestSum;
    }

    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        if (len == 1) {
            return k != nums[0] ? 0 : 1;
        }
        int cnt = 0;
        int sum = 0;
        Map<Integer, Integer> history = new HashMap<>();
        history.put(0, 1);
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            if (history.containsKey(sum - k)) {
                cnt += history.get(sum - k);
            }
            history.put(sum, history.getOrDefault(sum, 0) + 1);
        }
        return cnt;
    }

}
