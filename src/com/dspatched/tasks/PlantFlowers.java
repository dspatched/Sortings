package com.dspatched.tasks;

public class PlantFlowers {

    public static void main(String[] args) {
        PlantFlowers plantFlowers = new PlantFlowers();
        System.out.println(plantFlowers.canPlaceFlowers(new int[]{1,0,0,0,0,0,0}, 3));
    }

    // leetcode 605 (can place flowers)
    // Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n,
    // return true if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule and false otherwise.
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed == null || flowerbed.length == 0) {
            return false;
        }
        if (n == 0) {
            return true;
        }
        if (flowerbed.length == 1 && flowerbed[0] == 0 && n == 1) {
            return true;
        }
        int adjZeros = 0;
        int cnt = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0) {
                adjZeros++;
            } else {
                adjZeros = 0;
            }
            if (adjZeros == 2 && (i == 1 || i == flowerbed.length - 1)) {
                cnt++;
                adjZeros--;
            }
            if (adjZeros == 3) {
                cnt++;
                adjZeros = adjZeros - 2;
            }
        }
        return cnt >= n;
    }

}
