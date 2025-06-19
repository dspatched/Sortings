package com.dspatched.tasks;

import java.util.Arrays;

public class Matrixes {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{-10,-10},{-9,-9},{-8,-6},{-4,-2},{0,1},{3,3},{5,5},{6,8}};
        System.out.println(searchMatrix(matrix, 0));

        int[][] intervals = new int[][]{{1,4},{5,6},{10,12},{11,15}};
        Arrays.stream(merge(intervals))
                .forEach(arr -> Arrays.stream(arr).forEach(System.out::print));
    }

    private static int[][] merge(int[][] intervals) {
        int num = intervals.length;
        if (num == 1) {
            return intervals;
        }
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

    private static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length - 1;
        int n = matrix[0].length - 1;
        if (matrix[0][0] > target || matrix[m][n] < target) {
            return false;
        }
        if (matrix[0][0] == target || matrix[m][n] == target) {
            return true;
        }
        int vertStart = 0;
        int vertEnd = m;
        int horStart = 0;
        int horEnd = n;
        while (vertStart < vertEnd) {
            int vertMid = vertStart + (vertEnd - vertStart) / 2;
            if (target == matrix[vertMid][horEnd]
                    || target == matrix[vertStart][horStart]
                    || target == matrix[vertEnd][horEnd]) {
                return true;
            }
            if (target < matrix[vertMid][horEnd] && target > matrix[vertStart][horStart]) {
                vertEnd = vertMid;
            } else {
                vertStart = vertMid + 1;
            }
        }
        while (horStart <= horEnd) {
            int horMid = horStart + (horEnd - horStart) / 2;
            if (target == matrix[vertStart][horStart]
                    || target == matrix[vertStart][horMid]
                    || target == matrix[vertStart][horEnd]) {
                return true;
            }
            if (target > matrix[vertStart][horStart] && target < matrix[vertStart][horMid]) {
                horEnd = horMid - 1;
            } else {
                horStart = horMid + 1;
            }
        }
        return false;
    }

}
