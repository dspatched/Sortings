package com.dspatched;

import java.util.Arrays;

public class QuickSorting {

    static void sort(int[] arr, int min, int max) {
        if (min < max) {
            int pInd = rearrangment(arr, min, max);
            sort(arr, min, pInd-1);
            sort(arr, pInd+1, max);
        }
    }

    private static int rearrangment(int[] arr, int min, int max) {
        int pivot = arr[max]; // pivot really should be randomized for sake of better average performance
        int pInd = min;
        int tmp;
        for (int i = min; i < max; ++i) {
            if (arr[i] < pivot) {
                tmp = arr[pInd];
                arr[pInd] = arr[i];
                arr[i] = tmp;
                pInd++;
            }
        }
        arr[max] = arr[pInd];
        arr[pInd] = pivot;
        return pInd;
    }

    public static void main(String[] args) {
        int[] arr = ConsoleInput.input();
        sort(arr, 0, arr.length-1);
        Arrays.stream(arr).forEach(n -> System.out.print(n + " "));
    }
}
