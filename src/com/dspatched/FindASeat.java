package com.dspatched;

public class FindASeat {

    public static void main(String[] args) {
        FindASeat fs = new FindASeat();
        //int seat = fs.findSeat(new int[]{0,1,1,0});
        int seat = fs.findSeat(new int[]{0,1,0,0,1,0,0,0,0,1});
        System.out.println(seat);
    }

    private int findSeat(int[] seats) {
        if (seats == null || seats.length == 0) {
            return -1;
        }

        int len = seats.length;
        int curZeros = 0;
        int maxZeros = 0;
        int curZerosFirstIndex = -1;
        int curZerosLastIndex = -1;
        int maxZerosFirstIndex = -1;
        int maxZerosLastIndex = -1;
        boolean isPrevZero = false;

        for (int i = 0; i < len; i++) {
            int cur = seats[i];
            if (cur == 0 && !isPrevZero) {
                isPrevZero = true;
                curZeros++;
                curZerosFirstIndex = i;
                curZerosLastIndex = i;
                if (maxZeros < curZeros) {
                    maxZeros = curZeros;
                    maxZerosFirstIndex = curZerosFirstIndex;
                    maxZerosLastIndex = curZerosLastIndex;
                }
            } else if (cur == 0 && isPrevZero) {
                curZeros++;
                curZerosLastIndex++;
                if (maxZeros < curZeros) {
                    maxZeros = curZeros;
                    maxZerosFirstIndex = curZerosFirstIndex;
                    maxZerosLastIndex = curZerosLastIndex;
                }
            } else if (cur == 1 && isPrevZero) {
                isPrevZero = false;
                curZeros = 0;
                curZerosFirstIndex = -1;
                curZerosLastIndex = -1;
            }
        }

        if (maxZeros > 0) {
            return (maxZerosLastIndex + maxZerosFirstIndex) / 2;
        }

        return -1;
    }
}
