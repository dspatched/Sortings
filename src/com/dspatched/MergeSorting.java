package com.dspatched;

import java.util.Arrays;

public abstract class MergeSorting {

    static int[] sort(int[] arr) {
        int n = arr.length;
        if (n > 1) {
            int[] arrLeft = sort(Arrays.copyOfRange(arr, 0, n/2));
            int[] arrRight = sort(Arrays.copyOfRange(arr, n/2, n));
            int left = 0;
            int right = 0;
            for (int i = 0; i < n; ++i) {
                if (left >= arrLeft.length) arr[i] = arrRight[right++];
                else if (right >= arrRight.length) arr[i] = arrLeft[left++];
                else {
                    if (arrLeft[left] < arrRight[right]) arr[i] = arrLeft[left++];
                    else if (arrLeft[left] > arrRight[right]) arr[i] = arrRight[right++];
                    else {
                        arr[i] = arrLeft[left++];
                        arr[i+1] = arrRight[right++];
                        i++;
                    }
                }
            }
            return arr;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = ConsoleInput.input();
        arr = sort(arr);
        Arrays.stream(arr).forEach(System.out::print);
    }
}
