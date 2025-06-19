package com.dspatched.tasks;

import java.util.Arrays;
import java.util.Comparator;

public class MergeIntervalsSolver {

    public static void main(String[] args) {
        MergeIntervalsSolver solver = new MergeIntervalsSolver();
        int[][] intervals = new int[][]{{1,4},{0,0}};
        var result = solver.merge(intervals);
        for (int[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }
    }

    public int[][] merge(int[][] intervals) {
        int num = intervals.length;
        if (num == 1) {
            return intervals;
        }
        Arrays.sort(intervals, Comparator.comparingInt(arr -> arr[0]));

        int[][] res = new int[num][2];
        int[] curMerged = Arrays.copyOf(intervals[0], 2);
        int cnt = 0;
        boolean prevOverlap = false;
        for (int i = 0; i < num; i++) {
            if (i < (num - 1) && curMerged[1] >= intervals[i+1][0] && curMerged[0] <= intervals[i+1][1]) {
                if (!prevOverlap) {
                    curMerged[0] = Math.min(intervals[i][0], intervals[i+1][0]);
                    curMerged[1] = Math.max(intervals[i][1], intervals[i+1][1]);
                } else {
                    curMerged[0] = Math.min(curMerged[0], intervals[i+1][0]);
                    curMerged[1] = Math.max(curMerged[1], intervals[i+1][1]);
                }
                prevOverlap = true;
            } else if (i == num - 1 && !prevOverlap) {
                res[cnt++] = Arrays.copyOf(curMerged, 2);
            } else {
                if (curMerged[0] != curMerged[1]) {
                    res[cnt++] = Arrays.copyOf(curMerged, 2);
                }
                if (i < num - 1) {
                    curMerged[0] = intervals[i+1][0];
                    curMerged[1] = intervals[i+1][1];
                }
                prevOverlap = false;
            }
        }
        return Arrays.copyOf(res, cnt);
    }

}
