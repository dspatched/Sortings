package com.dspatched;

import java.util.*;
import java.util.stream.Stream;

public class RemoveZeros {

    public static void main(String[] args) {
        RemoveZeros rz = new RemoveZeros();
        List<Integer> list = new ArrayList<>(List.of(0, 1, 2, 3, 0, 6, 2, 0, 8, 9));
        //rz.removeZeros(list);
        //list.forEach(System.out::print);

        int[] arr = new int[]{0, 1, 2, 0, 5, 0, 4, 0, -3};
        rz.moveZeros(arr);
        Arrays.stream(arr).forEach(System.out::print);
    }

    void removeZeros(List<Integer> list) {
        // Collection.removeIf()
        list.removeIf(e -> e.equals(0));
        // своя реализация №1 (с итератором)
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer number = iterator.next();
            if (number == 0) {
                iterator.remove();
            }
        }
        // своя реализация №2 (ахахах не надо такое писать нигде)
        list.removeAll(Stream.iterate(0, x -> x).limit(list.size()).toList());
        // своя реализация №3 (просто указатели уменьшаем)
        int listSize = list.size();
        for (int i = 0; i < listSize; i++) {
            Integer current = list.get(i);
            if (current == 0) {
                list.remove(current);
                i--;
                listSize--;
            }
        }
    }

    void moveZeros(int[] arr) {
        int idx = 0;
        int size = arr.length;
        for (int i = 0; i < size; i++) {
            if (arr[i] != 0) {
                if (idx < i) {
                    arr[idx] = arr[i];
                    arr[i] = 0;
                }
                idx++;
            }
        }
    }

}
