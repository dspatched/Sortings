package com.dspatched.tasks;

import java.util.*;

public class FourSum {

    public static void main(String[] args) {
        int[] sum2 = twoSum(new int[]{2,6,5,7}, 8);
        System.out.println(sum2[0] + " " + sum2[1]);
        //fourSum(new int[]{2,2,2,2,2}, 8);
    }

    private static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                int left = j + 1;
                int right = n - 1;
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        int currentLeft = nums[left];
                        while (left < right && nums[left] == currentLeft) {
                            left++;
                        }
                        int currentRight = nums[right];
                        while (left < right && nums[right] == currentRight) {
                            right--;
                        }
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return result;
    }

    private static int[] twoSum(int[] nums, int target) {
        Set<Integer> cache = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            if (cache.contains(target - nums[i])) {
                return new int[]{target - nums[i], nums[i]};
            } else {
                cache.add(nums[i]);
            }
        }
        return null;
    }

}
