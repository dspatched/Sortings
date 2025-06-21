package com.dspatched.tasks;

class NumArray {

    private int[] nums;
    private int[] sums;

    public NumArray(int[] nums) {
        this.nums = nums;
        this.sums = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                sums[i] = nums[i];
            } else {
                sums[i] = nums[i] + sums[i-1];
            }
        }
    }

    public int sumRange(int left, int right) {
        if (left == 0) {
            return sums[right];
        } else {
            return sums[right] - sums[left-1];
        }
    }

    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{-2,0,3,-5,2,-1});
        System.out.println(numArray.sumRange(0,2));
        System.out.println(numArray.sumRange(2,5));
        System.out.println(numArray.sumRange(0,5));
    }
}