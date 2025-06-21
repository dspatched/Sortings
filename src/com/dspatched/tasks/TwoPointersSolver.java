package com.dspatched.tasks;

import java.util.*;

public class TwoPointersSolver {

    public static void main(String[] args) {
        TwoPointersSolver solver = new TwoPointersSolver();
        //System.out.println(solver.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));

//        var threeSumResult = solver.threeSum(new int[]{-1,0,1,2,-1,-4});
//        for (List<Integer> list : threeSumResult) {
//            list.forEach(e -> System.out.print(e + " "));
//            System.out.println();
//        }

        System.out.println(solver.threeSumClosest(new int[]{1,3,4,7,8,9}, 15));
    }

    public int maxArea(int[] height) {
        if (height.length <= 1) {
            return 0;
        }
        int num = height.length;
        int curX;
        int curY;
        int curArea;
        int maxArea = 0;
        int i = 0;
        int j = num - 1;
        while (i <= j) {
            curY = Math.min(height[i], height[j]);
            curX = j - i;
            curArea = curX * curY;
            maxArea = Math.max(maxArea, curArea);
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxArea;
    }

    public int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        if (len == 3) {
            return nums[0] + nums[1] + nums[2];
        }
        int curClosest = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);
        for (int i = 0; i < len - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                curClosest = Math.abs(target - curClosest) > Math.abs(target - sum) ? sum : curClosest;
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    return sum;
                }
            }
        }
        return curClosest;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

}
