package com.dspatched.tasks;

import java.util.Arrays;
import java.util.List;

record Session(long start, long end) {}

public class DetermineOverlappingSessionsService {
    public static int maxOverlappingSessions(List<Session> sessions) {
        if (sessions == null || sessions.isEmpty()) return 0;

        int n = sessions.size();
        long[] starts = new long[n];
        long[] ends = new long[n];

        for (int i = 0; i < n; i++) {
            starts[i] = sessions.get(i).start();
            ends[i] = sessions.get(i).end();
        }

        Arrays.sort(starts);
        Arrays.sort(ends);

        int maxCount = 0;
        int currentCount = 0;
        int i = 0;
        int j = 0;

        while (i < n && j < n) {
            if (starts[i] < ends[j]) {
                currentCount++;
                maxCount = Math.max(maxCount, currentCount);
                i++;
            } else {
                currentCount--;
                j++;
            }
        }

        return maxCount;
    }

    public static void main(String[] args) {
        List<Session> sessions = List.of(
                new Session(1L, 3L),
                new Session(2L, 4L),
                new Session(3L, 5L)
        );
        System.out.println(maxOverlappingSessions(sessions));
    }
}