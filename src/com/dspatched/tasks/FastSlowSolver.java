package com.dspatched.tasks;

public class FastSlowSolver {

    public static void main(String[] args) {
        FastSlowSolver solver = new FastSlowSolver();
        System.out.println(solver.findDuplicate(new int[]{2,5,9,6,9,3,8,9,7,1}));
        //System.out.println(solver.findDuplicate(new int[]{1,3,4,2,2}));
    }

    public int findDuplicate(int[] nums) {
        int i = nums[0];
        int j = nums[0];
        do {
            i = nums[i];
            j = nums[nums[j]];
        } while (i != j);
        i = nums[0];
        while (i != j) {
            i = nums[i];
            j = nums[j];
        }
        return j;
    }

}
