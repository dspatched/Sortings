package com.dspatched.tasks;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PrefixCommonArraySolver {

    public static void main(String[] args) {
        PrefixCommonArraySolver solver = new PrefixCommonArraySolver();
        // N == arrA.length == arrB.length && (N >= 1 && N <= 50)
        // arrA[i], arrB[i] (= [1,N], N (= [1,50]
        int[] arrA = new int[]{1,2,3,5,4}; // {1,2,3,5,4}
        int[] arrB = new int[]{5,3,7,4,1}; // {5,3,7,4,1}
        int[] result = solver.findThePrefixCommonArray2(arrA, arrB);
        Arrays.stream(result).forEach(System.out::print);
    }

    // тупое решение через сеты и retainAll
    public int[] findThePrefixCommonArray(int[] arrA, int[] arrB) {
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();
        int[] result = new int[arrA.length];

        for (int i = 0; i < arrA.length; i++) {
            setA.add(arrA[i]);
            setB.add(arrB[i]);

            Set<Integer> intersection = new HashSet<>(setA);
            intersection.retainAll(setB);

            result[i] = intersection.size();
        }
        return result;
    }

    // с сетом но намного лучше
    public int[] findThePrefixCommonArray2(int[] arrA, int[] arrB) {
        Set<Integer> set = new HashSet<>();
        int[] result = new int[arrA.length];
        int cnt = 0;

        for (int i = 0; i < arrA.length; i++) {
            if (!set.add(arrA[i])) {
                cnt++;
            }
            if (!set.add(arrB[i])) {
                cnt++;
            }
            result[i] = cnt;
        }
        return result;
    }

    // типа RADIX поиска
    // работает только если в массивах не будет содержаться N > длине массива (т.е. например длина массива 4, там не должно быть чисел больше 4)
    public int[] findThePrefixCommonArray3(int[] arrA, int[] arrB) {
        int[] frequency = new int[arrA.length + 1];
        int[] result = new int[arrA.length];
        int cnt = 0;

        for (int i = 0; i < arrA.length; i++) {
            if (++frequency[arrA[i]] == 2) {
                cnt++;
            }
            if (++frequency[arrB[i]] == 2) {
                cnt++;
            }
            result[i] = cnt;
        }

        return result;
    }

}
