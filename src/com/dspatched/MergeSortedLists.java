package com.dspatched;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class MergeSortedLists {

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>(List.of(1, 2, 3, 7, 7, 9, 13, 13));
        List<Integer> list2 = new ArrayList<>(List.of(0, 1, 4, 4, 4, 5, 13, 15));
//        List<Integer> list1 = new ArrayList<>(List.of(1, 2, 13, 13));
//        List<Integer> list2 = new ArrayList<>(List.of(4, 4, 13, 15));
        List<Integer> resultList = unionMerge(list1, list2);
        resultList.forEach(e -> System.out.print(e + " "));
    }

    private static List<Integer> unionMerge2(List<Integer> list1, List<Integer> list2) {
        TreeSet<Integer> treeSet = new TreeSet<>(list1);
        treeSet.addAll(list2);
        return new ArrayList<>(treeSet);
    }

    private static List<Integer> unionMerge(List<Integer> list1, List<Integer> list2) {
        int size1 = list1.size();
        int size2 = list2.size();
        int minSize = Math.min(size1, size2);
        int i = 0;
        int j = 0;
        int e;
        boolean repeat = false;
        List<Integer> resultList = new ArrayList<>();
        while (i < size1 || j < size2) {
            if (i == size1) {
                e = list2.get(j++);
                if (!resultList.contains(e)) {
                    resultList.add(e);
                }
            } else if (j == size2) {
                e = list1.get(i++);
                if (!resultList.contains(e)) {
                    resultList.add(e);
                }
            } else {
                int e1 = list1.get(i);
                int e2 = list2.get(j);
                if ((e1 < e2 && !repeat) || (e1 > e2 && repeat)) {
                    e = e1;
                    i++;
                } else if (e1 > e2) {
                    e = e2;
                    j++;
                } else {
                    e = e1;
                    i++;
                    j++;
                }
                if (resultList.contains(e)) {
                    repeat = true;
                } else {
                    resultList.add(e);
                    repeat = false;
                }
            }
        }
        return resultList;
    }

}
