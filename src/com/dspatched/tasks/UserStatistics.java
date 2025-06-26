package com.dspatched.tasks;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class UserStatistic {

    private Queue<Event> eventQueue;
    private Map<Long, Integer> userCountMap;
    private long limit;
    private long k;
    private int userCount;

    private static class Event {
        long time;
        long userId;
        Event(long time, long userId) {
            this.time = time;
            this.userId = userId;
        }
    }

    public UserStatistic(long limit, long k) {
        this.limit = limit;
        this.k = k;
        this.eventQueue = new LinkedList<>();
        this.userCountMap = new HashMap<>();
        this.userCount = 0;
    }

    private void updateUserCount(long now) {
        long threshold = now - k;
        while (!eventQueue.isEmpty() && eventQueue.peek().time <= threshold) {
            Event oldEvent = eventQueue.poll();
            long userId = oldEvent.userId;
            int oldCount = userCountMap.get(userId);
            int newCount = oldCount - 1;
            userCountMap.put(userId, newCount);
            if (oldCount >= limit && newCount < limit) {
                userCount--;
            }
            if (newCount == 0) {
                userCountMap.remove(userId);
            }
        }
    }

    public void event(long now, long userId) {
        updateUserCount(now);
        Event newEvent = new Event(now, userId);
        eventQueue.add(newEvent);
        int oldCount = userCountMap.getOrDefault(userId, 0);
        int newCount = oldCount + 1;
        userCountMap.put(userId, newCount);
        if (oldCount < limit && newCount >= limit) {
            userCount++;
        }
    }

    public int robotCount(long now) {
        updateUserCount(now);
        return userCount;
    }
}
