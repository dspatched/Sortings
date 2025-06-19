package com.dspatched.tasks;

public class LongestMonotoneSubarraySolver {

    public static void main(String[] args) {
        LongestMonotoneSubarraySolver solver = new LongestMonotoneSubarraySolver();
        int[] result = solver.findLongestMonotoneSubarray(new int[]{1,2,3,5,5,2});
        System.out.print(result[0] + ", " + result[1]);
    }

    private int[] findLongestMonotoneSubarray(int[] arr) {
        int len = arr.length;
        if (len == 1 || len == 2 && arr[0] == arr[1]) {
            return new int[]{0,0};
        }
        int start = 0;
        int end = 0;
        int cur = arr[0];
        int curLength = 1;
        int longestLength = 0;
        boolean asc = arr[0] < arr[1];
        for (int i = 1; i < len; i++) {
            if (cur < arr[i]) {
                if (asc) {
                    curLength++;
                    if (curLength > longestLength) {
                        longestLength = curLength;
                        end = i;
                        start = i - curLength + 1;
                    }
                } else {
                    asc = true;
                    if (curLength > longestLength) {
                        longestLength = curLength;
                        end = i;
                        start = i - curLength;
                    }
                    curLength = 2;
                }
            } else if (cur > arr[i]) {
                if (!asc) {
                    curLength++;
                    if (curLength > longestLength) {
                        longestLength = curLength;
                        end = i;
                        start = i - curLength + 1;
                    }
                } else {
                    asc = false;
                    if (curLength > longestLength) {
                        longestLength = curLength;
                        end = i;
                        start = i - curLength;
                    }
                    curLength = 2;
                }
            } else {
                if (curLength > longestLength) {
                    longestLength = curLength;
                    end = i - 1;
                    start = i - curLength;
                }
                curLength = 1;
                if (i < len - 1) {
                    asc = arr[i] < arr[i+1];
                }
            }
            cur = arr[i];
        }
        return new int[]{start, end};
    }

}
