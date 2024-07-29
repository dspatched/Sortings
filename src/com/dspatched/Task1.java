package com.dspatched;

import java.util.*;
import java.util.stream.Collectors;

public class Task1 {

    Integer num = Integer.MIN_VALUE;
    boolean fit1 = false;
    boolean fit2 = false;
    boolean fit3 = false;

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

        System.out.println(withSetStandardMethods(arr1, arr2, arr3));

        Task1 task1 = new Task1();
        System.out.println(task1.rightWay(arr1, arr2, arr3));

        System.out.println(solveWithHashMap(arr1, arr2, arr3));
    }

    // то что пришло в голову сразу же
    private static Integer withSetStandardMethods(int[] arr1, int[] arr2, int[] arr3) {
        Set<Integer> set = Arrays.stream(arr1)
                .boxed()
                .collect(Collectors.toSet());
        List<Integer> list2 = Arrays.stream(arr2)
                .boxed()
                .collect(Collectors.toList());
        List<Integer> list3 = Arrays.stream(arr3)
                .boxed()
                .collect(Collectors.toList());

        set.retainAll(list2);
        set.retainAll(list3);
        return set.stream().findFirst().orElseThrow(() -> new RuntimeException("No such number found"));
    }

    // не используем вспомогательных структур данных, меньше всего переборов при оптимальном сценарии выполнения
    private Integer rightWay(int[] arr1, int[] arr2, int[] arr3) {
        int len1 = arr1.length;
        int len2 = arr2.length;
        int len3 = arr3.length;
        if (len1 == 0 || len2 == 0 || len3 == 0) {
            throw new RuntimeException();
        }
        int i1 = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            i1 = compare(arr1, len1, i1, 1);
            i2 = compare(arr2, len2, i2, 2);
            i3 = compare(arr3, len3, i3, 3);
            if (fit1 == fit2 && fit2 == fit3) {
                break;
            }
            if (i1 >= len1 && i2 >= len2 && i3 >= len3) {
                throw new RuntimeException("No such number found");
            }
        }
        return num;
    }

    private int compare(int[] arr, int len, int i, int arrayNumber) {
        if (i < len) {
            if (this.num < arr[i]) {
                num = arr[i];
                switch (arrayNumber) {
                    case 1 -> {
                        this.fit1 = true;
                        this.fit2 = false;
                        this.fit3 = false;
                    }
                    case 2 -> {
                        this.fit1 = false;
                        this.fit2 = true;
                        this.fit3 = false;
                    }
                    case 3 -> {
                        this.fit1 = false;
                        this.fit2 = false;
                        this.fit3 = true;
                    }
                }
            } else if (this.num > arr[i]) {
                i++;
            } else {
                switch (arrayNumber) {
                    case 1 -> this.fit1 = true;
                    case 2 -> this.fit2 = true;
                    case 3 -> this.fit3 = true;
                }
            }
        }
        return i;
    }

    private static Integer solveWithHashMap(int[] arr1, int[] arr2, int[] arr3) {
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
            if (!(i > 0 && arr3[i] == arr3[i-1])) {
                if (map1.containsKey(arr3[i])) {
                    return arr3[i];
                }
            }
        }

        throw new RuntimeException("No such number found");
    }

    private static void printArray(int[] arr) {
        Arrays.stream(arr).forEach(n -> System.out.print(n + " "));
        System.out.println();
    }

}
