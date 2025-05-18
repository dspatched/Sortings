package com.dspatched;

public class FindASeat {

    public static void main(String[] args) {
        FindASeat fs = new FindASeat();
        int seat = fs.findSeat(new int[]{0,1,1,0,0});
        //int seat = fs.findSeat(new int[]{0,1,0,0,1,0,0,0,0,0,1});
        System.out.println("EMPTY SEAT NUMBER WHICH IS FARTHEST FROM OTHERS: " + seat);
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
            if (maxZeros == len) {
                System.out.println("ROW IS EMPTY");
                return 0;
            }
            if (maxZerosFirstIndex == 0) {
                System.out.println("MIN DISTANCE FROM CLOSEST PERSON: " +  maxZeros);
                return 0;
            }
            if (maxZerosLastIndex == len - 1) {
                System.out.println("MIN DISTANCE FROM CLOSEST PERSON: " +  maxZeros);
                return maxZerosLastIndex;
            }
            if (maxZeros % 2 == 0) {
                System.out.println("MIN DISTANCE FROM CLOSEST PERSON: " +  maxZeros / 2);
            } else {
                System.out.println("MIN DISTANCE FROM CLOSEST PERSON: " +  (maxZeros + 1) / 2);
            }
            return (maxZerosLastIndex + maxZerosFirstIndex) / 2;
        }

        return -1;
    }
}
