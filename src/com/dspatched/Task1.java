package com.dspatched;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Task1 {

    public static void main(String[] args) {
        int[] arr1 = ConsoleInput.input();
        int[] arr2 = ConsoleInput.input();
        int[] arr3 = ConsoleInput.input();
        arr1 = MergeSorting.sort(arr1);
        arr2 = MergeSorting.sort(arr2);
        arr3 = MergeSorting.sort(arr3);
        printArray(arr1);
        printArray(arr2);
        printArray(arr3);
        Map<Integer, Integer> map1 = new HashMap<>();
        for (int i = 0; i < arr1.length; i++) {
            if (!(i > 0 && arr1[i] == arr1[i-1])) map1.put(arr1[i], 1);
        }
        for (int i = 0; i < arr2.length; i++) {
            if (!(i > 0 && arr2[i] == arr2[i-1])) {
                if (map1.containsKey(arr2[i])) {
                    map1.put(arr2[i], 2);
                }
            }
        }
        for (int i = 0; i < arr3.length; i++) {
            if (!(i > 0 && arr2[i] == arr2[i-1])) {
                if (map1.containsKey(arr3[i])) {
                    System.out.println(arr3[i]);
                    return;
                }
            }
        }

        System.out.println("no such number found");
    }

    private static void printArray(int[] arr) {
        Arrays.stream(arr).forEach(n -> System.out.print(n + " "));
        System.out.println();
    }

}
