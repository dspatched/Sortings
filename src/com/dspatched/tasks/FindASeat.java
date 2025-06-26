package com.dspatched.tasks;

public class FindASeat {

    public static void main(String[] args) {
        FindASeat fs = new FindASeat();
        //int seat = fs.findSeat(new int[]{0,1,1,0,0});
        int seat = fs.findSeat(new int[]{0,1,0,0,1,0,0,0,0,0,1});
        System.out.println("EMPTY SEAT NUMBER WHICH IS FARTHEST FROM OTHERS: " + seat);
        System.out.println(maxDistToClosest(new boolean[]{true,false,false,false,false,false,false,true,false,false,false, false,false}));
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

    public static int maxDistToClosest(boolean[] seats) {
        int len = seats.length;
        boolean leftEdge = true;
        boolean rightEdge = false;

        int curEmpty = 0;
        int maxEmpty = 0;
        int leftEdgeMax = 0;
        int rightEdgeMax = 0;
        int result = 0;

        for (int i = 0; i < len; i++) {
            if (i == len - 1) {
                rightEdge = true;
            }

            boolean cur = seats[i];
            if (!cur && leftEdge == true) {
                curEmpty++;
                leftEdgeMax++;
                if (maxEmpty < curEmpty) {
                    maxEmpty = curEmpty;
                }
            } else if (!cur && leftEdge == false) {
                curEmpty++;
                if (rightEdge) {
                    rightEdgeMax = curEmpty;
                }
                if (maxEmpty < curEmpty) {
                    if (curEmpty % 2 == 0) {
                        result = curEmpty / 2;
                    } else {
                        result = curEmpty / 2 + 1;
                    }
                    int biggerEdgeCase = Math.max(leftEdgeMax, rightEdgeMax);
                    result = result < biggerEdgeCase ? biggerEdgeCase : result;
                }
            } else if (cur) {
                leftEdge = false;
                rightEdge = false;
                if (maxEmpty < curEmpty) {
                    if (curEmpty % 2 == 0) {
                        result = curEmpty / 2;
                    } else {
                        result = curEmpty / 2 + 1;
                    }
                }
                int biggerEdgeCase = Math.max(leftEdgeMax, rightEdgeMax);
                result = result < biggerEdgeCase ? biggerEdgeCase : result;
                curEmpty = 0;
            }
        }

        return result;
    }

    public static int maxDistToClosest2(boolean[] seats) {
        int len = seats.length;
        int prev = -1;
        int maxDistance = 0;

        for (int i = 0; i < len; i++) {
            if (seats[i]) {
                if (prev == -1) {
                    maxDistance = i;
                } else {
                    int distance = (i - prev - 1 + 1) / 2;
                    maxDistance = Math.max(maxDistance, distance);
                }
                prev = i;
            }
        }

        maxDistance = Math.max(maxDistance, len - 1 - prev);

        return maxDistance;
    }
}
