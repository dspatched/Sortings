package com.dspatched;

import java.util.Arrays;
import java.util.Optional;

public class BinarySearch {

    private static Integer search(int[] arr, int min, int max, int num) {
        int mid = (max + min) / 2;
        if (max - min < 1) return null;
        if (num == arr[mid]) return mid;
        else {
            if (num < arr[mid]) { return search(arr, min, mid, num); }
            else { return search(arr, mid+1, max, num); }
        }
    }

    public static void main(String[] args) {
        int[] arr = MergeSorting.sort(ConsoleInput.input());
        int num = 5; // enter a number to search here
        System.out.println("Sorted array: ");
        Arrays.stream(arr).forEach(n -> System.out.print(n + " "));
        System.out.println();
        Optional.ofNullable(search(arr,0,arr.length-1,num))
                .ifPresent(ind -> System.out.println("Number firstly found at index " + ind));
    }
}
