package com.dspatched.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class MergeSortedLists {

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>(List.of(1, 2, 3, 4, 7, 7, 9, 13, 13, 15));
        List<Integer> list2 = new ArrayList<>(List.of(0, 1, 4, 4, 4, 5, 13, 13, 15, 15, 15, 15));
//        List<Integer> list1 = new ArrayList<>(List.of(1, 4, 4, 4));
//        List<Integer> list2 = new ArrayList<>(List.of(1, 4, 4, 4, 4));
        List<Integer> resultList = unionMergeMaxRepeat(list1, list2);
        resultList.forEach(e -> System.out.print(e + " "));
        System.out.println();
        List<Integer> intersection = intersection(list1, list2);
        intersection.forEach(e -> System.out.print(e + " "));

        int[] num1 = new int[]{1,2,3,0,0,0};
        int[] num2 = new int[]{2,5,6};
        simpleMerge(num1, 3, num2, 3);
        Arrays.stream(num1).forEach(System.out::print);
    }

    public static void simpleMerge(int[] nums1, int m, int[] nums2, int n) {
        int[] copyNums1 = Arrays.copyOf(nums1, m);
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < m && j < n) {
            nums1[k++] = copyNums1[i] < nums2[j] ? copyNums1[i++] : nums2[j++];
        }
        while (i < m) {
            nums1[k++] = copyNums1[i++];
        }
        while (j < n) {
            nums1[k++] = nums2[j++];
        }
    }

    private static List<Integer> simpleMerge(List<Integer> list1, List<Integer> list2) {
        List<Integer> resultList = new ArrayList<>();
        int size1 = list1.size();
        int size2 = list2.size();
        int i = 0;
        int j = 0;
        while (i < size1 && j < size2) {
            resultList.add(list1.get(i) < list2.get(j) ? list1.get(i++) : list2.get(j++));
        }
        while (i < size1) {
            resultList.add(list1.get(i++));
        }
        while (j < size2) {
            resultList.add(list2.get(j++));
        }
        return resultList;
    }

    private static List<Integer> unionMerge2(List<Integer> list1, List<Integer> list2) {
        TreeSet<Integer> treeSet = new TreeSet<>(list1);
        treeSet.addAll(list2);
        return new ArrayList<>(treeSet);
    }

    private static List<Integer> intersection2(List<Integer> list1, List<Integer> list2) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        if (list1.size() >= list2.size()) {
            treeSet.addAll(list1);
            treeSet.retainAll(list2);
        } else {
            treeSet.addAll(list2);
            treeSet.retainAll(list1);
        }
        return new ArrayList<>(treeSet);
    }

    private static List<Integer> intersection(List<Integer> list1, List<Integer> list2) {
        List<Integer> resultList = new ArrayList<>();
        int size1 = list1.size();
        int size2 = list2.size();
        int i = 0;
        int j = 0;
        Integer e = null;
        while (i < size1 || j < size2) {
            if (i == size1) {
                e = list2.get(j++);
            } else if (j == size2) {
                e = list1.get(i++);
            } else {
                int e1 = list1.get(i);
                int e2 = list2.get(j);
                if (e1 < e2) {
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
                if (list1.contains(e) && list2.contains(e)) {
                    resultList.add(e);
                }
            }
        }
        return resultList;
    }

    private static List<Integer> unionMergeNoRepeat(List<Integer> list1, List<Integer> list2) {
        int size1 = list1.size();
        int size2 = list2.size();
        int i = 0;
        int j = 0;
        Integer e = null;
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
                if (e != null && e2 != e && e1 != e) {
                    repeat = false;
                }
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

    private static List<Integer> unionMergeMaxRepeat(List<Integer> list1, List<Integer> list2) {
        int size1 = list1.size();
        int size2 = list2.size();
        int i = 0;
        int j = 0;
        Integer e = null;
        List<Integer> resultList = new ArrayList<>();
        while (i < size1 || j < size2) {
            if (i == size1) {
                e = list2.get(j++);
                resultList.add(e);
            } else if (j == size2) {
                e = list1.get(i++);
                resultList.add(e);
            } else {
                int e1 = list1.get(i);
                int e2 = list2.get(j);
                if (e1 < e2) {
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
                resultList.add(e);
            }
        }
        return resultList;
    }

}
