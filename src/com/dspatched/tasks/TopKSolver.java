package com.dspatched.tasks;

import java.util.*;

public class TopKSolver {

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;
        TopKSolver solver = new TopKSolver();
        System.out.println(Arrays.toString(solver.topKFrequent(nums1, k1)));
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
        }

        Queue<Map.Entry<Integer, Integer>> kHeap =
                new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            kHeap.offer(entry);
            if (kHeap.size() > k) {
                kHeap.poll();
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = kHeap.poll().getKey();
        }

        return result;
    }

}
