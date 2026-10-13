package com.dspatched.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class WeightedRandom {

    private final List<Integer> nums = new ArrayList<>();
    private final List<Integer> prefixSum = new ArrayList<>();

    public WeightedRandom() {
        this.prefixSum.add(0);
    }

    public void add(int value, int weight) {
        nums.add(value);
        prefixSum.add(prefixSum.get(prefixSum.size() - 1) + weight);
    }

    public int getRandom() {
        int target = ThreadLocalRandom.current().nextInt(0, prefixSum.get(prefixSum.size() - 1));
        int index = search(prefixSum, target);
        return nums.get(index);
    }

    private int search(List<Integer> px, int target) {
        int left = 0;
        int right = px.size();

        while (right - left > 1) {
            int mid = left + (right - left) / 2;
            if (px.get(mid) <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return left;
    }

}
