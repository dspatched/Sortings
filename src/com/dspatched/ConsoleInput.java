package com.dspatched;

import java.util.Scanner;

public class ConsoleInput {

    private static Scanner scanner = new Scanner(System.in);

    public static int[] input() {
        System.out.println("Enter array size");
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        int[] arr = new int[n];
        System.out.println("Enter array (in one line separated by whitespaces)");
        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        scanner.close();
        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }
        return arr;
    }

    public static int inputNumber() {
        System.out.println("Enter a number");
        return scanner.nextInt();
    }
}
