package com.dspatched;

import java.util.Arrays;

public class InsertSorting {

    static int[] sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int tmpVar = arr[i], tmpInd = i;
            while (tmpInd > 0 && arr[tmpInd - 1] > tmpVar) {
                arr[tmpInd] = arr[tmpInd - 1];
                tmpInd--;
            }
            arr[tmpInd] = tmpVar;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = ConsoleInput.input();
        arr = sort(arr);
        Arrays.stream(arr).forEach(System.out::print);
    }
}