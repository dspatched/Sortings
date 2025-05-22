package com.dspatched.tasks;

public class Matrixes {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{-10,-10},{-9,-9},{-8,-6},{-4,-2},{0,1},{3,3},{5,5},{6,8}};
        System.out.println(searchMatrix(matrix, 0));
    }

    private static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length - 1;
        int n = matrix[0].length - 1;
        if (matrix[0][0] > target || matrix[m][n] < target) {
            return false;
        }
        if (matrix[0][0] == target || matrix[m][n] < target) {
            return true;
        }
        int vertStart = 0;
        int vertEnd = m;
        int horStart = 0;
        int horEnd = n;
        while (vertStart < vertEnd) {
            int vertMid = vertStart + (vertEnd - vertStart) / 2;
            if (target == matrix[vertMid][horEnd]
                    || target == matrix[vertStart][horStart]
                    || target == matrix[vertEnd][horEnd]) {
                return true;
            }
            if (target < matrix[vertMid][horEnd] && target > matrix[vertStart][horStart]) {
                vertEnd = vertMid;
            } else {
                vertStart = vertMid + 1;
            }
        }
        while (horStart <= horEnd) {
            int horMid = horStart + (horEnd - horStart) / 2;
            if (target == matrix[vertStart][horStart]
                    || target == matrix[vertStart][horMid]
                    || target == matrix[vertStart][horEnd]) {
                return true;
            }
            if (target > matrix[vertStart][horStart] && target < matrix[vertStart][horMid]) {
                horEnd = horMid - 1;
            } else {
                horStart = horMid + 1;
            }
        }
        return false;
    }

}
